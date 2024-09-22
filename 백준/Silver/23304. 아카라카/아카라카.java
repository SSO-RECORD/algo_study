import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        // 전체 문자열에 대해 조건 검사
        if (isAkaraka(str)) {
            System.out.println("AKARAKA");
        } else {
            System.out.println("IPSELENTI");
        }
    }

    // 문자열이 아카라카 조건을 만족하는지 확인하는 메소드
    private static boolean isAkaraka(String str) {
        // 전체가 회문인지 확인
        if (!isPalindrome(str)) {
            return false;
        }

        // 길이가 1 이하면 재귀 종료 (이미 회문임)
        if (str.length() <= 1) {
            return true;
        }

        // 문자열을 절반으로 나누어 재귀적으로 검사
        int mid = str.length() / 2;
        String left = str.substring(0, mid);
        String right = str.substring(str.length() % 2 == 0 ? mid : mid + 1);

        // 왼쪽 절반과 오른쪽 절반도 모두 아카라카 조건을 만족하는지 확인
        return isAkaraka(left) && isAkaraka(right);
    }

    // 주어진 문자열이 회문인지 확인하는 메소드
    private static boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
