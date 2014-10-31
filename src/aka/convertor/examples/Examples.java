package aka.convertor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import aka.convertor.json.JsonConvertor;
import aka.convertor.json.constants.AnnotationType;

public class Examples {

	public static void main(String[] args) {
		// try {
		// final String jsonToParse = new
		// String(Files.readAllBytes(Paths.get("./xml/examples/example1.txt")));
		// // final File tempDir = com.google.common.io.Files.createTempDir();
		// // final String path = tempDir.getPath();
		// final String path = "./src/aka/convertor/examples/example1/";
		// if (path != null) {
		// final JsonConvertor jsonConvertor = new JsonConvertor("Example1",
		// jsonToParse, "aka.convertor.examples.example1", path, "Welle",
		// AnnotationType.ECLIPSE);
		// }
		//
		// // final Example1 example1 =
		// // Example1JacksonMapper.readValue(jsonToParse);
		// } catch (final IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// try {
		// final String jsonToParse = new
		// String(Files.readAllBytes(Paths.get("./xml/examples/example2.txt")));
		// final JsonConvertor jsonConvertor = new JsonConvertor("Example2",
		// jsonToParse, "aka.convertor.examples.example2",
		// "./src/aka/convertor/examples/example2/", "Welle",
		// AnnotationType.ECLIPSE);
		// } catch (final IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
			final String jsonToParse = new String(Files.readAllBytes(Paths.get("./xml/examples/example3.txt")));
			final JsonConvertor jsonConvertor = new JsonConvertor("Example3", jsonToParse, "aka.convertor.examples.example3", "./src/aka/convertor/examples/example3/", "Welle", AnnotationType.ECLIPSE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
