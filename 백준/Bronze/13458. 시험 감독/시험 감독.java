import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //시험장의 개수
		int[] testPlace = new int[N];
		//각 시험장의 응시자 수 저장
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) { 
			testPlace[i] = Integer.parseInt(st.nextToken()); 
		}
		
		st = new StringTokenizer(br.readLine());
		int director = Integer.parseInt(st.nextToken()); //총감독이 관리할 수 있는 응시자 수
		int subDirector = Integer.parseInt(st.nextToken()); //부감독이 관리할 수 있는 응시자 수
		
		long result = 0;
		
		for(int i=0; i<N; i++) {
			int minus = testPlace[i] - director;
			if(minus < 0) {
				testPlace[i] = 0;
			} else {
				testPlace[i] = minus;
			}
		}
		
		for(int i=0; i<N; i++) {
			int quo = testPlace[i] / subDirector;
			//System.out.println(quo);
			result += quo;
			if(testPlace[i] % subDirector != 0) {
				result += 1;
			}
			//System.out.println("result: "+ result);
		}
		System.out.println(result + testPlace.length);
	}
}
