package com.mchaw.tauruspay.common.util;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 9:27
 * @description: 获取图片 绝对路径 暂时无用
 */
public class ImagePathUtils {

    // 判断手机系统版本号
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        // 4.4及以上系统使用这个方法处理图片
//                        handleImageOnKitKat(data);
//                    } else {
//                        // 4.4以下系统使用这个方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//    /**
//     * 4.4之前
//     * 获得图片绝对路径
//     *
//     * @param data
//     */
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        //得到图片
//        //displayImage(imagePath);
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        // 通过Uri和selection来获取真实的图片路径
//        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }
//
//    /**
//     * 4.4以上版本
//     * 获得图片绝对路径
//     *
//     * @param data
//     */
//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
//            // 如果是document类型的Uri，则通过document id处理
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1]; // 解析出数字格式的id
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // 如果是content类型的Uri，则使用普通方式处理
//            imagePath = getImagePath(uri, null);
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            // 如果是file类型的Uri，直接获取图片路径即可
//            imagePath = uri.getPath();
//        }
//        //displayImage(imagePath); // 根据图片路径显示图片
//    }
}
