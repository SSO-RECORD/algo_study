import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {

	int x; // x좌표
	int y; // y좌표
	int gram; // 그람을 획득했는지 여부(0: 획득x, 1: 획득o)
	int distance; // 이동 거리

	public Node(int x, int y, int gram, int distance) {
		super();
		this.x = x;
		this.y = y;
		this.gram = gram;
		this.distance = distance;
	}

}

public class Main {

	static int N, M;
	static int[][] castle;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 배열의 행 수
		M = Integer.parseInt(st.nextToken()); // 배열의 열 수
		int T = Integer.parseInt(st.nextToken()); // 제한시간

		castle = new int[N][M];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				castle[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int result = bfs(T);
		if (result == -1) {
			System.out.println("Fail");
		} else {
			System.out.println(result);
		}
	}

	private static int bfs(int time) {

		// 방문 여부 체크
		// 0은 그람 획득x, 1은 그람 획득o
		boolean[][][] visited = new boolean[2][N][M];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 0, 0)); // 시작좌표(벽 안부순 상태)
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.x == N - 1 && cur.y == M - 1 && cur.distance <= time) {
				return cur.distance;
			}

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dr[d];
				int ny = cur.y + dc[d];
				int gram = cur.gram;
				int distance = cur.distance;

				if (isIn(nx, ny) && !visited[gram][nx][ny]) {
					if (castle[nx][ny] == 0) {
						visited[gram][nx][ny] = true;
						queue.add(new Node(nx, ny, gram, distance + 1));
					}
					if (castle[nx][ny] == 2) {
						visited[gram][nx][ny] = true;
						queue.add(new Node(nx, ny, 1, distance + 1));
					}
					if (castle[nx][ny] == 1 && gram > 0) {
						visited[gram][nx][ny] = true;
						queue.add(new Node(nx, ny, gram, distance + 1));
					}
				}
			}
		}
		return -1;
	}

//	private static int bfs(int r, int c, int t, int gram) {
//
//		Queue<int[]> queue = new LinkedList<>();
//		queue.add(new int[] { r, c, 0 });
//		visited[r][c] = true;
//
//		while (!queue.isEmpty()) {
//			int[] cur = queue.poll();
//			int cr = cur[0];
//			int cc = cur[1];
//			int ct = cur[2];
//
//			if (cr == N - 1 && cc == M - 1 && ct <= t) {
//				return ct;
//			}
//			
//			if (gram > 0) {
//				visited = new boolean[N][M];
//			}
//
//			for (int d = 0; d < 4; d++) {
//				int nr = cr + dr[d];
//				int nc = cc + dc[d];
//				int nt = ct + 1;
//
//				if (isIn(nr, nc) && !visited[nr][nc]) {
//					if (castle[nr][nc] == 0) {
//						queue.add(new int[] { nr, nc, nt });
//						visited[nr][nc] = true;
//					}
//					if (castle[nr][nc] == 2) {
//						gram++;
//						queue.add(new int[] { nr, nc, nt });
////						visited[nr][nc] = true;
//					}
//					if (castle[nr][nc] == 1 && gram > 0) {
//						queue.add(new int[] { nr, nc, nt });
//						visited[nr][nc] = true;
//					}
//				}
//			}
//		}
//
//		return -1;
//
//	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M)
			return true;
		return false;
	}

}
