package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.DepartmentVM;
import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.core.TreeNode;
import com.auth.get.away.notice.entity.Department;
import com.auth.get.away.notice.service.IDepartmentService;
import com.auth.get.away.notice.utils.JsonFactory;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门管理
 * @author wxy
 *  2020 2-15
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
  @Autowired
    IDepartmentService departmentService;

    /**
     * 部门分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryDepartmentList")
    public Page<Department> queryDepartmentList(@RequestBody PageVM pageVM) {
        return departmentService.findByPage(pageVM.getParentId(), PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 新增修改部门
     * @param departmentVM
     * @return
     * @throws SQLException
     */
    @PostMapping("saveDepartment")
    private ResposeStatus saveDepartment(@RequestBody DepartmentVM departmentVM) throws SQLException {
        departmentService.save(departmentVM);
        return ResposeStatus.success();
    }

    /**
     * 删除部门
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("removeDepartment")
    private ResposeStatus removeDepartment(@RequestBody String id) throws SQLException {
        Boolean flag = departmentService.delete(id);
        if(flag == false){
            return ResposeStatus.error("该部门下有下级部门号无法删除","405");
        } else {
            return ResposeStatus.success();
        }
    }

    /**
     * 启用部门
     * @param ids
     * @return
     */
    @PostMapping("updateEnable")
    public ResposeStatus updateEnable(@RequestBody List<String> ids)
            throws SQLException  {
        departmentService.enable(ids);
        return ResposeStatus.success();
    }

    /**
     * 禁用部门
     * @param ids
     * @return
     */
    @PostMapping("updateDisable")
    public ResposeStatus updateDisable(@RequestBody List<String> ids)
            throws SQLException {
        departmentService.disable(ids);
        return ResposeStatus.success();
    }

    /**
     * 已设置菜单树显示
     * @return
     */
    @PostMapping("departmentTreeList")
    @ResponseBody
    public Object departmentTreeList(){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        List<Department> departments = departmentService.getDepartment();
        for(Department department : departments){
            TreeNode DeTreeNode = new TreeNode();
            DeTreeNode.setId(department.getId());
            DeTreeNode.setLabel(department.getName());
            DeTreeNode.setPid(department.getParentId());
            nodes.add(DeTreeNode);
        }
        List<TreeNode> treeNodes = JsonFactory.buildtree(nodes,"0");
        return treeNodes;
    }

}
