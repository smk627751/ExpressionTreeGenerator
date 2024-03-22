package com.smk627751.ExpressionTreeGenerator;

import java.util.Stack;

public class ExpressionTreeGenerator {
	private int getPrecedence(char c)
    {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }
    private boolean isOperand(char c)
    {
        return Character.isDigit(c);
    }
    String infixToPostfix(String exp)
    {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < exp.length(); i++)
        {
            char c = exp.charAt(i);
            if(isOperand(c))
            {
                while(i < exp.length() && isOperand(exp.charAt(i)))
                {
                    postfix.append(exp.charAt(i++));
                }
                postfix.append(' ');
                i--;
            }
            else if(c == '(')
            {
                stack.push(c);
            }
            else if(c == ')')
            {
                while(!stack.isEmpty() && stack.peek() != '(')
                {
                    postfix.append(stack.pop()).append(' ');
                }
                if(!stack.isEmpty())
                {
                	stack.pop();
                }
            }
            else
            {
                while(!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()))
                {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }
        return postfix.toString().trim();
    }
    Node<String> generate(String exp)
    {
    	Stack<Node<String>> stack = new Stack<>();
        String operators = "+-*/";
        for(String s : exp.split(" "))
        {
            if(!operators.contains(s))
            {
                stack.push(new Node<>(s));
            }
            else
            {
                Node<String> right = stack.pop();
                Node<String> left = stack.pop();
                Node<String> root = new Node<>(s);
                root.left = left;
                root.right = right;
                stack.push(root);
            }
        }
        return stack.pop();
    }
    double evaluate(String exp)
    {
    	Stack<Double> stack = new Stack<>();
        String operators = "+-*/";
        for(String s : exp.split(" "))
        {
           if(!operators.contains(s))
           {
        	   stack.push(Double.parseDouble(s));
           }
           else
           {
        	   Double right = stack.pop();
        	   Double left = stack.pop();
        	   switch(s)
        	   {
	        	   case "+" ->{
	        		   stack.push(left + right);
	        	   }
	        	   case "-" ->{
	        		   stack.push(left - right);
	        	   }
	        	   case "*" ->{
	        		   stack.push(left * right);
	        	   }
	        	   case "/" ->{
	        		   stack.push(left / right);
	        	   }
        	   }
           }
        }
        return stack.pop();
    }
}