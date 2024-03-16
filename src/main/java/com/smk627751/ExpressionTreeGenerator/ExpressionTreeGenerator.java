package com.smk627751.ExpressionTreeGenerator;

public class ExpressionTreeGenerator {
    private String getLowOperator(String exp)
    {
        if(exp.contains("+") || exp.contains("-"))
        {
            return "+-";
        }
        else if(exp.contains("*") || exp.contains("/"))
        {
            return "*/";
        }
        return null;
    }
    Node<String> generate(String exp)
    {
        String operator = getLowOperator(exp);
        if(operator == null)
        {
            return new Node<>(exp);
        }
        int index = 0;
        for(int i = 0; i < exp.length(); i++)
        {
            if(operator.contains(exp.charAt(i) + ""))
            {
                index = i;
            }
        }
        Node<String> expTree = new Node<>(String.valueOf(exp.charAt(index)));
        expTree.left = generate(exp.substring(0,index));
        expTree.right = generate(exp.substring(index + 1));
        return expTree;
    }
    double inorder(Node<String> exp)
    {
        if(exp.left == null && exp.right == null)
        {
            try {
				return Double.parseDouble(exp.root);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
        }
        double result = 0;
        switch (exp.root)
        {
            case "+" ->{
                result = inorder(exp.left) + inorder(exp.right);
            }
            case "-" ->{
                result = inorder(exp.left) - inorder(exp.right);
            }
            case "*" ->{
                result = inorder(exp.left) * inorder(exp.right);
            }
            case "/" ->{
                result = inorder(exp.left) / inorder(exp.right);
            }
        }
        return result;
    }
}