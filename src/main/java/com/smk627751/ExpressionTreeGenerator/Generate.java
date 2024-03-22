package com.smk627751.ExpressionTreeGenerator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/generate")
public class Generate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String expression = request.getParameter("expression");
        if(expression != null && !expression.isEmpty() && !expression.contains("[a-z,A-Z]"))
        {
        	ExpressionTreeGenerator obj = new ExpressionTreeGenerator();
        	String postFix = obj.infixToPostfix(expression.replace(" ",""));
            Node<String> expTree = obj.generate(postFix);
            double result = 0;
			try {
				result = obj.evaluate(postFix);
			} catch (Exception e) {
				request.setAttribute("error", "Invalid expression");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
            request.setAttribute("expression", expression);
            request.setAttribute("expTree", expTree);
            request.setAttribute("result",result);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else
        {
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
