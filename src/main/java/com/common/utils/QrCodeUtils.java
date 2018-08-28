package com.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.EnumMap;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:55 2018/8/1 0001
 * Modeified By:
 */
public class QrCodeUtils {
    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码背景色
    private static final int WHITE = 0xFFFFFFFF;
    //注：二维码颜色色差大，扫描快，但如果"BLACK'设置为黑色外其他颜色，可能无法扫描
    //二维码图片宽度
    private static final int WIDTH = 300;
    //二维码图片高度
    private static final int HEIGHT = 300;
    //二维码格式参数
    private static final EnumMap<EncodeHintType, Object> HINTS = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);

    private static final String FORMAT="png";
    static{
        /*二维码的纠错级别(排错率),4个级别：
         L (7%)、
         M (15%)、
         Q (25%)、
         H (30%)(最高H)
         纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级；
         选择M，扫描速度快。
         */
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码边界空白大小 1,2,3,4 (4为默认,最大)
        HINTS.put(EncodeHintType.MARGIN, 1);
        HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        HINTS.put(EncodeHintType.MAX_SIZE, 350);
        HINTS.put(EncodeHintType.MIN_SIZE, 150);
    }
    /**
     * 生成二维码方法
     *
     * @param content  二维码内容
     * @param resp response对象
     * @throws Exception 抛出异常
     */
    public static void getQrcode(String content, HttpServletResponse resp) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = resp.getOutputStream();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300);

            MatrixToImageWriter.writeToStream(bm, "png", stream);
        } catch (WriterException e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    public static OutputStream encodeImg(String contents,HttpServletResponse resp){
        System.out.println("bbb");
        BufferedImage image = null;
        try{
            BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, HINTS);
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            for(int x = 0; x < width; x++){
                for(int y =0;y < height; y++){
                    image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
                }
            }

             return encodeImgLogo(image,resp);
        }catch(Exception e){
            System.out.println("生成二维码失败"+e.getMessage());
        }
       return null;
    }

    public static ByteArrayOutputStream encodeImg(String contents){

        BufferedImage image = null;
        try{
            BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, HINTS);
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            for(int x = 0; x < width; x++){
                for(int y =0;y < height; y++){
                    image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
                }
            }
            return encodeImgLogo(image);
        }catch(Exception e){
            System.out.println("生成二维码失败"+e.getMessage());
        }
        return null;
    }

//    public static void analysisQrcode(){
//        try {
//            //解析二维码的类
//            MultiFormatReader multiFormatReader=new MultiFormatReader();
//            //要解析的二维码的图片
//            File image=new File("d:/Img.png");
//            BufferedImage bufferedImage= ImageIO.read(image);
//            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
//            //二维码的参数设置
//            HashMap hints=new HashMap();
//            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//设置二维码的编码
//            //得到解析结果,
//            Result result= multiFormatReader.decode(binaryBitmap,hints);
//            //打印结果信息
//            System.out.println("解析结果:"+result.toString());
//            System.out.println("二维码格式类型"+result.getBarcodeFormat());
//            System.out.println("二维码文本内容:"+result.getText());
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

    public static OutputStream encodeImgLogo(BufferedImage qrcode, HttpServletResponse resp){
        ServletOutputStream outputStream = null;
        try{
            outputStream=resp.getOutputStream();
            //获取画笔
            Graphics2D g = qrcode.createGraphics();
            //读取logo图
            URL url = new URL("https://artoriasqrcode.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180827161712.jpg");
            //URL url = new URL("http://psqrcode.oss-cn-hangzhou.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180827150918.png");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(5*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            BufferedImage logo = ImageIO.read(inputStream);
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int logoWidth = logo.getWidth(null) > qrcode.getWidth()*2 /10 ? (qrcode.getWidth()*2 /10) : logo.getWidth(null);
            int logoHeight = logo.getHeight(null) > qrcode.getHeight()*2 /10 ? (qrcode.getHeight()*2 /10) : logo.getHeight(null);
            //设置logo图片放置位置
            //中心
            int x = (qrcode.getWidth() - logoWidth) / 2;
            int y = (qrcode.getHeight() - logoHeight) / 2;
            //右下角，15为调整值
//          int x = twodimensioncode.getWidth()  - logoWidth-15;
//          int y = twodimensioncode.getHeight() - logoHeight-15;
            //开始合并绘制图片
            g.drawImage(logo, x, y, logoWidth, logoHeight, null);
            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
            //logo边框大小
            g.setStroke(new BasicStroke(2));
            //logo边框颜色
            g.setColor(Color.WHITE);
            g.drawRect(x, y, logoWidth, logoHeight);
            g.dispose();
            logo.flush();
            qrcode.flush();
            if (!ImageIO.write(qrcode, FORMAT, outputStream)) {
                throw new IOException("Could not write an image of format " + FORMAT);
            }
            return outputStream;
        }catch(Exception e){
            System.out.println("二维码绘制logo失败");
        }
        return null;
    }

    public static ByteArrayOutputStream encodeImgLogo(BufferedImage qrcode){
        ByteArrayOutputStream outputStream = null;
        try{

            outputStream=new ByteArrayOutputStream();
            //获取画笔
            Graphics2D g = qrcode.createGraphics();

            URL url = new URL("https://artoriasqrcode.oss-cn-beijing.aliyuncs.com/qrcode.png");
            //URL url = new URL("http://psqrcode.oss-cn-hangzhou.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180827150918.png");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(5*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            BufferedImage logo = ImageIO.read(inputStream);
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int logoWidth = logo.getWidth(null) > qrcode.getWidth()*2 /10 ? (qrcode.getWidth()*2 /10) : logo.getWidth(null);
            int logoHeight = logo.getHeight(null) > qrcode.getHeight()*2 /10 ? (qrcode.getHeight()*2 /10) : logo.getHeight(null);

            //设置logo图片放置位置
            //中心
            int x = (qrcode.getWidth() - logoWidth) / 2;
            int y = (qrcode.getHeight() - logoHeight) / 2;
            //右下角，15为调整值
//          int x = twodimensioncode.getWidth()  - logoWidth-15;
//          int y = twodimensioncode.getHeight() - logoHeight-15;
            //开始合并绘制图片
            g.drawImage(logo, x, y, logoWidth, logoHeight, null);
            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
            //logo边框大小
            g.setStroke(new BasicStroke(2));
            //logo边框颜色
            g.setColor(Color.WHITE);
            g.drawRect(x, y, logoWidth, logoHeight);
            g.dispose();
            logo.flush();
            qrcode.flush();
            if (!ImageIO.write(qrcode, FORMAT, outputStream)) {
                throw new IOException("Could not write an image of format " + FORMAT);
            }
            return outputStream;
        }catch(Exception e){
            System.out.println("二维码绘制logo失败");
        }
        return null;
    }

}
