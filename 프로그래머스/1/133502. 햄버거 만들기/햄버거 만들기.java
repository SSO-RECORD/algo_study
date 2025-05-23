import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int value : ingredient) {
            stack.push(value);
            
            if (stack.size() >= 4) {
                int size = stack.size();
                if (stack.get(size - 4) == 1 &&
                    stack.get(size - 3) == 2 &&
                    stack.get(size - 2) == 3 &&
                    stack.get(size - 1) == 1) {

                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    count++;
                }
            }
        }
        
        return count;
    }
}