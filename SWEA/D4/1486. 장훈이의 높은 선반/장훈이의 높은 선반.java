import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, B; //N: 점원의 수, B: 탑의 높이
	private static int[] employHeight; //점원들의 키를 저장할 배열
	private static boolean[] visited; //키의 선택여부를 저장할 배열
	private static int result, min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스의 수 입력
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //점원의 수 N 입력
			B = Integer.parseInt(st.nextToken()); //탑의 높이 입력
			
			//각 점원의 키 입력
			employHeight = new int[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				employHeight[n] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			subset(0);
			
			sb.append("#"+tc+" "+min+"\n");
		}
		//출력
		System.out.print(sb);
	}

	private static void subset(int cnt) {
		//기저조건
		if(cnt == N) { //모든 원소가 처리되었다면
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(visited[i]) { //선택된 점원의 키들의 합을 sum에 저장
					sum += employHeight[i];
				}

			}
			result = sum - B;
			if (result >= 0) min = Math.min(min, result);
			return;
		}
		
		//유토파트
		
		//선택
		visited[cnt] = true;
		subset(cnt+1);
		
		//비선택
		visited[cnt] = false;
		subset(cnt+1);
	}

}
