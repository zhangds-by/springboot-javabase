package com.zhangds.pool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**
 * 图片和Base64转换
 * @author: zhangds
 * @date: 2020/9/21 15:43
 */
public class Base64Utils {

    private static Logger log = LoggerFactory.getLogger(Base64Utils.class);

    private static String systemDir = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {

//        int i=1;
//        int j = i++; //先赋值后相加
//        System.out.println(i  + " " + j);


//        System.out.println(systemDir);
//        D:/IdeaProjects/springboot-javabase/springboot-pool/target/classes/
//        String path = Base64Utils.class.getResource("/").getPath();
//        File file = ResourceUtils.getFile("classpath*:/static/images/rain.jpg");
//        System.out.println(file.getPath());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());

        String bsse64 = ImageToBase64(systemDir + "/springboot-pool/src/main/resources/static/images/rain.jpg");
        Base64ToStream(bsse64);
    }

    /**
     * 图片转Base64
     * @param imgPath
     * @return
     */
    private static String ImageToBase64(String imgPath) {
        byte[] data = null;
        InputStream in = null;
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        log.info("本地图片转换Base64:" + encoder.encode(Objects.requireNonNull(data)));
        return encoder.encode(Objects.requireNonNull(data));
    }

    private static void Base64ToStream(String base64) {
        BASE64Decoder decoder = new BASE64Decoder();
        //图片类型
        String fileType = "png";
        InputStream input = null;
        try {
            //转化成流
            byte[] imageByte = decoder.decodeBuffer(base64);
            input = new ByteArrayInputStream(imageByte);
            BufferedImage bi = ImageIO.read(input);
            File file = new File(systemDir + "/springboot-pool/src/main/resources/static/images/rain-copy." + fileType + "");
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
