package com.fh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fh.entity.businessuser.Businessuser;
import com.fh.util.Const;

/**
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if ("/businessuser/smsCode.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/save.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/goforgetPassword.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/forgetPassWord.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/chenggong.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/shibai.do".trim().equals(path.trim())) {
			return true;
		}else  if ("/businessuser/register1.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/login_toLogin.do".trim().equals(path.trim())) {
			return true;
		}else if ("/businessuser/login_toLogin".trim().equals(path.trim())) {
			return true;
		}else if(path.trim().contains("/aBuser")){
			return true;
		}else if(path.trim().contains(".txt")){
			return true;
		}else 
		if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Businessuser businessuser = (Businessuser) session.getAttribute(Const.SESSION_USER);
			
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			System.out.println("BUSINESSUSER_ID----"+"("+BUSINESSUSER_ID+")");
			if (businessuser != null) {
				System.out.println("businessuser----"+"("+businessuser.getBUSINESSUSER_ID()+")");
				path = path.substring(1, path.length());
				boolean b = true;
				if (!b) {
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			} else if(BUSINESSUSER_ID!=null){
				boolean b = true;
				if (!b) {
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			}else{
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;
				// return true;
			}
		}
	}

}
