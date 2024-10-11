import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] paper;
	static int minusCnt, zeroCnt, plusCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				paper[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		minusCnt = 0;
		zeroCnt = 0;
		plusCnt = 0;

		cut(N, 0, 0);

		System.out.println(minusCnt);
		System.out.println(zeroCnt);
		System.out.println(plusCnt);

	}

	private static void cut(int size, int row, int col) {
		// 기저조건
		boolean check = numCheck(size, row, col);
//        System.out.println(check);

		if (check) {
			if (paper[row][col] == -1) {
				minusCnt++;
//                System.out.println("minusCnt : " + minusCnt);
			} else if (paper[row][col] == 0) {
				zeroCnt++;
//                System.out.println("zeroCnt : " + zeroCnt);
			} else if (paper[row][col] == 1) {
				plusCnt++;
//                System.out.println("plusCnt : " + plusCnt);
			}
			return;
		}
//        System.out.println("minusCnt : " + minusCnt);
//        System.out.println("zeroCnt : " + zeroCnt);
//        System.out.println("plusCnt : " + plusCnt);
		// 유도파트
		int newSize = size / 3; // 새로운 크기

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cut(newSize, row + i * newSize, col + j * newSize);
			}
		}
//        System.out.println("1");
//        cut(size/3, row, col);
//        System.out.println("2");
//        cut(size/3, row, col+N/3);
//        System.out.println("3");
//        cut(size/3, row, col + 2*(N/3));
//        System.out.println("4");
//        cut(size/3, row+N/3, col);
//        System.out.println("5");
//        cut(size/3, row+N/3, col+N/3);
//        System.out.println("6");
//        cut(size/3, row + N/3, col + 2*(N/3));
//        System.out.println("7");
//        cut(size/3, row + 2*(N/3), col);
//        System.out.println("8");
//        cut(size/3, row + 2*(N/3), col + N/3);
//        System.out.println("9");
//        cut(size/3, row + 2*(N/3), col + 2*(N/3));

	}

	private static boolean numCheck(int size, int row, int col) {
//        System.out.printf("row : " + row + " col : " + col + " size : " + size + "\n");
//        System.out.println("배열 체크");
//        for(int i=row; i<row+size; i++) {
//            for(int j=col; j<col+size; j++) {
//                System.out.print(paper[i][j] + " ");
//            }
//            System.out.println();
//        }

		int firstValue = paper[row][col];

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (paper[i][j] != firstValue) {
					return false;
				}
			}
		}

		return true;
	}

}