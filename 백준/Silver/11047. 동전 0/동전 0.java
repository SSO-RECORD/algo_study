import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //동전의 총 종류수 입력
		int K = Integer.parseInt(st.nextToken()); //가치의 합 입력
		int cnt = 0;
		
		Integer[] coin = new Integer[N];
		for(int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coin, Collections.reverseOrder());
		
//		for(int i=0; i<N; i++) {
//			System.out.print(coin[i] + " ");
//		}
		
		for(int i=0; i<N; i++) {
			if(K == 0) {
				break;
			}
			if(coin[i] > K) {
				continue;
			}
			cnt += K / coin[i];
			int remain = K % coin[i];
			K = remain;
		}
		
		System.out.println(cnt);
	}

}
