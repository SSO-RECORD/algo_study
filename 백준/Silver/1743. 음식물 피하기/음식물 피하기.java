import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static boolean[][] visited;
	private static int[][] map;
	static int N, M, K;
	static int cnt, size;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//N, M, K 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//N, M의 크기만큼 map과 visited 초기화
		map = new int[N][M];
		visited = new boolean[N][M];
		
		//(r, c) 좌표 입력받기
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			
			//음식물이 있는 곳의 좌표 입력받기(배열이기 때문에 -1)
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			
			//음식물이 있는 위치(r,c)를 1로 초기화
			map[r][c] = 1;
		}
		
		//현재 위치가 방문한 적이 없고, 음식물이 있는 위치라면 DFS 탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if((!visited[i][j]) && (map[i][j]==1)) {
					cnt = 0;
					DFS(i, j);
					size = Math.max(size, cnt);
				}
			}
		}
		System.out.println(size);

	}
	
	public static void DFS(int i, int j) {
		visited[i][j] = true;
		cnt++;
		
		//4방탐색
		for(int x=0; x<4; x++) {
			int nr = i + dr[x];
			int nc = j + dc[x];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] != 1) {
				continue;
			}
			DFS(nr, nc);
		}
		
	}

}
