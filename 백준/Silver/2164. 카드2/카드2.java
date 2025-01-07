import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());

		Queue<Integer> number = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			number.add(i);
		}

		while (number.size() != 1) {
			number.poll();
			int num = number.peek();
			number.poll();
			number.add(num);
		}

		int result = number.peek();

		System.out.println(result);

	}

}
