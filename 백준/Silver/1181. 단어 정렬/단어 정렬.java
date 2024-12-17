import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // 단어의 개수

		List<String> words = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String word = br.readLine().trim();
			words.add(word);
		}

		Collections.sort(words, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.length() != o2.length()) {
					return o1.length() - o2.length();
				} else {
					return o1.compareTo(o2);
				}
			}
		});

		for (int i = 0; i < words.size(); i++) {
			if (i == 0)
				System.out.println(words.get(i));
			else if (words.get(i - 1).equals(words.get(i)))
				continue;
			else
				System.out.println(words.get(i));
		}

	}

}
