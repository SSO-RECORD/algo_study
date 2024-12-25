import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		class Score implements Comparable<Score> {
			String name;
			int korea;
			int english;
			int math;

			public Score(String name, int korea, int english, int math) {
				super();
				this.name = name;
				this.korea = korea;
				this.english = english;
				this.math = math;
			}

			@Override
			public int compareTo(Score o) {
				if (this.korea != o.korea) {
					return o.korea - this.korea; // 내림차순
				} else if (this.english != o.english) {
					return this.english - o.english;
				} else if (this.math != o.math) {
					return o.math - this.math;
				} else {
					return this.name.compareTo(o.name);
				}
			}

			@Override
			public String toString() {
				return "Score [name=" + name + ", korea=" + korea + ", math=" + math + ", english=" + english + "]";
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim()); // 학생의 수

		List<Score> score = new ArrayList<>();

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			int korea = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());

			score.add(new Score(name, korea, english, math));
		}

		Collections.sort(score);

		for (int i = 0; i < score.size(); i++) {
			System.out.println(score.get(i).name);
		}

	}

}
