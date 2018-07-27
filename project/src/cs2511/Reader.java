package cs2511;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
	private String filename;
	
	public Reader(String filename) {
		this.filename = filename;
	}
	
	public void read() throws IOException {
		Scanner sc = new Scanner(new FileReader(filename));
		
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
	}
}
