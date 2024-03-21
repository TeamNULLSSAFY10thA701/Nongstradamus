package com.a701.nongstradamus.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String data = null;
    private String msg;
    private int code;

    public ErrorResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}