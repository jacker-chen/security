package com.bjfn.securityjwt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;


/**
 * sys_menu
 * @author 
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 是否删除(0.未删除;1.已删除)
     */
    private Byte isDelete;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    private List<SysRole> roleList;
}
