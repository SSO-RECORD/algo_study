import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] adjList;
    static int[] numbers = new int[2]; // 치킨집 두 개 선택할 배열
    static int minTotalDistance = Integer.MAX_VALUE; // 최소 왕복 거리의 총합
    static int bestBuilding1, bestBuilding2; // 최적의 두 건물 번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList[A].add(B);
            adjList[B].add(A);
        }

        comb(0, 1); // 두 개의 치킨집을 선택하는 조합 생성

        // 최적의 두 건물 번호와 최소 왕복 거리의 총합 출력
        System.out.println(bestBuilding1 + " " + bestBuilding2 + " " + minTotalDistance);
    }

    // 두 개의 치킨집을 선택하는 조합 생성
    private static void comb(int idx, int start) {
        if (idx == 2) {
            calculateTotalDistance(); // 두 건물 선택 후 접근성 합산 계산
            return;
        }

        for (int i = start; i <= N; i++) {
            numbers[idx] = i;
            comb(idx + 1, i + 1);
        }
    }

    // BFS로 각 건물에서 다른 건물로의 최단 거리 계산
    private static int[] bfs(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adjList[current]) {
                if (distance[neighbor] == Integer.MAX_VALUE) {
                    distance[neighbor] = distance[current] + 1;
                    queue.add(neighbor);
                }
            }
        }
        return distance;
    }

    // 선택된 두 치킨집에 대해 모든 건물의 왕복 시간 합산 계산
    private static void calculateTotalDistance() {
        int[] dist1 = bfs(numbers[0]); // 첫 번째 치킨집에서 각 건물까지의 거리
        int[] dist2 = bfs(numbers[1]); // 두 번째 치킨집에서 각 건물까지의 거리

        int totalDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (i != numbers[0] && i != numbers[1]) {
                // 각 건물에서 가장 가까운 치킨집까지의 왕복 거리 계산
                totalDistance += Math.min(dist1[i], dist2[i]) * 2;
            }
        }

        // 최소 왕복 거리의 총합과 최적의 두 건물 번호 갱신
        if (totalDistance < minTotalDistance) {
            minTotalDistance = totalDistance;
            bestBuilding1 = numbers[0];
            bestBuilding2 = numbers[1];
        } else if (totalDistance == minTotalDistance) {
            // 최소 왕복 거리의 총합이 같을 경우, 더 작은 건물 번호로 갱신
            if (numbers[0] < bestBuilding1 || (numbers[0] == bestBuilding1 && numbers[1] < bestBuilding2)) {
                bestBuilding1 = numbers[0];
                bestBuilding2 = numbers[1];
            }
        }
    }
}
