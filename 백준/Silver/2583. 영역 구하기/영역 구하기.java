import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static boolean visited[][];
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int[][] map;
	private static int size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //행의 수
		N = Integer.parseInt(st.nextToken()); //열의 수
		int K = Integer.parseInt(st.nextToken()); //입력받을 좌표의 수
		
		map = new int[M][N];
		int[] point = new int[4];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<4; col++) {
				point[col] = Integer.parseInt(st.nextToken());
				}
			for(int i=point[1]; i<point[3]; i++) {
				for(int j=point[0]; j<point[2]; j++) {
					map[i][j] = 1;
				}
			}	
		}
		
		visited = new boolean[M][N];
		ArrayList<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					size = 1;
					dfs(i, j);
					cnt += 1;
					list.add(size);
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
//		for(int i=0; i<M; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		
	}

	private static void dfs(int i, int j) {
		visited[i][j] = true;
		
		for(int d=0; d<4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			if(nr>=0 && nr<M && nc >=0 && nc<N && !visited[nr][nc] && map[nr][nc]==0) {
				dfs(nr, nc);
				size++;
			}
		}
	}

}
