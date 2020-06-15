package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.member.MemberVO;

@Component
public class QnaInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean check = false;
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		if(memberVO != null) {
			check = true;
		}else {
			response.sendRedirect("../message/messageResult?result=login gogo.&path=/member/memberLogin");
		}
		
		return check;
	}
	
}
