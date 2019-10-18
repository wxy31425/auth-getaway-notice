package com.auth.get.away.notice.controller;


import com.auth.get.away.notice.controller.vm.RegisterVM;
import com.auth.get.away.notice.service.dto.BusinessAccount;
import com.auth.get.away.notice.service.impl.BusinessMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessNoticeController {
    @Autowired
    private BusinessMailService mailService;

    @PostMapping("sendRegister")
    public ResponseEntity<Void> sendRegister(@RequestBody RegisterVM registerVM) {

        BusinessAccount businessAccount = new BusinessAccount();
        businessAccount.setEmail(registerVM.getMail());
        businessAccount.setKey(registerVM.getCode());
        mailService.sendActivationEmail(businessAccount);
        return ResponseEntity.ok().build();
    }

}
