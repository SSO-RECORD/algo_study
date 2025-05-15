import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        List<Integer> resultList = new ArrayList<>();
        
        for(int i=0; i<commands.length; i++) {
            int start = commands[i][0];
            int end = commands[i][1];
            int k = commands[i][2];
            
            List<Integer> newArrayList = new ArrayList<>();
            
            for(int j=start-1; j<end; j++) {
                newArrayList.add(array[j]);
            }
            
            Collections.sort(newArrayList);
            resultList.add(newArrayList.get(k-1));
        }
        
        int[] resultArray = new int[resultList.size()];
        for(int i=0; i<resultList.size(); i++){
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}