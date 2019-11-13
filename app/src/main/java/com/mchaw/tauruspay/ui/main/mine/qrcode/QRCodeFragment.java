package com.mchaw.tauruspay.ui.main.mine.qrcode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.common.util.Base64Util;
import com.mchaw.tauruspay.common.util.FileUtil;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDialog;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.constract.QRCodeConstract;
import com.mchaw.tauruspay.ui.main.mine.qrcode.presenter.QRCodePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description:
 */
public class QRCodeFragment extends BasePresentFragment<QRCodePresenter> implements QRCodeConstract.View,BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, QRCodeGroupDialog.ConfirmListener {
    private static final int REQUEST_CODE_SELECT_PHOTO = 0;
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQRList;

    private List<QRCodeGroupBean> list = new ArrayList();
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
        qrCodeListAdapter = new QRCodeListAdapter(list);
        qrCodeListAdapter.setOnItemChildClickListener(this);
        qrCodeListAdapter.setOnItemChildClickListener(this);
        rvQRList.setAdapter(qrCodeListAdapter);
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(),"token"));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.cl_303:
                getPhoneAlbum();
                break;
            case R.id.cl_313:
                getPhoneAlbum();
                break;
            case R.id.cl_785:
                getPhoneAlbum();
                break;
            case R.id.cl_786:
                getPhoneAlbum();
                break;
            case R.id.cl_1215:
                getPhoneAlbum();
                break;
            case R.id.cl_1216:
                getPhoneAlbum();
                break;
            case R.id.cl_2515:
                getPhoneAlbum();
                break;
            case R.id.cl_2516:
                getPhoneAlbum();
                break;
            case R.id.cl_4985:
                getPhoneAlbum();
                break;
            case R.id.cl_4988:
                getPhoneAlbum();
                break;
            case R.id.cl_7988:
                getPhoneAlbum();
                break;
            case R.id.cl_9988:
                getPhoneAlbum();
                break;
            case R.id.tv_show_order_list:
                QRCodeGroupBean qrCodeGroupBean = (QRCodeGroupBean) adapter.getItem(position);
                boolean ishow = qrCodeGroupBean.isShowItems();
                qrCodeGroupBean.setShowItems(!ishow);
                adapter.notifyItemChanged(position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @OnClick({R.id.iv_back,R.id.tv_add_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_add_item:
                ToastUtils.showShortToast(getContext(),"添加二维码库");
                QRCodeGroupDialog.showDialog(getChildFragmentManager());
                break;
            default:
                break;
        }
    }


    @Override
    public void setQRCodeGroupList(List<QRCodeGroupBean> list) {
        qrCodeListAdapter.setNewData(list);
    }

    @Override
    public void setQRCodeGroupBean(QRCodeGroupCreateBean qrCodeGroupCreateBean) {
        if(qrCodeGroupCreateBean == null){
            return;
        }
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(),"token"));
    }

    @Override
    public void onClickComplete(String code, String account, String nick) {
        presenter.getQRCodeGroupBean(PreferencesUtils.getString(getContext(),"token"),account,nick,code);
    }

    private void getPhoneAlbum(){
        Intent intent = new Intent();
        intent.setType("image/*");// 开启Pictures画面Type设定为image
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
    }

    private String mAvatar;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_PHOTO:
                    Uri uri = data.getData();
                    String filePath = FileUtil.getFilePathByUri(getContext(), uri);

                    if (!TextUtils.isEmpty(filePath)) {
                        //RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                        //将照片显示在 ivImage上
                        //bitmap = Glide.with(this).asFile().load(filePath).apply(requestOptions1);
                        Glide.with(getContext()).asBitmap().load(filePath).into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                mAvatar = Base64Util.bitmapToBase64(bitmap);
                                Log.i("cici",mAvatar);
                            }
                        });
                    }
                    break;
            }
        }
    }


}
