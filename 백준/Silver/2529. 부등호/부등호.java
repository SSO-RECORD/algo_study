import java.io.*;
import java.util.*;

public class Main {

	static int k;
	static char[] sign;
	static int[] numArr;
	static boolean[] isSelected;
	static long minValue;
	static long maxValue;
	static String minStr;
	static String maxStr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine().trim());
		sign = new char[k];
		numArr = new int[k + 1];
		isSelected = new boolean[10];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			sign[i] = st.nextToken().charAt(0);
		}

		minValue = Long.MAX_VALUE;
		maxValue = Long.MIN_VALUE;
		minStr = "";
		maxStr = "";

		// 0~9의 수 순열 만들기
		perm(0);

		System.out.println(maxStr);
		System.out.println(minStr);

	}

	private static void perm(int idx) {
		// 기저조건
		if (idx == k + 1) {
			String num = "";

			for (int x = 0; x < sign.length; x++) {
				if (sign[x] == '>' && numArr[x] > numArr[x + 1]) {
					num += numArr[x];
				} else if (sign[x] == '<' && numArr[x] < numArr[x + 1]) {
					num += numArr[x];
				} else {
					return;
				}
			}
			num += numArr[numArr.length - 1];

			if (Long.parseLong(num) < minValue) {
				minStr = num;
				minValue = Long.parseLong(num);
			}
			if (Long.parseLong(num) > maxValue) {
				maxStr = num;
				maxValue = Long.parseLong(num);
			}
			return;
		}

		// 유도파트
		for (int i = 0; i <= 9; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				numArr[idx] = i;
				perm(idx + 1);
				isSelected[i] = false;
			}
		}

	}

}
