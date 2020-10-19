package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 角色菜单实体类
 * @author wxy
 * 2020-1-31
 */
@Setter
@Getter
@Entity

@Table(name = "auth_getaway_role_menu")
public class RoleMenu {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 角色Id
     */
    @Column(columnDefinition = "char(32)")
    private String roleId;

    /**
     * 菜单Id
     */
    @Column(columnDefinition = "char(32)")
    private String menuId;
}
