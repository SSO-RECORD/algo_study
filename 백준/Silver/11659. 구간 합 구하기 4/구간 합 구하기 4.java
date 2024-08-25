import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] number;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); //수 N개 입력받기
		int M = Integer.parseInt(st.nextToken()); //합을 구해야하는 횟수 M 입력받기
		
		number = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) {
			number[n] = Integer.parseInt(st.nextToken());
		}
		for(int n=1; n<N; n++) {
			number[n] = number[n-1] + number[n];
		}
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			int result = 0;
			if(i==1) {
				result = number[j-1];
			}
			else {
				result = number[j-1] - number[i-2];
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
