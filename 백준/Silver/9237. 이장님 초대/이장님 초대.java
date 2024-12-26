import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim()); // 묘목의 수

		Integer[] tree = new Integer[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(tree, Collections.reverseOrder());

		int max = 0;

		for (int i = 0; i < tree.length; i++) {
			int days = tree[i] + (i + 1);
			if (days > max)
				max = days;
		}

		System.out.println(max + 1);

	}

}
