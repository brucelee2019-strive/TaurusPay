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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.ALiYunCodeBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.dialog.QRCodeGroupDialog;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.constract.QRCodeConstract;
import com.mchaw.tauruspay.ui.main.mine.qrcode.presenter.QRCodePresenter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description:
 */
public class QRCodeFragment extends BasePresentFragment<QRCodePresenter> implements QRCodeConstract.View, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, QRCodeGroupDialog.ConfirmListener {
    private static final int REQUEST_CODE_SELECT_PHOTO = 111;
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQRList;

    @BindView(R.id.iv_back)
    ImageView ivBack;

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
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
        Log.i("cici",PreferencesUtils.getString(getContext(), "token"));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.cl_303:
                pickImageFromAlbum2();
                break;
            case R.id.cl_313:
                pickImageFromAlbum2();
                break;
            case R.id.cl_785:
                pickImageFromAlbum2();
                break;
            case R.id.cl_786:
                pickImageFromAlbum2();
                break;
            case R.id.cl_1215:
                pickImageFromAlbum2();
                break;
            case R.id.cl_1216:
                pickImageFromAlbum2();
                break;
            case R.id.cl_2515:
                pickImageFromAlbum2();
                break;
            case R.id.cl_2516:
                pickImageFromAlbum2();
                break;
            case R.id.cl_4985:
                pickImageFromAlbum2();
                break;
            case R.id.cl_4988:
                pickImageFromAlbum2();
                break;
            case R.id.cl_7988:
                pickImageFromAlbum2();
                break;
            case R.id.cl_9988:
                pickImageFromAlbum2();
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

    @OnClick({R.id.iv_back, R.id.tv_add_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_add_item:
                ToastUtils.showShortToast(getContext(), "添加二维码库");
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
    private int index = 0;

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

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = false;
            newOpts.inSampleSize = 8;
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeFile(imagePath, newOpts);
            mAvatar = bitmapToBase64(bitmap);
            //调用阿里云
            tianALiYunDecode(mAvatar);
        } else {
            ToastUtils.showShortToast(getContext(), "获取图片失败!");
        }
    }

    //图片转base64
    private static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //请求天行url
    private void tianALiYunDecode(String mAvatar) {
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
                                        presenter.getUpLoadingQRCodeUrlBean(PreferencesUtils.getString(getContext(),"token"),index,qrCodeUrl);
                                    } else {
                                        ToastUtils.showShortToast(getContext(), "图片解析失败！");
                                    }
                                } else {
                                    ToastUtils.showShortToast(getContext(), "图片解析失败！");
                                }
                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShortToast(getContext(), "图片解析失败！");
                        }
                    });
                }
            }
        });

    }

    @Override
    public void setUpLoadingQRCodeUrlBean(QRCodeUrlBean qrCodeUrlBean) {

    }

}
