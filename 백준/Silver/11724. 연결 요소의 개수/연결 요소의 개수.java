import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static ArrayList<Integer>[] adjList;
	private static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 정점의 개수 입력받기
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수 입력받기
		adjList = new ArrayList[N + 1];
		// 배열의 각 요소를 개별적으로 초기화하여, 각 정점에 연결된 이웃 정점들을 저장할 수 있도록 함
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adjList[u].add(v);
			adjList[v].add(u);
		}

		int cnt = 0;
		int cnt_dfs = 0;
//		for (int i = 1; i <= N; i++) {
//			if (!visited[i]) {
//				dfs(i);
//				cnt_dfs += 1;
//			}
//		}
		
		int cnt_bfs = 0;
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				bfs(i);
				cnt_bfs += 1;
				
			}
		}
		System.out.println(cnt_bfs);
	}

	private static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
		visited[i] = true;
		queue.add(i);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int u : adjList[current]) {
				if(!visited[u]) {
					visited[u] = true;
					queue.add(u);
				}
			}
		}
	}

	private static void dfs(int i) {
		visited[i] = true;

		for (int u : adjList[i]) {
			if (!visited[u]) {
				dfs(u);
			}
		}
	}
}
