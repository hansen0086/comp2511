import java.io.IOException;

import cs2511.Reader;
import cs2511.Writer;


public class Main {
	public static void main(String[] args) throws IOException {
		String relative = "./files/test1.txt";
		
		Writer w = new Writer(relative);
		Reader r = new Reader(relative);
		
		w.write();
		r.read();
	}
}
