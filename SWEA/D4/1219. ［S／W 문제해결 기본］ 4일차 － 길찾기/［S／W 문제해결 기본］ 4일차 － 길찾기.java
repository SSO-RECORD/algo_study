import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static List<Integer>[] adjList;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1219_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken()); //테스트케이스 번호
			int pair = Integer.parseInt(st.nextToken()); //길의 총 개수
			
			adjList = new ArrayList[100];
			visited = new boolean[100];
			
			for(int i=0; i<100; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<pair; i++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				adjList[u].add(v);
			}
			
			result = 0;
			dfs(0);
			if(result == 1) {
				sb.append("#").append(tc).append(" ").append(result).append("\n");
				
			}else {
				result = 0;
				sb.append("#").append(tc).append(" ").append(result).append("\n");
			}
		}
		System.out.print(sb);

	}

	private static void dfs(int node) {
		
		visited[node] = true;
		
		if(node == 99) {
			result = 1;
			return;
		}
		
		if(adjList[node] == null) {
			result = -1;
			return;
		}
		
		for(int no : adjList[node]) {
			if(!visited[no]) {
				dfs(no);
			}
		}
	}

}
