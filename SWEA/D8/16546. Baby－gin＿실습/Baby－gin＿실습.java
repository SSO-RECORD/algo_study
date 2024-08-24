import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	private static int[] cardArr;
	private static int[] newArr;
	private static String result;
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA16546_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스의 수 입력받기
		//T만큼 반복
		for(int tc=1; tc<=T; tc++) {
			String cardNum = br.readLine(); //6장의 카드의 숫자들 입력받기
			cardArr = new int[6]; //카드를 저장할 배열
			newArr = new int[6]; //순서를 조합한 카드를 저장할 배열
			result = "false"; //결과값을 false로 초기화
			isSelected = new boolean[6]; //카드 선택여부 체크를 위한 배열
			//입력받은 문자열로 된 카드를 int형으로 바꿔서 배열에 저장
			for(int i=0; i<cardArr.length; i++) {
				cardArr[i] = cardNum.charAt(i) -'0';
			}
			//함수 호출
			perm(0);
			
			sb.append("#" + tc + " " + result + "\n");
		}
		//결과 출력
		System.out.print(sb);
	}
	
	private static void perm(int idx) {
		//기저조건
		if(idx == 6) { // 숫자의 조합이 완료되었을 때
			// 앞 3자리와 뒤 3자리의 조합이 baby-gin인지 검사
			if(run1(newArr) && run2(newArr)) result = "true";
			else if(run1(newArr) && triplet2(newArr)) result = "true";
			else if(triplet1(newArr) && run2(newArr)) result = "true";
			else if(triplet1(newArr) && triplet2(newArr)) result = "true";
			return;
		}
		//유도파트
		//카드 배열의 모든 순서를 조합
		for(int i=0; i<6; i++) {
			if(isSelected[i]) continue; // 이미 선택된 카드는 넘어감
			isSelected[i] = true; // 카드 선택
			newArr[idx] = cardArr[i]; // 선택된 카드를 newArr에 저장
			perm(idx+1); // 다음 카드 선택
			isSelected[i] = false; // 카드 선택 해제
		}
	}
	
	// 카드 배열의 첫 3자리가 run인지 확인
	private static boolean run1(int[] newArr){
		if((newArr[1] == newArr[0] + 1) && (newArr[2] == newArr[1] + 1)) {
			return true;
		}
		return false;
	}
	// 카드 배열의 마지막 3자리가 run인지 확인
	private static boolean run2(int[] newArr){
		if((newArr[4] == newArr[3] + 1) && (newArr[5] == newArr[4] + 1)) {
			return true;
		}
		return false;
	}
	// 카드 배열의 첫 3자리가 triplet인지 확인
	private static boolean triplet1(int[] newArr){
		if((newArr[0] == newArr[1] && newArr[1] == newArr[2])) {
			return true;
		}
		return false;
	}
	// 카드 배열의 마지막 3자리가 triplet인지 확인
	private static boolean triplet2(int[] newArr){
		if((newArr[3] == newArr[4] && newArr[4] == newArr[5])) {
			return true;
		}
		return false;
	}
}
