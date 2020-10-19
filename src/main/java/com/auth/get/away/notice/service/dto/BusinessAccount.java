package com.auth.get.away.notice.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessAccount extends AccountDTO {

    private String id;

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 账户所有者
     */
    private String role;

    private String key;


}
