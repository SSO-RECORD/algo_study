import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int cnt;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1953_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim()); //테스트케이스 수
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //지도의 행의 수
			M = Integer.parseInt(st.nextToken()); //지도의 열의 수
			int R = Integer.parseInt(st.nextToken()); //맨홀뚜껑이 위치한 장소의 행
			int C = Integer.parseInt(st.nextToken()); //맨홀뚜껑이 위치한 장소의 열
			int L = Integer.parseInt(st.nextToken()); //탈출 후 소요된 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int row=0; row<N; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0; col<M; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			cnt = 1;
			bfs(R, C, L);
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int r, int c, int time) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c, 1});
 		visited[r][c] = true;
 		
 		while(!queue.isEmpty()) {
 			int[] cur = queue.poll();
 			int x = cur[0];
 			int y = cur[1];
 			int t = cur[2];
 			
 			if(t == time) {
 				break;
 			}
 			
 			int tunnel = map[x][y];
 			switch(tunnel) {
 			case 1:
 				for(int d=0; d<4; d++) {
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==0 && !isBound(nx, ny) && map[nx][ny]!=0 && !visited[nx][ny] && map[nx][ny]!=3 && map[nx][ny]!=4 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==1 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=5 && map[nx][ny]!=6) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==2 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=6 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==3 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=4 && map[nx][ny]!=5) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 2:
 				for(int d=0; d<2; d++) {
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==0 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=4 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==1 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=5 && map[nx][ny]!=6) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 3:
 				for(int d=2; d<4; d++) {
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==2 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=6 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==3 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=4 && map[nx][ny]!=5) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 4:
 				for(int d=0; d<4; d++) {
 					if(d==1 || d==2) continue;
 					
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==0 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=4 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==3 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=4 && map[nx][ny]!=5) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 5:
 				for(int d=0; d<4; d++) {
 					if(d==0 || d==2) continue;
 					
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==1 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=5 && map[nx][ny]!=6) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==3 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=4 && map[nx][ny]!=5) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 6:
 				for(int d=0; d<4; d++) {
 					if(d==0 || d==3) continue;
 					
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==1 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=5 && map[nx][ny]!=6) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==2 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=6 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			case 7:
 				for(int d=0; d<4; d++) {
 					if(d==1 || d==3) continue;
 					
 					int nx = x + dx[d];
 					int ny = y + dy[d];
 					
 					if(d==0 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=3 && map[nx][ny]!=4 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 					
 					if(d==2 && !isBound(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0 && map[nx][ny]!=2 && map[nx][ny]!=6 && map[nx][ny]!=7) {
 						visited[nx][ny] = true;
 						queue.add(new int[] {nx, ny, t+1});
 						cnt++;
 					}
 				}
 				break;
 			}
 			//switch-case end
 			
 		}
	}

	private static boolean isBound(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M) return true;
		else return false;
	}

}
