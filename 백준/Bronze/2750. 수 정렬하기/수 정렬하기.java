import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine().trim());

			list.add(num);
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		for (int result : list) {
			System.out.println(result);
		}

	}

}
