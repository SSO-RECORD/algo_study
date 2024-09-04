import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 행크기
		M = Integer.parseInt(st.nextToken()); // 열크기
		int R = Integer.parseInt(st.nextToken()); // 회전횟수

		arr = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int layers = Math.min(N, M) / 2;
		for (int r = 0; r < R; r++) {
			rotateArray(N, M, layers);
		}

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				sb.append(arr[row][col] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void rotateArray(int n, int m, int layers) {

		for (int layer = 0; layer < layers; layer++) {
			int sr = layer;
			int er = n - 1 - layer;
			int sc = layer;
			int ec = m - 1 - layer;

			int temp = arr[sr][sc];

			// 왼쪽으로 밀기
			for (int i = sc; i < ec; i++) {
				arr[sr][i] = arr[sr][i + 1];
			}

			// 위쪽으로 밀기
			for (int i = sr; i < er; i++) {
				arr[i][ec] = arr[i + 1][ec];
			}

			// 오른쪽으로 밀기
			for (int i = ec; i > sc; i--) {
				arr[er][i] = arr[er][i - 1];
			}

			// 아래쪽으로 밀기
			for (int i = er; i > sr; i--) {
				arr[i][sc] = arr[i - 1][sc];
			}

			arr[sr + 1][sc] = temp;
		}

	}

}