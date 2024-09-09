import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] peoples;
	static List<ArrayList<Integer>> graph;
	static boolean[] selected;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim()); //구역의 개수
		peoples = new int[N];
		selected = new boolean[N];
		result = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		//구역별 인구수 정보
		for(int i=0; i<N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); //그 구역과 인접한 구역의 수
			for(int j=0; j<cnt; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph.get(i).add(num-1);
			}
		}
		
		divide(0);
		if(result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);
	}

	private static void divide(int idx) { //1.선거구 나누기
		//기저조건
		if(idx == N) {
			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				if(selected[i]) {
					aList.add(i);
				}
				else {
					bList.add(i);
				}
			}
			
			if(aList.size()==0 || bList.size()==0) {
				return;
			}
			
			if(check(aList) && check(bList)) {
				getPeopleDiff();
			}
			return;
		}
		
		//유도파트
		//선택
		selected[idx] = true;
		divide(idx+1);
		//비선택
		selected[idx] = false;
		divide(idx+1);
		
	}

	private static boolean check(List<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		visited = new boolean[N];
		visited[list.get(0)] = true;
		q.add(list.get(0));
		
		int count = 1; //방문한 지역 개수
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<graph.get(cur).size(); i++) {
				int y = graph.get(cur).get(i);
				if(!visited[y] && list.contains(y)) {
					q.offer(y);
					visited[y] = true;
					count++;
				}
			}
		}
		
		if(count == list.size()) {
			return true;
		}
		
		return false;
	}

	private static void getPeopleDiff() {
		int pA = 0, pB = 0;
		for(int i=0; i<N; i++) {
			if(selected[i]) {
				pA += peoples[i];
			}
			else {
				pB += peoples[i];
			}
		}
		int diff = Math.abs(pA - pB);
		result = Math.min(result, diff);
	}

	
}
