/*
 * [문제 설명]
 * 1부터 n이 각각 n개의 집합을 이루고 있을때, 합집합 연산 수행과
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 프로그램.
 * 
 * [구현]
 * 라이브 시간에 학습한 union-find 알고리즘을 그대로 구현하면 된다.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[] parents;

	// i를 유일한 원소로 하는 새로운 집합 생성
	static void make() {
		for (int i = 1; i <= n; i++) {
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
		// 집합의 대표자 찾기
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		// 대표자가 같으면 union 불가능
		if (aRoot == bRoot)
			return false;
		// 대표자가 다르면 union 가능
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA3289_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 입력
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken()); // 집합의 개수 입력
			int m = Integer.parseInt(st.nextToken()); // 연산의 개수 입력받기

			parents = new int[n + 1]; // 배열 초기화

			make(); // 서로소 집합 생성

			int[][] arr = new int[m][3]; // 연산의 정보를 저장할 배열 생성
			// 배열에 입력받은 정보 저장
			for (int row = 0; row < m; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < 3; col++) {
					arr[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#" + tc + " ");
			for (int row = 0; row < m; row++) {
				if (arr[row][0] == 0) { // union연산(0)을 해야하는 경우
					union(arr[row][1], arr[row][2]);
				} else {// findSet연산(1)을 해야하는 경우
					int set1 = findSet(arr[row][1]);
					int set2 = findSet(arr[row][2]);
					if (set1 == set2) { // 두 원소가 같은 집합에 속해있다면 1 출력
						sb.append(1);
					} else { // 두 원소가 다른 집합에 속해있다면 0 출력
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		// 출력
		System.out.print(sb);
	}
}
