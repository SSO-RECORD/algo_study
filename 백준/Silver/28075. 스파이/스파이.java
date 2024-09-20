import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 임수를 수행하는 총 일수
		M = Integer.parseInt(st.nextToken()); // 최소기여도

		st = new StringTokenizer(br.readLine());
		// 정보 수집 임무를 수족관, 시청, 학교에서 수행했을 때 얻을 수 있는 진척도
		int info_aqa = Integer.parseInt(st.nextToken());
		int info_hall = Integer.parseInt(st.nextToken());
		int info_school = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		// 감시 임무를 수족관, 시청, 학교에서 수행했을 때 얻을 수 있는 진척도
		int watch_aqa = Integer.parseInt(st.nextToken());
		int watch_hall = Integer.parseInt(st.nextToken());
		int watch_school = Integer.parseInt(st.nextToken());

		arr = new int[2][3];
		arr[0][0] = info_aqa;
		arr[0][1] = info_hall;
		arr[0][2] = info_school;
		arr[1][0] = watch_aqa;
		arr[1][1] = watch_hall;
		arr[1][2] = watch_school;

		cnt = 0;
		duty(0, 0, -1);
		System.out.println(cnt);

	}

	private static void duty(int idx, int curContri, int before) {// 선택된 개수, 기여도, 전날 수행한 장소 기록 변수
		// 기저조건
		if (idx == N) {
			if (curContri >= M) {
				cnt++;
			}
			return;
		}

		// 유도파트
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == before) {
					duty(idx + 1, curContri + arr[i][j] / 2, j);
				} else { // j != before
					duty(idx + 1, curContri + arr[i][j], j);
				}
			}
		}
	}
}
