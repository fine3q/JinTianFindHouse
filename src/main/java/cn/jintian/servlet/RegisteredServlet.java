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
		//��ʼ������
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("Utf-8");
		RegisteredServiceImpl rsi = new RegisteredServiceImpl();
		//1.1��ȡ�ֻ���
		String uIphone = request.getParameter("phoneNumber");
		String pwd = request.getParameter("passwordagain");
		if (pwd != null && uIphone != null) {
			String userCode = request.getParameter("veri-code");
			JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
			ResultInfo info = new ResultInfo();
			if(json == null){
				info.setErrorMsg("��֤�����");
				writeValue(info,response);
				return ;
			}
			if(!json.getString("phone").equals(uIphone)){
				info.setErrorMsg("�ֻ��Ŵ���");
				writeValue(info,response);
				return ;
			}
			if(!json.getString("code").equals(userCode)){
				info.setErrorMsg("��֤�����");
				writeValue(info,response);
				return ;
			}
			if((System.currentTimeMillis() - json.getLong("creatTime")) > 1000 * 60 * 5){
				info.setErrorMsg("��֤���ѹ���");
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
				info.setErrorMsg("ע��ʧ��");
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
	 * ����������л�Ϊjson,��д�ؿͻ���
	 * @param obj
	 */
	public void writeValue(Object obj, HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=utf-8");
			mapper.writeValue(response.getOutputStream(),obj);
	}

	/**
	 * ������Ķ������л�Ϊjson,����
	 * @return
	 */
	public String writeValueAsString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
