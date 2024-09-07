import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] pool;
	static boolean[][] visited;
	static int[] start;
	static int[] end;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA4193_sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 수영장의 크기 NxN

			// 수영장 정보 입력
			pool = new int[N][N];
			visited = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					pool[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작점
			start = new int[2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				start[i] = Integer.parseInt(st.nextToken());
			}

			// 도착점
			end = new int[2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				end[i] = Integer.parseInt(st.nextToken());
			}

			sb.append("#" + tc + " " + bfs(start, end) + "\n");

		}
		System.out.print(sb);
	}

	private static int bfs(int[] start, int[] end) {

		Queue<int[]> queue = new LinkedList<>();
		visited[start[0]][start[1]] = true;
		queue.add(new int[] { start[0], start[1], 0 });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int time = cur[2];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (nr == end[0] && nc == end[1]) {
					return time + 1;
				}

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if (pool[nr][nc] == 0) {
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc, time + 1 });
					} else if (pool[nr][nc] == 1)
						continue;
					else if (pool[nr][nc] == 2) {
						if (time % 3 == 0) {
							queue.add(new int[] { cr, cc, time + 1 });
						}
						if (time % 3 == 1) {
							queue.add(new int[] { cr, cc, time + 1 });
						}
						if (time % 3 == 2) {
							queue.add(new int[] { nr, nc, time + 1 });
							visited[nr][nc] = true;
						}

					}
				}
			}
		}
		return -1;
	}
}
