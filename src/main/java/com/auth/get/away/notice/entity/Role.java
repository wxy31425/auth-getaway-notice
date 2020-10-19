package com.auth.get.away.notice.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

/**
 * 角色实体类
 * @author wxy
 * 2019-12-15
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_role")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 角色名称
     */
    @Column(length = 50)
    private String name;

     /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;




}
