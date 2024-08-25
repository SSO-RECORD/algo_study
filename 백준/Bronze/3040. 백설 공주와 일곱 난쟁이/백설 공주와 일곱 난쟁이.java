import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int[] nineArr;
	private static int sum;
	private static int[] sevenArr;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		nineArr = new int[9];
		sevenArr = new int[7];
		for (int i = 0; i < 9; i++) {
			nineArr[i] = Integer.parseInt(br.readLine());
		}
		sum = 0;
		comb(0, 0);
		System.out.print(sb);
	}

	private static void comb(int idx, int start) {
		// 기저조건
		if (idx == 7) {
			sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += sevenArr[i];
			}
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					sb.append(sevenArr[i]).append("\n");
				}
			}
			return;
		}

		// 유도파트
		for (int i = start; i < 9; i++) {
			sevenArr[idx] = nineArr[i];
			comb(idx + 1, i + 1);
		}
	}
}
