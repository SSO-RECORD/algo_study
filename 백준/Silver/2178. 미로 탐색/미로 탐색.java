import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 개수 N 입력받기
		M = Integer.parseInt(st.nextToken()); // 열의 개수 M 입력받기

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = str.charAt(m) - '0';
			}
		}

		cnt = 0;

		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] != 0 && !visited[n][m]) {
					bfs(n, m);
				}
			}
		}
		System.out.println(map[N - 1][M - 1]);
	}

	private static void bfs(int n, int m) {
		Queue<int[]> queue = new LinkedList<>();
		visited[n][m] = true;
		queue.add(new int[] { n, m });

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 0) {
					queue.add(new int[] { nr, nc });
					map[nr][nc] = map[cr][cc] + 1;
					visited[nr][nc] = true;
				}
			}
		}
	}
}
