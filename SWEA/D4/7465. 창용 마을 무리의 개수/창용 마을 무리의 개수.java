/*
 * [문제 설명]
 * N명의 사람이 존재하고, 서로 알고 있는 두 사람의 번호가 M줄 주어질 때, 
 * 창용마을에 몇 개의 무리가 존재하는지 출력하는 프로그램
 * 이 문제에서는 두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면,
 * 이러한 사람들을 다 묶어서 하나의 무리라고 지칭한다.
 * 
 * [구현]
 * union-find 알고리즘을 사용하면 쉽게 구현할 수 있다.
 * 결과로 출력해야 할 무리의 개수 = 집합의 개수이다.
 * 집합의 개수 = parents 배열을 쭉 들여다보면서 나의 부모가 나인 것들의 개수를 count
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[] parents;

	// i를 유일한 원소로 하는 새로운 집합 생성
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	// 집합의 대표자 찾기
	static int findSet(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a]);
	}

	// a가 속한 집합과 b가 속한 집합 합치기
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA7465_s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 사람의 수 N명 입력
			int M = Integer.parseInt(st.nextToken()); // 서로 알고있는 두 사람의 수 입력
			int[][] arr = new int[M][2]; // 서로 알고 있는 사람 관계의 저장을 위한 Mx2 크기의 배열 생성
			parents = new int[N + 1];

			// 2차원 배열에 관계 저장
			for (int row = 0; row < M; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < 2; col++) {
					arr[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			make(); // 서로소 집합 생성

			// union 과정 반복
			for (int row = 0; row < M; row++) {
				union(arr[row][0], arr[row][1]);
			}

			// 집합의 개수 = parents 배열을 쭉 들여다보면서 나의 부모가 나인 것들의 개수를 cnt
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (i == parents[i])
					cnt += 1;
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		// 출력
		System.out.print(sb);
	}
}
