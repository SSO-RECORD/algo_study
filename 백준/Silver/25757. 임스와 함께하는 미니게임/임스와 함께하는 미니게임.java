import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 플레이 신청한 횟수
		char type = st.nextToken().charAt(0);

		Set<String> people = new HashSet<>();

		for (int i = 0; i < N; i++) {
			String name = br.readLine().trim();
			people.add(name);
		}

		int result = 0;

		switch (type) {
		case 'Y': {
			result = people.size();
			break;
		}
		case 'F': {
			result = people.size() / 2;
			break;
		}
		case 'O': {
			result = people.size() / 3;
			break;
		}
		}

		System.out.println(result);
	}
}
