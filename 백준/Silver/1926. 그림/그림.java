import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, cnt;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 행의 수
		m = Integer.parseInt(st.nextToken()); // 열의 수

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < m; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int picCnt = 0; // 그림의 개수를 저장할 변수
		int maxCnt = Integer.MIN_VALUE;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (arr[row][col] == 1 && !visited[row][col]) {
					cnt = 1; // 그림의 넓이를 저장할 변수
					dfs(row, col);
					picCnt++;
					maxCnt = Math.max(maxCnt, cnt);
				}
			}
		}

		if (picCnt == 0) {
			maxCnt = 0;
		}

		System.out.println(picCnt);
		System.out.println(maxCnt);

	}

	private static void dfs(int r, int c) {

		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 1) {
				cnt += 1;
				dfs(nr, nc);
			}
		}

	}

	private static boolean isIn(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < m)
			return true;
		return false;
	}

}
