package cn.jintian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jintian.pojo.ResultInfo;
import cn.jintian.pojo.Users;
import cn.jintian.service.impl.RegisteredServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author XGL
 *
 */
public class RegisteredServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//初始化设置
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("Utf-8");
		RegisteredServiceImpl rsi = new RegisteredServiceImpl();
		//1.1获取手机号
		String uIphone = request.getParameter("phoneNumber");
		String pwd = request.getParameter("passwordagain");
		if (pwd != null && uIphone != null) {
			String userCode = request.getParameter("veri-code");
			JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
			ResultInfo info = new ResultInfo();
			if(json == null){
				info.setErrorMsg("验证码错误");
				writeValue(info,response);
				return ;
			}
			if(!json.getString("phone").equals(uIphone)){
				info.setErrorMsg("手机号错误");
				writeValue(info,response);
				return ;
			}
			if(!json.getString("code").equals(userCode)){
				info.setErrorMsg("验证码错误");
				writeValue(info,response);
				return ;
			}
			if((System.currentTimeMillis() - json.getLong("creatTime")) > 1000 * 60 * 5){
				info.setErrorMsg("验证码已过期");
				writeValue(info,response);
				return ;
			}
			Users user = new Users();
			user.setU_name(uIphone);
			user.setU_phonenumber(uIphone);
			user.setU_pwd(pwd);
			Users reUser = rsi.registered(user);

			if (reUser != null) {
				//request.getSession().setAttribute("user", reUser);
				info.setFlag(true);
			}else{
				info.setFlag(false);
				info.setErrorMsg("注册失败");
			}
			writeValue(info,response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void renderData(HttpServletResponse response, String data){
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入对象序列化为json,并写回客户端
	 * @param obj
	 */
	public void writeValue(Object obj, HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=utf-8");
			mapper.writeValue(response.getOutputStream(),obj);
	}

	/**
	 * 将传入的对象序列化为json,返回
	 * @return
	 */
	public String writeValueAsString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
