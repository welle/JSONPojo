package aka.convertor.junittest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.Before;
import org.junit.Test;

import aka.convertor.json.JsonConvertor;
import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.constants.Generator;

@SuppressWarnings("javadoc")
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
            final File file = new File(ClassLoader.getSystemClassLoader().getResource("example1.txt").toURI());
            final String jsonToParse = new String(Files.readAllBytes(file.toPath()));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example1", "Example1", jsonToParse, this.path, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample2() {
        try {
            final File file = new File(ClassLoader.getSystemClassLoader().getResource("example2.txt").toURI());
            final String jsonToParse = new String(Files.readAllBytes(file.toPath()));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example2", "Example2", jsonToParse, this.path, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample3() {
        try {
            final File file = new File(ClassLoader.getSystemClassLoader().getResource("example3.txt").toURI());
            final String jsonToParse = new String(Files.readAllBytes(file.toPath()));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example3", "Example3", jsonToParse, this.path, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE);
        } catch (final IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample4() {
        try {
            final File file = new File(ClassLoader.getSystemClassLoader().getResource("example4.txt").toURI());
            final String jsonToParse = new String(Files.readAllBytes(file.toPath()));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.jmetaagents.main.jallocine.jperson", "JPerson", jsonToParse, this.path, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Charlotte", AnnotationType.ECLIPSE, true);
        } catch (final IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testExample5() {
        try {
            final File file = new File(ClassLoader.getSystemClassLoader().getResource("example5.txt").toURI());
            final String jsonToParse = new String(Files.readAllBytes(file.toPath()));
            final JsonConvertor jsonConvertor = new JsonConvertor("aka.convertor.examples.example5", "Example5", jsonToParse, this.path, this.path);
            jsonConvertor.generateAll(Generator.JACKSON, "deserializers", "Welle", AnnotationType.ECLIPSE, false);
        } catch (final IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
