package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.controller.vm.TaskVM;
import com.auth.get.away.notice.entity.Task;
import com.auth.get.away.notice.service.ITaskService;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 定时任务管理
 * @author wxy
 *  2020 2-2
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskService taskService;

    /**
     * 定时任务分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryTaskList")
    public Page<Task> queryTaskList(@RequestBody PageVM pageVM) {
        return taskService.findByPage(pageVM.getKeyWord(), pageVM.getStatus(),PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 新增修改定时任务
     * @param taskVM
     * @return
     * @throws SQLException
     */
    @PostMapping("saveTask")
    private ResposeStatus saveTask(@RequestBody TaskVM taskVM) throws SQLException {
        taskService.save(taskVM);
        return ResposeStatus.success();
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("removeTask")
    private ResposeStatus removeTask(@RequestBody String id) throws SQLException {
        Boolean flag = taskService.delete(id);
        if(flag == false){
            return ResposeStatus.error("该任务正在运行无法删除","405");
        } else {
            return ResposeStatus.success();
        }
    }

    /**
     * 启用定时器
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("enableTask")
    private ResposeStatus enableTask(@RequestBody String id) throws SQLException{

        taskService.enable(id);
        return ResposeStatus.success();
    }

    /**
     * 禁用定时器
     * @param id
     * @return
     * @throws SQLException
     */
    @PostMapping("disableTask")
    private ResposeStatus disableTask(@RequestBody String id) throws SQLException{
        taskService.disable(id);
        return ResposeStatus.success();
    }
}
