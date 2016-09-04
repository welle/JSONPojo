package aka.convertor.junittest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.Before;
import org.junit.Test;

import aka.convertor.json.JsonConvertor;
import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.constants.Generator;

public class JSonConvertorUnitTest {

    @NonNull
    private String path = "";

    @Before
    public void init() {
        final File tempDir = com.google.common.io.Files.createTempDir();
        final String tempPath = tempDir.getPath();
        assert tempPath != null;
        this.path = tempPath;
    }

    @Test
    public void testExample1() {
        try {
            final String jsonToParse = new String(Files.readAllBytes(Paths.get("./srcTest/txt/examples/example1.txt")));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example1", "Example1", jsonToParse, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample2() {
        try {
            final String jsonToParse = new String(Files.readAllBytes(Paths.get("./srcTest/txt/examples/example2.txt")));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example2", "Example2", jsonToParse, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample3() {
        try {
            final String jsonToParse = new String(Files.readAllBytes(Paths.get("./srcTest/txt/examples/example3.txt")));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example3", "Example3", jsonToParse, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample4() {
        try {
            final String jsonToParse = new String(Files.readAllBytes(Paths.get("./srcTest/txt/examples/example4.txt")));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.jmetaagents.main.jallocine.jperson", "JPerson", jsonToParse, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Charlotte", AnnotationType.ECLIPSE, true);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample5() {
        try {
            final String jsonToParse = new String(Files.readAllBytes(Paths.get("./srcTest/txt/examples/example5.txt")));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example5", "Example5", jsonToParse, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE, false);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
