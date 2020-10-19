package com.auth.get.away.notice.controller;


import com.auth.get.away.notice.controller.vm.AccountCreateVM;
import com.auth.get.away.notice.controller.vm.RegisterVM;
import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.service.IAccountService;
import com.auth.get.away.notice.service.dto.AccountDTO;
import com.auth.get.away.notice.service.dto.BusinessAccount;
import com.auth.get.away.notice.service.impl.BusinessMailService;
import com.boostor.framework.rest.ResposeStatus;
import com.boostor.framework.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

/**
 *  注册
 * @author wxy
 *  2020 1-15
 */
@RestController
public class BusinessNoticeController {
    @Autowired
    private BusinessMailService mailService;
    @Autowired
    private IAccountService accountService;

    private final StringRedisTemplate redisTemplate;

    public BusinessNoticeController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 发送注册邮件
     * @param registerVM
     * @return
     */
    @PostMapping("sendRegister")
    public ResponseEntity<Void> sendRegister(@RequestBody RegisterVM registerVM) {
        BusinessAccount businessAccount = new BusinessAccount();
        businessAccount.setEmail(registerVM.getMail());
        businessAccount.setKey(registerVM.getCode());
        mailService.sendActivationEmail(businessAccount);
        return ResponseEntity.ok().build();
    }

    /**
     * 发送修改密码邮件
     * @param account
     * @return
     */
    @PostMapping("sendPasswordResetMail")
    public ResponseEntity<Void> sendPasswordResetMail(@RequestBody BusinessAccount account) {
        mailService.sendPasswordResetMail(account);
        return ResponseEntity.ok().build();
    }

    /**
     * 注册创建
     * @param accountCreateVM
     * @return
     */
    @PostMapping("createAccount")
    public Account createAccount(@RequestBody AccountCreateVM accountCreateVM) {
        Account account = new Account();
        account.setUsername(accountCreateVM.getEmail());
        account.setEmail(accountCreateVM.getEmail());
        account.setPassword(accountCreateVM.getPassword());
        account.setPhone(accountCreateVM.getPhone());
        accountService.save(account);
        return account;
    }

    /**
     * 获取账号信息
     * @param username
     * @return
     */
    @GetMapping("getAccount")
    public Account getAccount(@RequestParam String username) {
         return accountService.findByUsername(username);
    }
    /**
     * 获取登录账号状态
     * @param accountId
     * @return
     */
    @GetMapping("getStatus")
    public Login getStatus(@RequestParam String accountId) {
        return accountService.getStatus(accountId);
    }

    /**
     * 获取账号登录信息
     * @return
     */
    @GetMapping("getLoginAccount")
    public AccountDTO getLoginAccount() {
        String account = redisTemplate.opsForValue().get("accountName");
        if (account == null) {
            return null;
        } else {
            return accountService.loadLoginByUserName(account);
        }
    }

    /**
     * 判断登录信息是否存在
     *
     * @return
     */
    @PostMapping("isExists")
    public ResposeStatus isExists() {
        String account = redisTemplate.opsForValue().get("accountName");
        if (account == null) {
            return ResposeStatus.error("缓存信息失效", "102");
        }
        Boolean exists = accountService.isExists(account);
        if (exists == false) {
            return ResposeStatus.error("登录信息失效或禁用", "101");
        } else {
            return ResposeStatus.success();
        }

    }

    /**
     * 保存登录信息
     * @return
     */
    @PostMapping("loginSave")
    public ResponseEntity<Void> loginSave(@RequestBody String accountId) {
        accountService.save(accountId);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改密码
     * @param accountCreateVM
     * @return
     */
    @PostMapping("updatePassword")
    public ResposeStatus updatePassword(@RequestBody AccountCreateVM accountCreateVM) throws SQLException {
        accountService.updatePassword(accountCreateVM.getUsername(),accountCreateVM.getPassword());
        return ResposeStatus.success();
    }
}

