import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		class Rank implements Comparable<Rank> {

			int id;
			int gold;
			int silver;
			int copper;

			public Rank(int id, int gold, int silver, int copper) {
				this.id = id;
				this.gold = gold;
				this.silver = silver;
				this.copper = copper;
			}

			@Override
			public int compareTo(Rank o) {
				if (this.gold != o.gold) {
					return o.gold - this.gold; // 금메달 기준 내림차순
				} else if (this.silver != o.silver) {
					return o.silver - this.silver; // 은메달 기준 내림차순
				} else {
					return o.copper - this.copper; // 동메달 기준 내림차순
				}
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 국가의 수
		int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가

		List<Rank> olimpic = new ArrayList<>();

		// 각 국가의 메달 정보를 읽어들여 리스트에 추가
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			int id = Integer.parseInt(st.nextToken()); // 국가 번호
			int gold = Integer.parseInt(st.nextToken()); // 금메달 수
			int silver = Integer.parseInt(st.nextToken()); // 은메달 수
			int copper = Integer.parseInt(st.nextToken()); // 동메달 수

			olimpic.add(new Rank(id, gold, silver, copper));
		}

		// 메달 수를 기준으로 내림차순 정렬
		Collections.sort(olimpic);

		int rank = 1; // 1등부터 시작
		int prevRank = -1; // 이전 순위를 저장
		int prevGold = -1, prevSilver = -1, prevCopper = -1; // 이전 국가의 금, 은, 동메달

		// 순위를 계산
		for (int i = 0; i < olimpic.size(); i++) {
			Rank currentRank = olimpic.get(i);

			// 동일한 메달 수를 가진 국가들은 같은 순위를 가짐
			if (currentRank.gold == prevGold && currentRank.silver == prevSilver && currentRank.copper == prevCopper) {
				// 순위는 그대로 유지
			} else {
				rank = i + 1; // 순위를 갱신 (0-based index에서 1-based index로 변환)
			}

			// K 국가의 순위를 찾으면 출력
			if (currentRank.id == K) {
				System.out.println(rank);
				return;
			}

			// 현재 국가의 메달 수를 저장
			prevGold = currentRank.gold;
			prevSilver = currentRank.silver;
			prevCopper = currentRank.copper;
		}
	}
}
