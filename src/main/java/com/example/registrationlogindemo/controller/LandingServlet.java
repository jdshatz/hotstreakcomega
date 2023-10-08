package com.example.registrationlogindemo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/landing")
public class LandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LandingServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destPage = "login.html";
		HttpSession session = request.getSession();
		destPage = "landing.html";


		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException  {
		String destPage = "landing.html";
	}

}
