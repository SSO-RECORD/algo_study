import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int[][] sudoku;
	static boolean[][][] check;
	static List<int[]> zeroIndex;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sudoku = new int[9][9];
		check = new boolean[3][9][10];
		zeroIndex = new ArrayList<>();
		flag = false;

		for (int row = 0; row < 9; row++) {
			String str = br.readLine();
			for (int col = 0; col < 9; col++) {
				sudoku[row][col] = str.charAt(col) - '0';

				if (sudoku[row][col] == 0) {
					zeroIndex.add(new int[] { row, col });
				} else {
					check[0][row][sudoku[row][col]] = true;
					check[1][col][sudoku[row][col]] = true;
					check[2][getSection(row, col)][sudoku[row][col]] = true;
				}
			}
		}

		fillNumber(0);

	}

	private static void fillNumber(int idx) {
		// 기저조건
		if (idx == zeroIndex.size()) {
			flag = true;
			printSudoku();
			return;
		}

		// 유도파트
		int[] cur = zeroIndex.get(idx);
		int cx = cur[0];
		int cy = cur[1];

		for (int i = 1; i <= 9; i++) {
			if (isValid(cx, cy, i)) {
				sudoku[cx][cy] = i;
				check[0][cx][i] = true;
				check[1][cy][i] = true;
				check[2][getSection(cx, cy)][i] = true;

				fillNumber(idx + 1);

				if (flag)
					return;

				sudoku[cx][cy] = 0;
				check[0][cx][i] = false;
				check[1][cy][i] = false;
				check[2][getSection(cx, cy)][i] = false;
			}
		}
	}

	private static void printSudoku() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				System.out.print(sudoku[row][col]);
			}
			System.out.println();
		}
	}

	private static boolean isValid(int x, int y, int num) {
		return (!check[0][x][num] && !check[1][y][num] && !check[2][getSection(x, y)][num]);
	}

	private static int getSection(int row, int col) {
		int r = row / 3;
		int c = col / 3;
		return r * 3 + c;
	}

}
