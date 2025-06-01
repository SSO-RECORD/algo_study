import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        Map<String, Integer> playersMap = new HashMap<>();
        for(int i=0; i<players.length; i++) {
            playersMap.put(players[i], i);
        }
        
        for(int i=0; i<callings.length; i++){
            String name = callings[i];
            int index = playersMap.get(name);
            String temp = players[index-1];
            players[index-1] = name;
            players[index] = temp;
            playersMap.put(name, index-1);
            playersMap.put(temp, index);
        }
        
        return players;
    }
}