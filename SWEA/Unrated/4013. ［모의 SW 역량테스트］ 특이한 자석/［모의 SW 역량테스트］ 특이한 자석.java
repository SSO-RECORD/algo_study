import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static boolean[] visited;
	static List<Integer> mag1;
	static List<Integer> mag2;
	static List<Integer> mag3;
	static List<Integer> mag4;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA4013_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 총 테스트케이스 수
		// T만큼 반복
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine().trim()); // 자석을 회전시키는 횟수

			// 1번 자석의 자성정보
			st = new StringTokenizer(br.readLine());
			mag1 = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				mag1.add(Integer.parseInt(st.nextToken()));
			}

			// 2번 자석의 자성정보
			st = new StringTokenizer(br.readLine());
			mag2 = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				mag2.add(Integer.parseInt(st.nextToken()));
			}

			// 3번 자석의 자성정보
			st = new StringTokenizer(br.readLine());
			mag3 = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				mag3.add(Integer.parseInt(st.nextToken()));
			}

			// 4번 자석의 자성정보
			st = new StringTokenizer(br.readLine());
			mag4 = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				mag4.add(Integer.parseInt(st.nextToken()));
			}

			// 자석의 회전 정보(자석의 번호, 회전방향)
			int[][] info = new int[K][2];
			for (int row = 0; row < K; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < 2; col++) {
					info[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			for (int k = 0; k < K; k++) {
				visited = new boolean[5];
				if (info[k][0] == 1) {// 1번 자석을 회전시키는 경우
					rotateMag1(info[k][1]);
				} else if (info[k][0] == 2) {
					rotateMag2(info[k][1]);
				} else if (info[k][0] == 3) {
					rotateMag3(info[k][1]);
				} else if (info[k][0] == 4) {
					rotateMag4(info[k][1]);
				}
			}

			int sum = 0;
			sum += (mag1.get(0) == 0) ? 0 : 1;
			sum += (mag2.get(0) == 0) ? 0 : 2;
			sum += (mag3.get(0) == 0) ? 0 : 4;
			sum += (mag4.get(0) == 0) ? 0 : 8;

			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		System.out.print(sb);
	}

	private static void rotateMag1(int dir) {
		visited[1] = true;
		if (mag1.get(2) != mag2.get(6) && !visited[2]) {
			rotateMag2(dir * -1);
		}
		rotate(mag1, dir);
	}

	private static void rotateMag2(int dir) {
		visited[2] = true;
		if (mag1.get(2) != mag2.get(6) && !visited[1]) {
			rotateMag1(dir * -1);
		}
		if (mag2.get(2) != mag3.get(6) && !visited[3]) {
			rotateMag3(dir * -1);
		}
		rotate(mag2, dir);

	}

	private static void rotateMag3(int dir) {
		visited[3] = true;
		if (mag2.get(2) != mag3.get(6) && !visited[2]) {
			rotateMag2(dir * -1);
		}
		if (mag3.get(2) != mag4.get(6) && !visited[4]) {
			rotateMag4(dir * -1);
		}
		rotate(mag3, dir);

	}

	private static void rotateMag4(int dir) {
		visited[4] = true;
		if (mag3.get(2) != mag4.get(6) && !visited[3]) {
			rotateMag3(dir * -1);
		}
		rotate(mag4, dir);

	}

	// 회전 메서드
	private static void rotate(List<Integer> mag, int dir) {
		// 시계방향 회전
		if (dir == 1) {
			int lastElement = mag.get(mag.size() - 1);
			mag.add(0, lastElement);
			mag.remove(mag.size() - 1);
		}
		// 반시계방향 회전
		else {
			mag.add(mag.get(0));
			mag.remove(0);
		}

	}

}
