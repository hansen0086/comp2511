package cs2511;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private String filename;
	
	public Writer(String filename) {
		this.filename = filename;
	}
	
	public void write() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		
		out.write("This is some test output\n");
		out.write("which should be read back in\n");
		out.close();
	}
}
