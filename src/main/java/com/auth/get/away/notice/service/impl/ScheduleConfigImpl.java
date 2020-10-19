package com.auth.get.away.notice.service.impl;

import com.auth.get.away.notice.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 定时任务管理配置、
 * @author wxy
 * 2020 2-3
 */
@Configuration
@EnableScheduling
public class ScheduleConfigImpl implements SchedulingConfigurer {
    private  String taskId = "402881ea7005ec2a01700651574d0001";
    @Autowired
    ITaskRepository taskRepository;

    /**
     * 获取启用startCron的Id
     * @param id
     */
    public void startCron(String id){
         taskId = id;
    }

    /**
     * 扫除定时器执行
     * @param scheduledTaskRegistrar
     */
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                ()-> System.out.println("执行的定时任务：" + LocalDateTime.now().toLocalTime()),
                triggerContext -> {
                    String cron = taskRepository.cornTask(taskId);
                    if(StringUtils.isEmpty(cron)){
                        System.out.println("执行的任务为空");
                    }
                    return  new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
