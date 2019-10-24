package cn.jintian.servlet;

import cn.jintian.pojo.ResultInfo;
import cn.jintian.pojo.Users;
import cn.jintian.service.impl.RegisteredServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author XGL
 *
 */
public class RegFindUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ʼ������
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("Utf-8");
		RegisteredServiceImpl rsi = new RegisteredServiceImpl();
		//1.1��ȡ�ֻ���
		String uIphone = request.getParameter("phoneNumber");
		//1.2�жϵ�ǰ�û��Ƿ�ע��
		if (uIphone == null) {
			return ;
		}
		boolean result = rsi.isExist(uIphone);
		System.out.println(result);
		ResultInfo info = new ResultInfo();
		if (result){
			info.setFlag(true);
		}else {
			info.setFlag(false);
			info.setErrorMsg("���ظ�");
		}
		writeValue(info,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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
