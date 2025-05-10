import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {0, 0, 0};
        //return answer;
        
        int one = 0;
        int two = 0;
        int three = 0;
        int num = 1;
        
        int var1 = 0;
        int var2 = 0;
        int var3 = 0;
        
        int[] arr = {1, 2, 3, 4 ,5};
        for(int i=0; i<answers.length; i++){
            for(int j=var1; j<arr.length; j++){
                if(answers[i] == arr[j]){
                    one += 1;
                }
                var1++;
                if(var1 == 5) var1 = 0;
                break;
            }
        }
        // System.out.println(one);
        answer[0] = one;
        
        int[] arr3 = {1, 3, 4, 5};
        for(int i=0; i<answers.length; i++){
            if(i%2 == 0 && answers[i] == 2){
                two += 1;
            }
            if(i%2 != 0){
                for(int j=var2; j<arr3.length; j++){
                    if(answers[i] == arr3[j]){
                        two += 1;
                    }
                    var2++;
                    if(var2 == 4) var2 = 0;
                    break;
                }
            }
        }
        // System.out.println(two);
        answer[1] = two;
        
        int[] arr2 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for(int i=0; i<answers.length; i++){
            for(int j=var3; j<arr2.length; j++){
                if(answers[i] == arr2[j]){
                    three += 1;
                }
                var3++;
                if(var3 == 10) var3 = 0;
                break;
            }
        }
        // System.out.println(three);
        answer[2] = three; 
        
        int max = 0;
        for(int i=0; i<=2; i++){
            if(answer[i] >= max){
                max = answer[i];
            }
        }
        
        List<Integer> person = new ArrayList<>();
        for(int i=0; i<=2; i++){
            if(answer[i] == max){
                person.add(i+1);
            }
        }
        
        int[] result = new int[person.size()];
        for(int i=0; i<result.length; i++){
            result[i] = person.get(i);
        }
        
        return result;
    }
}