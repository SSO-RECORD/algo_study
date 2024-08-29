import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); //선의 수
		
		Edge[] edges = new Edge[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); //시작 경로
			int end = Integer.parseInt(st.nextToken()); //끝 경로
			int weight = Integer.parseInt(st.nextToken()); //가중치
			edges[i] = new Edge(start, end, weight);
		}
		
		Arrays.sort(edges); //가중치 기준 오름차순 정렬
		
		make(); //서로소 집합 생성
		
		int cnt = 0;
		long cost = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				cost += edge.weight;
				if(++cnt == N-1) break;
			}
		}
		System.out.println(cost);
	}

	private static void make() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = -1;
		}
	}
	
	private static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
}
