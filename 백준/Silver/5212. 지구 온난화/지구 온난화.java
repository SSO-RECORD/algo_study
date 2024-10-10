import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행의 수
		C = Integer.parseInt(st.nextToken()); // 열의 수

		map = new char[R][C];

		for (int row = 0; row < R; row++) {
			String str = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = str.charAt(col);
			}
		}

		char[][] newMap = new char[R][C];
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] == '.') {
					newMap[row][col] = '.';
				}
				if (map[row][col] == 'X') {
					int adjCnt = fourDetection(row, col);
					if (adjCnt >= 3) {
						newMap[row][col] = '.';
					} else {
						newMap[row][col] = 'X';
					}
				}
			}
		}

		// 최소/최대 행과 열 찾기
		int minRow = R, maxRow = 0, minCol = C, maxCol = 0;
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (newMap[row][col] == 'X') {
					minRow = Math.min(minRow, row);
					maxRow = Math.max(maxRow, row);
					minCol = Math.min(minCol, col);
					maxCol = Math.max(maxCol, col);
				}
			}
		}

		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				System.out.print(newMap[row][col]);
			}
			System.out.println();
		}
	}

	private static int fourDetection(int r, int c) {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int cnt = 0;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '.') {
				cnt += 1;
			}
		}
		return cnt;

	}

}
