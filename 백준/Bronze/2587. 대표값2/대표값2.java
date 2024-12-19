import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = new int[5];
		int sum = 0;

		for (int i = 0; i < 5; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			arr[i] = num;
			sum += arr[i];
		}

		Arrays.sort(arr);

		int result = sum / 5;
		System.out.println(result);
		System.out.println(arr[2]);

	}

}
