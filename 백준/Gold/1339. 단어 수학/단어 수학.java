import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
	
	private static Map<Character, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //입력받을 단어의 개수
		
		List<String> words = new ArrayList<>();
		for(int n=0; n<N; n++) {
			String str = br.readLine();
			words.add(str);
		}
		
		stringPlus(words);
		
		int result = calculateWords(words);
		System.out.println(result);
	}
	
	private static int calculateWords(List<String> words) {
		int sum = 0;
		
		for(String word : words) {
			int wordValue = 0;
			for(int i=0; i<word.length(); i++) {
				char alpa = word.charAt(i);
				wordValue = wordValue * 10 + map.get(alpa);
			}
			sum += wordValue;
		}
		return sum;
	}

	public static void stringPlus(List<String> words) {
		Map<Character, Integer> weightMap = new HashMap<>();
		map = new HashMap<>();
		
		// 각 문자에 대한 가중치를 계산
		for(String word : words) {
			int len = word.length();
			for(int i = 0; i < len; i++) {
				char alpa = word.charAt(i);
				int weight = (int) Math.pow(10, len - i - 1);
				weightMap.put(alpa, weightMap.getOrDefault(alpa, 0) + weight);
			}
		}
		
		// 가중치에 따라 문자들을 정렬
		PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> weightMap.get(b) - weightMap.get(a));
		pq.addAll(weightMap.keySet());
		
		int num = 9;
		while(!pq.isEmpty()) {
			char alpa = pq.poll();
			map.put(alpa, num);
			num--;
		}
	}

}
