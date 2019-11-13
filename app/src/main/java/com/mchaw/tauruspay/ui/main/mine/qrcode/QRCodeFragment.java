package com.mchaw.tauruspay.ui.main.mine.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description:
 */
public class QRCodeFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    private static int REQUEST_CODE_SELECT_PHOTO = 0;
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQRList;

    private List<String> list = new ArrayList<String>();
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
    protected void initFragment() {
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("成功");
        rvQRList.setLayoutManager(new LinearLayoutManager(getContext()));
        qrCodeListAdapter = new QRCodeListAdapter(list);
        qrCodeListAdapter.setOnItemChildClickListener(this);
        qrCodeListAdapter.setOnItemChildClickListener(this);
        rvQRList.setAdapter(qrCodeListAdapter);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.cl_303:
                ToastUtils.showShortToast(getContext(),"303");
                Intent intent = new Intent();
                intent.setType("image/*");// 开启Pictures画面Type设定为image
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_SELECT_PHOTO);
                break;
            case R.id.cl_313:
                ToastUtils.showShortToast(getContext(),"313");
                break;
            case R.id.cl_785:
                break;
            case R.id.cl_786:
                break;
            case R.id.cl_1215:
                break;
            case R.id.cl_1216:
                break;
            case R.id.cl_2515:
                break;
            case R.id.cl_2516:
                break;
            case R.id.cl_4985:
                break;
            case R.id.cl_4988:
                break;
            case R.id.cl_7988:
                break;
            case R.id.cl_9988:
                break;
            case R.id.tv_show_order_list:
                ToastUtils.showShortToast(getContext(),"tv_show_order_list");
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            Uri uri = Crop.getOutput(data);
//            Bitmap bm;
//            try {
//                bm = ImageUtils.getZoomOutBitmap(this.getContentResolver(), uri, 750, 750);
//            } catch (FileNotFoundException e) {
//                Toast.makeText(this, "图片找不到", Toast.LENGTH_SHORT).show();
//                return;
//            }
////这里上传图片到服务器
//            //HttpUtils.uploadCircleImg(rQueue, new BitmapUploadParam(uri.getPath() + ".jpg", bm, 70), this,
//            //CODE_EVAL_UPLOAD);
//        } else if (resultCode == Crop.RESULT_ERROR) {
//            Toast.makeText(this, Crop.getError(data).getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }
}
