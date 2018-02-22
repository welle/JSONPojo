package aka.convertor.json.constants;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Enumeration of generators.
 *
 * @author Welle Charlotte
 */
public enum Generator {

    /**
     * GSON generator.
     */
    GSON("gsonPojo.tpl", "gsonObjectMapper.tpl", null, null, null),

    /**
     * Jackson generator.
     */
    JACKSON("jacksonPojo.tpl", "jacksonObjectMapper.tpl", "jacksonDateDeserialiser.tpl", "jacksonURLDeserialiser.tpl", "testJacksonPojo.tpl");

    @NonNull
    private String pojoTpl;
    @NonNull
    private String objectMapperTpl;
    @Nullable
    private String deserialiserDateTpl;
    @Nullable
    private String deserialiserURLTpl;
    @Nullable
    private String junitPojoTpl;

    private Generator(@NonNull final String pojoTpl, @NonNull final String objectMapperTpl, @Nullable final String deserialiserDateTpl, @Nullable final String deserialiserURLTpl, @Nullable final String junitPojoTpl) {
        this.pojoTpl = pojoTpl;
        this.objectMapperTpl = objectMapperTpl;
        this.deserialiserDateTpl = deserialiserDateTpl;
        this.deserialiserURLTpl = deserialiserURLTpl;
        this.junitPojoTpl = junitPojoTpl;
    }

    /**
     * Get POJO template.
     *
     * @return the pojoTpl
     */
    @NonNull
    public String getPojoTpl() {
        return this.pojoTpl;
    }

    /**
     * Get JUnit POJO template.
     *
     * @return the junitPojoTpl
     */
    @Nullable
    public String getJunitPojoTpl() {
        return this.junitPojoTpl;
    }

    /**
     * Get object mapper template.
     *
     * @return the objectMapperTpl
     */
    @NonNull
    public String getObjectMapperTpl() {
        return this.objectMapperTpl;
    }

    /**
     * Get Date deserialiser template.
     *
     * @return the deserialiserDateTpl
     */
    @Nullable
    public String getDeserialiserDateTpl() {
        return this.deserialiserDateTpl;
    }

    /**
     * Get URL deserialiser template.
     *
     * @return the deserialiserURLTpl
     */
    @Nullable
    public String getDeserialiserURLTpl() {
        return this.deserialiserURLTpl;
    }
}
