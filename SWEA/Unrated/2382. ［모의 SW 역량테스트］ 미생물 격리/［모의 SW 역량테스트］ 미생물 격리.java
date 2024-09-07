import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Micro {
	int r;
	int c;
	int micro;
	int dir;

	public Micro(int r, int c, int micro, int dir) {
		super();
		this.r = r;
		this.c = c;
		this.micro = micro;
		this.dir = dir;
	}

}

public class Solution {

	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA2382_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 한 변에 있는 셀의 개수
			int M = Integer.parseInt(st.nextToken()); // 격리 시간
			int K = Integer.parseInt(st.nextToken()); // 미생물 군집의 수

			List<Micro> micros = new ArrayList<>();

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()); // 행의 위치
				int c = Integer.parseInt(st.nextToken()); // 열의 위치
				int micro = Integer.parseInt(st.nextToken()); // 미생물의 수
				int dir = Integer.parseInt(st.nextToken()); // 이동 방향

				micros.add(new Micro(r, c, micro, dir));
			}

			for (int m = 0; m < M; m++) {
				moveMicros(micros);
				manageMicros(micros);
			}

			int totalMicro = 0;
			for (Micro mc : micros) {
				totalMicro += mc.micro;
			}

			sb.append("#" + tc + " " + totalMicro + "\n");
		}
		System.out.print(sb);
	}

	// 군집을 이동시키는 메서드
	private static void moveMicros(List<Micro> micros) {

		// 각 군집의 이동
		for (Micro mc : micros) {
			int nr = 0;
			int nc = 0;
			if (mc.dir == 1) {
				nr = mc.r + dr[0];
				nc = mc.c + dc[0];
			} else if (mc.dir == 2) {
				nr = mc.r + dr[1];
				nc = mc.c + dc[1];
			} else if (mc.dir == 3) {
				nr = mc.r + dr[2];
				nc = mc.c + dc[2];
			} else if (mc.dir == 4) {
				nr = mc.r + dr[3];
				nc = mc.c + dc[3];
			}

			mc.r = nr;
			mc.c = nc;

			// 경계에 위치한 군집 처리
			isBound(mc);
		}

	}

	// 군집의 충돌을 처리하는 메서드
	private static void manageMicros(List<Micro> micros) {

		// 군집의 위치를 저장할 2차원 배열
		List<Micro>[][] map = new ArrayList[N][N];

		// 배열 초기화
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = new ArrayList<>();
			}
		}

		// 군집을 배열에 배치
		for (Micro mc : micros) {
			if (mc.r >= 0 && mc.r < N && mc.c >= 0 && mc.c < N) {
				map[mc.r][mc.c].add(mc);
			}
		}

		List<Micro> newMicros = new ArrayList<>();

		// 충돌 관리
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (map[row][col].size() >= 2) {
					int microSum = 0;
					int maxMicro = Integer.MIN_VALUE;
					int newDir = 0;
					for (Micro mc : map[row][col]) {
						microSum += mc.micro;
						if (mc.micro > maxMicro) {
							maxMicro = mc.micro;
							newDir = mc.dir;
						}
					}
					newMicros.add(new Micro(row, col, microSum, newDir));
				} else if (map[row][col].size() == 1) {
					newMicros.add(map[row][col].get(0));
				}

			}
		}

		micros.clear();
		micros.addAll(newMicros);

	}

	// 경계에 위치한 군집 처리 메서드
	public static boolean isBound(Micro mc) {
		if (mc.r == 0 || mc.r == N - 1 || mc.c == 0 || mc.c == N - 1) {
			int liveMicro = (int) Math.floor(mc.micro / 2);
			if (liveMicro == 0)
				return false;
			mc.micro = liveMicro;
			if (mc.dir == 1)
				mc.dir = 2;
			else if (mc.dir == 2)
				mc.dir = 1;
			else if (mc.dir == 3)
				mc.dir = 4;
			else if (mc.dir == 4)
				mc.dir = 3;

		}
		return true;
	}

}
