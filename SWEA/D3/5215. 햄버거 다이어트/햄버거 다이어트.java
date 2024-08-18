import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{

	private static int totalCal, foods;
	private static int[][] array;
	private static boolean[] isSelected;
	private static int maxScore;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스 개수 입력받기
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			foods = Integer.parseInt(st.nextToken()); //재료의 수 입력받기
			totalCal = Integer.parseInt(st.nextToken()); //제한 칼로리 입력받기
			
			array = new int[foods][2];
			isSelected = new boolean[foods];
			for(int i=0; i<foods; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken()); //맛에 대한 점수 입력받기
				int cal = Integer.parseInt(st.nextToken()); //재료의 칼로리 입력받기
				
				array[i][0] = score;
				array[i][1] = cal;
			}
			maxScore = 0; // 각 테스트 케이스마다 최대 점수를 초기화
            subset(0, 0, 0);
            
            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
			
			//subset(0, 0, 0, maxScore);
		}
		System.out.print(sb);

	}
	
	private static void subset(int cnt, int currentScore, int currentCal) {
		if(cnt == foods) { //모든 원소가 다 처리되었다면
			if(currentCal <= totalCal) {
				if(currentScore > maxScore) {
					//maxScore=Math.max(currentScore, maxScore); 
					maxScore = currentScore;
				}
			}
			return;
		}
		
		
		//선택
		isSelected[cnt] = true;
		int scores = array[cnt][0];
		int calories = array[cnt][1];
		subset(cnt+1, scores + currentScore, calories + currentCal);
		
		//비선택
		isSelected[cnt] = false;
		subset(cnt+1, currentScore, currentCal);
	
	}
	

}