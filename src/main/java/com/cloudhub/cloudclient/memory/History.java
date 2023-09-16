package com.cloudhub.cloudclient.memory;

import java.util.Stack;

public class History {
    Stack<Long> stack = new Stack<>();
    private static History instance = new History();
    private History(){}
    public static History getInstance(){
        return instance;
    }
    public void push(Long value){
        stack.push(value);
    }
    public Long pop(){
        return stack.pop();
    }
    public Long peek(){
        return stack.peek();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public void clear(){
        stack.clear();
    }
    public void show(){
        //栈中元素从栈底到栈顶的顺序打印
        for (Long value : stack) {
            System.out.println(value);
        }
    }
}
