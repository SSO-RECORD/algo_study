import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //지도의 크기
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int row=0; row<N; row++) {
			String str = br.readLine();
			for(int col=0; col<N; col++) {
				map[row][col] = str.charAt(col) - '0';
			}
		}
		
		cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				if(map[row][col] != 0 && !visited[row][col]) {
					bfs(row, col);
					list.add(cnt);
					cnt = 0;
				}
			}
		}
		System.out.println(list.size());
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		visited[row][col] = true;
		queue.add(new int[] {row, col});
		cnt += 1;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			
			for(int d=0; d<4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && map[nr][nc]==1) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
					cnt += 1;
				}
			}
		}
		
	}

}
