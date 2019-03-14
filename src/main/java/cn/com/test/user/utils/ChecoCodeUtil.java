package cn.com.test.user.utils;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
 * @Auther: mazhiqiang
 * @Date: 2018\10\10 0010 12:59
 * @Description:
 */
public class ChecoCodeUtil {
    private BufferedImage image; // 图像
    private String str; // 验证码

    private  void RandomNumUtil(){

    }

    private ChecoCodeUtil() {
        init();
    }
    // 获取实例
    public static ChecoCodeUtil Instance() {
        return new ChecoCodeUtil();
    }

    // 获取图片
    public RenderedImage getImage() {
        return this.image;
    }

    // 获取6位随机验证码
    public String getStr() {
        return this.str;
    }

    private void init() {
        // 在内存中创建图象，设置图像的宽高
        int width = 120, height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, height - 4));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 26, 25);
        }
        // 赋值验证码
        this.str = sRand;
        // 图象生效
        g.dispose();
        this.image = image;

    }

    // 给定范围获得随机颜色
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


}
