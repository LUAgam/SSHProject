/**

* Title: User.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package io.aomen.guo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 
 * Title: User
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity<User> {

	/** serialVersionUID*/
	private static final long serialVersionUID = -4607639114659020669L;

	// ���
	@Column(name = "nname")
	private String name;

	// �˺�
	@Column(name = "username")
	private String username;

	// ����
	@Column(name = "password")
	private String password;

	@Version
	private int version;

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * （非 Javadoc）
	 * 
	 * Title: toString
	 * 
	 * Description:
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password=" + password + ", version=" + version
				+ "]";
	}

}
