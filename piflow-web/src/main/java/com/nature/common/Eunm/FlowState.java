package com.nature.common.Eunm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nature.base.TextureEnumSerializer;

@JsonSerialize(using = TextureEnumSerializer.class)
public enum FlowState {
    STARTED("STARTED", "启动"),
    COMPLETED("COMPLETED", "完成"),
    HOME("FAILED", "失败"),
    ABORTED("ABORTED","中止"),
    FORK("FORK","FORK");

    private final String value;
    private final String text;

    private FlowState(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public static FlowState selectGender(String name) {
        for (FlowState portType : FlowState.values()) {
            if (name.equalsIgnoreCase(portType.name())) {
                return portType;
            }
        }
        return null;
    }
}
