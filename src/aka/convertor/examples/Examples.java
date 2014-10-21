package aka.convertor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import aka.convertor.json.JsonConvertor;

public class Examples {

	public static void main(String[] args) {
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./xml/examples/example1.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example1", jsonToParse, "aka.convertor.examples.example1", "./src/aka/convertor/examples/example1/");
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./xml/examples/example2.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example2", jsonToParse, "aka.convertor.examples.example2", "./src/aka/convertor/examples/example2/");
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
