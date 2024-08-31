import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	private static int N;
//	private static int[][] section;
//	private static int[][] newSection;
	private static boolean[][] visited;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //구역의 크기
		int[][] section = new int[N][N];
		int[][] newSection = new int[N][N];
		visited = new boolean[N][N];
		
		for(int row=0; row<N; row++) {
			String str = br.readLine();
			for(int col=0; col<N; col++) {
				section[row][col] = str.charAt(col);
			}
		}
		
		for(int row=0; row<N; row++) {
			newSection[row] = Arrays.copyOf(section[row], N);
		}
		
		
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print((char)newSection[i][j]);
//			}
//			System.out.println();
//		}
//		
		
		int cnt=0;
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				if(visited[row][col])continue;
				bfs(row, col,section);
				cnt += 1;
			}
		}
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				newSection[row][col] = section[row][col];
				if(section[row][col] == 'G') {
					newSection[row][col] = 'R';
				}
			}
		}
		visited = new boolean[N][N];
		int rgbCnt=0;
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				if(!visited[row][col]) {
					bfs(row, col, newSection);
					rgbCnt += 1;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(rgbCnt);
		
	}

	private static void bfs(int row, int col, int[][] section) {
		Queue<int[]> queue = new LinkedList<>();
		visited[row][col] = true;
		queue.add(new int[] {row, col});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			
			for(int d=0; d<4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && section[nr][nc] == section[row][col]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}	
	}
}
