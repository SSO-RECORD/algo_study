import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	// 방향을 나타내는 상수
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;

	// 방향에 따른 이동 변화
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	// 상태 클래스 정의
	static class State {
		int row, col, dir, memory;

		public State(int row, int col, int dir, int memory) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.memory = memory;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1824_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수
		// T만큼 반복
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			char[][] grid = new char[R][C];
			for (int row = 0; row < R; row++) {
				String str = br.readLine();
				for (int col = 0; col < C; col++) {
					grid[row][col] = str.charAt(col);
				}
			}

			Queue<State> queue = new LinkedList<>();
			boolean[][][][] visited = new boolean[R][C][4][16];

			State initial = new State(0, 0, RIGHT, 0);
			queue.add(initial);
			visited[0][0][RIGHT][0] = true;

			boolean found = false;

			while (!queue.isEmpty()) {
				State cur = queue.poll();
				int row = cur.row;
				int col = cur.col;
				int dir = cur.dir;
				int mem = cur.memory;

				char command = grid[row][col];

				if (command == '@') {
					found = true;
					break;
				}

				int newDir = dir;
				int newMem = mem;

				switch (command) {
				case '<':
					newDir = LEFT;
					break;
				case '>':
					newDir = RIGHT;
					break;
				case '^':
					newDir = UP;
					break;
				case 'v':
					newDir = DOWN;
					break;
				case '_':
					if (mem == 0)
						newDir = RIGHT;
					else
						newDir = LEFT;
					break;
				case '|':
					if (mem == 0)
						newDir = DOWN;
					else
						newDir = UP;
					break;
				case '?':
					for (int d = 0; d < 4; d++) {
						int nr = ((row + dr[d]) + R) % R;
						int nc = ((col + dc[d]) + C) % C;
						if (!visited[nr][nc][d][mem]) {
							queue.add(new State(nr, nc, d, mem));
							visited[nr][nc][d][mem] = true;
						}
					}
					continue;
				case '.':
					break;
				case '+':
					newMem = (mem + 1) % 16;
					break;
				case '-':
					newMem = (mem == 0) ? 15 : mem - 1;
					break;
				default:
					if (command >= '0' && command <= '9') {
						newMem = command - '0';
					}
					break;
				}

				int nr = (row + dr[newDir] + R) % R;
				int nc = (col + dc[newDir] + C) % C;

				if (!visited[nr][nc][newDir][newMem]) {
					queue.add(new State(nr, nc, newDir, newMem));
					visited[nr][nc][newDir][newMem] = true;
				}
			}
			if (found) {
				sb.append("#").append(t).append(" YES").append("\n");
			} else {
				sb.append("#").append(t).append(" NO").append("\n");
			}
		}
		System.out.print(sb);
	}

}
