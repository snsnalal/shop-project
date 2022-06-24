package com.shop.projectlion.global.error.exception;

import feign.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.StreamingHttpOutputMessage;

import java.util.Collection;
import java.util.Map;

@Getter
@Slf4j
public class FeignClientException extends RuntimeException {

    private final int status;
    private final String errorMessage;
    private final Map<String, Collection<String>> headers;

    public FeignClientException(Integer status, String errorMessage, Map<String, Collection<String>> headers) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
        this.headers = headers;
    }

}
