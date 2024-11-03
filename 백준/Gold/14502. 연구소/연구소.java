import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static List<int[]> list;
	static List<int[]> newList;
	static int maxCnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수

		map = new int[N][M];
		list = new ArrayList<>();
		newList = new ArrayList<>();

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());

				if (map[row][col] == 0) {
					list.add(new int[] { row, col });
				}
			}
		}

		maxCnt = Integer.MIN_VALUE;
		comb(0, 0);

		System.out.println(maxCnt);

//		for(int i=0; i<list.size(); i++) {
//			int[] position = list.get(i);
//			System.out.println(position[0] + " " + position[1]);
//		}

	}

	private static void comb(int idx, int start) {
		// 기저조건
		if (idx == 3) {
			int[][] mapCopy = copyMap(map);

			for (int[] pos : newList) {
				mapCopy[pos[0]][pos[1]] = 1;
			}

			int curCnt = bfs(mapCopy);
			maxCnt = Math.max(maxCnt, curCnt);
			return;
		}

		// 유도파트
		for (int i = start; i < list.size(); i++) {
			newList.add(list.get(i));
			comb(idx + 1, i + 1);
			newList.remove(newList.size() - 1);
		}

	}

	private static int bfs(int[][] mapCopy) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (mapCopy[row][col] == 2) {
					queue.add(new int[] { row, col });
					visited[row][col] = true;
				}
			}
		}

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (isIn(nr, nc) && !visited[nr][nc] && mapCopy[nr][nc] == 0) {
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = true;
					mapCopy[nr][nc] = 2;
				}
			}
		}

		return safeSection(mapCopy);

	}

	private static int safeSection(int[][] mapCopy) {
		int cnt = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (mapCopy[row][col] == 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

	private static int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = Arrays.copyOf(map[i], M);
		}
		return copyMap;
	}

}
