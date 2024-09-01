import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static int[][] city;
	private static ArrayList<int[]> house;
	private static ArrayList<int[]> chicken;
	private static ArrayList<int[]> selected;
	private static boolean[] visited;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //도시의 크기
		M = Integer.parseInt(st.nextToken()); //치킨집의 수
		
		city = new int[N][N];
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		selected = new ArrayList<>();
		for(int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<N; col++) {
				city[row][col] = Integer.parseInt(st.nextToken());
				if(city[row][col] == 1) {
					house.add(new int[] {row, col});
				}
				else if(city[row][col] == 2) {
					chicken.add(new int[] {row, col});
				}
			}
		}
		
		visited = new boolean[chicken.size()];
		result = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(result);
	}

	private static void comb(int idx, int start) {
		
		//기저조건
		if(idx == M) {
			int sum = 0;
			for(int[] h : house) {
				int min = Integer.MAX_VALUE;
				for(int[] s : selected) {
					int distance = Math.abs(h[0] - s[0]) + Math.abs(h[1] - s[1]);
					min = Math.min(distance, min);
				}
				sum += min;
			}
			result = Math.min(sum, result);
		}
		
		//유토파트
		for(int i=start; i<chicken.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected.add(chicken.get(i));
				comb(idx+1, i+1);
				selected.remove(selected.size()-1);
				visited[i] = false;
				
			}
		}
	}

}
