package com.jluzh.taobao;

import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P> 模拟淘宝登陆流程 </P>
 *
 * @author lian.weiyuan@hand-china.com
 * @version 1.0.0
 * @ClassName TaobaoLogin.java
 * @createTime 2020年04月13日 15:23:00
 */
public class TaobaoLogin {
	public static  final Pattern P = Pattern.compile("\"st\":\"(.*?)\"");
	/**
	 * <P> request请求 </P>
	 */
	Session session = Requests.session();
	TaobaoUser taobaoUser;

	public TaobaoLogin(TaobaoUser taobaoUser) {
		this.taobaoUser = taobaoUser;
	}

	public static void main(String[] args) {
		//用户名
		String username = "13226546492";
		//AES加密前密码
		String password = "";
		//ua
		String ua = "122#m02XgJXQEExjgDpZMEpJEJponDJE7SNEEP7rEJ+/f9t/2oQLpo7iEDpWnDEeK51HpyGZp9hBuDEEJFOPpC76EJponDJL7gNpEPXZpJRgu4Ep+FQLpoGUEJLWn4yP7SQEEyuLpERsoTRqprZCnaRx9kbwgHNmqKCUXaUXSrxT2+s9SxUfNTb/Rs1K5JiGHPz9bsH5ATvfm5rWWKZDWs+9vel7JaSma2vVwH8jzEf0HkxEkDPBWMp1uOxnD95kKEjyZ826hoFZvSm2E5py1g2+06QAHHtr397NgqjpyBvmqM3bD5pMngaHOkPtDLVr8oRfJDEERgMdt1+oEEpxkMp1ul5bDRXZ8oL6JNEEyBfDqMfbDDpanSL4ul0EDLVr8Cp6J4bEyF3mqW32E9vxnSpvGOIEDLVbuowhJS6RrFv9zSV53Ja04FH6GRkP+8KMn9UQWF1dpe3XUGy0P1c1gVQJyXTnS8oEFJ2nbbPT50Pq7gWW73va7+50TkNYxGDUXb4Q2gcD+91iXt6Ff/jLLIJWDjyN8K3gHJpADQVXhuu71yiI79Ci1/Er0dhl1/mCn5DS9iVJYdJp2rHXxWQghxwK83zdBlDOynL+7xJQ+T7pEiKw6ntr8e/JG7IzqMk3mp9POzS6WwIebv2lhq9Sj31YwYrwLKq8KJKx8Qvar1vChAbhiTaBKopZtmlgKQQ/2xUnazlsGbxojb5xfPcAHqnDFcCt0HG1SlmWWdsHNmPsolAyxDh9QM/eU7Y99w5/b+wAp9x5XoRJKbpD7VSU/UdZzKZ35V+SPuKIftlXTgTs3hHTkiSAL1NaoiKo7UqJeoNZyLPC1OBGq4QwKKgPp3XnNgOfKj6Okwryi/2RqDx8AXj6PJGhPN7OVT5viuycqMjyjjQixvIVa2WoeRhqyyo/cy0qs+K3hFlI/tjBSI2vDH+ijKDvWKcPeBF2V2h8N1f4BQSvF0vlt3rTgQ9mTTY90CBVOVdDGVuHzuiSH6ChyDvwOnj8J+Ql2dQ3boI2x53YZ/12Sas5wX78+nKw758Hxuei2B1NP6uLnWV36Hzsudokjh246b6Rx5XwqRhD4aGbpOV3FFNm8SP9tf8gmG+Jv6fnK7woztfyBcCWqwKQiK4rddYpGATGr8Z14T+63sZBSBgIF2zKR7OBM8efTalXz6rCTFcw+8Ff";

		TaobaoUser taobaoUser = new TaobaoUser(username,password,ua);

		TaobaoLogin taobaoLogin = new TaobaoLogin(taobaoUser);
		taobaoLogin.checkCode();
		String token = taobaoLogin.verifyPassword();
		taobaoLogin.login(token);
		taobaoLogin.getTaobaoName();

	}

	/**
	 * <P> 通过ST码登陆 </P>
	 * @param token
	 * @return: void
	 * @Author: lian.weiyuan@hand-china.com
	 * @date: 2020/4/13 16:09
	 */
	private void login(String token) {
		Map<String,String> header = new HashMap<String,String>(8);
		header.put("Host","login.taobao.com");
		header.put("Connection","Keep-Alive");
		header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		String res = session.get(RequestUrl.ST_CODE_URL + token).headers(header).send().readToText();
		System.out.println(res);
	}

