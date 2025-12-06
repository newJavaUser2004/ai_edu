package com.coder_mart_server.public_modules.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StringEnum {

    EMPTY("");

    private final String value;
}
