/*
 * [문제 설명]
 * N×N형태로 늘어서 있는 N^2개의 방이 있다.
 * 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 1 더 크면 이동 가능하다.
 * 처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구해야 한다.
 * 
 * [내가 생각한 방법]
 * i) 배열의 첫번째 요소부터 차례대로 4방탐색을 한다.
 * ii) 1 더 큰 요소가 인접한 곳에 있다면, 그것에 대해 dfs 탐색을 진행한다.
 * iii) 모든요소에 대해 이동한 방의 횟수를 계산해서 가장 많은 방을 이동한 횟수를 구한다.
 * 
 * [구현]
 * 생각한 방법 그대로 구현을 진행했다.
 * 
 * [느낀점]
 * 변수값 초기화를 안해줘서 결과가 잘 출력되지 않는 문제를 겪었다.
 * 앞으로 변수 초기화에 주의해야겠다고 생각했다.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, cnt;
	private static int[][] room;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int maxCnt;
	private static int startRoom;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 수 입력받기
		// T만큼 반복
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 정수 N 입력받기
			room = new int[N][N]; // NxN 크기의 배열 생성
			maxCnt = 0;
			startRoom = 0;
			// 배열에 숫자 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//배열의 요소에 하나씩 접근하여 깊이우선탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 0;
					dfs(i, j);
					if (cnt > maxCnt) { //cnt값이 maxCnt값보다 크다면
						maxCnt = cnt; //최대값 갱신
						startRoom = room[i][j]; //시작한 방 번호 저장
					} else if (cnt == maxCnt) { //cnt값이 maxCnt와 같다면
						if (startRoom > room[i][j]) { //가장 작은 번호를 가진 방을 출력해줘야 함
							maxCnt = cnt;
							startRoom = room[i][j];
						}
					}
				}
			}
			sb.append("#" + t + " " + startRoom + " " + maxCnt + "\n");
		}
		//출력
		System.out.print(sb);
	}

	private static void dfs(int i, int j) {
		cnt += 1;
		int[] current = new int[] { i, j };
		int cx = current[0];
		int cy = current[1];
		
		//사방탐색
		for (int d = 0; d < 4; d++) {
			int nr = cx + dr[d];
			int nc = cy + dc[d];

			//상하좌우가 배열 범위 내에 존재하는지 체크
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (room[nr][nc] == room[i][j] + 1) { //이동하려는 방의 숫자가 현재방의 숫자보다 1 더 크다면
					dfs(nr, nc); //이동
				}
			}

		}

	}
}