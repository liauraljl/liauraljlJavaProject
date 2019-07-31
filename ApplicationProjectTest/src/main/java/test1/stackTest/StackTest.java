package test.stackTest;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        new StackTest().start();
    }

    private void start(){
        Stack<Integer> stack=new Stack<Integer>();
        stack.add(0);
        stack.push(1);
        stack.add(2);
        stack.remove(2);
        stack.push(2);
        stack.push(3);
        //stack.forEach(o->System.out.println(o));


    }
}
