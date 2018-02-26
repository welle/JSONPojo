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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.constants.Generator;
import aka.convertor.json.data.DeserialiseItem;
import aka.convertor.json.data.Deserialiser;
import aka.convertor.json.data.JSONComponent;
import aka.convertor.json.data.ObjectMetaData;
import aka.convertor.json.helpers.LoggerHelper;
import aka.convertor.json.helpers.StringUtilities;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Convert JSON String to java objects.
 *
 * @author Welle Charlotte
 */
public class JsonConvertor {

    @NonNull
    private static final Logger LOGGER = Logger.getLogger(JsonConvertor.class.getName());

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
    private Generator generatorToUse = Generator.JACKSON;
    private boolean useList = true;
    private @NonNull final String pathTest;

    /**
     * Constructor.
     *
     * @param packageName package name for the java objects
     * @param name name of the main class
     * @param jsonToParse JSON to parse
     * @param path path where to save files
     */
    public JsonConvertor(@NonNull final String packageName, @NonNull final String name, @NonNull final String jsonToParse, @NonNull final String path, @NonNull final String pathTest) {
        this.jsonMetaData = new JsonMetaData(name, jsonToParse);
        this.packageName = packageName;
        this.name = name;
        this.path = path;
        this.pathTest = pathTest;
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param author author to display in java generated files
     * @param annotationType annotation type (eclipse or JSR)
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @Nullable final String author, @NonNull final AnnotationType annotationType) {
        this.annotation = annotationType;
        generateAll(generator, subPath, author);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param author author to display in java generated files
     * @param annotationType annotation type (eclipse or JSR)
     * @param useListParam
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @Nullable final String author, @NonNull final AnnotationType annotationType, final boolean useListParam) {
        this.annotation = annotationType;
        this.useList = useListParam;
        generateAll(generator, subPath, author);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param author author to display in java generated files
     * @param useListParam
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @Nullable final String author, final boolean useListParam) {
        this.useList = useListParam;
        generateAll(generator, subPath, author);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param annotationType annotation type (eclipse or JSR)
     * @param useListParam
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @NonNull final AnnotationType annotationType, final boolean useListParam) {
        this.annotation = annotationType;
        this.useList = useListParam;
        generateAll(generator, subPath, null);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param useListParam
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, final boolean useListParam) {
        this.useList = useListParam;
        generateAll(generator, subPath, null);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param useListParam
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, final boolean useListParam) {
        this.useList = useListParam;
        generateAll(generator, null, null);
    }

    /**
     * Generate all files.
     *
     * @param generator generator (GSON or Jackson)
     * @param subPath sub path
     * @param author author to display in java generated files
     * @see Generator
     * @see AnnotationType
     */
    public void generateAll(@NonNull final Generator generator, @Nullable final String subPath, @Nullable final String author) {
        this.generatorToUse = generator;
        // objects
        final Configuration cfg = getConfiguration();
        generatePojos(subPath, author, cfg);

        // Junit for object
        generateJunitPojos(subPath, author, cfg);

        // object mapper
        generateObjectMapper(author, cfg);

        // Deserialisers
        generateDeserialisers(subPath, author, cfg);
    }

    @NonNull
    private Configuration getConfiguration() {
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // Configure Freemarker
        cfg.setClassForTemplateLoading(JsonConvertor.class, "/tpl/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    private void generateDeserialisers(@Nullable final String subPath, @Nullable final String author, @NonNull final Configuration cfg) {
        FileOutputStream fos = null;
        Writer out = null;
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
                    template = cfg.getTemplate(this.generatorToUse.getDeserialiserDateTpl());
                } else if ("URL".equals(deserialiser.getType())) {
                    template = cfg.getTemplate(this.generatorToUse.getDeserialiserURLTpl());
                }
                if (template != null) {
                    final List<DeserialiseItem> subDeserializers = deserialiser.getItems();
                    for (final DeserialiseItem deserialiseItem : subDeserializers) {
                        final Map<String, Object> data = new HashMap<>();
                        data.put("package", deserializersString);
                        final JSONComponent component = new JSONComponent(deserialiseItem.getName(), this.annotation, author);
                        data.put("comp", component);
                        data.put("deserialiser", deserialiseItem);

                        fos = new FileOutputStream(filePath + StringUtilities.firstLetterUpperCase(deserialiseItem.getName()) + "Deserializer.java");
                        out = new OutputStreamWriter(fos);
                        template.process(data, out);
                        out.flush();
                        out.close();
                        fos.close();
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateDeserialisers", e.getMessage(), e);
        } catch (final TemplateException e) {
            LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateDeserialisers", e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (final IOException e) {
                LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateDeserialisers", e.getMessage(), e);
            }
        }
    }

