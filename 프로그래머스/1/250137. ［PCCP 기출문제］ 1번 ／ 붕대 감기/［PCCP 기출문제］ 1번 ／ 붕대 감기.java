import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxTime = attacks[attacks.length-1][0];
        int maxHealth = health;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<attacks.length; i++) {
            map.put(attacks[i][0], attacks[i][1]);
        }
        
        int continuous = 0;
        for (int time=0; time<=maxTime; time++) {
            if (health <= 0) break;
            // System.out.println("continuous : " + continuous);
            if (map.containsKey(time)){
                // System.out.println("time1 : " + time);
                health -= map.getOrDefault(time, 0);
                // System.out.println("health1 : " + health);
                continuous = 0;
                
            } else {
                // System.out.println("time2 : " + time);
                // 연속성 공격이 0이 아닌 경우
                if (continuous != 0) {
                    // 연속성 공격이 시전 시간인 경우
                    if (continuous == bandage[0]) {
                        health += (bandage[1] + bandage[2]);
                        if (health > maxHealth) {
                            health = maxHealth;
                        }
                        continuous = 0;
                        // System.out.println("health2 : " + health);
                    } else { // 연속성 공격이 시전 시간이 아닌 경우
                        // System.out.println("여기");
                        health += bandage[1];
                        if (health > maxHealth) {
                            health = maxHealth;
                        }
                        // continuous += 1;
                        // System.out.println("health3 : " + health);
                        // System.out.println("continuous : " + continuous);
                    }
                }
                // 연속성 공격이 0인 경우
                // else {
                //     continuous += 1;
                // }
            }
            continuous += 1;
        }
        
        if (health <= 0) health = -1;
        
        return health;
    }
}