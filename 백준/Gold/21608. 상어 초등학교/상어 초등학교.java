import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] studentNum;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // N 입력받기

        int student; // 학생번호 변수
        int like1, like2, like3, like4; // 선호하는 학생 번호
        studentNum = new int[N * N]; // 학생번호(student)를 저장할 변수
        int[][] likeNum = new int[N * N][4]; // 선호하는 학생 4명의 번호를 저장할 변수
        int[][] emptyCnt = new int[N][N];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        // 새로운 class 배열 생성
        int[][] classArray = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine()); // 현재 학생의 번호와 선호하는 학생의 번호를 입력받음
            student = Integer.parseInt(st.nextToken());
            like1 = Integer.parseInt(st.nextToken());
            like2 = Integer.parseInt(st.nextToken());
            like3 = Integer.parseInt(st.nextToken());
            like4 = Integer.parseInt(st.nextToken());

            studentNum[i] = student; // 학생의 번호를 배열에 저장
            likeNum[i] = new int[]{like1, like2, like3, like4}; // 선호하는 학생의 번호를 2차원 배열에 저장
        }

        // emptyCnt 배열에 인접요소의 빈칸의 개수 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int x = 0; x < 4; x++) {
                    int nr = i + dr[x];
                    int nc = j + dc[x];

                    // 경계 조건과 값 체크
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        count++;
                    }
                }
                // 현재 위치에 count 저장
                emptyCnt[i][j] = count;
            }
        }

        // 첫 번째 학생 배치
        placeStudent(0, studentNum, likeNum, classArray, emptyCnt, dr, dc, N);

        // 1번째부터 N*N-1번째 학생 배치
        for (int i = 1; i < N * N; i++) {
            placeStudent(i, studentNum, likeNum, classArray, emptyCnt, dr, dc, N);
        }

        // 만족도 계산
        int totalSatisfaction = calculateSatisfaction(classArray, likeNum, N, dr, dc);

        // 결과 출력
        //System.out.println("Total Satisfaction: " + totalSatisfaction);
        System.out.println(totalSatisfaction);
    }

    // studentNum[i]를 classArray에 배치하는 메소드
    public static void placeStudent(int studentIndex, int[] studentNum, int[][] likeNum, int[][] classArray, int[][] emptyCnt, int[] dr, int[] dc, int N) {
        int[] likedStudents = likeNum[studentIndex];
        int maxCount = -1;
        int bestRow = -1;
        int bestCol = -1;
        int maxEmptyCount = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 빈칸이 아닌 경우는 무시
                if (classArray[i][j] != 0) {
                    continue;
                }

                // 현재 위치의 인접한 선호 학생 수 계산
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && classArray[nr][nc] != 0) {
                        for (int l = 0; l < 4; l++) {
                            if (classArray[nr][nc] == likedStudents[l]) {
                                count++;
                                break;
                            }
                        }
                    }
                }

                // 최대 선호도와 빈칸 개수 비교
                if (count > maxCount || (count == maxCount && emptyCnt[i][j] > maxEmptyCount) ||
                    (count == maxCount && emptyCnt[i][j] == maxEmptyCount && (i < bestRow || (i == bestRow && j < bestCol)))) {
                    maxCount = count;
                    maxEmptyCount = emptyCnt[i][j];
                    bestRow = i;
                    bestCol = j;
                }
            }
        }

        // 찾은 위치에 studentNum의 현재 요소를 저장
        if (bestRow != -1 && bestCol != -1) {
            classArray[bestRow][bestCol] = studentNum[studentIndex];
            emptyCnt[bestRow][bestCol] = 0; // emptyCnt에서 해당 위치의 값을 0으로 초기화

            // 사방 탐색하여 인접한 곳의 cnt 값을 1 감소
            for (int x = 0; x < 4; x++) {
                int nr = bestRow + dr[x];
                int nc = bestCol + dc[x];

                // 경계 조건과 값 체크
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && emptyCnt[nr][nc] > 0) {
                    emptyCnt[nr][nc]--; // 인접한 위치의 cnt 값을 1 감소
                }
            }
        }
    }

    // 만족도를 계산하는 메소드
    public static int calculateSatisfaction(int[][] classArray, int[][] likeNum, int N, int[] dr, int[] dc) {
        int totalSatisfaction = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = classArray[i][j];
                int[] likedStudents = null;

                // 현재 학생의 선호도 찾기
                for (int k = 0; k < N * N; k++) {
                    if (studentNum[k] == student) {
                        likedStudents = likeNum[k];
                        break;
                    }
                }

                if (likedStudents != null) {
                    // 인접한 선호 학생의 수를 계산
                    int likedCount = 0;
                    for (int x = 0; x < 4; x++) {
                        int nr = i + dr[x];
                        int nc = j + dc[x];

                        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                            for (int l = 0; l < 4; l++) {
                                if (classArray[nr][nc] == likedStudents[l]) {
                                    likedCount++;
                                    break;
                                }
                            }
                        }
                    }

                    // 만족도 계산
                    switch (likedCount) {
                        case 1:
                            totalSatisfaction += 1;
                            break;
                        case 2:
                            totalSatisfaction += 10;
                            break;
                        case 3:
                            totalSatisfaction += 100;
                            break;
                        case 4:
                            totalSatisfaction += 1000;
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        return totalSatisfaction;
    }
}
