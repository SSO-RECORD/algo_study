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

		int N = Integer.parseInt(br.readLine()); // 입력받을 단어의 개수

		List<String> words = new ArrayList<>();
		// N만큼 단어를 입력받음 -> 리스트에 집어넣음
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			words.add(str);
		}

		wordsWeight(words);
		int result = calculateWords(words);
		System.out.println(result);
	}

	// 단어에 포함된 문자의 가중치 계산하기
	private static void wordsWeight(List<String> words) {
		Map<Character, Integer> weightMap = new HashMap<>();
		for (String word : words) { // 각각의 단어에 접근
			int len = word.length();
			for (int i = 0; i < len; i++) {
				char alpa = word.charAt(i); // 단어의 각각의 문자에 접근
				int weight = (int) Math.pow(10, len - i - 1); // 가중치 계산
				weightMap.put(alpa, weightMap.getOrDefault(alpa, 0) + weight);
			}
		}

		PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> weightMap.get(b) - weightMap.get(a));
		pq.addAll(weightMap.keySet());

		map = new HashMap<>();
		int num = 9;
		while (!pq.isEmpty()) {
			char alpa = pq.poll();
			map.put(alpa, num);
			num--;
		}
	}

	// 단어의 문자를 숫자로 치환하여 더하기
	private static int calculateWords(List<String> words) {
		int sum = 0;

		for (String word : words) {
			int wordValue = 0;
			for (int i = 0; i < word.length(); i++) {
				char alpa = word.charAt(i);
				wordValue = wordValue * 10 + map.get(alpa);
			}
			sum += wordValue;
		}
		return sum;
	}
}
