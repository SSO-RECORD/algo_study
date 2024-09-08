import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class fireball {
	int r, c, m, s, d;

	public fireball(int r, int c, int m, int s, int d) {
		super();
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		int M = Integer.parseInt(st.nextToken()); // 파이어볼 발사 개수
		int K = Integer.parseInt(st.nextToken()); // 이동 횟수

		List<fireball> fireballs = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 파이어볼의 행 위치
			int c = Integer.parseInt(st.nextToken()) - 1; // 파이어볼의 열 위치
			int m = Integer.parseInt(st.nextToken()); // 파이어볼의 질량
			int s = Integer.parseInt(st.nextToken()); // 파이어볼의 속력
			int d = Integer.parseInt(st.nextToken()); // 파이어볼의 이동 방향

			// 리스트에 객체 추가
			fireballs.add(new fireball(r, c, m, s, d));
		}

		// 파이어볼의 이동
		for (int k = 0; k < K; k++) {
			moveFireball(fireballs);
			manageFireball(fireballs);
		}

		int totalMass = 0;
		for (fireball fb : fireballs) {
			totalMass += fb.m;
		}
		System.out.println(totalMass);
	}

	// 파이어볼의 충돌 처리 등 파이어볼을 관리하는 메서드
	private static void manageFireball(List<fireball> fireballs) {
		// 파이어볼의 위치를 저장할 2차원 배열
		List<fireball>[][] grid = new ArrayList[N][N];

		// 배열의 각 요소를 리스트로 초기화
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				grid[row][col] = new ArrayList<>();
			}
		}

		// 배열에 파이어볼 배치
		for (fireball fb : fireballs) {
			grid[fb.r][fb.c].add(fb);
		}
		
		//핸들링 된 파이어볼들을 저장할 새로운 리스트
		List<fireball> newFireballs = new ArrayList<>();

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (grid[row][col].size() >= 2) { // 2개 이상의 파이어볼이 충돌했다면

					int sumMass = 0;
					int sumSpeed = 0;
					boolean allEven = true;
					boolean allOdd = true;

					for (fireball fb : grid[row][col]) {
						sumMass += fb.m;
						sumSpeed += fb.s;

						if (fb.d % 2 == 0) {
							allOdd = false;
						} else {
							allEven = false;
						}
					}

					int newMass = sumMass / 5;
					int newSpeed = sumSpeed / grid[row][col].size();

					if (newMass > 0) {
						for (int k = 0; k < 4; k++) {
							int newDir = (allOdd || allEven) ? 2 * k : 2 * k + 1;
							newFireballs.add(new fireball(row, col, newMass, newSpeed, newDir));
						}
					}
				} else if (grid[row][col].size() == 1) { //칸에 파이어볼의 개수가 1개라면 그대로 유지
					newFireballs.add(grid[row][col].get(0));
				}
			}
		}
		fireballs.clear();
		fireballs.addAll(newFireballs);
	}

	// 파이어볼을 이동시키는 메서드
	private static void moveFireball(List<fireball> fireballs) {
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

		for (fireball fb : fireballs) {
			int nr = (fb.r + dr[fb.d] * fb.s) % N;
			int nc = (fb.c + dc[fb.d] * fb.s) % N;

			if (nr < 0)
				nr = nr + N;
			if (nc < 0)
				nc = nc + N;

			fb.r = nr;
			fb.c = nc;
		}

	}

}