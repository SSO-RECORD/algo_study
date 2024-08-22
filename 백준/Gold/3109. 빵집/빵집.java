/*
 * [문제 설명]
 * 가스관과 빵집을 연결하는 파이프를 설치하려고 한다. 모든 파이프 열을 첫째 열에서 시작해야하고,
 * 마지막 열에서 끝나야 한다. 가스관과 빵집을 연결하는 파이프라인의 최대 개수는?
 * 
 * [내가 생각한 구현 방법]
 * {우상, 우, 우하} 방향으로 dfs 탐색을 하며 마지막 열에 도달하면 cnt += 1을 해준다.
 * 방문한 위치는 방문처리를 해준다.
 * => 이렇게만 하면, dfs 함수가 더 이상 경로를 찾을 수 없을 때에도 탐색을 이어가는 문제 발생
 * => 이로 인해 같은 출발점에서 여러 경로를 시도하게 되어 경로들이 서로 겹칠 가능성이 생김
 * 
 * [작성된 알고리즘]
 * 위의 문제점을 수정하여 dfs 탐색을 통해 구현 완료
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int R, C,cnt;
	private static char[][] arr;
	private static boolean[][] visited;
	private static int[] dr = {-1, 0, 1};
	private static int[] dc = {1, 1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); //행의 개수인 R 입력받기
		C = Integer.parseInt(st.nextToken()); //열의 개수인 C 입력받기
		
		arr = new char[R][C]; //RxC 크기의 배열 생성
		visited = new boolean[R][C]; //RxC 크기의 방문체크를 위한 배열 생성
		//배열에 값 입력받기
		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		//첫번째 열에 대해 파이프라인 설치
		for(int i=0; i<R; i++) {
			if(dfs(i, 0)) { //dfs 탐색 시작, 성공적인 경로가 있으면 cnt 증가
				cnt += 1;
			}
		}
		System.out.println(cnt); //설치된 파이프라인의 최대 개수 출력
	}

	private static boolean dfs(int i, int j) {
		visited[i][j] = true; //현재 위치 방문 처리

		//만약 마지막 열에 도달했다면, 성공적인 경로임을 반환
		if(j == C-1) {
			return true;
		}
		
		//우상, 우, 우하 탐색
		for(int d=0; d<3; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			//새 위치가 격자 내에 있고, 방문하지 않았으며, 건물이 아닌 경우
			if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc] && arr[nr][nc] != 'x') {
				//새 위치로부터 탐색을 재귀적으로 호출, 성공적인 경로가 있으면 true 반환
				if(dfs(nr, nc)) {
					return true; //성공적인 경로이므로 더 이상 다른 경로 탐색 필요 없음
				}
			}
		}
		return false; //모든 방향에서 성공적인 경로를 찾지 못한 경우 false 반환
	}

}
