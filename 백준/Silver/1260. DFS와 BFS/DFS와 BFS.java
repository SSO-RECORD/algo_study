import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer>[] adjList;
	private static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//정점의 개수 N, 간선의 개수 M, 탐색을 시작할 정점의 번호 V 입력받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		//adjList라는 변수에 크기가 N + 1인 ArrayList 배열을 할당
		//크기가 N + 1인 ArrayList 배열을 생성
		adjList = new ArrayList[N+1];
		//배열의 각 요소를 개별적으로 초기화하여, 각 정점에 연결된 이웃 정점들을 저장할 수 있도록 함
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			//간선이 연결하는두 정점의 번호 입력받기
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		//정점 번호가 작은 것을 먼저 방문하기 위해 정렬
		for(int i=1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		//해당 정점의 방문여부를 저장하기 위한 배열, false로 초기화
		visited = new boolean[N+1];
		//탐색을 시작할 정점의 번호 V부터 DFS탐색
		dfs(V);
		
		System.out.println();
		
		visited = new boolean[N+1];
		bfs(V);
	}
	

	private static void dfs(int v) {
		//탐색을 시작하면 무조건 방문
		visited[v] = true;
		System.out.print(v + " ");
		
		//방문하지 않은 노드들을 대상으로 깊이우선탐색 실행
		for(int u : adjList[v]) {
			if(!visited[u]) {
				dfs(u);
			}
		}
		
	}
	
	private static void bfs(int v) {
		//탐색할 정점들을 저장하는 자료구조 큐
		Queue<Integer> queue = new LinkedList<>();
		//시작 정점을 방문했다고 표시
		visited[v] = true;
		//시작 정점을 큐에 넣음
		queue.offer(v);
		
		//큐가 비어있지 않은 동안 계속해서 탐색 진행
		while(!queue.isEmpty()) {
			int first = queue.poll(); //현재 탐색중인 정점을 큐에서 꺼냄
			System.out.print(first + " ");
			
			//인접 정점을 넓이 우선 탐색
			for(int u : adjList[first]) {
				if(!visited[u]) {
					visited[u] = true;
					queue.offer(u);
				}
			}
		}
		
	}

}
