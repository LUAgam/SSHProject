package com.aomen.guo.web.formbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author AMGuo E-mail:www.guoao@foxmail.com
 * @version 创建时间：2017年8月30日 下午4:56:07 类说明
 */
public class UserFB extends BaseFormBean{

    /**
     * 
     */
    private static final long serialVersionUID = -1489759430412742934L;

    @NotEmpty(message = "名称不为空")
    private String name;

    @NotEmpty(message = "用户名不为空")
    private String username;

    @NotEmpty(message = "密码不为空")
    private String password;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
