import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int L, R, C;
	static char[][][] building;
	static boolean[][][] visited;
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());

			L = Integer.parseInt(st.nextToken()); // 상범빌딩의 층 수
			R = Integer.parseInt(st.nextToken()); // 행의 수
			C = Integer.parseInt(st.nextToken()); // 열의 수

			if (L == 0 && R == 0 && C == 0)
				break;

			building = new char[R][C][L];
			visited = new boolean[R][C][L];

			int startR = 0, startC = 0, startH = 0;
			for (int height = 0; height < L; height++) {
				for (int row = 0; row < R; row++) {
					String str = br.readLine().trim();
					if (str.isEmpty()) { // 빈 줄이 들어온 경우 예외 처리
						row--;
						continue;
					}
					for (int col = 0; col < C; col++) {
						building[row][col][height] = str.charAt(col);

						if (building[row][col][height] == 'S') {
							startR = row;
							startC = col;
							startH = height;
						}
					}
				}
				br.readLine(); // 각 층의 입력이 끝나면 빈 줄을 처리 (빈 줄이 있으면)
			}

			int result = bfs(startR, startC, startH, 0);
			if (result == -1) {
				sb.append("Trapped!").append("\n");
			} else {
				sb.append("Escaped in ").append(result).append(" minute(s).").append("\n");
			}
		}
		System.out.print(sb);
	}

	private static int bfs(int r, int c, int h, int t) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c, h, t });
		visited[r][c][h] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ch = cur[2];
			int ct = cur[3];

			if (building[cr][cc][ch] == 'E') {
				// System.out.println("ct : " + ct);
				return ct;
			}
			for (int d = 0; d < 6; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int nh = ch + dh[d];

				if (isIn(nr, nc, nh) && !visited[nr][nc][nh]
						&& (building[nr][nc][nh] == '.' || building[nr][nc][nh] == 'E')) {
					queue.add(new int[] { nr, nc, nh, ct + 1 });
					visited[nr][nc][nh] = true;
				}
			}
		}

		return -1;

	}

	private static boolean isIn(int r, int c, int h) {
		if (r >= 0 && r < R && c >= 0 && c < C && h >= 0 && h < L)
			return true;
		return false;
	}

}