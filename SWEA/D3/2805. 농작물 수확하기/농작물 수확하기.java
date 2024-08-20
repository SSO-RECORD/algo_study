/*
 * [문제]
 * 농장의 크기 N와 농작물의 가치가 주어질 때, 규칙에 따라 얻을 수 있는 수익은 얼마인지 구하기
 * 
 * [해결 방법]
 * 규칙을 찾으면 된다.
 * 나는 마름모를 수평으로 잘라서 위쪽 삼각형, 아래쪽 삼각형으로 나누었고,
 * 위쪽 삼각형에서 왼쪽과 오른쪽, 아래쪽 삼각형에서 왼쪽과 오른쪽을 나누어 
 * 규칙을 통해 가치의 합을 계산했다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스의 수 입력
		//T만큼 반복
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine()); //배열의 크기 N 입력
			int[][] farm = new int[N][N]; //NxN 크기의 배열 생성
			for(int i=0; i<N; i++) {
				String value = br.readLine();
				for(int j=0; j<N; j++) {
					farm[i][j] = value.charAt(j) -'0'; //나중에 연산을 위해 char->int로 변환
				}
			}
		
			int sum = 0; //합을 저장하기 위한 변수
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i <= N/2) { //삼각형 위쪽
						if(j<=N/2) { //위쪽에서 왼쪽
							if(i+j >= N/2) {
								sum += farm[i][j];
							}
						}
						if(j > N/2) { //위쪽에서 오른쪽
							if(j-i <= N/2) {
								sum += farm[i][j];
							}
						}
					}
					else if(i > N/2) { //삼각형 아래쪽
						if(j <= N/2) { //아래쪽에서 왼쪽
							if(i-j <= N/2) {
								sum += farm[i][j];
							}
						}
						if(j > N/2) { //아래쪽에서 오른쪽
							if(i+j <= (N/2) * 3) {
								sum += farm[i][j];
							}
						}
					}
				}
			}
			sb.append("#"+tc+" "+sum+"\n");
		}
		//출력
		System.out.print(sb);
	}

}
