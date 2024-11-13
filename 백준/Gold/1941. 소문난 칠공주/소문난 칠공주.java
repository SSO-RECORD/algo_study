import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static char[][] student;
	static int[] number;
	static boolean[] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		student = new char[5][5];
		number = new int[7];

		for (int row = 0; row < 5; row++) {
			String str = br.readLine();
			for (int col = 0; col < 5; col++) {
				student[row][col] = str.charAt(col);
			}
		}

		int[] studentNo = new int[25];
		for (int i = 0; i < 25; i++) {
			studentNo[i] = i;
		}

		result = 0;
		comb(0, 0);
		System.out.println(result);
	}

	// 7명의 학생을 선택하는 메서드
	private static void comb(int idx, int start) {

		// 기저조건
		if (idx == 7) {
			if (!adjCheck()) {
				return;
			}
			if (checkSom()) {
				result++;
			}
			return;
		}

		// 유도파트
		for (int i = start; i < 25; i++) {
			number[idx] = i;
			comb(idx + 1, i + 1);
		}
	}

	// 이다솜파가 4명이상인지 체크
	private static boolean checkSom() {
		int cnt = 0;
		for (int no : number) {
			int r = no / 5;
			int c = no % 5;

			if (student[r][c] == 'S') {
				cnt++;
			}
		}

		if (cnt >= 4) {
			return true;
		} else {
			return false;
		}

	}

	// 7명이 서로 인접해있는지 체크하는 메서드
	private static boolean adjCheck() {

		Queue<Integer> q = new LinkedList<>();
		q.add(number[0]);
		visited = new boolean[25];
		visited[number[0]] = true;

		int count = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();
			int curR = cur / 5;
			int curC = cur % 5;

			for (int d = 0; d < 4; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];

				if (isIn(nr, nc) && !visited[nr * 5 + nc] && containsNumber(nr * 5 + nc)) {
					q.add(nr * 5 + nc);
					visited[nr * 5 + nc] = true;
					count++;
				}
			}
		}

		if (count != 7) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean containsNumber(int pos) {
		for (int num : number) {
			if (num == pos) {
				return true;
			}
		}
		return false;
	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < 5 && c >= 0 && c < 5);
	}

}
