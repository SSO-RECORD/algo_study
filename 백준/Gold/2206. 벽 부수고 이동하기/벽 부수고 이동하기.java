import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x; //x좌표
	int y; //y좌표
	int wall; //벽 부쉈는지 여부(0 또는 1)
	int cnt; //이동거리
	
	public Point(int x, int y, int wall, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.cnt = cnt;
	}
}

public class Main {

	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수

		map = new int[N][M];
		// 배열에 정보 입력
		for (int row = 0; row < N; row++) {
			String str = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row][col] = str.charAt(col) - '0';
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		//방문 여부 체크
		//0은 벽 안부쉈고, 1은 벽 부수고 이동
		boolean[][][] visited = new boolean[2][N][M];
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 1)); //시작 좌표
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			//종료 조건
			if(cur.x == N-1 && cur.y == M-1) {
				return cur.cnt;
			}
			
			for(int d=0; d<4; d++) {
				int dx = cur.x + dr[d];
				int dy = cur.y + dc[d];
				int cnt = cur.cnt;
				int wall = cur.wall;
				
				if(dx>=0 && dx<N && dy>=0 && dy<M && !visited[wall][dx][dy]) {
					if(map[dx][dy] == 0) {
						visited[wall][dx][dy] = true;
						q.add(new Point(dx, dy, wall, cnt+1));
					}
					else if(map[dx][dy] == 1 && wall == 0) {
						visited[wall][dx][dy] = true;
						q.add(new Point(dx, dy, wall+1, cnt+1));
					}
				}
			}
		}
		//경로 없음
		return -1;
	}
}
