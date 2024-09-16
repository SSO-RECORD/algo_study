import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA5432_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		int result;
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			List<Character> list = new ArrayList<>();
			result = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') { // 문자가 '('인 경우
					list.add(str.charAt(i));
				} else { // 문자가 ')'인 경우
					list.remove(list.size() - 1);

					if (str.charAt(i - 1) == '(') { // 레이저인 경우
						result += list.size();
					} else { // 막대기의 끝인 경우
						result += 1;
					}
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.print(sb);
	}
}
