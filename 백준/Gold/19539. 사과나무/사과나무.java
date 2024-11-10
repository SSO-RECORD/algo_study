import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim()); // 사과나무의 개수
		int[] tree = new int[N];

		st = new StringTokenizer(br.readLine());
		int even = 0;
		int odd = 0;
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			even += tree[i] / 2;
			odd += tree[i] % 2;
		}

		while (even > odd) {
			even--;
			odd += 2;
		}

		if (even == odd)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
