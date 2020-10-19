package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskVM {
    private String id;
    private String name;
    private String cron;
    private Date createTime;
}
