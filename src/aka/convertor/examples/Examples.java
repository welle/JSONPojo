package aka.convertor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import aka.convertor.json.JsonConvertor;
import aka.convertor.json.constants.AnnotationType;

public class Examples {

	public static void main(String[] args) {
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example1.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example1", jsonToParse, "aka.convertor.examples.example1", "./src/aka/convertor/examples/example1/", "deserializers", "Welle",
					AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example2.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example2", jsonToParse, "aka.convertor.examples.example2", "./src/aka/convertor/examples/example2/", "deserializers", "Welle",
					AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example3.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example3", jsonToParse, "aka.convertor.examples.example3", "./src/aka/convertor/examples/example3/", "deserializers", "Welle",
					AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example4.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example4", jsonToParse, "aka.convertor.examples.example4", "./src/aka/convertor/examples/example4/", "deserializers", "Welle",
			        AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
