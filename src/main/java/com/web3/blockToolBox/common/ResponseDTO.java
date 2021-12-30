package com.web3.blockToolBox.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseDTO<T> {
    public ResponseDTO() {
    }

    public ResponseDTO(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseDTO(T responseData) {
        this.responseData = responseData;
    }

    /**
     * is_success : true
     * error_code : ""
     * error_msg : ""
     * error_details : {}
     * response_data : {}
     */

    @JSONField(name = "is_success")
    private Boolean success;
    @JSONField(name = "error_code")
    private Integer errorCode;
    @JSONField(name = "error_msg")
    private String errorMsg;
    @JSONField(name = "error_details")
    private Object errorDetails;
    @JSONField(name = "response_data")
    private T responseData;
}
