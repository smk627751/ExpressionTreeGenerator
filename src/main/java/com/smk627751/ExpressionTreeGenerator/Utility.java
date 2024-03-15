package com.smk627751.ExpressionTreeGenerator;
import static java.lang.System.out;;
public class Utility {
	public static void printTree(Node<String> node, int depth) {
        if (node != null) {
            out.println("<div class='row'>");
            out.println("<div class='node' style='margin-left: " + (depth * 20) + "px'>" + node.root + "</div>");
            printTree(node.left, depth + 1);
            printTree(node.right, depth + 1);
            out.println("</div>");
        }
    }
}
