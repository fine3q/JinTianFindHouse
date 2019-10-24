package cn.jintian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.jintian.pojo.ResultInfo;
import cn.jintian.util.SMSUtil;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegSendMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("Utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phoneNumber");
		JSONObject json = new JSONObject();
		if (phone != null) {
			int sendCode = SMSUtil.SendCode(phone);
			ResultInfo info = new ResultInfo();
			info.setFlag(true);
			ObjectMapper mapper = new ObjectMapper();
			String json2 = mapper.writeValueAsString(info);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(json2);
			//renderData(response, "发送成功");
			json.put("code", sendCode);
			json.put("phone", phone);
			json.put("creatTime", System.currentTimeMillis());
			request.getSession().setAttribute("verifyCode", json);
			return ;
		}
		out.flush();
		out.close();
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
