import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static List<Integer> kyuCard;
	static List<Integer> inCard;
	static boolean[] isSelected;
	static List<Integer> newPerm;
	static int kyuSum, inSum, kyuResult, inResult;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA6808_s_input.txt"));;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim()); //테스트케이스 수
		//T만큼 반복
		for(int tc=1; tc<=T; tc++) {
			
			kyuCard = new ArrayList<>();
			inCard = new ArrayList<>();
			newPerm = new ArrayList<>();
			isSelected = new boolean[9];
	
			st= new StringTokenizer(br.readLine());
			for(int card=0; card<9; card++) {
				kyuCard.add(Integer.parseInt(st.nextToken()));
			}
			
			for(int num=1; num<=18; num++) {
				if(!kyuCard.contains(num)) {
					inCard.add(num);
				}
			}
			kyuResult = 0;
			inResult = 0;
			perm(0);
			
			sb.append("#"+tc+" "+kyuResult + " " + inResult+"\n");
		}
		System.out.print(sb);
	}

	private static void perm(int idx) {
		//기저조건
		if(idx == 9) {

			kyuSum = 0;
			inSum = 0;

			for(int i=0; i<9; i++) {
				if(kyuCard.get(i) > newPerm.get(i)) {
					kyuSum += kyuCard.get(i) + newPerm.get(i);
				}
				else if(kyuCard.get(i) < newPerm.get(i)) {
					inSum += kyuCard.get(i) + newPerm.get(i);
				}
			}
			
			if(kyuSum > inSum) {
				kyuResult += 1;
			}
			else if(kyuSum < inSum) {
				inResult += 1;
			}
			
			return;
		}
		
		//유도파트
		for(int i=0; i<9; i++) {
			if(isSelected[i]) {
				continue;
			}
			newPerm.add(inCard.get(i));
			isSelected[i] = true;
			perm(idx+1);
			newPerm.remove(newPerm.size()-1);
			isSelected[i] = false;
		}
	}
}