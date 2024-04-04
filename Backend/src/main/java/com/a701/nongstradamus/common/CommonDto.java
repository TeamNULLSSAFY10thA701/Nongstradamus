package com.a701.nongstradamus.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDto<T> {
    private T data;
    private String msg;
    private int code;

    public CommonDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
