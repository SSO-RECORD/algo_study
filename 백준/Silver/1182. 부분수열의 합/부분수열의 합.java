import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, cnt;
	static int[] arr;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 입력받을 정수의 개수
		S = Integer.parseInt(st.nextToken()); // 합이 S

		arr = new int[N];
		isSelected = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		cnt = 0;
		subset(0, 0, 0);
		System.out.println(cnt);
	}

	private static void subset(int idx, int sum, int eCnt) {
		// 기저조건
		if (idx == N) {
			if (sum == S && eCnt > 0)
				cnt++;
			return;
		}

		// 유도파트

		// 선택
		isSelected[idx] = true;
		subset(idx + 1, sum + arr[idx], eCnt + 1);

		// 비선택
		isSelected[idx] = false;
		subset(idx + 1, sum, eCnt);
	}
}
