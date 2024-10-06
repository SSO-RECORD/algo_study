import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim()); // 사진틀의 개수
		int total = Integer.parseInt(br.readLine().trim()); // 전체 학생의 총 추천 수
		int[] student = new int[101];

		Queue<Integer> queue = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < total; i++) {
			int recomStudent = Integer.parseInt(st.nextToken());

			if (queue.size() < N) { // 비어있는 사진틀이 존재하는 경우
				if (queue.contains(recomStudent)) { // 사진틀에 이미 추천받은 학생이 존재하는 경우
					student[recomStudent]++;
				} else { // 사진틀에 추천받은 학생이 존재하지 않는 경우
					queue.add(recomStudent);
					student[recomStudent]++;
				}
			} else { // 비어있는 사진틀이 존재하지 않는 경우
				if (queue.contains(recomStudent)) { // 사진틀에 이미 추천받은 학생이 존재하는 경우
					student[recomStudent]++;
				} else { // 사진틀에 추천받은 학생이 존재하지 않는 경우
					// 1. 추천수가 가장 적은 학생을 삭제한다.
					// 2. 추천수가 적은 학생이 여러명이라면, 가장 먼저 추천받았던 학생을 삭제한다.
					int recomCnt = Integer.MAX_VALUE;
					int removeStudentNo = 0;
					for (int no : queue) {
						if (student[no] < recomCnt) {
							recomCnt = student[no];
							removeStudentNo = no;
						}
					}
					// 학생의 사진 삭제
					queue.remove(removeStudentNo);
					student[removeStudentNo] = 0;

					// 새로 추천받은 학생 등록
					queue.add(recomStudent);
					student[recomStudent]++;
				}
			}
		}

		int len = queue.size();
		int[] arr = new int[len];
		for (int j = 0; j < len; j++) {
			arr[j] = queue.poll();
		}

		Arrays.sort(arr);
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}

}
