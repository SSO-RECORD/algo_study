import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, X, M;
	static int[][] record;
	static int[] hamster;
	static int maxHamster;
	static int[] result;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA8275_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 햄스터 우리의 개수
			X = Integer.parseInt(st.nextToken()); // 0<=햄스터 수<=X
			M = Integer.parseInt(st.nextToken()); // 기록의 수

			record = new int[M][3];
			hamster = new int[N];
			result = new int[N];
			maxHamster = Integer.MIN_VALUE;

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				record[m][0] = Integer.parseInt(st.nextToken()) - 1; // 시작 우리
				record[m][1] = Integer.parseInt(st.nextToken()) - 1; // 끝 우리
				record[m][2] = Integer.parseInt(st.nextToken()); // l~r번 우리까지의 햄스터 수 합

			}

			dfs(0);

			sb.append("#").append(tc).append(" ");
			if (maxHamster >= 0) {
				for (int i = 0; i < result.length; i++) {
					sb.append(result[i]).append(" ");
				}
				sb.append("\n");
			} else {
				sb.append(-1).append("\n");
			}
		}
		System.out.print(sb);

	}

	private static void dfs(int idx) {

		// 기저조건
		if (idx == N) {
			for (int i = 0; i < M; i++) {
				int l = record[i][0];
				int r = record[i][1];
				int s = record[i][2];

				int sum = 0;
				for (int range = l; range <= r; range++) {
					sum += hamster[range];
				}

				if (sum != s)
					return;
			}

			int totalHamster = 0;
			for (int i = 0; i < hamster.length; i++) {
				totalHamster += hamster[i];
			}
			if (totalHamster > maxHamster) {
				maxHamster = totalHamster;
				result = Arrays.copyOf(hamster, N);
			}
			return;
		}

		// 유도파트
		for (int i = 0; i <= X; i++) {
			hamster[idx] = i;
			dfs(idx + 1);
		}

	}

}