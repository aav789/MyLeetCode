package Stacks;

import java.util.Stack;

public class LeetCode_20_ValidParenthesis {
    public boolean isValid(String s) {
        if(s.length()%2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '{' || c == '[' || c =='(') stack.push(c);
            else if(!stack.isEmpty() && c == ')' && stack.peek() == '(') stack.pop();
            else if(!stack.isEmpty() && c == '}' && stack.peek() == '{') stack.pop();
            else if(!stack.isEmpty() && c == ']' && stack.peek() == '[') stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }


}
