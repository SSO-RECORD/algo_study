import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static List<Integer> mag1;
	static List<Integer> mag2;
	static List<Integer> mag3;
	static List<Integer> mag4;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA4013_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 개수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine().trim()); // 자석을 회전시키는 횟수

			// 1번 자석
			mag1 = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				mag1.add(Integer.parseInt(st.nextToken()));
			}

			// 2번 자석
			mag2 = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				mag2.add(Integer.parseInt(st.nextToken()));
			}

			// 3번 자석
			mag3 = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				mag3.add(Integer.parseInt(st.nextToken()));
			}

			// 4번 자석
			mag4 = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				mag4.add(Integer.parseInt(st.nextToken()));
			}

			// 자석의 회전 정보
			int[][] rotateArr = new int[K][2];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				rotateArr[k][0] = Integer.parseInt(st.nextToken()); // 회전하는 자석의 번호
				rotateArr[k][1] = Integer.parseInt(st.nextToken()); // 회전 방향
			}

			for (int k = 0; k < K; k++) {

				visited = new boolean[5];

				if (rotateArr[k][0] == 1) {
					workMag1(rotateArr[k][1]);
				} else if (rotateArr[k][0] == 2) {
					workMag2(rotateArr[k][1]);
				} else if (rotateArr[k][0] == 3) {
					workMag3(rotateArr[k][1]);
				} else if (rotateArr[k][0] == 4) {
					workMag4(rotateArr[k][1]);
				}
			}

			int sum = 0;
			sum += mag1.get(0) == 0 ? 0 : 1;
			sum += mag2.get(0) == 0 ? 0 : 2;
			sum += mag3.get(0) == 0 ? 0 : 4;
			sum += mag4.get(0) == 0 ? 0 : 8;

			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.print(sb);
	}

	private static void workMag1(int dir) {
		visited[1] = true;
		if (mag1.get(2) != mag2.get(6) && !visited[2]) {
			workMag2(dir * -1);
		}
		rotateMag(mag1, dir);
	}

	private static void workMag2(int dir) {
		visited[2] = true;
		if (mag1.get(2) != mag2.get(6) && !visited[1]) {
			workMag1(dir * -1);
		}
		if (mag2.get(2) != mag3.get(6) && !visited[3]) {
			workMag3(dir * -1);
		}
		rotateMag(mag2, dir);
	}

	private static void workMag3(int dir) {
		visited[3] = true;
		if (mag2.get(2) != mag3.get(6) && !visited[2]) {
			workMag2(dir * -1);
		}
		if (mag3.get(2) != mag4.get(6) && !visited[4]) {
			workMag4(dir * -1);
		}
		rotateMag(mag3, dir);

	}

	private static void workMag4(int dir) {
		visited[4] = true;
		if (mag3.get(2) != mag4.get(6) && !visited[3]) {
			workMag3(dir * -1);
		}
		rotateMag(mag4, dir);

	}

	private static void rotateMag(List<Integer> mag, int dir) {

		if (dir == 1) {
			int lastIdx = mag.size() - 1;
			int lastElement = mag.get(lastIdx);
			mag.add(0, lastElement);
			mag.remove(mag.size() - 1);
		}

		if (dir == -1) {
			mag.add(mag.get(0));
			mag.remove(0);
		}
	}

}
