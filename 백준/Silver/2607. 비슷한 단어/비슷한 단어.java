import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int num = Integer.parseInt(st.nextToken());
		int diffWordCnt = 0;

		String word = br.readLine();
		int[] wordArr = new int[26];
		alpaCheck(word, wordArr);

		for (int i = 0; i < num - 1; i++) {
			String similar = br.readLine();
			int[] similarArr = new int[26];
			alpaCheck(similar, similarArr);

//			System.out.println(compareArr(wordArr, similarArr));
			diffWordCnt += compareArr(wordArr, similarArr);

		}

		System.out.println((num - 1) - diffWordCnt);

	}

	private static void alpaCheck(String word, int[] arr) {
		for (int i = 0; i < word.length(); i++) {
			arr[word.charAt(i) - 65] += 1;
		}
	}

	private static int compareArr(int[] wordArr, int[] similarArr) {
		int cnt = 0;
		int diffOnePlus = 0;
		int diffOneMinus = 0;

		for (int i = 0; i < 26; i++) {
			if (wordArr[i] == similarArr[i])
				continue;
			if (Math.abs(wordArr[i] - similarArr[i]) >= 2)
				cnt++;
			if (wordArr[i] - similarArr[i] == 1)
				diffOnePlus++;
			if (wordArr[i] - similarArr[i] == -1)
				diffOneMinus++;
		}

		if (cnt > 0 || diffOnePlus >= 2 || diffOneMinus >= 2) {
			return 1;
		}
		return 0;
	}

}
