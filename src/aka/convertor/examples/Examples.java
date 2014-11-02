package aka.convertor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import aka.convertor.json.JsonConvertor;
import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.constants.Generator;

public class Examples {

	public static void main(final String[] args) {
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example1.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example1", "Example1", jsonToParse, "./src/aka/convertor/examples/example1/");
			jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example2.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example2", "Example2", jsonToParse, "./src/aka/convertor/examples/example2");
			jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example3.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example3", "Example3", jsonToParse, "./src/aka/convertor/examples/example3/");
			jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./txt/examples/example4.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example4", "Example4", jsonToParse, "./src/aka/convertor/examples/example4/");
			jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
