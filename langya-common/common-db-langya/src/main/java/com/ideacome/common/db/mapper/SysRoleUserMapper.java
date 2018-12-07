package com.ideacome.common.db.mapper;

import com.ideacome.common.db.domain.SysRoleUser;
import com.ideacome.common.db.domain.SysRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int countByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int deleteByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int insert(SysRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int insertSelective(SysRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    List<SysRoleUser> selectByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    SysRoleUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int updateByExampleSelective(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int updateByExample(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int updateByPrimaryKeySelective(SysRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    int updateByPrimaryKey(SysRoleUser record);
}