package com.auth.get.away.notice.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Tree节点类
 * @author wxy
 * 2020-2-1
 */
@Getter
@Setter
public class TreeNode {
    private String id;
    private String name;
    private String label;
    private String pid;
    private String icon;
    private String url;
    private List<TreeNode> children;
    public TreeNode(){}
}

