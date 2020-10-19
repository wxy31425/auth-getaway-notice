package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务实体持久化接口
 * @author wxy
 * 2020-1-27
 */
@Repository
public interface ITaskRepository extends JpaSpecificationExecutor<Task>, JpaRepository<Task, String> {
    /**
     * 根据Id查询执行定时任务
     * @return
     */
    @Query(value = "select cron from auth_getaway_task where id =:id  ",nativeQuery = true)
     String cornTask(@Param("id") String id);

    /**
     * 根据除了指定Id以外的数据
     * @param id
     * @return
     */
    @Query(value = "select * from auth_getaway_task where id not in(\n" +
            "select id from auth_getaway_task where id =:id)  ",nativeQuery = true)
    List<Task> findNotById(@Param("id") String id);

    /**
     * 启用定时任务更改状态
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_task set status = 1  where id =:id", nativeQuery = true)
    Integer enableTask(@Param("id") String id);

    /**
     * 禁用定时任务更改状态
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_task set status = 0  where id =:id", nativeQuery = true)
    Integer disableTask(@Param("id") String id);
    /**
     * 删除该任务
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_task where id =:id", nativeQuery = true)
    Integer deleteTask(@Param("id") String id);

}
