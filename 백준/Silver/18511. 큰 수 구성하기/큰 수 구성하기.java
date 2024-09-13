import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static List<Integer> arr;
    static List<Integer> numbers;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 자연수 입력받기
        K = Integer.parseInt(st.nextToken()); // 입력받을 원소의 개수

        arr = new ArrayList<>();
        numbers = new ArrayList<>();
        // K개 만큼 원소 입력받기
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        
        max = Integer.MIN_VALUE;
        // 1자리부터 N의 자릿수만큼 반복
        for (int i = 1; i <= (int)(Math.log10(N)+1); i++) {
            perm(0, i);
        }
        System.out.println(max);
    }

    private static void perm(int idx, int targetLen) {
        // 기저조건
        if (idx == targetLen) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < numbers.size(); i++) {
                str.append(numbers.get(i));
            }
            int sum = Integer.parseInt(str.toString());
            if (sum > N) {
                return;
            }
            max = Math.max(sum, max);
            return;
        }

        // 유도파트
        for (int i = 0; i < K; i++) {
            numbers.add(arr.get(i));
            perm(idx + 1, targetLen);
            numbers.remove(numbers.size()-1);
        }
    }
}
