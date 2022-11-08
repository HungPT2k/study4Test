package com.example.study4test.contains;

import com.example.study4test.exception.customException;

;
public class ResponseObject {
    private customException exception;
    private Object data; // kiểu dữ liệu nói chung, chứa dc nhiều loại: data, user, message..
    public ResponseObject(){
    }

    public ResponseObject(customException exception, Object data) {
        this.exception = exception;
        this.data = data;
    }

    public customException getException() {
        return exception;
    }

    public void setException(customException exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
