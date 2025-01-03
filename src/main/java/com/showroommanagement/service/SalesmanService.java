package com.showroommanagement.service;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Salesman;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.SalesmanRepository;
import com.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {
    private final SalesmanRepository salesmanRepository;

    public SalesmanService(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Transactional
    public ResponseDTO createSalesman(final Salesman salesman) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesmanRepository.save(salesman));
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Integer id) {
        if (this.salesmanRepository.existsById(id)) {
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.salesmanRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveAll() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanRepository.findAll());
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Integer id, final Salesman salesman) {
        final Salesman salesmanObject = this.salesmanRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (salesman.getName() != null) {
            salesmanObject.setName(salesman.getName());
        }
        if (salesman.getAddress() != null) {
            salesmanObject.setAddress(salesman.getAddress());
        }
        if (salesman.getContactNumber() != 0) {
            salesmanObject.setContactNumber(salesman.getContactNumber());
        }
        if (salesman.getExperience() != 0) {
            salesmanObject.setExperience(salesman.getExperience());
        }
        if (salesman.getSalary() != 0) {
            salesmanObject.setSalary(salesman.getSalary());
        }
        if (salesman.getShowroom() != null) {
            salesmanObject.setShowroom(salesman.getShowroom());
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanRepository.save(salesmanObject));
        return responseDTO;
    }


    public ResponseDTO deleteById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.salesmanRepository.existsById(id)) {
            this.salesmanRepository.deleteById(id);
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
