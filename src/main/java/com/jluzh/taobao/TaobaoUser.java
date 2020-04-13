package com.jluzh.taobao;

/**
 * <P> 保存登陆用到的信息</P>
 *
 * @author lian.weiyuan@hand-china.com
 * @version 1.0.0
 * @ClassName TaobaoUser.java
 * @createTime 2020年04月13日 15:21:00
 */
public class TaobaoUser {

	public TaobaoUser(String loginId, String password, String ua) {
		this.loginId = loginId;
		this.password = password;
		this.ua = ua;
	}

	/**
	 * <P> 登录名 </P>
	 */
	private String loginId;
	/**
	 * <P> 密码  填写AES加密前密码</P>
	 */
	private String password;
	/**
	 * <P> ua参数,根据F12获取 </P>
	 */
	private String ua;

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
