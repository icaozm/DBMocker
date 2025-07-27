package com.db.mock.comon.bean;

import java.util.List;

/**
 * @author: caozm
 * @create: 2022-06-18 13:16
 * @Version 1.0
 * @description:
 **/
public class GroupBean {
    /**
     * 分组ID
     */
    private String groupId;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组描述
     */
    private String groupDesc;

    /**
     * 分组表列表
     */
    private List<TableBean> tableList;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public List<TableBean> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableBean> tableList) {
        this.tableList = tableList;
    }
}