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
import org.eclipse.jdt.annotation.Nullable;

import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.constants.Generator;
import aka.convertor.json.data.Component;
import aka.convertor.json.helpers.StringUtility;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JsonConvertor {

	@NonNull
	private final JsonMetaData jsonMetaData;
	@NonNull
	private final String path;
	@NonNull
	private AnnotationType annotation = AnnotationType.NONE;
	@NonNull
	private final String name;
	@NonNull
	private final String packageName;
	@NonNull
	private Generator generator = Generator.JACKSON;

	public JsonConvertor(@NonNull final String packageName, @NonNull final String name, @NonNull final String jsonToParse, @NonNull final String path) {
		this.jsonMetaData = new JsonMetaData(name, jsonToParse);
		this.packageName = packageName;
		this.name = name;
		this.path = path;
	}

	public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @Nullable final String author, @NonNull final AnnotationType annotationType) {
		this.annotation = annotationType;
		this.generator = generator;
		generateAll(subPath, author);
	}

	public void generateAll(@Nullable final String subPath, @Nullable final String author) {
		// objects
		final Configuration cfg = getConfiguration();
		generatePojos(subPath, author, cfg);

		// object mapper
		generateObjectMapper(author, cfg);

		// Deserialisers
		generateDeserialisers(subPath, author, cfg);
	}

	@NonNull
	private Configuration getConfiguration() {
		final Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		// Configure Freemarker
		cfg.setClassForTemplateLoading(getClass(), "./tpl");
		cfg.setDefaultEncoding("UTF-8");
		return cfg;
	}

	private void generateDeserialisers(@Nullable final String subPath, @Nullable final String author, @NonNull final Configuration cfg) {
		try {
			// Load the template
			final List<@NonNull Deserialiser> deserializers = this.jsonMetaData.getDeserialisers();

			String filePath = this.path + "/";
			if (!deserializers.isEmpty()) {
				if (subPath != null) {
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

			String deserializersString = this.packageName;
			if (subPath != null) {
				deserializersString = deserializersString + "." + subPath;
			}
			for (final Deserialiser deserialiser : deserializers) {
				Template template = null;
				if ("Date".equals(deserialiser.getType())) {
					template = cfg.getTemplate(this.generator.getDeserialiserDateTpl());
				} else if ("URL".equals(deserialiser.getType())) {
					template = cfg.getTemplate(this.generator.getDeserialiserURLTpl());
				}
				if (template != null) {
					final List<DeserialiseItem> subDeserializers = deserialiser.getItems();
					for (final DeserialiseItem deserialiseItem : subDeserializers) {
						final Map<String, Object> data = new HashMap<String, Object>();
						data.put("package", deserializersString);
						final Component component = new Component(deserialiseItem.getName(), this.annotation, author);
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

	private void generateObjectMapper(@Nullable final String author, @NonNull final Configuration cfg) {
		try {
			final Template templateMapper = cfg.getTemplate(this.generator.getObjectMapperTpl());
			// Load the template
			final Map<String, Object> data = new HashMap<String, Object>();
			data.put("package", this.packageName);
			final Component component = new Component(this.name, this.annotation, author);
			data.put("comp", component);

			final FileOutputStream fos = new FileOutputStream(this.path + "/" + StringUtility.firstLetterUpperCase(this.name) + "JacksonMapper.java");
			final Writer out = new OutputStreamWriter(fos);
			templateMapper.process(data, out);
			out.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}
	}

	private void generatePojos(@Nullable final String subPath, @Nullable final String author, @NonNull final Configuration cfg) {
		try {
			// Load the template
			final Template template = cfg.getTemplate(this.generator.getPojoTpl());
			final ArrayList<ObjectMetaData> objects = this.jsonMetaData.getObjects();
			for (final ObjectMetaData object : objects) {
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("package", this.packageName);
				final Component component = new Component(object.getJavaObjectName(), this.annotation, author);
				component.setNodes(object.getFields());
				data.put("comp", component);
				data.put("deserialisers", subPath);

				final FileOutputStream fos = new FileOutputStream(this.path + "/" + StringUtility.firstLetterUpperCase(object.getJavaObjectName()) + ".java");
				final Writer out = new OutputStreamWriter(fos);
				template.process(data, out);
				out.flush();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}
	}
}
