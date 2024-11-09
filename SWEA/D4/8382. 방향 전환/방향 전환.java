import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA8382_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int rowDiff = Math.abs(x1 - x2);
			int colDiff = Math.abs(y1 - y2);
			int rowcolDiff = Math.abs(rowDiff - colDiff);

			int ans = Math.min(rowDiff, colDiff) * 2;

			if (rowcolDiff % 2 == 0) {
				ans += rowcolDiff * 2;
			} else {
				ans += rowcolDiff * 2 - 1;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}

}
