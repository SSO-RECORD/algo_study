import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine()); //연산의 개수 입력받기
		int S = 0; //초기 공집합
		
		for(int i=0; i<M; i++) { //연산의 수만큼 반복
			st = new StringTokenizer(br.readLine());
			String mode = st.nextToken();
			
			if(mode.equals("add")) { //연산이 add라면
				int num = Integer.parseInt(st.nextToken());
				S |= (1 << (num-1));
			}
			else if(mode.equals("check")) { //연산이 check라면
				int num = Integer.parseInt(st.nextToken());
				sb.append((S & (1 << (num-1))) != 0 ? "1\n" : "0\n");
			}
			else if(mode.equals("remove")) { //연산이 remove라면
				int num = Integer.parseInt(st.nextToken());
				S &= ~(1 << (num-1));
			}
			else if(mode.equals("toggle")) { //연산이 toggle라면
				int num = Integer.parseInt(st.nextToken());
				S ^= 1 << (num-1);
			}
			else if(mode.equals("all")) { //연산이 all라면
				for(int x=1; x<=20; x++) { //모든 비트를 1로 설정
					S |= (1 << (x-1));
				}
			}
			else if(mode.equals("empty")) { //연산이 empty라면
				S = 0; //모든 비트를 0으로 설정
			}
		}
		System.out.print(sb);
			
	}
	
}


