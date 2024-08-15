/*
 * 구현을 위해 생각한 방법
 * - 가장 먼저, emptyCnt 2차원 배열을 생성하여 4방탐색으로 모든 인덱스의 주변의 비어있는 칸의 개수를 세어 저장한다
 * - 위의 과정을 완료하면, 1번째 학생의 위치를 저장할 최적의 인덱스를 찾는다.
 * - 이때 고려해야 할 점은, 우선순위가 (1) 주변의 비어있는 칸의 개수가 가장 큰 곳 
 * - (2) (1)에 해당하는 곳이 여러 개라면, 가장 위쪽에 있는 인덱스 선택,
 * - (3) (2)에 해당하는 곳도 여래 개라면, 가장 왼쪽에 있는 인덱스를 선택.
 * - 위의 과정을 거쳐 1번째 학생이 저장될 최적의 위치를 찾고, 학생이 배열에 배치되면 emptyCnt 배열에 저장된 값도 갱신해준다.
 * - 1번 학생의 위치가 확정되면 이후 2번부터 끝번까지의 과정은 동일하게 진행된다.
 * - 2번에서 끝번 학생을 배치하기 위해 추가적으로 고려해야 할 점은 각 학생이 선호하는 학생이 인접한 곳에 얼마나 있는지 체크해줘서 
 * - 주변에 선호하는 학생이 많은 곳에 우선적으로 각 학생을 배치해줘야한다는 점이다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] studentNum; //학생 번호를 저장하는 배열

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // N 입력받기

        int student; // 현재 학생번호 변수
        int like1, like2, like3, like4; // 선호하는 학생 번호
        studentNum = new int[N * N]; // 학생번호(student)를 저장할 배열
        int[][] likeNum = new int[N * N][4]; // 선호하는 학생 4명의 번호를 저장할 배열
        int[][] emptyCnt = new int[N][N]; //각 위치의 인접 빈칸 수를 저장할 배열
        int[] dr = {-1, 1, 0, 0}; //상하좌우 방향벡터
        int[] dc = {0, 0, -1, 1}; //상하좌우 방향벡터
        int[][] classArray = new int[N][N]; //학생들의 최종 위치를 저장할 배열

        for (int i = 0; i < N * N; i++) {
        	// 현재 학생의 번호와 선호하는 학생의 번호를 입력받음
            st = new StringTokenizer(br.readLine()); 
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
        int[] likedStudents = likeNum[studentIndex]; //현재 학생이 좋아하는 학생 목록
        int maxCount = -1;
        int bestRow = -1;
        int bestCol = -1;
        int maxEmptyCount = -1;
        
        //모든 빈칸을 탐색하며 최적의 위치를 찾는다
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

    // 배열을 출력하는 메소드
//    public static void printArray(int[][] array) {
//        for (int[] row : array) {
//            for (int value : row) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }
//    }
}
