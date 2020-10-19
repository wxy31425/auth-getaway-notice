package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * 管理员实体类
 * @author wxy
 * 2019-12-15
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_admin")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;
    /**
     * 角色id
     */
    @Column(length = 50)
    private  String roleId;


    /**
     * 账户名
     */
    @Column(length = 50)
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 手机号码
     */
    @Column
    private String phone;


    /**
     * 注册时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime = new Date();

    /**
     * 权限
     */
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
