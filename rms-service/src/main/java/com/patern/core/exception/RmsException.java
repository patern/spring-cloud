package com.patern.core.exception;

import com.patern.result.ResponseInfo;

/**
 * Created by patern on 2017/9/7.
 */
public class RmsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5105962065932059244L;
	private ResponseInfo responseInfo;

    public RmsException(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
