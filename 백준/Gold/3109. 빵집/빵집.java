import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, cnt;
    private static char[][] arr;
    private static boolean[][] visited;
    private static int[] dr = {-1, 0, 1};
    private static int[] dc = {1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행의 개수
        C = Integer.parseInt(st.nextToken()); // 열의 개수

        arr = new char[R][C];
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                arr[r][c] = str.charAt(c);
            }
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean dfs(int i, int j) {
        visited[i][j] = true;

        if (j == C - 1) {
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && arr[nr][nc] != 'x') {
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }

        return false;
    }
}