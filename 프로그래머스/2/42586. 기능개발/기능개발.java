import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Stack<Integer> stack = new Stack<>();
        int cnt = 1;
        ArrayList<Integer> list = new ArrayList<>();
        int work = 0;
        
        for(int i=0; i<progresses.length; i++){
            int quo = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0){
                work = quo + 1;
            } else{
                work = quo;
            }
            list.add(work);
        }
        System.out.println(list);
    
        ArrayList<Integer> list2 = new ArrayList<>();
        
        for(int i=0; i<list.size(); i++){
            
            if(stack.isEmpty()){
                stack.push(list.get(i));
            } else{
                if(stack.get(0) >= list.get(i)){
                    stack.push(list.get(i));
                } else{
                    list2.add(stack.size());
                    stack.clear();
                    stack.push(list.get(i));
                }
            }
        }
        list2.add(stack.size());
        
        int[] array = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            array[i] = list2.get(i).intValue();
        }
        return array;
    }
}