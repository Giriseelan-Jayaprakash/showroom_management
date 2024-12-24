package com.showroommanagement.exception;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.util.Constant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestServiceAlertException.class)
    public ResponseEntity<ResponseDTO> handleBadRequestServiceAlertException(final BadRequestServiceAlertException exception, WebRequest webRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        exception.printStackTrace();
        responseDTO.setMessage(Constant.NOT_FOUND);
        responseDTO.setStatusCode(400);
        responseDTO.setData(exception.getMessage());
        return ResponseEntity.ok().body(responseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleSecurityException(Exception exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        exception.printStackTrace();
        responseDTO.setMessage(Constant.NOT_FOUND);
        responseDTO.setStatusCode(400);
        responseDTO.setData(exception.getMessage());
        return ResponseEntity.ok().body(responseDTO);
    }
}
