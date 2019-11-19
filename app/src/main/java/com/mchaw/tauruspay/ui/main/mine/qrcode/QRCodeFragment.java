package com.mchaw.tauruspay.ui.main.mine.qrcode;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.ALiYunCodeBean;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.Base64Utils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDeleteDialog;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDialog;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.constract.QRCodeConstract;
import com.mchaw.tauruspay.ui.main.mine.qrcode.presenter.QRCodePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description:
 */
public class QRCodeFragment extends BasePresentFragment<QRCodePresenter> implements QRCodeConstract.View, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, QRCodeGroupDialog.ConfirmListener, QRCodeGroupDeleteDialog.ConfirmListener {
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
        qrCodeListAdapter.setOnItemClickListener(this);
        rvQRList.setAdapter(qrCodeListAdapter);
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
        Log.i("cici", PreferencesUtils.getString(getContext(), "token"));
        pageState = Constant.PAGE_NORMAL_STATE;
    }

    private int groupid;
    private QRCodeGroupBean qrCodeGroupBean;
    private int tag;
    private boolean canDone = true;

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
                    presenter.getQRCodeStalls(String.valueOf(groupid), PreferencesUtils.getString(getContext(), "token"));
                }
                break;
            case R.id.iv_delete:
                QRCodeGroupDeleteDialog.showDialog(getChildFragmentManager(),qrCodeGroupBean.getAccount(),qrCodeGroupBean.getNick(),qrCodeGroupBean.getId(),qrCodeGroupBean.getPaytype());
                break;
            default:
                break;

        }
    }

    private void openPhotoAlbum(int tag) {
        if (qrCodeGroupBean.getQrcodes().get(tag).getStatus() == 1) {
            ToastUtils.showShortToast(getContext(), "审核中...，不能修改！");
            canDone = false;
            return;
        }
        pickImageFromAlbum2();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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

    private void pageState(int state) {
        switch (state) {
            case Constant.PAGE_NORMAL_STATE:
                if(qrCodeGroupBeanList!=null&&qrCodeGroupBeanList.size()>0) {
                pageState = Constant.PAGE_DELETE_STATE;
                tvRight.setText("取消");
                ivAddItem.setVisibility(View.GONE);
                    for (QRCodeGroupBean bean : qrCodeGroupBeanList) {
                        bean.setCanDelete(Constant.PAGE_DELETE_STATE);
                        bean.setShowItems(false);
                        bean.setCanClickShowItems(true);
                    }
                    qrCodeListAdapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showShortToast(getContext(),"没有请求到二维码库！");
                }
                break;
            case Constant.PAGE_DELETE_STATE:
                pageState = Constant.PAGE_NORMAL_STATE;
                tvRight.setText("编辑");
                ivAddItem.setVisibility(View.VISIBLE);
                if(qrCodeGroupBeanList!=null&&qrCodeGroupBeanList.size()>0) {
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

    /**
     * 获取二维码库的集合
     *
     * @param list
     */
    @Override
    public void setQRCodeGroupList(List<QRCodeGroupBean> list) {
        qrCodeGroupBeanList = list;
        qrCodeListAdapter.setNewData(list);
    }

    @Override
    public void setQRCodeGroupBean(QRCodeGroupCreateBean qrCodeGroupCreateBean) {
        if (qrCodeGroupCreateBean == null) {
            return;
        }
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
    }

    @Override
    public void onClickComplete(String code, String account, String nick) {
        presenter.getQRCodeGroupBean(PreferencesUtils.getString(getContext(), "token"), account, nick, code);
    }

    private void getPhoneAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");// 开启Pictures画面Type设定为image
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
    }

    public void pickImageFromAlbum2() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
    }

    private Bitmap bitmap;
    private String mAvatar;
    private String qrCodeUrl;

    //相机返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_PHOTO:
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                    break;
                default:
                    break;
            }
        } else {
            canDone = true;
        }
    }

    /**
     * 4.4之前
     * 获得图片绝对路径
     *
     * @param data
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        //得到图片
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 4.4以上版本
     * 获得图片绝对路径
     *
     * @param data
     */
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    /**
     * @param imagePath
     */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = false;
            newOpts.inSampleSize = 8;
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeFile(imagePath, newOpts);
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
                                        presenter.getUpLoadingQRCodeUrlBean(PreferencesUtils.getString(getContext(), "token"), qrCodeGroupBean.getQrcodes().get(tag).getId(), qrCodeUrl);
                                    } else {
                                        ToastUtils.showShortToast(getContext(), "图片错误,图片解析失败！");
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
     * 上传二维码url给服务器返回的结果
     *
     * @param qrCodeUrlBean
     */
    @Override
    public void setUpLoadingQRCodeUrlBean(QRCodeUrlBean qrCodeUrlBean) {
        canDone = true;
        //qrCodeListAdapter需刷新，档口的UI为审核状态
        qrCodeGroupBean.getQrcodes().get(tag).setStatus(1);
        qrCodeListAdapter.notifyDataSetChanged();
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
     * @param id
     */
    @Override
    public void onClickComplete(int id) {
        presenter.deleteQRCodeGroup(String.valueOf(id),PreferencesUtils.getString(getContext(),"token"));
    }

    /**
     * 删除二维码分组成功返回的结果
     * @param deleteQRCodeGroupBean
     */
    @Override
    public void setDeleteQRCodeGroup(DeleteQRCodeGroupBean deleteQRCodeGroupBean) {
        if(deleteQRCodeGroupBean==null){
            ToastUtils.showShortToast(getContext(),"服务器错误，删除失败！");
        }
        ToastUtils.showShortToast(getContext(),"删除成功！");
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
        pageState(Constant.PAGE_DELETE_STATE);
    }
}
