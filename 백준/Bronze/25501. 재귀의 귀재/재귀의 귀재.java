import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			cnt = 0;

			System.out.println(isPalindrome(str) + " " + cnt);
		}

	}

	private static int isPalindrome(String s) {

		cnt++;
		return recursion(s, 0, s.length() - 1);

	}

	private static int recursion(String s, int l, int r) {
		if (l >= r)
			return 1;
		else if (s.charAt(l) != s.charAt(r))
			return 0;
		else {
			cnt++;
			return recursion(s, l + 1, r - 1);
		}
	}

}
