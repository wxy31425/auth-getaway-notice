package com.auth.get.away.notice.service;

import com.auth.get.away.notice.controller.vm.TaskVM;
import com.auth.get.away.notice.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;

/**
 * 定时任务管理ITaskService
 * @author wxy
 * 2020 2-2
 */
public interface ITaskService {

    /**
     * 分页查询定时任务信息
     * @param pageable
     * @return
     */
    Page<Task> findByPage(String keyword,Integer status, Pageable pageable);
    /**
     * 保存定时任务信息
     * @param taskVM
     * @return
     */
    Object save(TaskVM taskVM) throws SQLException;

    /**
     * 删除该定时任务
     * @param id
     * @return
     * @throws SQLException
     */
    Boolean delete(String id) throws SQLException;

    /**
     * 启用定时任务更改状态
     * @param id
     * @return
     * @throws SQLException
     */
    Object enable(String id)throws SQLException;

    /**
     * 禁用定时任务更改状态
     * @param id
     * @return
     * @throws SQLException
     */
    Object disable(String id)throws SQLException;
}
