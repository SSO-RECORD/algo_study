/*
 * [문제 설명]
 * 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 
 * 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하는 문제
 * 
 * [구현 방식]
 * 1. 연락망 데이터를 그래프로 표현하기 위해 ArrayList 배열을 사용하여 각 노드가 연결된 다른 노드들을 저장
 * 2. BFS 알고리즘을 사용하여 시작점에서부터 모든 노드를 탐색하면서 가장 멀리 있는 노드의 번호를 추적
 * 3. 같은 깊이에 있는 노드가 여러 개 있을 경우, 가장 큰 번호의 노드를 결과로 선택
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static ArrayList<Integer>[] contact; //연락망을 저장할 ArrayList 배열 선언
	static int start, result; //bfs 시작점과 결과를 저장할 변수 선언

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		//10만큼 반복
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int dataLength = Integer.parseInt(st.nextToken()); // 입력받을 데이터의 길이
			start = Integer.parseInt(st.nextToken()); // 시작점 입력받기
			
			contact = new ArrayList[101]; // 배열 초기화(연락인원이 최대 100명이기 때문에 크기 101)
			// 배열의 각 인덱스를 ArrayList로 초기화
			for (int i = 1; i < 101; i++) {
				contact[i] = new ArrayList<>();
			}
			//입력 데이터를 읽어와서 연락망 그래프 생성
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < dataLength / 2; i++) {
				int from = Integer.parseInt(st.nextToken()); // from 입력받기
				int to = Integer.parseInt(st.nextToken()); // to 입력받기

				contact[from].add(to);
			}
			
			result = 0; //결과 초기화
			bfs(start); //bfs 탐색
			sb.append("#"+tc+" "+result +"\n");
		}
		System.out.print(sb); //출력
	}
	
	//bfs 탐색
	private static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>(); //int[]타입 큐 선언
		boolean[] visited = new boolean[101];// 방문 여부를 체크하는 배열
		//시작점을 큐에 추가하고 방문표시
		queue.add(new int[] {start, 0}); 
		visited[start] = true;
		
		int maxDepth = 0; //최대깊이를 저장할 변수
		result = 0; //결과를 저장할 변수
		
		//큐가 빌 때까지 반복
		while(!queue.isEmpty()) {
			//큐에서 현재 노드번호와 깊이 정보 가져오기
			int[] current = queue.poll();
			int currentNode = current[0];
			int currentDepth = current[1];
			
			//현재 깊이가 최대 깊이보다 클 경우 업데이터
			if(currentDepth > maxDepth) {
				maxDepth = currentDepth;
				result = currentNode;
			}
			//현재 깊이가 최대 깊이와 같은 경우 숫자가 더 큰 노드를 결과로 저장
			else if(currentDepth == maxDepth) {
				result = Math.max(currentNode, result);
			}
			
			//현재 노드에 연결된 모든 노드를 탐색
			for(int node : contact[currentNode]) {
				//방문하지 않은 노드만 큐에 추가
				if(!visited[node]) {
					visited[node] = true;
					queue.add(new int[] {node, currentDepth + 1});
				}
			}
		}
	}
}
