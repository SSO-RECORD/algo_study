/*
 * [문제 설명]
 * 주어진 정수 N에 대해 N이 2가 될 때까지 조작해야 하는 횟수의 최솟값을 구하는 문제이다.
 * 
 * [내가 생각한 알고리즘]
 * BFS 알고리즘을 이용하여 문제를 해결하려고 하였으나, N이 최대 10^12까지 가능하여 메모리 초과가 발생하게 된다.
 * 따라서, BFS는 이 문제를 푸는 데 적합하지 않다.
 * 
 * [작성된 알고리즘]
 * 1. N이 2가 될 때까지 while 루프를 돌며 N의 상태를 갱신한다.
 * 2. 제곱근이 정수라면 N을 그 제곱근으로 바꾸고 연산 횟수를 1 증가시킨다.
 * 3. 제곱근이 정수가 아니라면, N보다 큰 가장 작은 정수 제곱을 찾고, 그 차이를 연산 횟수에 더한 후 N을 그 정수 제곱으로 갱신한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스 수 입력받기
		//T만큼 반복
		for(int tc=1; tc<=T; tc++) {
			long N = Long.parseLong(br.readLine()); //정수 N 입력받기
			cnt = 0;
			findTwo(N); //함수호출
			sb.append("#"+tc+" "+cnt+"\n");
		}
		//출력
		System.out.print(sb);
	}

	private static void findTwo(long n) {
		while(n != 2) {
			if(Math.sqrt(n)%1==0) { //제곱근이 정수라면
				n = (long) Math.sqrt(n); // 새로운 n으로 업데이트
				cnt += 1;
			}
			else { //제곱근이 정수가 아니라면
				long target = (long)Math.ceil(Math.sqrt(n));
				cnt += Math.pow(target, 2) - n;
				n = (long) Math.pow(target, 2); // 새로운 n으로 업데이트
			}
		}		
	}
}
