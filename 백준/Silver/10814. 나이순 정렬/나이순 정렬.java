import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static class Person implements Comparable<Person>{
		int age;
		String name;
		
		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Person o) {
			return this.age - o.age;
		}	
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<Person> person = new ArrayList<>(); //Person 객체를 저장할 리스트
		int N = Integer.parseInt(br.readLine()); //온라인 저지 회원의 수 N 입력받기
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken()); //입력받은 나이 저장
			String name = st.nextToken(); //입력받은 이름 저장
			person.add(new Person(age, name)); //리스트에	Person 객체 추가
		}
		
		//System.out.println("------------가변정렬(나이 오름차순)--------------");
		Collections.sort(person, new Comparator<Person>() { //Comparator를 사용하여 나이기준 오름차순 정렬

			@Override
			public int compare(Person o1, Person o2) {
				return o1.age - o2.age;
			}
			
		});
		
		//출력
		for(Person personSort : person) {
			System.out.println(personSort.age + " " + personSort.name);
		}

//		System.out.println("------------고정정렬(나이 오름차순)--------------");
//		Collections.sort(person);
//		for(Person personSort : person) { //Comparable를 사용하여 나이 기준 오름차순 정렬
//			System.out.println(personSort.age + " " + personSort.name);
//		}
	}

}
