import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static char[][] array;
	private static boolean[][] visited;
	static int cnt_w=0;
	static int cnt_b=0;
	static int N, M;
	static int result_w = 0;
	static int result_b = 0;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//전쟁터의 가로크기 N, 세로크기 M 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		array = new char[M][N];
		for(int i=0; i<M; i++) {
			String army = br.readLine();
			for(int j=0; j<N; j++) {
				array[i][j] = army.charAt(j);
			}
		}
		
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if((!visited[i][j]) && (array[i][j] == 'W')) {
					cnt_w = 0; // 탐색 시작 전에 초기화
                    DFS(i, j);
                    result_w += cnt_w * cnt_w;
					
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if((!visited[i][j]) && (array[i][j] == 'B')) {
					cnt_b = 0; // 탐색 시작 전에 초기화
                    DFS(i, j);
                    result_b += cnt_b * cnt_b;
				}
			}
		}
		System.out.print(result_w + " " + result_b);
	}

	private static void DFS(int i, int j) {
		visited[i][j] = true;
		
		if(array[i][j] == 'W') {
			cnt_w += 1;
		}else {
			cnt_b += 1;
		}
		
		for(int x=0; x<4; x++) {
			int nr = i + dr[x];
			int nc = j + dc[x];
			
			if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || array[i][j] != array[nr][nc]) {
				continue;
			}else {
				DFS(nr, nc);
			}
		}
		
	}

}