	/**
	 * <P> 是否需要滑动框校验 </P>
	 * @param
	 * @return: void
	 * @Author: lian.weiyuan@hand-china.com
	 * @date: 2020/4/13 15:53
	 */
	private void checkCode() {
		Map<String,String> body = new HashMap<String,String>(8);
		body.put("username","13226546492");
		body.put("ua","122#m02XgJXQEExjgDpZMEpJEJponDJE7SNEEP7rEJ+/f9t/2oQLpo7iEDpWnDEeK51HpyGZp9hBuDEEJFOPpC76EJponDJL7gNpEPXZpJRgu4Ep+FQLpoGUEJLWn4yP7SQEEyuLpERsoTRqprZCnaRx9kbwgHNmqKCUXaUXSrxT2+s9SxUfNTb/Rs1K5JiGHPz9bsH5ATvfm5rWWKZDWs+9vel7JaSma2vVwH8jzEf0HkxEkDPBWMp1uOxnD95kKEjyZ826hoFZvSm2E5py1g2+06QAHHtr397NgqjpyBvmqM3bD5pMngaHOkPtDLVr8oRfJDEERgMdt1+oEEpxkMp1ul5bDRXZ8oL6JNEEyBfDqMfbDDpanSL4ul0EDLVr8Cp6J4bEyF3mqW32E9vxnSpvGOIEDLVbuowhJS6RrFv9zSV53Ja04FH6GRkP+8KMn9UQWF1dpe3XUGy0P1c1gVQJyXTnS8oEFJ2nbbPT50Pq7gWW73va7+50TkNYxGDUXb4Q2gcD+91iXt6Ff/jLLIJWDjyN8K3gHJpADQVXhuu71yiI79Ci1/Er0dhl1/mCn5DS9iVJYdJp2rHXxWQghxwK83zdBlDOynL+7xJQ+T7pEiKw6ntr8e/JG7IzqMk3mp9POzS6WwIebv2lhq9Sj31YwYrwLKq8KJKx8Qvar1vChAbhiTaBKopZtmlgKQQ/2xUnazlsGbxojb5xfPcAHqnDFcCt0HG1SlmWWdsHNmPsolAyxDh9QM/eU7Y99w5/b+wAp9x5XoRJKbpD7VSU/UdZzKZ35V+SPuKIftlXTgTs3hHTkiSAL1NaoiKo7UqJeoNZyLPC1OBGq4QwKKgPp3XnNgOfKj6Okwryi/2RqDx8AXj6PJGhPN7OVT5viuycqMjyjjQixvIVa2WoeRhqyyo/cy0qs+K3hFlI/tjBSI2vDH+ijKDvWKcPeBF2V2h8N1f4BQSvF0vlt3rTgQ9mTTY90CBVOVdDGVuHzuiSH6ChyDvwOnj8J+Ql2dQ3boI2x53YZ/12Sas5wX78+nKw758Hxuei2B1NP6uLnWV36Hzsudokjh246b6Rx5XwqRhD4aGbpOV3FFNm8SP9tf8gmG+Jv6fnK7woztfyBcCWqwKQiK4rddYpGATGr8Z14T+63sZBSBgIF2zKR7OBM8efTalXz6rCTFcw+8Ff");
		String res = session.post(RequestUrl.USER_CHECK_URL).body(body).send().readToText();
		if(res.contains("true")){
			throw new RuntimeException("需要滑动窗口验证,请过段时间再试");
		}
		System.out.println("是否需要滑动框校验:" + res);
	}

	/**
	 * <P> 获取登陆后的页面(未对页面处理) </P>
	 * @param
	 * @return: void
	 * @Author: lian.weiyuan@hand-china.com
	 * @date: 2020/4/13 16:03
	 */
	private void getTaobaoName(){
		Map<String,String> map = new HashMap<String, String>(8);
		map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		String s = session.get(RequestUrl.MY_TAOBAO_URL).headers(map).send().readToText();
		System.out.println("\n\n\n\n");
		System.out.println(s);
	}

	/**
	 * <P> 校验密码并返回st </P>
	 * @param
	 * @return: java.lang.String 用于登陆的ST
	 * @Author: lian.weiyuan@hand-china.com
	 * @date: 2020/4/13 15:54
	 */
	private String verifyPassword(){
		Map<String,String> body = new HashMap<String, String>(32);
		body.put("TPL_username", taobaoUser.getLoginId());
		body.put("umidGetStatusVal","255");
		body.put("screenPixel","2048x1152");
		body.put("navlanguage", "zh-CN");
		body.put("navUserAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
		body.put("navPlatform","Win32");
		body.put("appName","taobao");
		body.put("appEntrance","taobao_pc");
		body.put("_csrf_token","fdDamCP3GRdbY76GcPDPWA");
		body.put("umidToken","3219e6c923eda2234871dcf4c02be62f004a9d47");
		body.put("hsiz","169bcd1f46aa73f6685a15787ee5e1b4");
		body.put("style","style: default");
		body.put("appkey","00000000");
		body.put("from","tb");
		body.put("TPL_password_2",taobaoUser.getPassword());
		body.put("isMobile","false");
		body.put("lang","zh_CN");
		body.put("fromSite", "0");
		body.put("ua",taobaoUser.getUa());

		Map<String,String> header = new HashMap<String, String>(16);
		header.put("Connection","keep-alive");
		header.put("Cache-Control","max-age=0");
		header.put("Origin","https://login.taobao.com");
		header.put("Upgrade-Insecure-Requests","1");
		header.put("Content-Type","application/x-www-form-urlencoded");
		header.put("Referer","https://login.taobao.com/member/login.jhtml?spm=a21bo.2017.754894437.1.5af911d9CZ3pmA&f=top&redirectURL=https%3A%2F%2Fwww.taobao.com%2F");
		header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		String res = session.post(RequestUrl.TOKEN_URL).body(body).headers(header).send().readToText();
		String stUrl = findUrl(res);
		if(stUrl == null){
			throw new  RuntimeException("用户名密码验证失败");
		}
		System.out.println("验证用户名密码成功，st码申请地址：" + stUrl);
		String stToken = session.get(RequestUrl.ST_CODE_URL).send().readToText();
		return stCode(stToken);
	}



	private String findUrl(String s){
		String[] split = s.trim().split("src=");
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("token")){
				for (String url : split[i].split("\"")){
					if(url.contains("token")){
						return url;
					}
				}
			}
		}
		return null;
	}


	private String stCode(String s){
		Matcher m = P.matcher(s);
		String stCode = "";
		if(m.find()){
			stCode = m.group().split("\"")[3];
		}
		return  stCode;
	}


}
