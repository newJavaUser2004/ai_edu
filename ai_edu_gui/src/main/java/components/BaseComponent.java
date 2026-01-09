package components;

import javax.swing.*;
import java.awt.*;

/**
 * 基础组件类
 */
public class BaseComponent extends JPanel {

    //当前位置
    protected Integer panelX;
    protected Integer panelY;

    //当前大小
    protected Integer height;
    protected Integer width;

    //边框大小
    protected float borderSize = 0.0f;

    //设置初始位置
    public void setStartLocal(Integer x, Integer y){
        this.setBounds(x,y,(int)(width+borderSize),(int)(height+borderSize));
    }

    //绘制边框
    public void drawBorder(Graphics2D graphics,Integer upRadian,Integer lowRadian,Color borderColor){
        if(borderSize > 0) {
            graphics.setColor(borderColor);
            graphics.setStroke(new BasicStroke(borderSize));
            graphics.drawRoundRect(panelX, panelY, width, height, upRadian, lowRadian);
        }
    }
}















