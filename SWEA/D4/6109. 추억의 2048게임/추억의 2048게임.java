import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] grid;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());// 격자의 크기
			String S = st.nextToken(); // 이동 방향

			grid = new int[N][N];
			visited = new boolean[N][N];
			// 배열에 정보 입력
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					grid[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			// 방향에 따라 타일 이동시키기
			if (S.equals("up")) {
				moveUp();
			} else if (S.equals("down")) {
				moveDown();
			} else if (S.equals("left")) {
				moveLeft();
			} else if (S.equals("right")) {
				moveRight();
			}

			sb.append("#" + tc + "\n");
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					sb.append(grid[row][col] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

	private static void moveRight() {
		boolean moved = true;

		visited = new boolean[N][N];

		while (moved) {
			moved = false;

			for (int row = 0; row < N; row++) {
				for (int col = N - 1; col > 0; col--) {
					if (grid[row][col] == 0 && grid[row][col - 1] != 0) {
						grid[row][col] = grid[row][col - 1];
						grid[row][col - 1] = 0;
						// 밀린 후 원래 자리는 더 이상 병합 상태가 아니므로 visited 해제
						visited[row][col - 1] = false;
						moved = true;
					}
					if (grid[row][col] != 0 && grid[row][col] == grid[row][col - 1] && !visited[row][col]
							&& !visited[row][col - 1]) {
						grid[row][col] = grid[row][col] * 2;
						grid[row][col - 1] = 0;
						visited[row][col] = true;
						moved = true;
					}
				}
			}
		}

	}

	private static void moveLeft() {
		boolean moved = true;

		visited = new boolean[N][N];

		while (moved) {
			moved = false;

			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N - 1; col++) {
					if (grid[row][col] == 0 && grid[row][col + 1] != 0) {
						grid[row][col] = grid[row][col + 1];
						grid[row][col + 1] = 0;
						// 밀린 후 원래 자리는 더 이상 병합 상태가 아니므로 visited 해제
						visited[row][col + 1] = false;
						moved = true;
					}
					if (grid[row][col] != 0 && grid[row][col] == grid[row][col + 1] && !visited[row][col]
							&& !visited[row][col + 1]) {
						grid[row][col] = grid[row][col] * 2;
						grid[row][col + 1] = 0;
						visited[row][col] = true;
						moved = true;
					}
				}
			}
		}
	}

	private static void moveDown() {
		boolean moved = true;

		visited = new boolean[N][N];

		while (moved) {
			moved = false;

			for (int row = N - 1; row > 0; row--) {
				for (int col = 0; col < N; col++) {
					if (grid[row][col] == 0 && grid[row - 1][col] != 0) {
						grid[row][col] = grid[row - 1][col];
						grid[row - 1][col] = 0;
						// 밀린 후 원래 자리는 더 이상 병합 상태가 아니므로 visited 해제
						visited[row - 1][col] = false;
						moved = true;
					}
					if (grid[row][col] != 0 && grid[row][col] == grid[row - 1][col] && !visited[row][col]
							&& !visited[row - 1][col]) {
						grid[row][col] = grid[row][col] * 2;
						grid[row - 1][col] = 0;
						visited[row][col] = true;
						moved = true;
					}
				}
			}
		}
	}

	private static void moveUp() {
		boolean moved = true;

		visited = new boolean[N][N];

		while (moved) {
			moved = false;

			for (int row = 0; row < N - 1; row++) {
				for (int col = 0; col < N; col++) {
					if (grid[row][col] == 0 && grid[row + 1][col] != 0) {
						grid[row][col] = grid[row + 1][col];
						grid[row + 1][col] = 0;
						// 밀린 후 원래 자리는 더 이상 병합 상태가 아니므로 visited 해제
						visited[row + 1][col] = false;
						moved = true;
					}
					if (grid[row][col] != 0 && grid[row][col] == grid[row + 1][col] && !visited[row][col]
							&& !visited[row + 1][col]) {
						grid[row][col] = grid[row][col] * 2;
						grid[row + 1][col] = 0;
						visited[row][col] = true;
						moved = true;
					}
				}
			}
		}
	}
}
