package com.auth.get.away.notice.service.impl;
import com.auth.get.away.notice.controller.vm.TaskVM;
import com.auth.get.away.notice.entity.Task;
import com.auth.get.away.notice.repository.ITaskRepository;
import com.auth.get.away.notice.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 定时任务管理ITaskService实现类
 * @author wxy
 * 2020 1-27
 */
@Service
@Slf4j
public class TaskServiceImpl implements ITaskService {
    @Autowired
    ITaskRepository taskRepository;
    @Autowired
    ScheduleConfigImpl scheduleConfig;

    /**
     * 分页查询定时任务信息
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Task> findByPage(String keyword,Integer status, Pageable pageable) {
        Specification<Task> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(status)) {
                predicates.add(builder.equal(root.get("status"), status));
            }
            if (!StringUtils.isEmpty(keyword)) {
                predicates.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(builder.asc(root.get("createTime")));
            return query.getRestriction();
        };
        return taskRepository.findAll(specification, pageable);
    }

    /**
     * 保存定时任务信息
     * @param taskVM
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Object save(TaskVM taskVM) throws SQLException {
        Task task = new Task();
        if(taskVM.getId() == null){
            taskVM.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(taskVM, task);
        task  =  taskRepository.save(task);
        if(task  == null){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Boolean delete(String id) throws SQLException {
        Optional<Task> tasks = taskRepository.findById(id);
        if(tasks.get().getStatus() == 1){
            return false;
        } else {
            int i = taskRepository.deleteTask(id);
            if(i < 1){
                throw new SQLException();
            } else {
                return true;
            }
        }
    }

    /**
     * 启用定时任务更改状态
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Object enable(String id) throws SQLException {
        int i = taskRepository.enableTask(id);
        List<Task> taskList =taskRepository.findNotById(id);
        if(taskList.size()!=0){
            for(Task task:taskList){
              taskRepository.disableTask(task.getId());
            }
        }
        scheduleConfig.startCron(id);
        if(i < 1) {
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 禁用定时器更改状态
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Object disable(String id) throws SQLException {
        int i = taskRepository.disableTask(id);
        scheduleConfig.startCron(id);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }
}
