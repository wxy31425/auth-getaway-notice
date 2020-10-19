package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.CommonVM;
import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.controller.vm.RoleVM;
import com.auth.get.away.notice.core.TreeNode;
import com.auth.get.away.notice.entity.Menu;
import com.auth.get.away.notice.entity.Role;
import com.auth.get.away.notice.service.IRoleService;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * @author wxy
 *  2020 1-27
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    /**
     * 角色分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryList")
    public Page<Role> queryList(@RequestBody PageVM pageVM) {
        return roleService.findByPage(pageVM.getKeyWord(), PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 新增修改角色
     * @param roleVM
     * @return
     * @throws SQLException
     */
    @PostMapping("saveRole")
    private ResposeStatus saveRole(@RequestBody RoleVM roleVM) throws SQLException {
        roleService.save(roleVM);
        return ResposeStatus.success();
    }

    /**
     * 删除角色
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("removeRole")
    private ResposeStatus removeRole(@RequestBody String id) throws SQLException {
        Boolean flag = roleService.delete(id);
        if(flag == false){
            return ResposeStatus.error("该角色下有管理员账号无法删除","405");
        } else {
            return ResposeStatus.success();
        }
    }

    /**
     * 角色设置菜单
     * @param commonVM
     */
    @PostMapping("setMenu")
    public ResposeStatus setMenu(@RequestBody CommonVM commonVM) throws SQLException{
        roleService.setMenu(commonVM.getId(),commonVM.getIds());
        return ResposeStatus.success();
    }
}

