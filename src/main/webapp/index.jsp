<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.smk627751.ExpressionTreeGenerator.Node"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ExpressionTreeGenerator</title>
<Link rel="stylesheet" href="style.css">
</head>
<body>
	<%
		String expression = (String)request.getAttribute("expression");
		Double result = (Double)request.getAttribute("result");
		Node<String> expTree = (Node<String>)request.getAttribute("expTree");
		String err = (String)request.getAttribute("error");
	%>
	<h1>Expression Tree Generator</h1>
	<form action=<%= request.getContextPath()+"/generate"%>>
		<div>
			<input type="text" name="expression" placeholder="eg. 1*2+3*4">
			<input type="submit" value="Generate">
		</div>
		<div class="result">
		<%
			if(result != null)
			{
				out.println("<span>"+expression+"</span>=<span>"+result+"</span>");
			}
			if(err != null)
			{
				out.println("<span class='err'>"+err+"</span>");
			}
		%>
		</div>
	</form>
	<%
	    if (expTree != null) {
	        out.println("<div class='container'>");
	        printTree(expTree,out);
	        id = 0;
	        out.println("</div>");
	    }
	%>
	
	<%! 
		int id = 0;
	    void printTree(Node<String> node, JspWriter out) throws IOException {
	        if (node != null) {
	            out.println("<div class='row'>");
	            out.println("<span class='node' style='--i:"+id++ +"'>" + node.root + "</span>");
	            printTree(node.left, out);
	            printTree(node.right,out);
	            out.println("</div>");
	        }
	    }
	%>
</body>
</html>