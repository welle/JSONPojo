package aka.convertor.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.data.Component;
import aka.convertor.json.helpers.StringUtility;

import com.sun.istack.internal.Nullable;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JsonConvertor {

	public JsonConvertor(@NonNull String name, @NonNull String jsonToParse, @NonNull String packageName, @NonNull String path, @NonNull String subPath, @Nullable String author,
	        @NonNull AnnotationType annotationType) {
		final JsonMetaData jsonMetaData = new JsonMetaData(name, name, "NEW", jsonToParse);

		// objects
		final Configuration cfg = new Configuration();
		// Configure Freemarker
		cfg.setClassForTemplateLoading(getClass(), "./tpl");
		try {
			// Load the template
			final Template template = cfg.getTemplate("jacksonPojo.tpl");
			final ArrayList<ObjectMetaData> objects = jsonMetaData.getObjects();
			System.out.println("[JSON2XMLToolNut] componentClicked - Processing tables :: " + objects.size());
			for (final ObjectMetaData object : objects) {
				System.out.println("[JSON2XMLToolNut] componentClicked - Processing table :: " + object.getObjectName());
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("package", packageName);
				final Component component = new Component(annotationType);
				component.setName(object.getJavaObjectName());
				component.setNodes(object.getFields());
				component.setAuthor(author);
				data.put("comp", component);
				data.put("deserialisers", subPath);

				final FileOutputStream fos = new FileOutputStream(path + "/" + StringUtility.firstLetterUpperCase(object.getJavaObjectName()) + ".java");
				final Writer out = new OutputStreamWriter(fos);
				template.process(data, out);
				out.flush();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}

		// object mapper
		try {
			final Template templateMapper = cfg.getTemplate("jacksonObjectMapper.tpl");
			System.out.println("[JSON2XMLToolNut] componentClicked - Processing Object mapper");
			// Load the template
			final Map<String, Object> data = new HashMap<String, Object>();
			data.put("package", packageName);
			final Component component = new Component(annotationType);
			component.setName(name);
			component.setAuthor(author);
			data.put("comp", component);

			final FileOutputStream fos = new FileOutputStream(path + "/" + StringUtility.firstLetterUpperCase(name) + "JacksonMapper.java");
			final Writer out = new OutputStreamWriter(fos);
			templateMapper.process(data, out);
			out.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}
		final OutputStreamWriter osw = null;

		// Deserialisers
		try {
			// Load the template

			final List<Deserialiser> deserializers = jsonMetaData.getDeserialises();
			System.out.println("Processing Deserializers :: " + deserializers.size());

			String filePath = path + "/";
			if (!deserializers.isEmpty()) {
				if (subPath != null && subPath.trim().length() > 0) {
					filePath = filePath + subPath + "/";
					final File theDir = new File(filePath);

					// if the directory does not exist, create it
					if (!theDir.exists()) {
						try {
							theDir.mkdir();
						} catch (final SecurityException se) {
							// handle it
						}
					}
				}
			}

			String deserializersString = packageName;
			if (subPath != null && subPath.trim().length() > 0) {
				deserializersString = deserializersString + "." + subPath;
			}
			for (final Deserialiser deserialiser : deserializers) {
				Template template = null;
				System.out.println("[JSON2XMLToolNut] componentClicked - Processing table :: " + deserialiser.getType());
				if ("Date".equals(deserialiser.getType())) {
					template = cfg.getTemplate("jacksonDateDeserialiser.tpl");
				} else if ("URL".equals(deserialiser.getType())) {
					template = cfg.getTemplate("jacksonURLDeserialiser.tpl");
				}
				if (template != null) {
					final List<DeserialiseItem> subDeserializers = deserialiser.getItems();
					for (final DeserialiseItem deserialiseItem : subDeserializers) {
						final Map<String, Object> data = new HashMap<String, Object>();
						data.put("package", deserializersString);
						final Component component = new Component(annotationType);
						component.setName(deserialiseItem.getName());
						component.setAuthor(author);
						data.put("comp", component);
						data.put("deserialiser", deserialiseItem);

						final FileOutputStream fos = new FileOutputStream(filePath + StringUtility.firstLetterUpperCase(deserialiseItem.getName()) + "Deserializer.java");
						final Writer out = new OutputStreamWriter(fos);
						template.process(data, out);
						out.flush();
					}
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
