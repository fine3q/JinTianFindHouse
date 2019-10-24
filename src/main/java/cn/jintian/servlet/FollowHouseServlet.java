package cn.jintian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.jintian.pojo.Users;
import cn.jintian.service.impl.FollowHouseServiceImpl;
import cn.jintian.util.OldHousePage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sexy Six
 *
 */
public class FollowHouseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//1.���ղ���
		String indexTmp = request.getParameter("index");
		//2.�������
		int index = 1;
		if (indexTmp != null && indexTmp.length() > 0) {
			index = Integer.parseInt(indexTmp);
		}
		//3.����Service

		FollowHouseServiceImpl fhsi = new FollowHouseServiceImpl();
		Users u = (Users) request.getSession().getAttribute("user");
		if (u == null){
			return;
		}
		Integer uId = u.getU_id();
		System.out.println(uId);
		OldHousePage oldHoursePage = fhsi.getOldHourse(index, 2, uId.toString());
		//4.����json
		writeValue(oldHoursePage,response);
		/*request.setAttribute("oldHoursePage", oldHoursePage);
		request.getRequestDispatcher("favorHouse/index.jsp").forward(request,response);*/
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
