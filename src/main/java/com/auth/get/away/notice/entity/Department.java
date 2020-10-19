package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 部门实体类
 * @author wxy
 * 2020-2-05
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_department")
public class Department {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 部门名称
     */
    @Column(length = 120)
    private String name;

    /**
     * 部门描述
     */
    @Column(length = 100)
    private String description;

    /**
     * 部门父类Id
     */
    @Column(columnDefinition = "char(32)")
    private String parentId;

    /**
     * 部门顺序
     */
    @Column()
    private Integer seq;

    /**
     * 部门状态
     * 1:可用 0:失效
     */
    @Column()
    private Integer status = 1;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;






}
