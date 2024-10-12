import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int H, W, minBricks;
	static int[][] arr;
	static int[] selectedCols;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA5656_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 개수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 구슬 쏘는 횟수
			W = Integer.parseInt(st.nextToken()); // 행의 수
			H = Integer.parseInt(st.nextToken()); // 열의 수

			arr = new int[H][W];
			selectedCols = new int[N];
			for (int row = 0; row < H; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < W; col++) {
					arr[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			minBricks = Integer.MAX_VALUE;
			perm(0, W, N);

			sb.append("#").append(tc).append(" ").append(minBricks).append("\n");
//			System.out.println("minBricks : " + minBricks);
//			
//			for(int row=0; row<H; row++) {
//				for(int col=0; col<W; col++) {
//					System.out.print(arr[row][col] + " ");
//				}
//				System.out.println();
//			}
		}
		System.out.print(sb);
	}

	// 구슬을 떨어뜨릴 열을 선택하는 메서드
	private static void perm(int idx, int w, int n) {
		// 기저조건
		if (idx == n) {
//			System.out.print("선택된 열 : ");
//			for(int i=0; i<selectedCols.length; i++) {
//				System.out.print(selectedCols[i] + " ");
//			}
//			System.out.println();

			int[][] tempArr = copyArray(arr);

			for (int col : selectedCols) {
				dropBall(col, tempArr);
			}

			// 남은 벽돌 수 계산
			int remainBricks = countRemainBricks(tempArr);
			minBricks = Math.min(remainBricks, minBricks);
			return;
		}

		// 유도파트(중복순열 : 같은 열을 여러번 선택할 수 있고, 열에 구슬을 떨어뜨리는 순서가 유의미)
		for (int i = 0; i < w; i++) {
			selectedCols[idx] = i;
			perm(idx + 1, w, n);
		}
	}

	private static int countRemainBricks(int[][] arr) {
		int count = 0;
		for (int row = 0; row < H; row++) {
			for (int col = 0; col < W; col++) {
				if (arr[row][col] > 0) {
					count++;
				}
			}
		}
		return count;
	}

	// 기존의 배열 복사 매서드(순열마다 독립적으로 처리 위해)
	private static int[][] copyArray(int[][] arr) {
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}

	// 열에 구슬을 떨어뜨리는 메서드
	private static void dropBall(int col, int[][] arr) {

		for (int row = 0; row < H; row++) {
			// 떨어뜨릴 위치가 배열 내 범위에 있는지 확인
			if (!isIn(row, col))
				continue;
			// 떨어뜨릴 열의 값이 0인 경우
			if (arr[row][col] == 0)
				continue;
			// 떨어뜨릴 열의 값이 0이 아닌 경우
			if (arr[row][col] != 0) {
				breakBricks(row, col, arr);
				// 배열 재배치
				rearrangeBricks(arr);
				break;
			}
		}

	}

	// 빈공간이 있을때 벽돌을 밑으로 떨어지게 하는 메서드
	private static void rearrangeBricks(int[][] arr) {
		for (int col = 0; col < W; col++) {
			for (int row = H - 1; row > 0; row--) {
				if (arr[row][col] == 0) { // 빈 공간을 찾으면
					int nr = row - 1;
					while (nr >= 0 && arr[nr][col] == 0) {
						nr--; // 윗쪽에서 벽돌 찾기
					}
					if (nr >= 0) { // 벽돌을 찾았으면 교체
						arr[row][col] = arr[nr][col];
						arr[nr][col] = 0;
					}
				}
			}
		}

	}

	// 벽돌을 부수는 메서드
	private static void breakBricks(int row, int col, int[][] arr) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { row, col });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			int brickValue = arr[cr][cc]; // 현재 벽돌의 값

			// 이미 0이 된 벽돌은 무시
			if (brickValue == 0)
				continue;

			arr[cr][cc] = 0; // 현재 벽돌 제거

			// 벽돌 값이 1보다 크면 그 벽돌이 부술 수 있는 범위 탐색
			if (brickValue > 1) {
				for (int d = 0; d < 4; d++) {
					for (int step = 1; step < brickValue; step++) {
						int nr = cr + dr[d] * step;
						int nc = cc + dc[d] * step;

						if (isIn(nr, nc) && arr[nr][nc] > 0) {
							queue.add(new int[] { nr, nc }); // 부술 수 있는 벽돌을 큐에 추가
						}
					}
				}
			}
		}

	}

	// 범위 내에 있는지 체크하는 메서드
	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < H && c >= 0 && c < W)
			return true;
		return false;
	}

}
