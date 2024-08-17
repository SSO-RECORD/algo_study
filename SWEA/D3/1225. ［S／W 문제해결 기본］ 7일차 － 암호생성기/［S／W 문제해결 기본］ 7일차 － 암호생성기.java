import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//10개의 테스트케이스를 처리하기 위한 반복문
		for(int i=1; i<=10; i++) {
			Queue<Integer> q = new LinkedList<>(); //암호 생성을 위한 큐
			int tc_num = Integer.parseInt(br.readLine()); //테스트케이스 번호 입력받기
			int cnt = 0; //사이클을 세기 위한 변수
			st = new StringTokenizer(br.readLine(), " ");
			
			//큐에 입력받은 8개의 데이터를 집어넣음
			while(st.hasMoreTokens()) {
				q.offer(Integer.parseInt(st.nextToken()));
			}

			//암호 생성 과정
			while(true) {
				int first = q.poll(); //큐의 첫번째 값을 꺼내 변수 first에 저장
				cnt++; //사이클 카운트 증가
				int last = first - cnt; //첫번째 값에서 현재 사이클 값을 뺀 값을 last에 저장
				if(last > 0) q.add(last); //last 값이 0보다 크면 큐에 다시 삽입
				else { //last 값이 0 이하가 되면 큐에 0을 삽입하고 종료
					q.add(0);
					break;
				}
				if(cnt == 5) cnt = 0; //사이클이 5까지 증가하면 다시 0으로 초기화
			}
			
			sb.append("#").append(tc_num).append(" ");
			for(int num : q) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
