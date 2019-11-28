package com.mchaw.tauruspay.ui.main.mine.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.ALiYunCodeBean;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.Base64Utils;
import com.mchaw.tauruspay.common.util.FileUtil;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDeleteDialog;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDialog;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.constract.QRCodeConstract;
import com.mchaw.tauruspay.ui.main.mine.qrcode.presenter.QRCodePresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description: 二维码库Fragment
 */
public class QRCodeFragment extends BasePresentListFragment<QRCodePresenter> implements QRCodeConstract.View, BaseQuickAdapter.OnItemChildClickListener, QRCodeGroupDialog.ConfirmListener, QRCodeGroupDeleteDialog.ConfirmListener {
    private static final int REQUEST_CODE_SELECT_PHOTO = 111;
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQRList;

    @BindView(R.id.iv_add_item)
    ImageView ivAddItem;
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private int pageState;

    private List<QRCodeGroupBean> qrCodeGroupBeanList = new ArrayList();
    private QRCodeListAdapter qrCodeListAdapter;
    private int groupid;//二维码组id
    private QRCodeGroupBean qrCodeGroupBean;
    private int tag;//二维码挡位tag
    private boolean canDone = true;//控制二维码挡位能否点击

    private Bitmap bitmap; //内存中bitmap
    private String mAvatar; //base64码串
    private String qrCodeUrl; //阿里转换后url
    private File imageFile; //根据image URI 得到的 imageFile

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qr_code;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        rvQRList.setLayoutManager(new LinearLayoutManager(getContext()));
        qrCodeListAdapter = new QRCodeListAdapter(getActivity(), qrCodeGroupBeanList);
        qrCodeListAdapter.setOnItemChildClickListener(this);
        rvQRList.setAdapter(qrCodeListAdapter);
        onRefresh();
        Log.i("cici", PreferencesUtils.getString(getContext(), "token"));
        pageState = Constant.PAGE_NORMAL_STATE;
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvQRList.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvQRList.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvQRList.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        qrCodeListAdapter.setEmptyView(loadingView);
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
    }

    /**
     * 普通与编辑状态下页面管理
     *
     * @param state
     */
    private void pageState(int state) {
        switch (state) {
            case Constant.PAGE_NORMAL_STATE:
                if (qrCodeGroupBeanList != null && qrCodeGroupBeanList.size() > 0) {
                    pageState = Constant.PAGE_DELETE_STATE;
                    tvRight.setText("取消");
                    ivAddItem.setVisibility(View.GONE);
                    for (QRCodeGroupBean bean : qrCodeGroupBeanList) {
                        bean.setCanDelete(Constant.PAGE_DELETE_STATE);
                        bean.setShowItems(false);
                        bean.setCanClickShowItems(true);
                    }
                    qrCodeListAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showShortToast(getContext(), "没有请求到二维码库！");
                }
                break;
            case Constant.PAGE_DELETE_STATE:
                pageState = Constant.PAGE_NORMAL_STATE;
                tvRight.setText("编辑");
                ivAddItem.setVisibility(View.VISIBLE);
                if (qrCodeGroupBeanList != null && qrCodeGroupBeanList.size() > 0) {
                    for (QRCodeGroupBean bean : qrCodeGroupBeanList) {
                        bean.setCanDelete(Constant.PAGE_NORMAL_STATE);
                        bean.setShowItems(false);
                        bean.setCanClickShowItems(false);
                    }
                    qrCodeListAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_add_item, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_add_item:
                QRCodeGroupDialog.showDialog(getChildFragmentManager());
                break;
            case R.id.tv_right:
                pageState(pageState);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        qrCodeGroupBean = (QRCodeGroupBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.cl_303:
                if (canDone) {
                    tag = 0;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_313:
                if (canDone) {
                    tag = 1;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_785:
                if (canDone) {
                    tag = 2;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_786:
                if (canDone) {
                    tag = 3;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_1215:
                if (canDone) {
                    tag = 4;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_1216:
                if (canDone) {
                    tag = 5;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_2515:
                if (canDone) {
                    tag = 6;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_2516:
                if (canDone) {
                    tag = 7;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_4985:
                if (canDone) {
                    tag = 8;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_4988:
                if (canDone) {
                    tag = 9;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_7988:
                if (canDone) {
                    tag = 10;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.cl_9988:
                if (canDone) {
                    tag = 11;
                    openPhotoAlbum(tag);
                }
                canDone = false;
                break;
            case R.id.tv_show_order_list:
                boolean ishow = qrCodeGroupBean.isShowItems();
                qrCodeGroupBean.setShowItems(!ishow);
                adapter.notifyItemChanged(position);
                if (!ishow) {
                    groupid = qrCodeGroupBean.getId();
                    //presenter.getQRCodeStalls(String.valueOf(groupid), PreferencesUtils.getString(getContext(), "token"));
                    startPolling(1);
                } else {
                    stopPolling();
                }
                break;
            case R.id.iv_delete:
                QRCodeGroupDeleteDialog.showDialog(getChildFragmentManager(), qrCodeGroupBean.getAccount(), qrCodeGroupBean.getNick(), qrCodeGroupBean.getId(), qrCodeGroupBean.getPaytype());
                break;
            default:
                break;

        }
    }

    /**
     * 调用相册预备
     *
     * @param tag
     */
    private void openPhotoAlbum(int tag) {
        if (qrCodeGroupBean.getQrcodes() != null && qrCodeGroupBean.getQrcodes().size() > 0) {
            if (qrCodeGroupBean.getQrcodes().get(tag).getStatus() == 2) {
                ToastUtils.showShortToast(getContext(), "审核中...，不能修改！");
                return;
            }
        }
        pickImageFromAlbum2();
    }

    /**
     * 调用相册
     */
    public void pickImageFromAlbum2() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
    }

    //相机返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_PHOTO:
                    Uri uri = data.getData();
                    imageFile = FileUtil.uriToFile(uri, getContext());
                    displayImage(imageFile);
                    break;
                default:
                    break;
            }
        } else {
            canDone = true;
        }
    }

    /**
     * 图片处理
     *
     * @param imagePath
     */
    private void displayImage(File imagePath) {
        if (imagePath != null) {
            try {
                bitmap = new Compressor(getContext())
                        .setMaxWidth(640)
                        .setMaxHeight(480)
                        .setQuality(75)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToBitmap(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mAvatar = Base64Utils.bitmapToBase64(bitmap);
            //调用阿里云
            aLiYunDecode(mAvatar);
        } else {
            ToastUtils.showShortToast(getContext(), "获取图片失败!");
        }
    }

    /**
     * 通过阿里云获取图片的url
     *
     * @param mAvatar 图片的base64字符串
     */
    private void aLiYunDecode(String mAvatar) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("imgdata", "data:image/jpeg;base64," + mAvatar).build();
        Request request = new Request.Builder().url("http://qrapi.market.alicloudapi.com/yunapi/qrdecode.html")
                .addHeader("Host", "qrapi.market.alicloudapi.com")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Authorization", "APPCODE ceff3e32309048aba2659a5459c62670").post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String result = response.body().string();
                    ALiYunCodeBean ALiYunCodeBean = new Gson().fromJson(result, ALiYunCodeBean.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (ALiYunCodeBean != null) {
                                if (ALiYunCodeBean.getStatus() == 1) {
                                    if (!TextUtils.isEmpty(ALiYunCodeBean.getData().getRaw_text())) {
                                        qrCodeUrl = ALiYunCodeBean.getData().getRaw_text();
                                        if (qrCodeGroupBean.getQrcodes() != null && qrCodeGroupBean.getQrcodes().size() > 0) {
                                            presenter.getUpLoadingQRCodeUrlBean(PreferencesUtils.getString(getContext(), "token"), qrCodeGroupBean.getQrcodes().get(tag).getId(), qrCodeUrl);
                                        }
                                    } else {
                                        ToastUtils.showShortToast(getContext(), "null,图片解析失败！");
                                        canDone = true;
                                    }
                                } else {
                                    ToastUtils.showShortToast(getContext(), "图片错误，图片解析失败！");
                                    canDone = true;
                                }
                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            canDone = true;
                            ToastUtils.showShortToast(getContext(), "图片解析失败！");
                        }
                    });
                }
            }
        });
    }

    /**
     * 获取二维码库的集合
     *
     * @param list
     */
    @Override
    public void setQRCodeGroupList(List<QRCodeGroupBean> list) {
        qrCodeGroupBeanList = list;
        if (list != null && list.size() > 0) {
            qrCodeListAdapter.setNewData(list);
        } else {
            qrCodeListAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setQRCodeGroupListFail() {
        qrCodeListAdapter.setEmptyView(errorView);
    }


    /**
     * 创建二维码库成功
     *
     * @param qrCodeGroupCreateBean
     */
    @Override
    public void setQRCodeGroupBean(QRCodeGroupCreateBean qrCodeGroupCreateBean) {
        if (qrCodeGroupCreateBean == null) {
            return;
        }
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
    }

    /**
     * 创建二维码库,dialog回调
     *
     * @param code
     * @param account
     * @param nick
     */
    @Override
    public void onClickComplete(String code, String account, String nick) {
        presenter.getQRCodeGroupBean(PreferencesUtils.getString(getContext(), "token"), account, nick, code);
    }


    /**
     * 上传二维码url给服务器返回的结果
     *
     * @param qrCodeUrlBean
     */
    @Override
    public void setUpLoadingQRCodeUrlBean(QRCodeUrlBean qrCodeUrlBean) {
        canDone = true;
        //qrCodeListAdapter需刷新，档口的UI为审核状态 status=2
        if (qrCodeGroupBean.getQrcodes() != null && qrCodeGroupBean.getQrcodes().size() > 0) {
            qrCodeGroupBean.getQrcodes().get(tag).setStatus(2);
            ToastUtils.showShortToast(getContext(), "上传成功");
        }
        qrCodeListAdapter.notifyDataSetChanged();
    }

    /**
     * 传二维码url给服务器返回失败
     */
    @Override
    public void setUpLoadingQRCodeUrlBeanFail() {
        canDone = true;
    }

    /**
     * 获取分组的二维码档口返回的结果
     *
     * @param bean
     */
    @Override
    public void setQRCodeStalls(QRCodeStallBean bean) {
        //组装数据给qrCodeListAdapter
        if (bean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
        }
        if (bean.getQrcodes() == null || bean.getQrcodes().size() <= 0) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
        }
        if (groupid == bean.getGroupid()) {//确保同一组
            //赋值 list(12个二维码档口id)
            qrCodeGroupBean.setQrcodes(bean.getQrcodes());
        }
        qrCodeListAdapter.notifyDataSetChanged();
    }

    /**
     * 确定删除二维码组
     *
     * @param id
     */
    @Override
    public void onClickComplete(int id) {
        presenter.deleteQRCodeGroup(String.valueOf(id), PreferencesUtils.getString(getContext(), "token"));
    }

    /**
     * 删除二维码分组成功返回的结果
     *
     * @param deleteQRCodeGroupBean
     */
    @Override
    public void setDeleteQRCodeGroup(DeleteQRCodeGroupBean deleteQRCodeGroupBean) {
        if (deleteQRCodeGroupBean == null) {
            ToastUtils.showShortToast(getContext(), "服务器错误，删除失败！");
        }
        ToastUtils.showShortToast(getContext(), "删除成功！");
        presenter.getQRCodeGroupList(PreferencesUtils.getString(MyFrameApplication.getInstance(), "token"));
        pageState(Constant.PAGE_DELETE_STATE);
    }

    //以下是轮询
    private Disposable disposable;

    public void startPolling(int time) {
        Log.i("cici", "二维码审核 开始轮询...");
        disposable = Observable.interval(0, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici", "二维码审核 轮询中...");
                        presenter.getQRCodeStalls(String.valueOf(groupid), PreferencesUtils.getString(MyFrameApplication.getInstance(), "token"));
                    }
                });
    }

    public void stopPolling() {
        Log.i("cici", "二维码审核 结束轮询");
        if (disposable != null) {
            disposable.dispose();
        }
    }

    //生命周期
    @Override
    public void onResume() {
        super.onResume();
        //startPolling(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPolling();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPolling();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopPolling();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPolling();
    }
}
