package com.bjfn.securityjwt.mapper;

import com.bjfn.securityjwt.pojo.SysRole;
import com.bjfn.securityjwt.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByName(String name);

    List<SysRole> selectAllRole(Integer id);
}
