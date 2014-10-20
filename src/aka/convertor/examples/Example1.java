package aka.convertor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import aka.convertor.json.JsonConvertor;

public class Example1 {

	public static void main(String[] args) {
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./xml/examples/example1.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor(jsonToParse);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
