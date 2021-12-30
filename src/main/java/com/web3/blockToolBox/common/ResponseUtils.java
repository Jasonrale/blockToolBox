package com.web3.blockToolBox.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<ResponseDTO<?>> success() {
        return new ResponseEntity<>(new ResponseDTO<>().setSuccess(true), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseDTO<? extends T>> success(T responseData) {
        return new ResponseEntity<>(new ResponseDTO<T>().setSuccess(true).setResponseData(responseData), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseDTO<?>> nodata(T responseData, Integer code) {
        return new ResponseEntity<>(new ResponseDTO<T>().setErrorCode(code).setSuccess(true).setResponseData(responseData), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseDTO<?>> created(T responseData) {
        return new ResponseEntity<>(new ResponseDTO<>().setSuccess(true).setResponseData(responseData), HttpStatus.OK);//HttpStatus.CREATED 应前端要求全要200
    }

    public static <T> ResponseEntity<ResponseDTO<?>> moved(int count) {
        return new ResponseEntity<>(new ResponseDTO<>().setSuccess(true).setResponseData(count), HttpStatus.OK);//HttpStatus.MOVED_PERMANENTLY
    }

    public static <T> ResponseEntity<ResponseDTO<?>> failed(HttpStatus httpStatus, String errorMsg) {
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new ResponseDTO<T>(httpStatus.value(), errorMsg).setSuccess(false), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseDTO<?>> failed(ResponseDTO responseData) {
        return new ResponseEntity<>(new ResponseDTO<T>(responseData.getErrorCode(),
                responseData.getErrorMsg()).setSuccess(false), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseDTO<?>> failed() {
        return new ResponseEntity<>(new ResponseDTO<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "内部错误，请联系管理员").setSuccess(false), HttpStatus.OK);
    }
}

