import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] yard;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int sheep, wolf;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		yard = new char[R][C];
		visited = new boolean[R][C];

		for (int row = 0; row < R; row++) {
			String str = br.readLine();
			for (int col = 0; col < C; col++) {
				yard[row][col] = str.charAt(col);
			}
		}

		int liveWolf = 0, liveSheep = 0;
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				sheep = 0;
				wolf = 0;
				if (!visited[row][col] && yard[row][col] != '#') {
					dfs(row, col);
//					System.out.print("sheep : " + sheep + " wolf : " + wolf);
//					System.out.println();
					if (sheep > wolf) {
						wolf = 0;
						liveSheep += sheep;
						liveWolf -= wolf;
//						System.out.println("양의수 > 늑대수일때");
//						System.out.print("liveSheep : " + liveSheep + " liveWolf : " + liveWolf);
//						System.out.println();
					}
					if (sheep <= wolf) {
						sheep = 0;
						liveSheep -= sheep;
						liveWolf += wolf;
//						System.out.println("양의수 <= 늑대수일때");
//						System.out.print("liveSheep : " + liveSheep + " liveWolf : " + liveWolf);
//						System.out.println();
					}
					if (liveSheep < 0)
						liveSheep = 0;
					if (liveWolf < 0)
						liveWolf = 0;
//					System.out.print("최종 liveSheep : " + liveSheep + " liveWolf : " + liveWolf);
//					System.out.println();
				}
			}
		}

		System.out.print(liveSheep + " " + liveWolf);

	}

	private static void dfs(int row, int col) {

		visited[row][col] = true;
		if (yard[row][col] == 'v')
			wolf++;
		if (yard[row][col] == 'o')
			sheep++;

		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && yard[nr][nc] != '#') {
				dfs(nr, nc);
			}
		}

	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C)
			return true;
		return false;
	}

}
