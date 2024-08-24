import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] numbers;
	private static boolean[] isSelected;
	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //자연수 N 입력받기
		M = Integer.parseInt(st.nextToken()); //순열의 길이 M 입력받기
		
		numbers = new int[M]; //크기가 M인 배열 생성
		isSelected = new boolean[N+1];
		perm(0); //파라미터는 현재 선택된 원소의 개수
	}

	private static void perm(int idx) {
		// 기저조건
		if(idx == M) {
			for(int num : numbers) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		//유도파트
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			numbers[idx] = i;
			perm(idx+1);
			isSelected[i] = false;
		}
	}
}
