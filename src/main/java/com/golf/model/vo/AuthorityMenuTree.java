package com.golf.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.golf.util.TreeNode;

public class AuthorityMenuTree extends TreeNode implements Serializable{
    String text;
    List<AuthorityMenuTree> nodes = new ArrayList<AuthorityMenuTree>();
    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public AuthorityMenuTree(String text, List<AuthorityMenuTree> nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    public AuthorityMenuTree() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AuthorityMenuTree> getNodes() {
        return nodes;
    }

    public void setNodes(List<AuthorityMenuTree> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<AuthorityMenuTree>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AuthorityMenuTree n = new AuthorityMenuTree();
        BeanUtils.copyProperties(node,n);
        nodes.add(n);
    }
}
