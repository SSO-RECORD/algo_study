import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 변화 관찰 일 수
		int M = Integer.parseInt(st.nextToken()); // 스트레스의 양

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		if(arr[0]>=M) cnt +=1;
		if(arr[0] < 0) arr[0] = 0;
		for(int i=1; i<N; i++) {
			arr[i] = arr[i-1] + arr[i];
			if(arr[i] < 0) arr[i] = 0;
			if(arr[i] >= M) cnt+=1;
		}

		System.out.println(cnt);
	}

}
