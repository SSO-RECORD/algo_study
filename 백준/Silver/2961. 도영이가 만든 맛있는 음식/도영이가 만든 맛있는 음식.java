import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[][] food;
	private static boolean[] isSelected;
	private static int minValue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine()); //재료의 개수 N 입력받기
		food = new int[N][2];
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			food[n][0] = Integer.parseInt(st.nextToken()); //신맛 입력받기
			food[n][1] = Integer.parseInt(st.nextToken()); //쓴맛 입력받기
		}
		minValue = Integer.MAX_VALUE;
		if(N == 1) {
			System.out.println(Math.abs(food[0][0] - food[0][1]));
		}
		else {
			subset(0, 1, 0, 0);
			System.out.println(minValue);
		}
	}

	private static void subset(int idx, int sour, int bitter, int cnt) {
		// 기저조건
		if(idx == N) {
			if(cnt > 0 && Math.abs(sour - bitter) < minValue) {
				minValue = Math.abs(sour - bitter);
			}
			return;
		}
		// 유도파트
		int sours = food[idx][0];
		int bitters = food[idx][1];
		//선택
		subset(idx+1, sour * sours, bitter + bitters, cnt+1);
		//비선택
		subset(idx+1, sour, bitter, cnt);
	}

}
