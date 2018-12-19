package com.example.wisdom.partybuilding.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BitmapUtils {
    /**
     * 穿件文件目录
     */
//
//    public static String getfiledirectory() {
//
//        String urls = null;
//
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//
//        Date date = new Date(System.currentTimeMillis());
//        String filename = format.format(date);
//        //创建一个新的文件
//        File file = new File(Environment.getExternalStorageDirectory() + "/luxiaozhao/luxiaozhao");
//        //先创建文件夹
//        urls = file.toURI().toString();
//
//        if (!file.exists()) {
//            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
//            file.mkdirs();
//        }
//
//        return urls;
//    }



//    public static void compressBmpToFile(Bitmap bmp, File file){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        int options = 80;
//        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
//        while (baos.toByteArray().length / 1024 > 100) {
//            baos.reset();
//            options -= 10;
//            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
//        }
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(baos.toByteArray());
//            fos.flush();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 压缩图片（质量压缩）
     */
    public static File compressImages() {



        //创建一个新的文件
        File file = new File(Environment.getExternalStorageDirectory() + "luxiao");
        //先创建文件夹
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }



        return file;
    }


    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }


//    private void saveImage(Bitmap bitmap, File saveFile) {
//        try {
//            FileOutputStream fos = new FileOutputStream(saveFile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 压缩图片（质量压缩）
//     *
//     * @param bitmap
//     */
//    public static File compressImage(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        int options = 100;
//        while (baos.toByteArray().length / 1024 > 800) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
//            baos.reset();//重置baos即清空baos
//            options -= 10;//每次都减少10
//            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
//            long length = baos.toByteArray().length;
//        }
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//
//        Date date = new Date(System.currentTimeMillis());
//        String filename = format.format(date);
//        //创建一个新的文件
//        File file = new File(Environment.getExternalStorageDirectory() + "/yikeyan/caijian");
//        //先创建文件夹
//
//
//        if (!file.exists()) {
//            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
//            file.mkdirs();
//        }
//        //创建这个文件
////
////        File file = new File(Environment.getExternalStorageDirectory(),filename+".png");
//        File file2 = new File(Environment.getExternalStorageDirectory() + "/yikeyan/caijian", filename + ".png");
//
//
//        Log.e("TAG", "yyyy" + Environment.getExternalStorageDirectory() + "/yikeyan/caijian" + filename + ".png");
//        try {
//            FileOutputStream fos = new FileOutputStream(file2);
//            try {
//                fos.write(baos.toByteArray());
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
////                BAFLogger.e(TAG,e.getMessage());
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
////            BAFLogger.e(TAG,e.getMessage());
//            e.printStackTrace();
//        }
//        recycleBitmap(bitmap);
//        return file2;
//    }
//



//    /**
//     * 根据路径 转bitmap
//     * @param urlpath
//     * @return
//     */
//    public static Bitmap getBitMBitmap(String urlpath) {
//
//        Bitmap map = null;
//        try {
//            URL url = new URL(urlpath);
//            URLConnection conn = url.openConnection();
//            conn.connect();
//            InputStream in;
//            in = conn.getInputStream();
//            map = BitmapFactory.decodeStream(in);
//            // TODO Auto-generated catch block
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//





