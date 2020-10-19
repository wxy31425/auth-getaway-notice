package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
  * 分页
 * @author wxy
 * 2020 1-22
 */
@Setter
@Getter
public class PageVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer curPage ;
    private Integer pageSize;
    private String keyWord;
    private Integer status;
    private String parentId;
    private String departmentId;
    private String employeesId;
}
