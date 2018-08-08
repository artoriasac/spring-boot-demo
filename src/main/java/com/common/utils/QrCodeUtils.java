package com.common.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:55 2018/8/1 0001
 * Modeified By:
 */
public class QrCodeUtils {
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

    public static void analysisQrcode(){
        try {
            //解析二维码的类
            MultiFormatReader multiFormatReader=new MultiFormatReader();
            //要解析的二维码的图片
            File image=new File("d:/Img.png");
            BufferedImage bufferedImage= ImageIO.read(image);
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            //二维码的参数设置
            HashMap hints=new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//设置二维码的编码
            //得到解析结果,
            Result result= multiFormatReader.decode(binaryBitmap,hints);
            //打印结果信息
            System.out.println("解析结果:"+result.toString());
            System.out.println("二维码格式类型"+result.getBarcodeFormat());
            System.out.println("二维码文本内容:"+result.getText());
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
