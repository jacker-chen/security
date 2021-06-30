package com.bjfn.securityjwt.mapper;

import com.bjfn.securityjwt.pojo.SysMenu;
import com.bjfn.securityjwt.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysRole> selectByMenuName(String menuName);

    List<SysMenu> selectListSysMenu();
}
