<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.smk627751.ExpressionTreeGenerator.Node"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ExpressionTreeGenerator</title>
<Link rel="stylesheet" href="style.css">
</head>
<body>
	<%
		String expression = (String)request.getAttribute("expression");
		Double result = (Double)request.getAttribute("result");
		Node<String> expTree = (Node<String>)request.getAttribute("expTree");
	%>
	<h1>Expression Tree Generator</h1>
	<form action="generate">
		<div>
			<input type="text" name="expression">
			<input type="submit" value="Generate">
		</div>
		<div>
		<%
			if(result != null)
			{
				out.println("<span>"+expression+"</span>=<span>"+result+"</span>");
			}
		%>
		</div>
	</form>
	<%
	    if (expTree != null) {
	        out.println("<div class='container'>");
	        printTree(expTree, out);
	        out.println("</div>");
	    }
	%>
	
	<%! 
	    void printTree(Node<String> node, JspWriter out) throws IOException {
	        if (node != null) {
	            out.println("<div class='row'>");
	            out.println("<span class='node'>" + node.root + "</span>");
	            printTree(node.left, out);
	            printTree(node.right, out);
	            out.println("</div>");
	        }
	    }
	%>
</body>
</html>