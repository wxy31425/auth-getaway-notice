package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 菜单实体类
 * @author wxy
 * 2020-1-31
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_menu")
public class Menu {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 菜单名称
     */
    @Column(length = 50)
    private String name;

    /**
     * 菜单图标
     */
    @Column(length = 100)
    private String icon;

    /**
     * 菜单地址
     */
    @Column(length = 200)
    private String url;

    /**
     * 菜单父类Id
     */
    @Column(columnDefinition = "char(32)")
    private String parentId;

    /**
     * 菜单顺序
     */
    @Column()
    private Integer seq;

    /**
     * 菜单状态
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