    private void generateObjectMapper(@Nullable final String author, @NonNull final Configuration cfg) {
        FileOutputStream fos = null;
        Writer out = null;
        try {
            final Template templateMapper = cfg.getTemplate(this.generatorToUse.getObjectMapperTpl());
            // Load the template
            final Map<String, Object> data = new HashMap<>();
            data.put("package", this.packageName);
            final JSONComponent component = new JSONComponent(this.name, this.annotation, author);
            data.put("comp", component);
            data.put("isRootAnArray", Boolean.valueOf(this.jsonMetaData.isRootAnArray()));
            data.put("useList", Boolean.valueOf(this.useList));

            fos = new FileOutputStream(this.path + "/" + StringUtilities.firstLetterUpperCase(this.name) + "JacksonMapper.java");
            out = new OutputStreamWriter(fos);
            templateMapper.process(data, out);
            out.flush();
            out.close();
            fos.close();
        } catch (final IOException e) {
            LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateObjectMapper", e.getMessage(), e);
        } catch (final TemplateException e) {
            LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateObjectMapper", e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (final IOException e) {
                LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateObjectMapper", e.getMessage(), e);
            }
        }
    }

    private void generatePojos(@Nullable final String subPath, @Nullable final String author, @NonNull final Configuration cfg) {
        FileOutputStream fos = null;
        Writer out = null;
        try {
            // Load the template
            final Template template = cfg.getTemplate(this.generatorToUse.getPojoTpl());
            final ArrayList<ObjectMetaData> objects = this.jsonMetaData.getObjects();
            for (final ObjectMetaData object : objects) {
                final Map<@NonNull String, Object> data = new HashMap<>();
                data.put("package", this.packageName);
                final JSONComponent component = new JSONComponent(object.getJavaObjectName(), this.annotation, author);
                component.setFields(object.getFields());
                data.put("comp", component);
                data.put("deserialisers", subPath);
                data.put("useList", Boolean.valueOf(this.useList));

                fos = new FileOutputStream(this.path + "/" + StringUtilities.firstLetterUpperCase(object.getJavaObjectName()) + ".java");
                out = new OutputStreamWriter(fos);
                template.process(data, out);
                out.flush();
                out.close();
                fos.close();
            }
        } catch (final IOException e) {
            LoggerHelper.getLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
        } catch (final TemplateException e) {
            LoggerHelper.getLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (final IOException e) {
                LOGGER.logp(Level.SEVERE, "JsonConvertor", "generatePojos", e.getMessage(), e);
            }
        }
    }

    private void generateJunitPojos(@Nullable final String subPath, @Nullable final String author, @NonNull final Configuration cfg) {
        FileOutputStream fos = null;
        Writer out = null;
        try {
            // Load the template
            final Template template = cfg.getTemplate(this.generatorToUse.getJunitPojoTpl());
            final ArrayList<ObjectMetaData> objects = this.jsonMetaData.getObjects();
            for (final ObjectMetaData object : objects) {
                final Map<@NonNull String, Object> data = new HashMap<>();
                data.put("package", this.packageName);
                final JSONComponent component = new JSONComponent(object.getJavaObjectName(), this.annotation, author);
                component.setFields(object.getFields());
                data.put("comp", component);
                data.put("useList", Boolean.valueOf(this.useList));

                fos = new FileOutputStream(this.pathTest + "/" + StringUtilities.firstLetterUpperCase(object.getJavaObjectName()) + "_JUnitTest.java");
                out = new OutputStreamWriter(fos);
                template.process(data, out);
                out.flush();
                out.close();
                fos.close();
            }
        } catch (final IOException e) {
            LoggerHelper.getLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
        } catch (final TemplateException e) {
            LoggerHelper.getLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (final IOException e) {
                LOGGER.logp(Level.SEVERE, "JsonConvertor", "generateJunitPojos", e.getMessage(), e);
            }
        }
    }
}
