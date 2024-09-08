import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class fireBall {
	int r, c, m, s, d;

	public fireBall(int r, int c, int m, int s, int d) {
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
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자의 크기 NxN
		int M = Integer.parseInt(st.nextToken()); // 파이어볼 발사 개수
		int K = Integer.parseInt(st.nextToken()); // 이동 횟수

		// 파이어볼들을 저장할 ArrayList
		ArrayList<fireBall> fireballs = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 파이어볼의 행 위치
			int c = Integer.parseInt(st.nextToken()) - 1; // 파이어볼의 열 위치
			int m = Integer.parseInt(st.nextToken()); // 파이어볼의 질량
			int s = Integer.parseInt(st.nextToken()); // 파이어볼의 속력
			int d = Integer.parseInt(st.nextToken()); // 파이어볼의 방향

			// 파이어볼 객체 생성 후 리스트에 추가
			fireballs.add(new fireBall(r, c, m, s, d));
		}

		// 파이어볼 정보 출력
//		for (fireBall fb : fireballs) {
//			System.out
//					.println("파이어볼 위치: (" + fb.r + ", " + fb.c + "), 질량: " + fb.m + ", 속도: " + fb.s + ", 방향: " + fb.d);
//		}
		
		for (int k = 0; k < K; k++) {
		    moveFireballs(fireballs);  // 파이어볼 이동
		    manageFireballs(fireballs); // 충돌 처리
		}
		
		int totalMass = 0;
		for (fireBall fb : fireballs) {
		    totalMass += fb.m;
		}

		System.out.println(totalMass);
	}

	// 파이어볼을 이동시키는 메서드
	public static void moveFireballs(ArrayList<fireBall> fireballs) {
		for (fireBall fb : fireballs) {
			
			 // 모듈로 연산을 사용하여 위치를 계산
            int nr = (fb.r + dr[fb.d] * fb.s) % N;
            int nc = (fb.c + dc[fb.d] * fb.s) % N;

            // 음수 인덱스를 처리
            if (nr < 0) nr += N;
            if (nc < 0) nc += N;

            fb.r = nr;
            fb.c = nc;
			// 현재 위치에서 속도와 방향에 따라 파이어볼 이동
//			int nr = fb.r + dr[fb.d] * fb.s;
//			int nc = fb.c + dc[fb.d] * fb.s;
//
//			if (nr >= N) {
//				nr = nr - N;
//			} else if (nr < 0) {
//				nr = nr + N;
//			}
//
//			if (nc >= N) {
//				nc = nc - N;
//			} else if (nc < 0) {
//				nc = nc + N;
//			}
//
//			fb.r = nr;
//			fb.c = nc;
		}
	}

	// 파이어볼의 충돌을 처리해주는 메서드
	public static void manageFireballs(ArrayList<fireBall> fireballs) {

		// 파이어볼의 위치를 저장할 2차원 배열
		ArrayList<fireBall>[][] grid = new ArrayList[N][N];

		// 초기화
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				grid[row][col] = new ArrayList<>();
			}
		}

		// 파이어볼을 배열에 배치
		for (fireBall fb : fireballs) {
			grid[fb.r][fb.c].add(fb);
		}

		// 새로운 파이어볼 리스트
		ArrayList<fireBall> newFireball = new ArrayList<>();

		// 충돌 관리
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (grid[row][col].size() > 1) {
					int sumM = 0;
					int sumS = 0;
					int count = grid[row][col].size();
					boolean allEven = true;
					boolean allOdd = true;

					for (fireBall fb : grid[row][col]) {
						sumM += fb.m;
						sumS += fb.s;
						if (fb.d % 2 == 0) {
							allOdd = false; // 짝수 방향
						} else {
							allEven = false; // 홀수 방향
						}

					}

					// 충돌 후 새로 생긴 파이어볼 계산
					int newMass = sumM / 5;
					int newSpeed = sumS / count;

					// 질량이 0이면 파이어볼은 소멸
					if (newMass > 0) {
						// 4개의 파이어볼로 분리
						for (int k = 0; k < 4; k++) {
							int newDir = (allEven || allOdd) ? 2 * k : 2 * k + 1; // 모두 홀수/짝수 방향인지에 따라 결정
							newFireball.add(new fireBall(row, col, newMass, newSpeed, newDir));
						}
					}
				}
				else if (grid[row][col].size() == 1) {
                    // 충돌이 없으면 기존 파이어볼 유지
                    newFireball.add(grid[row][col].get(0));
                }
			}
		}
		fireballs.clear();
        fireballs.addAll(newFireball); // 새로운 파이어볼 리스트로 갱신
	}

}