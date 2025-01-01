import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim()); // 상근이가 가지고 있는 숫자 카드의 개수

		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> number = new HashMap<>();
		for (int i = 0; i < N; i++) {
			number.put(Integer.parseInt(st.nextToken()), 1);
		}

		int M = Integer.parseInt(br.readLine().trim());
		int[] result = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (number.containsKey(num))
				result[i] = 1;
			else
				result[i] = 0;
		}

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

}
