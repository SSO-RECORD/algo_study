import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //수빈이가 있는 위치 N 입력받기
		int K = Integer.parseInt(st.nextToken()); //동생이 있는 위치 K 입력받기
		
		System.out.println(bfs(N, K)); //bfs 탐색
	}

	private static int bfs(int n, int k) {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[100001];
		q.add(new int[] {n, 0});
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int currentPosition = current[0];
			int currentTime = current[1];
			
			if(currentPosition == k) {
				return currentTime;
			}
			
			int[] currentPositions = {currentPosition-1, currentPosition+1, currentPosition*2};
			for(int position : currentPositions) {
				if(position>=0 && position<=100000 && !visited[position]) {
					q.add(new int[] {position, currentTime+1});
					visited[position] = true;
				}
			}
		}
		return -1;
	}
}
