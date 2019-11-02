package cn.jintian.servlet;

import cn.jintian.pojo.Old_H;
import cn.jintian.service.impl.IndexOldHouseServiceImpl;
import cn.jintian.util.OldHousePage;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class IndexOldHouseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int index = 1;
		/*String parameter = request.getParameter("index");
		if (parameter != null) {
			index = Integer.parseInt(parameter);
		}*/
		IndexOldHouseServiceImpl ioh = new IndexOldHouseServiceImpl();
		OldHousePage ohp = ioh.getOldHourse(index, 2);
		List<String> imgInfo = ioh.getImgInfo(index, 2);
		for (Old_H old_h : ohp.getHourse()) {
			for (String s : imgInfo) {
				old_h.setOld_h_img(s);
			}

		}
		System.out.println(ohp.getHourse().size()+"ca");
		//System.out.println(oimg.getImg().size()+"s");
		request.setAttribute("ohp", ohp);
		////request.setAttribute("oimg",oimg.getImg());
		String s = JSONObject.toJSONString(ohp);
		out.print(s);
//		request.getRequestDispatcher("secondhandhouse/index.jsp").forward(request, response);
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
