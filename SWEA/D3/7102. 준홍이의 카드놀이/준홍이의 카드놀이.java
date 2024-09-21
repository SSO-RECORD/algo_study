import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

//	static int N, M;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA7012_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N + 1][M + 1];
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= M; col++) {
					arr[row][col] = row + col;
				}
			}

			List<Integer> result = findMostFrequencyNumber(arr);

			sb.append("#" + tc + " ");
			for (int value : result) {
				sb.append(value + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static List<Integer> findMostFrequencyNumber(int[][] array) {

		// 각 숫자의 등장 횟수를 기록할 HashMap
		Map<Integer, Integer> frequencyNumber = new HashMap<>();

		// 2차원 배열을 순회하여 숫자별 등장 횟수를 기록
		for (int[] row : array) {
			for (int num : row) {
				if (num != 0) {
					frequencyNumber.put(num, frequencyNumber.getOrDefault(num, 0) + 1);
				}
			}
		}

		// 해시맵 출력 테스트
//		for (Map.Entry<Integer, Integer> entry : frequencyNumber.entrySet()) {
//			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//		}

		// 가장 높은 빈도 찾기
		int maxFrequency = 0;
		for (int freq : frequencyNumber.values()) {
			if (freq > maxFrequency) {
				maxFrequency = freq;
			}
		}

		//
		List<Integer> mostFrequentNumbers = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : frequencyNumber.entrySet()) {
			if (entry.getValue() == maxFrequency) {
				mostFrequentNumbers.add(entry.getKey());
			}
		}

		// 정렬
		Collections.sort(mostFrequentNumbers);

		return mostFrequentNumbers;
	}
}
