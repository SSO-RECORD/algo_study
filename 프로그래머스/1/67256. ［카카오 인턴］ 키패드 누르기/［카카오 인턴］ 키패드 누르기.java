import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        String result = "";
        
        // 1, 4, 7 이라면 왼손으로 클릭
        // 3, 6, 9 라면 오른손으로 클릭
        // 2, 5, 8, 0이라면 
        int[] left = new int[2];
        int[] right = new int[2];
        
        left[0] = 3;
        left[1] = 0;
        right[0] = 3;
        right[1] = 2;
        
        for (int i=0; i<numbers.length; i++) {
            int num = numbers[i];
            
            if (num == 1 || num == 4 || num == 7) {
                answer += "L";
                left[0] = num / 3;
                left[1] = 0;
                // System.out.println("left : " + left[0] + "," + left[1]);
            } else if (num == 3 || num == 6 || num == 9) {
                answer += "R";
                right[0] = (num / 3) - 1;
                right[1] = 2;
                // System.out.println("right : " + right[0] + "," + right[1]);
            } else {
                int x = 0;
                int y = 0;
        
                if (num == 0) {
                    x = 3;
                    y = 1;
                } else {
                    x = num / 3;
                    y = 1;
                }
                // System.out.println("x : " + x + " y : " + y);
                result = distance(left, right, x, y, hand);
                if (result.equals("R")) {
                    // System.out.println("여기!");
                    right[0] = x;
                    right[1] = y;
                    // System.out.println("right : " + right[0] + "," + right[1]);
                } else {
                    left[0] = x;
                    left[1] = y; 
                    // System.out.println("left : " + left[0] + "," + left[1]);
                }
                // System.out.println("result : " + result);
                answer += result;
            }
        }
        return answer;
    }
    
    // 거리를 구하는 메서드
    public String distance(int[] left, int[] right, int x, int y, String hand) {
        int leftDiff = Math.abs(left[0] - x) + Math.abs(left[1] - y);
        int rightDiff = Math.abs(right[0] - x) + Math.abs(right[1] - y);
        
        // System.out.println("leftDiff : " + leftDiff + " rightDiff: " + rightDiff);
        if (leftDiff > rightDiff) {
            return "R";
        } else if (leftDiff < rightDiff) {
            return "L";
        } else {
            // System.out.println("hand : " + hand.equals("right"));
            if (hand.equals("right")) return "R";
            else return "L";
        }
    }
}