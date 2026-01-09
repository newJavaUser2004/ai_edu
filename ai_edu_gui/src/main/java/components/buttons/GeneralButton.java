package components.buttons;

import components.BaseComponent;
import components.models.colors.ButtonColor;
import components.models.enums.StateColorEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * 普通按钮
 * 圆滑方形，能设置其图标
 */
public class GeneralButton extends BaseComponent {

    //上弧度
    private Integer upRadian;
    //下弧度
    private Integer lowRadian;

    //按钮颜色
    private Color backColor = new Color(233, 233, 235);

    //悬浮状态标志
    private boolean isHovered = false;

    //绑定的事件处理器
    private ArrayList<Runnable> functions = new ArrayList<>();


    //构造器
    public GeneralButton(Integer width, Integer height,Integer upRadian,Integer lowRadian) {
        this.panelX = 0;
        this.panelY = 0;
        this.height = height;
        this.width = width;
        this.upRadian = upRadian;
        this.lowRadian = lowRadian;
        this.borderSize = 1.0f;

        // 添加鼠标进入和离开监听事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint(); // 重绘组件
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint(); // 重绘组件
            }
        });
    }

    //初始即刷新（只运行一次）
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        // 设置抗锯齿和渲染提示
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        paintBack(graphics);
    }

    //画底部
    private void paintBack(Graphics2D graphics){
        // 根据是否悬浮选择背景颜色
        if (isHovered) {
            graphics.setColor(new Color(244, 244, 245));
        } else {
            graphics.setColor(this.backColor);
        }

        //绘制一个底部按钮
        graphics.fillRoundRect(panelX,panelY,width,height,upRadian,lowRadian);

        //绘制边框（默认灰色边框）
        drawBorder(graphics,upRadian,lowRadian, ButtonColor.BorderColor.defaultColor);
    }

    //绘制图标
    private void paintIcon(){

    }

    //绑定事件
    public void bindOnClick(Runnable function){
        this.functions.add(function);
    }

    //设置背景颜色
    public void setBackColor(Color backColor){
        this.backColor = backColor;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
