import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, X, M;
    static int[] hamster;
    static int[][] record;
    static int maxHamster;
    static int[] result;
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/SWEA8275_sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim()); //테스트케이스 수
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //햄스터 우리의 개수
            X = Integer.parseInt(st.nextToken()); //우리에 햄스터가 0마리 이상 x마리이하
            M = Integer.parseInt(st.nextToken()); //기록의 수
            
            hamster = new int[N];
            result = new int[N];
            
            record = new int[M][3];
            for(int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                record[m][0] = Integer.parseInt(st.nextToken())-1; //l번우리에서
                record[m][1] = Integer.parseInt(st.nextToken())-1; //r번우리까지의
                record[m][2] = Integer.parseInt(st.nextToken()); //햄스터의 수 합이 s
            }
            
            maxHamster = Integer.MIN_VALUE;
            //우리에 햄스터를 배치하기 위한 중복 순열
            batchHamster(0);
            
            sb.append("#").append(tc).append(" ");
            if(maxHamster < 0) {
                sb.append(-1);
            }else {
                for(int i=0; i<result.length; i++) {
                    sb.append(result[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    //우리에 햄스터를 배치하는 메서드
    private static void batchHamster(int idx) {
        
        //기저조건
        if(idx == N) {
            for(int m=0; m<M; m++) {
                if(!checkHamster(m)) {
                	return;
                }
            }
            
            int totalHamster = 0;
            for(int i=0; i<hamster.length; i++) {
                totalHamster += hamster[i];
            }
            
            if(totalHamster > maxHamster) {
                maxHamster = totalHamster;
                result = Arrays.copyOf(hamster, N);
            }
            
            return;
        }
        
        //유도파트
        for(int i=0; i<=X; i++) {
            hamster[idx] = i;
            batchHamster(idx+1);
        }
    }

    private static boolean checkHamster(int m) {
        int sum = 0;
        for(int i=record[m][0]; i<=record[m][1]; i++) {
            sum += hamster[i];
        }
        
        if(sum != record[m][2]) {
            return false;
        }else {
            return true;
        }
        
    }

}