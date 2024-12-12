import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int P = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수

		for (int tc = 1; tc <= P; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken()); // 테스트케이스 번호

			List<Integer> heightList = new ArrayList<>();
			int totalCnt = 0;

			for (int h = 0; h < 20; h++) {
				int height = Integer.parseInt(st.nextToken());

				// 이진탐색으로 삽입 위치 찾기
				// height값이 리스트에 존재하면 해당 인덱스 리턴, 없으면 -(삽입위치 + 1) 리턴
				int pos = Collections.binarySearch(heightList, height);
				if (pos < 0) {
					pos = -(pos + 1);
				}

				// 삽입 및 이동 횟수 계산
				heightList.add(pos, height);
				totalCnt += heightList.size() - pos - 1;
			}
			sb.append(num).append(" ").append(totalCnt).append("\n");
		}
		System.out.print(sb);
	}

}
