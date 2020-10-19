package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.CommonVM;
import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.service.IAccountService;
import com.auth.get.away.notice.service.dto.LoginDTO;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;



/**
 * 管理员管理
 * @author wxy
 *  2020 1-15
 */
@RestController
@RequestMapping("/admin")

public class AccountController {

    @Autowired
    IAccountService accountService;

    /**
     * 管理员分页列表查询
     * @return
     */
    @PostMapping("queryList")
    public Page<Login> queryList(@RequestBody PageVM pageVM) {
        return accountService.findByPage(pageVM.getKeyWord(),pageVM.getStatus(),PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 管理员重置角色
     * @param id
     * @return
     */
    @PostMapping("resetRole")
    public ResposeStatus resetRole(@RequestBody String id) throws SQLException{
           accountService.resetRole(id);
        return ResposeStatus.success();
    }

    /**
     * 管理员设置角色
     * @param commonVM
     */
    @PostMapping("setRole")
    public ResposeStatus setRole(@RequestBody CommonVM commonVM) throws SQLException{
        accountService.setRole(commonVM.getId(),commonVM.getIds());
        return ResposeStatus.success();
    }

    /**
     * 管理员详情信息
     * @return
     */
    @PostMapping("getAccount")
    public LoginDTO getAccount(@RequestBody String id) {
        return accountService.getAccount(id);
    }



    /**
     * 启用管理员用户
     * @param ids
     * @return
     */
    @PostMapping("updateEnable")
    public ResposeStatus updateEnable(@RequestBody List<String> ids)
            throws SQLException  {
          accountService.enable(ids);
         return ResposeStatus.success();
    }
    /**
     * 禁用管理员用户
     * @param ids
     * @return
     */
    @PostMapping("updateDisable")
    public ResposeStatus updateDisable(@RequestBody List<String> ids)
            throws SQLException {
        accountService.disable(ids);
        return ResposeStatus.success();
    }

    /**
     * 删除管理员用户
     * @param id
     */
    @PostMapping("removeAdmin")
    private ResposeStatus removeAdmin(@RequestBody String id) throws SQLException {
        Boolean flag = accountService.delete(id);
        if(flag == false){
            return ResposeStatus.error("该管理员账号下有角色无法删除","405");
        } else {
            return ResposeStatus.success();
        }
    }

}

