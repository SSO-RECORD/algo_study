import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Sort implements Comparable<Sort> {

		String word;
		int cnt;

		public Sort(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Sort o) {
			return 0;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 단어의 개수
		int M = Integer.parseInt(st.nextToken()); // 암기할 단어의 최소 길이

		Map<String, Integer> words = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() < M) {
				continue;
			}
			words.put(word, words.getOrDefault(word, 0) + 1);
		}

		List<Sort> sortWords = new ArrayList<>();
		
		for (Map.Entry<String, Integer> entry : words.entrySet()) {
			sortWords.add(new Sort(entry.getKey(), entry.getValue()));
//			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		
        Collections.sort(sortWords, new Comparator<Sort>() {
            @Override
            public int compare(Sort o1, Sort o2) {
                
                if (o1.cnt != o2.cnt) {
                    return o2.cnt - o1.cnt;
                }
                
                if (o1.word.length() != o2.word.length()) {
                    return o2.word.length() - o1.word.length();
                }
                
                return o1.word.compareTo(o2.word);
            }
        });

		for (Sort wordSort : sortWords) {
			sb.append(wordSort.word).append("\n");
//			System.out.println(personSort.word + " " + personSort.cnt);
//			System.out.println(wordSort.word);
		}

		System.out.println(sb);
	}

}
