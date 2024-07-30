import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        int work = 0;
        // 작업이 완료될 때까지 걸리는 시간을 list에 저장
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
            // 1. 스택이 비어있으면 무조건 push
            if(stack.isEmpty()){
                stack.push(list.get(i));
            } else{ // 2. 그렇지 않으면 스택의 0번째 요소와 비교해가며 스택의 size를 측정
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
        
        // 리스트에 저장된 결과값을 배열로 변환하여 리턴
        int[] array = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            array[i] = list2.get(i).intValue();
        }
        return array;
    }
}