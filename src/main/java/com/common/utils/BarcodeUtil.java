package com.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 10:30 2018/8/24 0024
 * Modeified By:
 */
public class BarcodeUtil {
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }

        Code39Bean bean = new Code39Bean();

        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        //设置高度
        bean.setHeight(9);

        bean.setFontName("Malgun Gothic");

        //设置字体
        bean.setFontSize(2);

        //设置左右空白位
        bean.doQuietZone(false);

        String format = "image/png";
        try {
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void main(String[] args) {
        byte[] generate = generate("M2CRW-P11180402AAA0100");
        BASE64Encoder encoder=new BASE64Encoder();
        System.out.println(encoder.encode(generate));
    }

}
