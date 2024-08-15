/*
 * 여러 가지 재료 중 일부를 선택하여 최대한의 점수를 얻는 '조합'을 찾는 문제 
 * 단, 선택한 재료들의 칼로리 합이 주어진 제한 칼로리를 넘지 않아야 함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    private static int foods, limitCal; //foods: 재료의 수, limitCal: 제한 칼로리
    private static ArrayList<int[]> array; // 각 재료의 [score, cal]을 저장할 리스트
    private static int maxScore; // 최대 점수를 저장할 변수

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //테스트케이스 갯수 입력받기
        int T = Integer.parseInt(br.readLine());
        //재료의 수, 제한칼로리 입력받기
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            foods = Integer.parseInt(st.nextToken());
            limitCal = Integer.parseInt(st.nextToken());

            array = new ArrayList<>();
            maxScore = 0;
            //재료의 갯수 만큼 재료정보(점수, 칼로리) 입력받기
            for (int i = 0; i < foods; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int cal = Integer.parseInt(st.nextToken());
                array.add(new int[] { score, cal });
            }

            // 조합을 시작하는 메서드 호출
            comb(0, 0, 0); 

            // 결과 출력
            System.out.println("#" + tc + " " + maxScore);
        }
    }

    private static void comb(int index, int currentScore, int currentCal) {
        // 제한 칼로리 초과 시 종료
        if (currentCal > limitCal) {
            return;
        }

        // 현재 조합의 점수가 최대 점수인지 확인하여 갱신
        if (currentScore > maxScore) {
            maxScore = currentScore;
        }

        // 재귀적으로 다음 재료를 고려
        for (int i = index; i < foods; i++) {
            int score = array.get(i)[0]; //현재 재료의 점수
            int cal = array.get(i)[1]; //현재 재료의 칼로리
            //다음 재료를 포함한 새로운 조합으로 재귀 호출
            comb(i + 1, currentScore + score, currentCal + cal);
        }
    }
}