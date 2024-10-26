import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int N;
	static int[][] map;
	static int[][] minTimeArr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1249_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 지도의 크기

			map = new int[N][N];
			minTimeArr = new int[N][N];

			for (int row = 0; row < N; row++) {
				String str = br.readLine();
				for (int col = 0; col < N; col++) {
					map[row][col] = str.charAt(col) - '0';
					minTimeArr[row][col] = Integer.MAX_VALUE; // 초기화를 큰 값으로 설정
				}
			}

			bfs(0, 0);
			sb.append("#").append(t).append(" ").append(minTimeArr[N - 1][N - 1]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sr, sc });
		minTimeArr[sr][sc] = 0; // 시작 좌표의 작업시간은 0

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (isIn(nr, nc) && minTimeArr[nr][nc] > minTimeArr[cr][cc] + map[nr][nc]) {
					minTimeArr[nr][nc] = minTimeArr[cr][cc] + map[nr][nc];
					queue.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}

}
