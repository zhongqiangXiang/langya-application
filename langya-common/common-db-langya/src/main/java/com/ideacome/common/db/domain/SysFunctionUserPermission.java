package com.ideacome.common.db.domain;

import java.util.Date;

public class SysFunctionUserPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.function_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Integer functionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.user_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.permission_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Byte permissionFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.creator
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.modifier
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.add_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.modify_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_function_user_permisssion.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    private Byte sysFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.id
     *
     * @return the value of sys_function_user_permisssion.id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.id
     *
     * @param id the value for sys_function_user_permisssion.id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.function_id
     *
     * @return the value of sys_function_user_permisssion.function_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Integer getFunctionId() {
        return functionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.function_id
     *
     * @param functionId the value for sys_function_user_permisssion.function_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.user_id
     *
     * @return the value of sys_function_user_permisssion.user_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.user_id
     *
     * @param userId the value for sys_function_user_permisssion.user_id
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.permission_flag
     *
     * @return the value of sys_function_user_permisssion.permission_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Byte getPermissionFlag() {
        return permissionFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.permission_flag
     *
     * @param permissionFlag the value for sys_function_user_permisssion.permission_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setPermissionFlag(Byte permissionFlag) {
        this.permissionFlag = permissionFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.creator
     *
     * @return the value of sys_function_user_permisssion.creator
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.creator
     *
     * @param creator the value for sys_function_user_permisssion.creator
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.modifier
     *
     * @return the value of sys_function_user_permisssion.modifier
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.modifier
     *
     * @param modifier the value for sys_function_user_permisssion.modifier
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.add_time
     *
     * @return the value of sys_function_user_permisssion.add_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.add_time
     *
     * @param addTime the value for sys_function_user_permisssion.add_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.modify_time
     *
     * @return the value of sys_function_user_permisssion.modify_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.modify_time
     *
     * @param modifyTime the value for sys_function_user_permisssion.modify_time
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_function_user_permisssion.sys_flag
     *
     * @return the value of sys_function_user_permisssion.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public Byte getSysFlag() {
        return sysFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_function_user_permisssion.sys_flag
     *
     * @param sysFlag the value for sys_function_user_permisssion.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:29:52 CST 2018
     */
    public void setSysFlag(Byte sysFlag) {
        this.sysFlag = sysFlag;
    }
}