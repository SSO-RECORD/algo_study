import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> wheel1;
	static List<Integer> wheel2;
	static List<Integer> wheel3;
	static List<Integer> wheel4;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		wheel1 = new ArrayList<>();
		wheel2 = new ArrayList<>();
		wheel3 = new ArrayList<>();
		wheel4 = new ArrayList<>();

		visited = new boolean[5];
		String state1 = br.readLine();
		for (int i = 0; i < 8; i++) {
			wheel1.add(state1.charAt(i) - '0');
		}
		String state2 = br.readLine();
		for (int i = 0; i < 8; i++) {
			wheel2.add(state2.charAt(i) - '0');
		}
		String state3 = br.readLine();
		for (int i = 0; i < 8; i++) {
			wheel3.add(state3.charAt(i) - '0');
		}
		String state4 = br.readLine();
		for (int i = 0; i < 8; i++) {
			wheel4.add(state4.charAt(i) - '0');
		}

		int K = Integer.parseInt(br.readLine()); // 회전 횟수 K
		int[][] info = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken()); // 회전시킬 바퀴의 번호
			info[i][1] = Integer.parseInt(st.nextToken()); // 방향(1:시계방향, -1:반시계방향)
		}

		for (int i = 0; i < K; i++) {
			visited = new boolean[5];
			// 1번 톱니바퀴를 회전시키는 경우
			if (info[i][0] == 1) {
				workWheel1(info[i][1]);
			} else if (info[i][0] == 2) {
				workWheel2(info[i][1]);
			} else if (info[i][0] == 3) {
				workWheel3(info[i][1]);
			} else if (info[i][0] == 4) {
				workWheel4(info[i][1]);
			}
		}
		int sum = 0;
		sum += wheel1.get(0) == 1 ? 1 : 0;
		sum += wheel2.get(0) == 1 ? 2 : 0;
		sum += wheel3.get(0) == 1 ? 4 : 0;
		sum += wheel4.get(0) == 1 ? 8 : 0;
		System.out.println(sum);
	}

	private static void workWheel1(int dir) {
		visited[1] = true;
		if (wheel1.get(2) != wheel2.get(6) && !visited[2]) {
			workWheel2(dir * -1);
		}
		rotate(wheel1, dir);
	}

	// 독립적으로 나를 기준으로 입력값이 왔을떄 1과 같은지 보고 3과 같은지 보고
	private static void workWheel2(int dir) {
		visited[2] = true;
		if (wheel2.get(6) != wheel1.get(2) && !visited[1]) {
			workWheel1(dir * -1);
		}
		if (wheel2.get(2) != wheel3.get(6) && !visited[3]) {
			workWheel3(dir * -1);
		}
		rotate(wheel2, dir);
	}

	private static void workWheel3(int dir) {
		visited[3] = true;
		if (wheel3.get(6) != wheel2.get(2) && !visited[2]) {
			workWheel2(dir * -1);
		}
		if (wheel3.get(2) != wheel4.get(6) && !visited[4]) {
			workWheel4(dir * -1);
		}
		rotate(wheel3, dir);

	}

	private static void workWheel4(int dir) {
		visited[4] = true;
		if (wheel4.get(6) != wheel3.get(2) && !visited[3]) {
			workWheel3(dir * -1);
		}
		rotate(wheel4, dir);

	}

	private static void rotate(List<Integer> wheel, int dir) {
		if (dir == 1) { // 시계방향 회전
			int lastIdx = wheel.size() - 1;
			int lastElement = wheel.get(lastIdx);
			wheel.add(0, lastElement);
			wheel.remove(wheel.size() - 1);
		}

		if (dir == -1) {// 반시계방향 회전
			wheel.add(wheel.get(0));
			wheel.remove(0);
		}

	}

}
