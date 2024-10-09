import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	static int result;
	static char[][] mirro;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1226_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {

			int T = Integer.parseInt(br.readLine()); // 테스트케이스 번호
			mirro = new char[16][16];
			visited = new boolean[16][16];
			int startRow = 0, startCol = 0, endRow = 0, endCol = 0;

			for (int row = 0; row < 16; row++) {
				String str = br.readLine();
				for (int col = 0; col < 16; col++) {
					mirro[row][col] = str.charAt(col);
					if (mirro[row][col] == '2') {// 미로의 시작 지점
						startRow = row;
						startCol = col;
					}
					if (mirro[row][col] == '3') {// 미로의 도착 지점
						endRow = row;
						endCol = col;
					}
				}
			}

			result = 0;
			dfs(startRow, startCol);
			if (result == 1) {
				sb.append("#").append(tc).append(" ").append(result).append("\n");
			} else {
				sb.append("#").append(tc).append(" ").append(result).append("\n");
			}
		}

		System.out.print(sb);

	}

	private static void dfs(int row, int col) {

		visited[row][col] = true;

		if (mirro[row][col] == '3') {
			result = 1;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = row + dx[d];
			int ny = col + dy[d];

			if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && mirro[nx][ny] != '1' && !visited[nx][ny]) {
				dfs(nx, ny);
			}
		}

	}

}
