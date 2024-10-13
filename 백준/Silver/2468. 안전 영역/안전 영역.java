import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim()); // 배열의 크기

		arr = new int[N][N];

		int maxHeight = 0;
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
				if (arr[row][col] >= maxHeight) {
					maxHeight = arr[row][col];
				}
			}
		}

		int maxCnt = Integer.MIN_VALUE;
		for (int height = 0; height <= maxHeight; height++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (arr[row][col] > height && !visited[row][col]) {
						bfs(row, col, height);
						cnt++;
						// System.out.println("cnt : " + cnt);
					}
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}

		System.out.println(maxCnt);

//		if(maxCnt == 0) {
//			System.out.println(1);
//		}else {
//			System.out.println(maxCnt);
//		}

//		for(int row=0; row<N; row++) {
//			for(int col=0; col<N; col++) {
//				for(int height=1; height<=maxHeight; height++) {
//					bfs(row, col, height);
//				}
//			}
//		}

	}

	private static void bfs(int r, int c, int h) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] > h) {
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
		}

	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

}
