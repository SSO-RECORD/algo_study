import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, H;
	static int[][][] arr;
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 수
		N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 수
		H = Integer.parseInt(st.nextToken()); // 쌓아올려지는 상자의 수

		arr = new int[N][M][H];

		Queue<int[]> queue = new LinkedList<>();
		int unripeTomatoes = 0;
		for (int height = 0; height < H; height++) {
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < M; col++) {
					arr[row][col][height] = Integer.parseInt(st.nextToken());

					if (arr[row][col][height] == 1) { // 익은 토마토가 있는 곳의 좌표를 모두 큐에 저장
						queue.add(new int[] { row, col, height, 0 });
					}

					if (arr[row][col][height] == 0) { // 익지 않은 토마토의 개수 세기
						unripeTomatoes++;
					}
				}
			}
		}

		// 익지 않은 토마토의 개수가 0이라면
		if (unripeTomatoes == 0) {
			System.out.println(0);
			return;
		}

		int result = bfs(queue, unripeTomatoes);

		System.out.println(result);

	}

	private static int bfs(Queue<int[]> queue, int unripeTomatoes) {
		int days = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ch = cur[2];
			int cd = cur[3];

			days = cd;

			for (int d = 0; d < 6; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int nh = ch + dh[d];

				if (isIn(nr, nc, nh) && arr[nr][nc][nh] == 0) {
					queue.add(new int[] { nr, nc, nh, cd + 1 });
					arr[nr][nc][nh] = 1;
					unripeTomatoes--;
				}
			}
		}

		return (unripeTomatoes == 0) ? days : -1;

	}

	private static boolean isIn(int r, int c, int h) {
		if (r >= 0 && r < N && c >= 0 && c < M && h >= 0 && h < H)
			return true;
		return false;
	}

}