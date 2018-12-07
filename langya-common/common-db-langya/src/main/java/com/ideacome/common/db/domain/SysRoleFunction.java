package com.ideacome.common.db.domain;

import java.util.Date;

public class SysRoleFunction {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.role_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.function_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Integer functionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.valid_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Byte validFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.creator
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.modifier
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.add_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.modify_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_function.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    private Byte sysFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.id
     *
     * @return the value of sys_role_function.id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.id
     *
     * @param id the value for sys_role_function.id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.role_id
     *
     * @return the value of sys_role_function.role_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.role_id
     *
     * @param roleId the value for sys_role_function.role_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.function_id
     *
     * @return the value of sys_role_function.function_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Integer getFunctionId() {
        return functionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.function_id
     *
     * @param functionId the value for sys_role_function.function_id
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.valid_flag
     *
     * @return the value of sys_role_function.valid_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Byte getValidFlag() {
        return validFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.valid_flag
     *
     * @param validFlag the value for sys_role_function.valid_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setValidFlag(Byte validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.creator
     *
     * @return the value of sys_role_function.creator
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.creator
     *
     * @param creator the value for sys_role_function.creator
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.modifier
     *
     * @return the value of sys_role_function.modifier
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.modifier
     *
     * @param modifier the value for sys_role_function.modifier
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.add_time
     *
     * @return the value of sys_role_function.add_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.add_time
     *
     * @param addTime the value for sys_role_function.add_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.modify_time
     *
     * @return the value of sys_role_function.modify_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.modify_time
     *
     * @param modifyTime the value for sys_role_function.modify_time
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_function.sys_flag
     *
     * @return the value of sys_role_function.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public Byte getSysFlag() {
        return sysFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_function.sys_flag
     *
     * @param sysFlag the value for sys_role_function.sys_flag
     *
     * @mbggenerated Fri Dec 07 10:51:41 CST 2018
     */
    public void setSysFlag(Byte sysFlag) {
        this.sysFlag = sysFlag;
    }
}