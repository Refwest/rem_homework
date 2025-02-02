//package com.homework.rem.service;
//
//import com.homework.rem.data.entities.BankEntity;
//import com.homework.rem.data.repository.BankRepository;
//import com.homework.rem.service.exception.NotFoundException;
//import com.homework.rem.web.models.BankResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class BankService {
//
//    private BankRepository bankRepository;
//
//    public BankService(BankRepository bankRepository) {
//        this.bankRepository = bankRepository;
//    }
//
//    private BankResponse fetch(Long id) {
//        Optional<BankEntity> optional = bankRepository.findById(id);
//        return optional.map(BankResponse::new).orElseThrow(NotFoundException::new);
//    }
//
//}
