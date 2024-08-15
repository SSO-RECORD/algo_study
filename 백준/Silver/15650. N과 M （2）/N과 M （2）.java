/*
 * 주어진 N개의 숫자 중에서 M개의 숫자를 중복 없이 고르고, 오름차순으로 출력하는 문제를 해결하는 코드
 * 오름차순 순서 유지에 신경 써야 하므로, perm 함수에서 start 인자를 추가로 받아 처리함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static boolean[] isSelected;
	private static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//자연수 N, M 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N+1]; //숫자가 1~N까지이므로 선택여부를 체크 할 N+1크기의 배열 선언
		numbers = new int[M]; //N개의 숫자를 저장할 배열 생성
		
		// 순열 생성 함수 호출, depth는 0부터 시작, start는 1부터 시작
		perm(0, 1);
	}

	private static void perm(int depth, int start) {
		if(depth == M) { //선택한 숫자의 개수가 M과 같아지면
			for(int num : numbers) { //저장된 숫자들 출력
				System.out.print(num + " ");
			}
			System.out.println();
			return; //현재 호출을 종료하고 이전 상태로 돌아감
		}else {//선택한 숫자의 개수가 M이 아니라면
			for(int i=start; i<=N; i++) {//start부터 N까지 탐색
				if(isSelected[i]) {//이미 선택된 숫자라면 중복을 방지하기 위해 건너뜀
					continue;
				}
				//선택되지 않은 숫자라면
				isSelected[i] = true; //선택 상태로 변경
				numbers[depth] = i; //선택한 숫자를 배열에 저장
				perm(depth+1, i+1); // 다음 depth로 넘어가며, 선택한 숫자 i의 다음 숫자부터 시작하도록 설정
				isSelected[i] = false; // 선택 상태를 복구 (다음 탐색을 위해)
			}
		}	
	}
}
