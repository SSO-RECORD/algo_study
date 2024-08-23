/*
 * [문제 설명]
 * 사용자가 넣는 입력(U, D, L, R, S)에 따라 각각의 동작을 수행하여, 모든 입력을 처리하고 난 후의
 * 게임 맵의 상태를 출력하는 문제이다.
 * 
 * [구현]
 * 입력이 U, D, L, R, S일 때 행하는 동작을 조건문과 반복문을 통해 각각 구현하였다.
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1873_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Character[][] map;
		int currentH = 0, currentW = 0;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수 입력받기
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken()); // 높이(행) 입력받기
			int W = Integer.parseInt(st.nextToken()); // 너비(열) 입력받기

			map = new Character[H][W]; // HxW 크기의 배열 생성
			for (int h = 0; h < H; h++) {
				String str = br.readLine(); // 문자열 입력받기
				for (int w = 0; w < W; w++) {
					map[h][w] = str.charAt(w);// 입력받은 문자열을 배열에 차근차근 집어넣기
					// 전차의 위치를 변수에 저장
					if (map[h][w] == '^' || map[h][w] == 'v' || map[h][w] == '<' || map[h][w] == '>') {
						currentH = h;
						currentW = w;
					}
				}
			}

			int userInput = Integer.parseInt(br.readLine()); // 사용자가 넣을 입력의 개수
			Character[] inputArr = new Character[userInput]; // 사용자의 입력을 관리할 배열
			String inputType = br.readLine();
			for (int i = 0; i < userInput; i++) {
				inputArr[i] = inputType.charAt(i); // 배열에 사용자의 입력을 한글자씩 저장
			}

			// 명령어에 따른 동작 수행
			for (int i = 0; i < inputArr.length; i++) {
				// 명령이 'U'인 경우
				if (inputArr[i] == 'U') {
					map[currentH][currentW] = '^';
					if (currentH != 0 && map[currentH - 1][currentW] == '.') {
						map[currentH][currentW] = '.';
						currentH--; // 현재 위치 갱신
						map[currentH][currentW] = '^';
					}
				}

				// 명령이 'D'인 경우
				if (inputArr[i] == 'D') {
					map[currentH][currentW] = 'v';
					if (currentH != H - 1 && map[currentH + 1][currentW] == '.') {
						map[currentH][currentW] = '.';
						currentH++; // 현재 위치 갱신
						map[currentH][currentW] = 'v';
					}
				}

				// 명령이 'L'인 경우
				if (inputArr[i] == 'L') {
					map[currentH][currentW] = '<';
					if (currentW != 0 && map[currentH][currentW - 1] == '.') {
						map[currentH][currentW] = '.';
						currentW--; // 현재 위치 갱신
						map[currentH][currentW] = '<';
					}
				}

				// 명령이 'R'인 경우
				if (inputArr[i] == 'R') {
					map[currentH][currentW] = '>';
					if (currentW != W - 1 && map[currentH][currentW + 1] == '.') {
						map[currentH][currentW] = '.';
						currentW++; // 현재 위치 갱신
						map[currentH][currentW] = '>';
					}
				}

				// 명령이'S'인 경우
				if (inputArr[i] == 'S') {
					// 전차의 방향이 '^'라면
					if (map[currentH][currentW] == '^') {
						for (int x = currentH - 1; x >= 0; x--) {
							if (map[x][currentW] == '*') {
								map[x][currentW] = '.';
								break;
							}
							if (map[x][currentW] == '#') {
								break;
							}
						}
					}

					// 전차의 방향이 'v'라면
					if (map[currentH][currentW] == 'v') {
						for (int x = currentH + 1; x < H; x++) {
							if (map[x][currentW] == '*') {
								map[x][currentW] = '.';
								break;
							}
							if (map[x][currentW] == '#') {
								break;
							}
						}
					}

					// 전차의 방향이 '<'라면
					if (map[currentH][currentW] == '<') {
						for (int y = currentW - 1; y >= 0; y--) {
							if (map[currentH][y] == '*') {
								map[currentH][y] = '.';
								break;
							}
							if (map[currentH][y] == '#') {
								break;
							}
						}
					}

					// 전차의 방향이 '>'라면
					if (map[currentH][currentW] == '>') {
						for (int y = currentW + 1; y < W; y++) {
							if (map[currentH][y] == '*') {
								map[currentH][y] = '.';
								break;
							}
							if (map[currentH][y] == '#') {
								break;
							}
						}
					}
				}
			}

			// 출력
			sb.append("#" + tc + " ");
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

}
