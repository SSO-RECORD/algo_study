import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	private static int N;
	private static int[][] processor;
	private static ArrayList<int[]> core;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int maxCore;
	private static int minLen;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1767_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스 수 입력
		//T만큼 반복
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine()); //멕시노스의 가로, 세로 크기
			
			processor = new int[N][N]; //NxN크기의 프로세서
			core = new ArrayList<>();
			
			for(int row=0; row<N; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col=0; col<N; col++) {
					processor[row][col] = Integer.parseInt(st.nextToken());
					if(processor[row][col] == 1 && !isBound(row, col)) {
						core.add(new int[] {row, col}); //리스트에 core가 위치한 좌표 추가
					}
				}
			}
			//값이 잘 들어갔는지 체크해보기
//			for(int[] num : core) {
//				System.out.println(num[0] + " " + num[1]);
//			}
			
			maxCore = 0;
			minLen = Integer.MAX_VALUE;
			//탐색 시작
			dfs(0, 0, 0);
			sb.append("#"+t+" "+minLen+"\n");
		}
		//출력
		System.out.print(sb);
	}
	
	//core 좌표들을 dfs 탐색
	private static void dfs(int idx, int currentCore, int currentLen) {
		//종료 조건
		if(idx == core.size()) {
			//최대 코어일 때, 최소 길이 구하기
			if(currentCore > maxCore) {
				maxCore = currentCore;
				minLen = currentLen;
			}
			else if(currentCore == maxCore) {
				minLen = Math.min(minLen, currentLen);
			}
			return;
		}
		//유도파트
		int[] current = core.get(idx);
		//현재 코어의 행(cr), 열(cc) 좌표
		int cr = current[0];
		int cc = current[1];
		
		//코어에 전원을 연결하는 경우
		for(int d=0; d<4; d++) {
			int len = canConnect(cr, cc, d);
			if (len == -1) continue;
			setWire(cr, cc, d, 2);
			dfs(idx+1, currentCore + 1 ,currentLen + len);
			setWire(cr, cc, d, 0);
		}
		//코어에 전원을 연결하지 않는 경우
		dfs(idx+1, currentCore, currentLen);
	}
	
	//멕시노스가 연결된 상태를 표시해주는 메서드
	public static void setWire(int cr, int cc, int d, int value) {
		int nr = cr + dr[d];
		int nc = cc + dc[d];
		
		while (nr>=0 && nr<N && nc>=0 && nc<N) {
			processor[nr][nc] = value;
			
			nr += dr[d];
			nc += dc[d];
		}	
	}
	
	//코어가 뻗을 수 있는 길이 반환
	public static int canConnect(int cr, int cc, int d) {
		int nr = cr + dr[d];
		int nc = cc + dc[d];
		
		int len = 0;
		while (nr>=0 && nr<N && nc>=0 && nc<N) {
			if(processor[nr][nc] != 0) return -1;
			len += 1;
			nr += dr[d];
			nc += dc[d];
		}
		return len;	
	}
	
	//배열의 경계에 위치하는지 체크하는 메서드
	public static boolean isBound(int row, int col) {
		return (row == 0 || row == N-1 || col == 0 || col == N-1);
	}
}
