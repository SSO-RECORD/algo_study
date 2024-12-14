import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String word = br.readLine().trim();
		word = word.toUpperCase();

		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < word.length(); i++) {
			map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0) + 1);
		}

		int maxCnt = 0;
		int max = Collections.max(map.values());
		char alpa = ' ';

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				maxCnt++;
				alpa = entry.getKey();
			}
		}

		if (maxCnt >= 2)
			System.out.println("?");
		else
			System.out.println(alpa);

	}

}
