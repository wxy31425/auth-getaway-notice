package com.auth.get.away.notice.utils;

import com.auth.get.away.notice.core.TreeNode;
import java.util.ArrayList;
import java.util.List;
/**
 *  以对象形式传回前台
 * @author wxy
 * 2020-2-1
 */
public class JsonFactory {
	public static List<TreeNode> buildtree(List<TreeNode> nodes, String id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node = new TreeNode();
			node.setId(treeNode.getId());
			node.setLabel(treeNode.getLabel());
			node.setPid(treeNode.getPid());

			if (id.equals(treeNode.getPid())) {
				List<TreeNode> childrens=buildtree(nodes, node.getId());
				if(childrens!=null&&childrens.size()>0){
					node.setChildren(childrens);
				}
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}

	public static List<TreeNode> useBuildtree(List<TreeNode> nodes, String id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node = new TreeNode();
			node.setId(treeNode.getId());
			node.setLabel(treeNode.getLabel());
			node.setPid(treeNode.getPid());
				List<TreeNode> childrens=buildtree(nodes, node.getId());
				if(childrens!=null&&childrens.size()>0){
					node.setChildren(childrens);
				}
				treeNodes.add(node);
			}
		return treeNodes;
	}
}
