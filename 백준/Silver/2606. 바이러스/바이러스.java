import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static ArrayList<Integer>[] array;
	private static boolean[] visited;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c_num = Integer.parseInt(br.readLine()); //컴퓨터의 수 입력받기
		int c_pair = Integer.parseInt(br.readLine()); //컴퓨터 쌍의 수 입력받기
		
		//각 요소에  ArrayList 초기화
		array = new ArrayList[c_num+1];
		for(int i=1; i<=c_num; i++) {
			array[i] = new ArrayList<>();
		}
		 
		//연결된 컴퓨터 쌍 입력받기
		for(int i=0; i<c_pair; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			//연결된 컴퓨터 쌍을 배열에 저장
			array[p1].add(p2);
			array[p2].add(p1);
		}
		
//		visited = new boolean[c_num+1];
//		cnt = 0;
//		dfs(1);
		
		//System.out.printf("DFS 탐색 : %d\n", cnt -1);
		
		visited = new boolean[c_num+1];
		cnt = 0;
		bfs(1);
		
		//System.out.printf("BFS 탐색 : %d\n", cnt);
		System.out.println(cnt);
	}

	private static void bfs(int i) {
		//탐색할 정점들을 저장하는 자료구조 큐
		Queue<Integer> queue = new LinkedList<>();
		visited[i] = true; //시작 정점을 방문했다고 표시
		queue.offer(i); //시작 정점을 큐에 넣음
		
		//큐가 비어있지 않은 동안 계속해서 탐색 진행
		while(!queue.isEmpty()) {
			int first = queue.poll(); //현재 탐색중인 정점을 큐에서 꺼냄
			
			//인접 정점을 넓이 우선 탐색
			for(int x : array[first]) {
				if(!visited[x]) {
					visited[x] = true;
					cnt += 1;
					queue.offer(x);
				}
			}
		}
		
	}

	private static void dfs(int i) {
		//탐색을 시작하면 무조건 방문
		visited[i] = true;
		cnt += 1;
		
		//방문하지 않은 노드들을 대상으로 깊이우선탐색 실행
		for(int x : array[i]) {
			if(!visited[x]) {
				dfs(x);
			}
		}	
	}
}
