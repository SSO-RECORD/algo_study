import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Lecture implements Comparable<Lecture> {
		int start;
		int end;

		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Lecture [start=" + start + ", end=" + end + "]";
		}

		// 1.종료시간이 빠른순으로 오름차순 정렬 2. 종료시간이 같다면 시작시간이 빠른순으로 오름차순 정렬
		@Override
		public int compareTo(Lecture o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 총 수업의 개수 N 입력받기
		List<Lecture> lectures = new ArrayList<>();

		// N개의 (시작시간, 종료시간) 회의 입력받기
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 강의 시작시간
			int end = Integer.parseInt(st.nextToken()); // 강의 종료시간

			lectures.add(new Lecture(start, end)); // 리스트에 입력받은 N개의 [시작시간, 종료시간] 추가
		}

		// 1.종료시간이 빠른순으로 오름차순 정렬 2. 종료시간이 같다면 시작시간이 빠른순으로 오름차순 정렬
		Collections.sort(lectures);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(lectures.get(0).end); // 첫 번째 강의의 종료 시간을 추가

		// 두 번째 강의부터 비교
		for (int i = 1; i < N; i++) {
			// 가장 빨리 끝나는 강의실의 종료 시간과 현재 강의의 시작 시간을 비교
			if (pq.peek() <= lectures.get(i).start) {
				pq.poll(); // 현재 강의실을 사용할 수 있으므로 종료 시간 갱신
			}
			// 새로운 강의실이 필요하더라도 큐에 종료 시간 추가
			pq.add(lectures.get(i).end);
        }

		// 최종적으로 우선순위 큐에 남아있는 요소의 개수가 필요한 강의실의 최소 개수
		System.out.println(pq.size());
	}

}
