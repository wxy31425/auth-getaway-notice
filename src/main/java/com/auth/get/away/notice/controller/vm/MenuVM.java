package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class MenuVM {
    private String id;
    private String name;
    private String icon;
    private String url;
    private Integer seq;
    private String parentId;
    private Date createTime;
}
