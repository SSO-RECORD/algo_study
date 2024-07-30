import java.util.Stack;

class Solution {
    boolean solution(String s) {
        if((s.charAt(0)==')') || (s.length() % 2 != 0)){
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='('){
                stack.push('(');
            }else if((s.charAt(i)==')') && (stack.size()==0)){
                return false;
            }else{
                stack.pop();
            }
        }
        if (stack.size() == 0){
            return true;
        } else{
            return false;
        }
    }
}