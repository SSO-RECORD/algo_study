import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] card;
	static int[] numbers;
	static List<Integer> sumList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //카드의 개수
		M = Integer.parseInt(st.nextToken()); 
		
		card = new int[N];
		numbers = new int[3];
		sumList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) {
			card[n] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0);
		Collections.sort(sumList);
		System.out.println(sumList.get(sumList.size()-1));
	}

	private static void comb(int idx, int start) {
		//기저조건
		if(idx == 3) {
			int sum=0;
			for(int i=0; i<3; i++) {
				sum += numbers[i];
			}
			if(sum <= M) {
				sumList.add(sum);
			}
			return;
		}
		
		//유도파트
		for(int i=start; i<N; i++) {
			numbers[idx] = card[i];
			comb(idx+1, i+1);
		}
	}

}
