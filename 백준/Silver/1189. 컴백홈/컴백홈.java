import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C, K;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int distance;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); //행의 수
		C = Integer.parseInt(st.nextToken()); //열의 수
		K = Integer.parseInt(st.nextToken()); //거리
		
		map = new char[R][C];
		visited = new boolean[R][C];
		distance = 0;
		cnt = 0;
		
		for(int row=0; row<R; row++) {
			String str = br.readLine();
			for(int col=0; col<C; col++) {
				map[row][col] = str.charAt(col);
			}
		}
		
		visited[R-1][0] = true;
		dfs(R-1, 0, 1);
		
		System.out.println(cnt);

	}

	private static void dfs(int r, int c, int distance) {
		
		//기저조건
		if(r == 0 && c == C-1) {
			if(distance == K) {
				cnt++;
			}
			return;
		}
		
		//유도파트
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'T') {
				visited[nr][nc] = true;
				dfs(nr, nc, distance+1);
				visited[nr][nc] = false;
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return (r>=0 && r<R && c>=0 && c<C);
	}

}
