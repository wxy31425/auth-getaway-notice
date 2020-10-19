package com.auth.get.away.notice.controller;
import com.auth.get.away.notice.controller.vm.MenuVM;
import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.core.TreeNode;
import com.auth.get.away.notice.entity.Menu;
import com.auth.get.away.notice.entity.RoleMenu;
import com.auth.get.away.notice.service.IMenuService;
import com.auth.get.away.notice.utils.JsonFactory;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author wxy
 *  2020 1-31
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService menuService;

    /**
     * 角色分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryHostList")
    public Page<Menu> queryHostList(@RequestBody PageVM pageVM) {
        return menuService.findByPage(pageVM.getParentId(),PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 新增修改菜单
     * @param menuVM
     * @return
     * @throws SQLException
     */
    @PostMapping("saveMenu")
    private ResposeStatus saveMenu(@RequestBody MenuVM menuVM) throws SQLException {
        menuService.save(menuVM);
        return ResposeStatus.success();
    }

    /**
     * 删除菜单
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("removeMenu")
    private ResposeStatus removeMenu(@RequestBody String id) throws SQLException {
        Boolean flag = menuService.delete(id);
        if(flag == false){
            return ResposeStatus.error("该菜单下有下级菜单号无法删除","405");
        } else {
            return ResposeStatus.success();
        }
    }
    /**
     * 菜单树显示
     * @return
     */
    @PostMapping("menuTreeList")
    @ResponseBody
    public Object menuTreeList(){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        List<Menu> menus = menuService.getMenu();
        for(Menu menu :menus){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(menu.getId());
            treeNode.setLabel(menu.getName());
            treeNode.setPid(menu.getParentId());
            nodes.add(treeNode);
        }
        List<TreeNode> treeNodes = JsonFactory.buildtree(nodes,"0");
        return treeNodes;
    }
    /**
     * 已设置菜单树显示
     * @return
     */
    @PostMapping("roleMenuTreeList")
    @ResponseBody
    public Object roleMenuTreeList(@RequestBody String roleId){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        List<Map> menus = menuService.getRoleMenu(roleId);
        for(Map menu : menus){
            TreeNode treeNode = new TreeNode();
            treeNode.setId((String) menu.get("id"));
            treeNode.setLabel((String) menu.get("name"));
            treeNode.setPid((String) menu.get("parent_id"));
            nodes.add(treeNode);
        }
        List<TreeNode> treeNodes = JsonFactory.useBuildtree(nodes,"0");
        return treeNodes;
    }

    /**
     * 禁用菜单
     * @param ids
     * @return
     */
    @PostMapping("updateDisable")
    public ResposeStatus updateDisable(@RequestBody List<String> ids)
            throws SQLException {
        menuService.disable(ids);
        return ResposeStatus.success();
    }

    /**
     * 启用菜单
     * @param ids
     * @return
     */
    @PostMapping("updateEnable")
    public ResposeStatus updateEnable(@RequestBody List<String> ids)
            throws SQLException {
        menuService.enable(ids);
        return ResposeStatus.success();
    }
}

