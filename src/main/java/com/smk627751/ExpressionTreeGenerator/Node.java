package com.smk627751.ExpressionTreeGenerator;

public class Node <T>
{
    public T root;
    public Node<T> left;
    public Node<T> right;
    Node(T root)
    {
        this.root = root;
    }
}
