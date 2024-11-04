import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int X;
	static int[] distances;
	static List<Edge>[] edgeList;

	static class Edge implements Comparable<Edge> {

		int vertex;
		int cost;

		public Edge(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) { // 오름차순
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		int K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

		distances = new int[N + 1];
		edgeList = new ArrayList[N + 1];
		Arrays.fill(distances, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			edgeList[i] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			edgeList[start].add(new Edge(end, 1));
		}

		distances[X] = 0;

		dijkstra();

		int result = 0;
		for (int i = 1; i < distances.length; i++) {
			int distance = distances[i];
			if (distance == K) {
				System.out.println(i);
				result++;
			}
		}
		if (result == 0) {
			System.out.println(-1);
		}
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(X, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int vertex = edge.vertex;
			int cost = edge.cost;

			if (distances[vertex] < cost) {
				continue;
			}

			for (int i = 0; i < edgeList[vertex].size(); i++) {
				int newVertex = edgeList[vertex].get(i).vertex;
				int newCost = edgeList[vertex].get(i).cost + cost;

				if (distances[newVertex] > newCost) {
					distances[newVertex] = newCost;
					pq.add(new Edge(newVertex, newCost));
				}
			}
		}

	}

}
