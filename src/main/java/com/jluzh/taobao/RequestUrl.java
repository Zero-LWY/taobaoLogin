package com.jluzh.taobao;

/**
 * <P> 登陆用到的URL常量类</P>
 *
 * @author lian.weiyuan@hand-china.com
 * @version 1.0.0
 * @ClassName RequestUrl.java
 * @createTime 2020年04月13日 15:29:00
 */
public  final  class RequestUrl {
	private RequestUrl(){

	}
	/**
	 * <P> 检测是否需要滑动框 URL </P>
	 */
	public static final String USER_CHECK_URL = "https://login.taobao.com/member/request_nick_check.do?_input_charset=utf-8";

	/**
	 * <P> 获取ST码链接和token URL</P>
	 */
	public static final String TOKEN_URL = "https://login.taobao.com/member/login.jhtml";

	/**
	 * <P> 根据st码登陆</P>
	 */
	public static final String ST_CODE_URL = "https://login.taobao.com/member/vst.htm?st=";

	/**
	 * <P> 我的主页 </P>
	 */
	public static final String MY_TAOBAO_URL = "http://i.taobao.com/my_taobao.htm";

}
