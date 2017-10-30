package io.aomen.guo.web.formbean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AMGuo E-mail:www.guoao@foxmail.com
 * @version 创建时间：2017年8月30日 下午4:56:07 类说明
 */
public class UserFB extends BaseFormBean{

    /**
     * 
     */
    private static final long serialVersionUID = -1489759430412742934L;

    @NotBlank(message = "用户名不为空")
    private String username;

    @NotBlank(message = "密码不为空")
    private String password;
    
    private String rememberMe;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * @return the rememberMe
	 */
	public String getRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

}
