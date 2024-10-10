import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, cnt;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int x;

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

		int picCnt = 0;
		int maxCnt = Integer.MIN_VALUE;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (arr[row][col] == 1 && !visited[row][col]) {
					//cnt = 0;
					x = 1;
					dfs(row, col);
					picCnt++;
					//System.out.println("cnt : " + x);
					//System.out.println("maxCnt : " + maxCnt);
					maxCnt = Math.max(maxCnt, x);
				}
			}
		}
		
		if(picCnt == 0) {
			maxCnt = 0;
		}

//		for (int row = 0; row < n; row++) {
//			for (int col = 0; col < m; col++) {
//				System.out.print(arr[row][col] + " ");
//			}
//			System.out.println();
//		}
	

	System.out.println(picCnt);
	System.out.println(maxCnt);

//	int maxValue = Integer.MIN_VALUE;if(picCnt==0)
//	{
//		maxValue = 0;
//	}else
//	{
//		for (int row = 0; row < n; row++) {
//			for (int col = 0; col < m; col++) {
//				if (arr[row][col] > maxValue) {
//					maxValue = arr[row][col];
//				}
//			}
//		}
//	}

//	System.out.println(maxValue);
	}

	private static void dfs(int r, int c) {
		
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 1) {
//				cnt += 1;
				x += 1;
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
