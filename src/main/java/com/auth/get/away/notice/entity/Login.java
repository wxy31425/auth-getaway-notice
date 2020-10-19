package com.auth.get.away.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * 登录日志
 * @author wxy
 * 2019-12-15
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_account_loginLog")
public class Login {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 管理员id
     */
    @OneToOne
    @JoinColumn(name = "account_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnoreProperties("Login")
    @NotFound(action= NotFoundAction.IGNORE)
    private  Account account;

    /**
     * 登录IP
     */
    @Column
    private String ip;

    /**
     * 账户状态
     * 1:启用 0:关闭
     */
    @Column
    private Integer status = 1;

    /**
     * 登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date loginTime =  new Date();
}
