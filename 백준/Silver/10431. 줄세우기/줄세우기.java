import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int P = Integer.parseInt(st.nextToken()); // 테스트케이스 수

		for (int tc = 1; tc <= P; tc++) {
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken()); // 테스트케이스 번호
			int totalCnt = 0;

			List<Integer> heightList = new ArrayList<>();

			for (int h = 0; h < 20; h++) {
				int height = Integer.parseInt(st.nextToken());
				int cnt = 0;
				int size = heightList.size();
				boolean check = false;

				if (size == 0) {
					heightList.add(height);
				} else {
					for (int i = 0; i < size; i++) {
						if (heightList.get(i) < height) {
							cnt++;
							continue;
						}
						heightList.add(i, height);
//						size++;
//						i++;
						totalCnt += size - cnt;
						check = true;
						break;

					}
					if (!check) {
						heightList.add(height);
					}
				}

			}
			sb.append(num).append(" ").append(totalCnt).append("\n");
		}
		System.out.print(sb);

	}

}
