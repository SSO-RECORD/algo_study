import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] campus;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수

		campus = new char[N][M];
		visited = new boolean[N][M];

		int startR = 0, startC = 0;
		for (int row = 0; row < N; row++) {
			String str = br.readLine();
			for (int col = 0; col < M; col++) {
				campus[row][col] = str.charAt(col);

				if (campus[row][col] == 'I') {
					startR = row;
					startC = col;
				}
			}
		}

		cnt = 0;
		dfs(startR, startC);
		if (cnt == 0) {
			System.out.println("TT");
		} else {
			System.out.println(cnt);
		}

	}

	private static void dfs(int startR, int startC) {

		visited[startR][startC] = true;

		for (int d = 0; d < 4; d++) {
			int nr = startR + dr[d];
			int nc = startC + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && campus[nr][nc] != 'X') {
				if (campus[nr][nc] == 'P')
					cnt++;
				dfs(nr, nc);
			}
		}
	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
