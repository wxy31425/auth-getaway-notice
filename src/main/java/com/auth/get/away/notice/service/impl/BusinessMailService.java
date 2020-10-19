package com.auth.get.away.notice.service.impl;


import com.auth.get.away.notice.core.NoticeProperties;
import com.auth.get.away.notice.service.MailService;
import com.auth.get.away.notice.service.dto.BusinessAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@Slf4j
public class BusinessMailService extends MailService {
    public BusinessMailService(JavaMailSender javaMailSender, MessageSource messageSource, SpringTemplateEngine templateEngine, NoticeProperties noticeProperties) {
        super(javaMailSender, messageSource, templateEngine,noticeProperties);
    }

    @Async
    public void sendActivationEmail(BusinessAccount user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendBusinessTemplate(user, "mail/activationEmail", "email.activation.title");
    }

//    @Async
//    public void sendCreationEmail(BusinessAccount user) {
//        log.debug("Sending creation email to '{}'", user.getEmail());
//        sendBusinessTemplate(user, "mail/creationEmail", "email.activation.title");
//    }
//
    @Async
    public void sendPasswordResetMail(BusinessAccount user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendBusinessTemplate(user, "mail/passwordResetEmail", "email.reset.title");
    }
}
