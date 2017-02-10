package com.bit2017.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.guestbook.dao.GuestbookDAO;
import com.bit2017.guestbook.vo.GuestbookVO;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "utf-8");
		
		String actionName = request.getParameter("a");
		
		if("deleteform".equals( actionName) ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/deleteform.jsp");
			dispatcher.forward(request, response);
		} else if ("delete".equals(actionName)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVO guestbookVO = new GuestbookVO();
			guestbookVO.setNo(no);
			guestbookVO.setPassword(password);
			
			new GuestbookDAO().delete(guestbookVO);
			
			response.sendRedirect(request.getContextPath() + "/gb");
		} else if ("add".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			GuestbookVO vo = new GuestbookVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			new GuestbookDAO().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/gb");
		} else {
			
			GuestbookDAO dao = new GuestbookDAO();
			List<GuestbookVO> list = dao.getList();
			
			request.setAttribute("list", list);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	

}
