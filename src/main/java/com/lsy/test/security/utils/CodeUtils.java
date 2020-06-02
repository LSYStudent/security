package com.lsy.test.security.utils;

import com.lsy.test.security.exception.StudyBaseException;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * 图片验证码处理类
 */
@Slf4j
public class CodeUtils {

    //验证码词典
    private final static String CODE_CONTENT = "0123456789qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPASDFGHJKLMNBVCXZ";

    //宽
    private final static Integer CODE_WIDTH = 95;

    //高
    private final static Integer CODE_HEIGHT = 25;

    //干扰线数量
    private final static Integer LINE_SIZE = 40;

    //验证码数量
    private final static Integer CODE_NUM = 4;

    //伪随机数流
    private static Random random = new Random();


    public static void generateCode(HttpServletResponse response) {
        BufferedImage image = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, CODE_WIDTH, CODE_HEIGHT);
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        graphics.setColor(getColor(random.nextInt(110), random.nextInt(133)));

        for (int i = 0;  i < LINE_SIZE; i++) {
            drawLine(graphics);
        }

        String str = "";
        for (int i = 1; i <= CODE_NUM; i++){
            str = drawCode(graphics, str, i);
        }
        graphics.dispose();
        try (OutputStream stream = response.getOutputStream()){
            ImageIO.write(image, "JPEG", stream);
        }catch (Exception e) {
            e.printStackTrace();
            log.info("图形验证码生成错误：" + e.getMessage());
            throw new StudyBaseException("图形验证码生成错误");
        }
    }


    /**
     * 绘制验证码
     * @param graphics
     * @param string
     * @param i
     * @return
     */
    private static String drawCode(Graphics graphics,String string, int i) {
        graphics.setFont(getFont());
        graphics.setColor(new Color(random.nextInt(101), random.nextInt(101), random.nextInt(121)));
        String str = String.valueOf(CODE_CONTENT.charAt(string.length()));
        string += str;
        graphics.translate(random.nextInt(3), random.nextInt(3));
        graphics.drawString(str, 13 * i, 16);
        return string;
    }

    /**
     * 绘制干扰线
     * @param graphics
     */
    private static void drawLine(Graphics graphics) {
        int x = random.nextInt(CODE_WIDTH);
        int y = random.nextInt(CODE_HEIGHT);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        graphics.drawLine(x, y, x1, y1);
    }

    /**
     * 获得字体
     * @return
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     * @param fc
     * @param bc
     * @return
     */
    private static Color getColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*public static String randomNum() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i< CODE_NUM; i++) {
            int num = random.nextInt(CODE_CONTENT.length());
            builder.append(CODE_CONTENT.charAt(num));
        }
        return builder.toString();
    }*/
}
