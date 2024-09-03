import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1208_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int dump = Integer.parseInt(br.readLine()); // 덤프 횟수

			st = new StringTokenizer(br.readLine());
			List<Integer> box = new ArrayList<>();
			for (int i = 0; i < 100; i++) {// 리스트에 100개의 상자 높이 저장
				box.add(Integer.parseInt(st.nextToken()));
			}
			// dump 작업
			for (int d = 0; d < dump; d++) {
				int max = Collections.max(box);
				int min = Collections.min(box);
				box.set(box.indexOf(max), max - 1);
				box.set(box.indexOf(min), min + 1);
			}

			int resultMax = Collections.max(box);
			int resultMin = Collections.min(box);

			sb.append("#" + tc + " ").append(resultMax - resultMin).append("\n");
		}
		System.out.print(sb);
	}
}
