import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //컵의 수
		int X = Integer.parseInt(st.nextToken()); //공이 숨겨진 컵의 번호
		int K = Integer.parseInt(st.nextToken()); //컵의 위치를 맞바꾸는 횟수
		
		int[] game = new int[N];
		game[X-1] = 1; //공이 들어있는 위치에 1 표시
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int temp = game[A-1];
			game[A-1] = game[B-1];
			game[B-1] = temp;
		}
		
		for(int i=0; i<game.length; i++) {
			if(game[i] == 1) {
				System.out.println(i+1);
			}
		}
	}
}
