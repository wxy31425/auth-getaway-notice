package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

/**
 * 定时任务实体类
 * @author wxy
 * 2020-1-31
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_task")
public class Task {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 定时任务名称
     */
    @Column(length = 120)
    private String name;

    /**
     * 定时corn表达式
     */
    @Column(length = 30)
    private String cron;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;

    /**
     * 任务状态
     * 1:启用 0:关闭
     */
    @Column
    private Integer status = 0;


}
