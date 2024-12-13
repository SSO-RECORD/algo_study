import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim()); // 전체 사람의 수

        int[] weightList = new int[N];
        int[] heightList = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weightList[i] = Integer.parseInt(st.nextToken());
            heightList[i] = Integer.parseInt(st.nextToken());
        }

        // 등수 계산
        int[] rank = new int[N];
        Arrays.fill(rank, 1); // 초기 등수는 모두 1등

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                // 현재 사람(i)보다 다른 사람(j)이 크면 i의 등수 증가
                if (weightList[i] < weightList[j] && heightList[i] < heightList[j]) {
                    rank[i]++;
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.print(rank[i] + " ");
        }
    }
}
