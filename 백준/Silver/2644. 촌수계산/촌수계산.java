import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static boolean[] visited;
    static List<Integer>[] adjList;
    static int cnt, p2;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine().trim()); //사람의 수
        
        //촌수를 계산해야하는 두 사람의 번호
        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        
        //부모 자식들 간의 관계의 개수
        int m = Integer.parseInt(br.readLine());
        
        visited = new boolean[n+1];
        
        //List<Integer> 타입의 배열 선언
        adjList = new ArrayList[n+1];
        
        //배열의 인덱스를 리스트로 초기화
        for(int i=0; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        //부모 자식 간의 관계
        for(int M=1; M<=m; M++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());//부모번호
            int y = Integer.parseInt(st.nextToken());//자식번호
            
            adjList[x].add(y);
            adjList[y].add(x);
        }
        
//        for(int i=0; i<adjList.length; i++) {
//            System.out.println(adjList[i]);
//        }
        
        cnt = 0;
        int result = 0;
        dfs(p1, 0);
        if(!visited[p2]) {
            result = -1;
        } else {
            result = cnt;
        }
        
        System.out.println(result);
    }

    private static void dfs(int p1, int depth) {
        visited[p1] = true;
        
        if(p1 == p2) {
            cnt = depth;
            return;
        }
        
        for(int num : adjList[p1]) {
            if(!visited[num]) {
                dfs(num, depth+1);
            }
        }
    
    }

}