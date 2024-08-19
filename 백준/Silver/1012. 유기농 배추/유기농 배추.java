import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	private static boolean[][] visited;
	private static int[][] array;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int M, N;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); //테스트개이스 개수 입력받기
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //배추밭의 가로길이 M 입력받기
			N = Integer.parseInt(st.nextToken()); //배추밭의 세로길이 N 입력받기
			int K = Integer.parseInt(st.nextToken()); //배추가 심어져 있는 위치의 개수  K 입력받기
			
			array = new int[M][N];
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()); //배추가 심어져 있는 곳의 x좌표
				int c = Integer.parseInt(st.nextToken()); //배추가 심어져 있는 곳의 y좌표
				array[r][c] = 1;
			}
			
			visited = new boolean[M][N]; //방문여부를 체크할 2차원 배열
			int cnt1 = 0; //지렁이 개수를 세기 위한 변수(bfs용 지렁이)
			int cnt2 = 0; //지렁이 개수를 세기 위한 변수(dfs용 지렁이)
			int cnt = 0; //지렁이 개수를 세기 위한 변수(제출용)
			//배추가 심어진 곳 탐색
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					if(array[i][j]==1 && !visited[i][j]) { //배추가 심어져있고, 방문하지 않은 곳이라면
						bfs(i, j); //bfs 탐색
						dfs(i, j); //dfs 탐색
						cnt1 += 1; //탐색이 끝나면 지렁이 개수 +1
						cnt2 += 1;
						cnt += 1;
					}
				}
			}
			//출력
//			System.out.println("BFS 탐색 결과: " + cnt1);
//			System.out.println("DFS 탐색 결과: " + cnt2);
			System.out.println(cnt);
		}
	}

	//dfs 탐색
	private static void dfs(int i, int j) {
		visited[i][j] = true;
		int[] current = new int[] {i, j};
		int cx = current[0];
		int cy = current[1];
		//4방탐색
		for(int d=0; d<4; d++) {
			int nr = cx + dr[d];
			int nc = cy + dc[d];
			
			if(nr>=0 && nr<M && nc>=0 && nc<N && !visited[nr][nc] && array[nr][nc]==1) {
				dfs(nr, nc);
			}
		}
		
	}

	//bfs 탐색
	private static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		visited[i][j] = true;
		queue.add(new int[]{i, j});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cx = current[0];
			int cy = current[1];
			
			//4방탐색
			for(int d=0; d<4; d++) {
				int nr = cx + dr[d];
				int nc = cy + dc[d];
				
				if(nr>=0 && nr<M && nc>=0 && nc<N && !visited[nr][nc] && array[nr][nc]==1) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}

}
