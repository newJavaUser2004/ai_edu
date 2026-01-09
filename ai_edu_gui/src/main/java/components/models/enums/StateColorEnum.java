package components.models.enums;

import java.awt.*;

/**
 * 状态颜色枚举
 */
public enum StateColorEnum {

    //工作状态
    WORK("work",Color.GREEN),

    //空闲状态
    FREE("free",new Color(200, 201, 204));

    StateColorEnum(String state, Color color) {
        this.state = state;
        this.color = color;
    }

    private String state;
    private Color color;

    public String getState() {
        return state;
    }

    public Color getColor() {
        return color;
    }


}