//
//    //递归求取目录文件个数
//    public static long getlist(File f) {
//        long size = 0;
//
//        if (f!=null){
//            File flist[] = f.listFiles();
//            if (flist!=null){
//                size = flist.length;
//                for (int i = 0; i < flist.length; i++) {
//                    if (flist[i].isDirectory()) {
//                        size = size + getlist(flist[i]);
//                        size--;
//                    }
//                }
//            }
//
//        }
//
//        return size;
//    }
//
//
//    /**
//     *    * 得到所有文件
//     *    *
//     *    * @param dir
//     *    * @return
//     *    
//     */
//    public static ArrayList<String> getAllFiles(File dir) {
//        ArrayList<File> allFiles = new ArrayList<File>();
//// 递归取得目录下的所有文件及文件夹
//
//        File[] files = dir.listFiles();
////        new File(Environment.getExternalStorageDirectory() + "/yikeyan/caijian/")
//        for (int i = 0; i < files.length; i++) {
//            File file = files[i];
//            if (file.isDirectory()) {
//                getAllFiles(file);
//            }
//        }
//        ArrayList<String> list = new ArrayList<>();
//        for (File file : allFiles) {
//            list.add(file.getPath());
//        }
//        return list;
//    }
//
//
//    public static ArrayList<String> getFileList(String path) {
//        ArrayList<String> list = new ArrayList<>();
//        File file = new File(path);
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            if (files != null) {
//                for (int i = 0; i < files.length; i++) {
//                    list.add(files[i].getPath());
//                }
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//        return list;
//    }
//    //flie：要删除的文件夹的所在位置
//    public static void  deleteFiletwo(File file) {
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (int i = 0; i < files.length; i++) {
//                File f = files[i];
//                deleteFiletwo(f);
//            }
////            file.delete();//如要保留文件夹，只删除文件，请注释这行
//        } else if (file.exists()) {
//            file.delete();
//        }
//    }
//
//
//    /**
//     *    * 本地保存图片(相机)
//     *    *
//     *    * @param fromFile
//     *    * @param toFile
//     *    * @throws IOException
//     *    
//     */
//    public static String localSave(File fromFile) throws IOException {
//        FileInputStream from = null;
//        FileOutputStream to = null;
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//
//        Date date = new Date(System.currentTimeMillis());
//        String filename = format.format(date);
//        //创建一个新的文件
//        File file = new File(Environment.getExternalStorageDirectory() +"/yikeyan/temporary/");
//        //先创建文件夹
//
//        if (!file.exists()) {
//            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
//            file.mkdirs();
//        }
//        //创建这个文件
//        String path = Environment.getExternalStorageDirectory() +"/yikeyan/temporary/"+"Avatar"+filename + ".jpg";
//        try {
//            from = new FileInputStream(fromFile);
//            to = new FileOutputStream(path);
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = from.read(buffer)) != -1)
//                to.write(buffer, 0, bytesRead); // write
//        } finally {
//            if (from != null)
//                try {
//                    from.close();
//                } catch (IOException e) {
//                }
//            if (to != null)
//                try {
//                    to.close();
//                } catch (IOException e) {
//                }
//        }
//        return path;
//    }
//
//
//
//
//
//
//
//
//    /**
//     * 复制文件目录
//     * @param srcDir 要复制的源目录 eg:/mnt/sdcard/DB
//     * @param destDir 复制到的目标目录 eg:/mnt/sdcard/db/
//     * @return
//     */
//    public static boolean copyDir(String srcDir, String destDir){
//        File sourceDir = new File(srcDir);
//        //判断文件目录是否存在
//        if(!sourceDir.exists()){
//            return false;
//        }
//        //判断是否是目录
//        if (sourceDir.isDirectory()) {
//            File[] fileList = sourceDir.listFiles();
//            File targetDir = new File(destDir);
//            //创建目标目录
//            if(!targetDir.exists()){
//                targetDir.mkdirs();
//            }
//            //遍历要复制该目录下的全部文件
//            for(int i= 0;i<fileList.length;i++){
//                if(fileList[i].isDirectory()){//如果如果是子目录进行递归
//                    copyDir(fileList[i].getPath()+ "/",
//                            destDir + fileList[i].getName() + "/");
//                }else{//如果是文件则进行文件拷贝
//                    copyFile(fileList[i].getPath(), destDir +fileList[i].getName());
//                }
//            }
//            return true;
//        }else {
//            copyFileToDir(srcDir,destDir);
//            return true;
//        }
//    }
//
//    /**
//     * 把文件拷贝到某一目录下
//     * @param srcFile
//     * @param destDir
//     * @return
//     */
//    public static boolean copyFileToDir(String srcFile, String destDir){
//        File fileDir = new File(destDir);
//        if (!fileDir.exists()) {
//            fileDir.mkdir();
//        }
//        String destFile = destDir +"/" + new File(srcFile).getName();
//        try{
//            InputStream streamFrom = new FileInputStream(srcFile);
//            OutputStream streamTo = new FileOutputStream(destFile);
//            byte buffer[]=new byte[1024];
//            int len;
//            while ((len= streamFrom.read(buffer)) > 0){
//                streamTo.write(buffer, 0, len);
//            }
//            streamFrom.close();
//            streamTo.close();
//            return true;
//        } catch(Exception ex){
//            return false;
//        }
//    }
//
//    /**
//     * 复制文件（非目录）
//     * @param srcFile 要复制的源文件
//     * @param destFile 复制到的目标文件
//     * @return
//     */
//    private static boolean copyFile(String srcFile, String destFile){
//        try{
//            InputStream streamFrom = new FileInputStream(srcFile);
//            OutputStream streamTo = new FileOutputStream(destFile);
//            byte buffer[]=new byte[1024];
//            int len;
//            while ((len= streamFrom.read(buffer)) > 0){
//                streamTo.write(buffer, 0, len);
//            }
//            streamFrom.close();
//            streamTo.close();
//            return true;
//        } catch(Exception ex){
//            return false;
//        }
//    }
//
//
//
//    /**
//     * 删除文件（包括目录）
//     * @param delFile
//     */
//    public static void deleteFile(File delFile) {
//        //如果是目录递归删除
//        if (delFile.isDirectory()) {
//            File[] files = delFile.listFiles();
//            for (File file : files) {
//                deleteFile(file);
//            }
//        }else{
//            delFile.delete();
//        }
//        //如果不执行下面这句，目录下所有文件都删除了，但是还剩下子目录空文件夹
//        delFile.delete();
//    }
//
//
//
//
//    public static Bitmap compressionavatarimg(Bitmap image) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        int options = 100;
//        while ( baos.toByteArray().length / 1024>100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
//            baos.reset();//重置baos即清空baos
//            options -= 10;//每次都减少10
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
//
//        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
//        return bitmap;
//    }
//    /**
//     * 图片按比例大小压缩方法
//     * @param srcPath （根据路径获取图片并压缩）
//     * @return
//     */
//    public static Bitmap getimage(String srcPath) {
//
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = 800f;// 这里设置高度为800f
//        float ww = 480f;// 这里设置宽度为480f
//        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 0;// be=1表示不缩放
//        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0)
//            be = 0;
//        newOpts.inSampleSize = be;// 设置缩放比例
//        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//        return compressImagess(bitmap);// 压缩好比例大小后再进行质量压缩
//    }
//
//    /**
//     * 质量压缩方法
//     * @param image
//     * @return
//     */
//    public static Bitmap compressImagess(Bitmap image) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 70, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        int options = 90;
//        while (baos.toByteArray().length / 500 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
//            baos.reset(); // 重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//            options -= 10;// 每次都减少10
//        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//        return bitmap;
//    }
//
//
//
//
//
//    /**
//     * 得到bitmap的大小
//     */
//    public static int getBitmapSize(Bitmap bitmap) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
//            return bitmap.getAllocationByteCount();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
//            return bitmap.getByteCount();
//        }
//        // 在低版本中用一行的字节x高度
//        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
//
//
//    }
//
//    /*
//     * 镜像
//     * */
//    public Bitmap mirrorConvert(Bitmap srcBitmap, int flag) {
//        //flag: 0 左右翻转，1 上下翻转
//        Matrix matrix = new Matrix();
//        if (flag == 0) //左右翻转
//            matrix.setScale(-1, 1);
//        if (flag == 1)//上下翻转
//            matrix.setScale(1, -1);
//        return Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
//    }

//    /*
//     * 旋转
//     * */
//    private Bitmap rotateBitmap(Bitmap origin, float alpha) {
//        if (origin == null) {
//            return null;
//        }
//        int width = origin.getWidth();
//        int height = origin.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.setRotate(alpha);
//        // 围绕原地进行旋转
//        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
//        if (newBM.equals(origin)) {
//            return newBM;
//        }
//        origin.recycle();
//        return newBM;
//    }


}
