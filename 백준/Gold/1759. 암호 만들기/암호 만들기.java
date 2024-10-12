import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, C;
	static char[] charArr;
	static char[] cipher;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 암호의 길이
		C = Integer.parseInt(st.nextToken()); // 문자의 종류

		charArr = new char[C];
		cipher = new char[L];

		st = new StringTokenizer(br.readLine());
		for (int cnt = 0; cnt < C; cnt++) {
			charArr[cnt] = st.nextToken().charAt(0);
		}

		Arrays.sort(charArr);

		comb(0, 0, C, L);

	}

	private static void comb(int idx, int start, int n, int r) {

		// 기저조건
		if (idx == r) {
			int jaeum = 0, moeum = 0;
			for (int i = 0; i < cipher.length; i++) {
				if (cipher[i] == 'a' || cipher[i] == 'e' || cipher[i] == 'i' || cipher[i] == 'o' || cipher[i] == 'u') {
					jaeum++;
				} else {
					moeum++;
				}
			}

			if (jaeum >= 1 && moeum >= 2) {
				for (int i = 0; i < cipher.length; i++) {
					System.out.print(cipher[i]);
				}
				System.out.println();
			}

			return;
		}

		// 유도파트
		for (int i = start; i < n; i++) {
			cipher[idx] = charArr[i];
			comb(idx + 1, i + 1, n, r);
		}
	}

}
