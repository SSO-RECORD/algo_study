import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//라인 단위로 입력받기(Enter를 경계로)
		String str = bf.readLine();
		
		String[] array = str.split(" ");
		
		int cnt = 0;
		for(int i=0; i<array.length; i++) {
			if(array[i] == "") {
				cnt +=1;
			}
		}
		System.out.println(array.length - cnt);
	}

}
