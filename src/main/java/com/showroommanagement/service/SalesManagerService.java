package com.showroommanagement.service;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.SalesManager;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.SalesManagerRepository;
import com.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SalesManagerService {
    private final SalesManagerRepository salesManagerRepository;

    public SalesManagerService(SalesManagerRepository salesManagerRepository) {
        this.salesManagerRepository = salesManagerRepository;
    }

    @Transactional
    public ResponseDTO createSalesManager(final SalesManager salesManager) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesManagerRepository.save(salesManager));
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Integer id) {
        if (this.salesManagerRepository.existsById(id)) {
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.salesManagerRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveALl() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesManagerRepository.findAll());
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Integer id, final SalesManager salesManager) {
        final SalesManager salesManagerObject = this.salesManagerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (salesManager.getName() != null) {
            salesManagerObject.setName(salesManager.getName());
        }
        if (salesManager.getAddress() != null) {
            salesManagerObject.setAddress(salesManager.getAddress());
        }
        if (salesManager.getContactNumber() != 0) {
            salesManagerObject.setContactNumber(salesManager.getContactNumber());
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesManagerRepository.save(salesManagerObject));
        return responseDTO;
    }

    public ResponseDTO deleteById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.salesManagerRepository.existsById(id)) {
            this.salesManagerRepository.deleteById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.DELETE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(Constant.REMOVE);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
