import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] cave;
	static int[][] minMoneyArr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testCase = 0;

		while (true) {
			testCase++;
			N = Integer.parseInt(br.readLine().trim()); // 동굴의 크기

			if (N == 0)
				break;

			cave = new int[N][N];
			minMoneyArr = new int[N][N];

			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					cave[row][col] = Integer.parseInt(st.nextToken());
					minMoneyArr[row][col] = Integer.MAX_VALUE;
				}
			}

			bfs(0, 0);// 탐색 시작 죄표
			sb.append("Problem ").append(testCase).append(": ").append(minMoneyArr[N - 1][N - 1]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int sr, int sc) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sr, sc });
		minMoneyArr[sr][sc] = cave[sr][sc];

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (inIn(nr, nc) && minMoneyArr[nr][nc] > minMoneyArr[cr][cc] + cave[nr][nc]) {
					minMoneyArr[nr][nc] = minMoneyArr[cr][cc] + cave[nr][nc];
					queue.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean inIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}

}
