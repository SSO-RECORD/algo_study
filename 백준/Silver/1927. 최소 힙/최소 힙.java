import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//연산의 개수 N 입력 받기
		int N = Integer.parseInt(br.readLine());
		
		//우선순위가 낮은 숫자가 먼저 나오는 pq 선언
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int min_num;
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine()); //연산에 대한 정보를 나타내는 정수 x 입력받기
			
			if(x==0 && pq.size()==0) {
				list.add(0);
				//System.out.println(0);
			} else if(x == 0 && pq.size()!=0) {
				min_num = pq.poll();
				list.add(min_num);
				//System.out.println(pq.poll());
			} else {
				pq.add(x);
			}
		}
		for(int num : list) {
			System.out.println(num);
		}

	}

}
