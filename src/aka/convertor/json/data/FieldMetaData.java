package aka.convertor.json.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;

import aka.convertor.json.JsonMetaData;
import aka.convertor.json.helpers.DateHelper;
import aka.convertor.json.helpers.StringUtilities;
import aka.convertor.json.helpers.URLHelper;

public class FieldMetaData {

    private final int size;
    private final int decimals;
    @NonNull
    private String localName;
    @NonNull
    private String paramName;
    @NonNull
    private final String serialisableName;
    @NonNull
    private String javaType;
    @NonNull
    private String JavaSubType;
    @NonNull
    private final JsonMetaData jsonMetaData;
    private String deserName;
    @NonNull
    private final ObjectMetaData parentObject;
    @Nullable
    private ObjectMetaData object;
    private boolean isObject = false;

    /**
     * @param fieldName
     * @param serName
     * @param jsonNode
     * @param jsonMetaData
     * @param parentObject
     */
    public FieldMetaData(@NonNull final String fieldName, @NonNull final String serName, @NonNull final JsonNode jsonNode, @NonNull final JsonMetaData jsonMetaData,
            @NonNull final ObjectMetaData parentObject) {
        this.jsonMetaData = jsonMetaData;
        this.localName = StringUtilities.getLocalName(fieldName);
        this.paramName = StringUtilities.getLocalName(fieldName);
        this.serialisableName = serName;
        this.deserName = "";
        this.parentObject = parentObject;

        final boolean isArray = jsonNode.isArray();

        if (isArray) {
            // this is a list
            final String temp = List.class.getSimpleName();
            this.javaType = temp;
            this.JavaSubType = StringUtilities.firstLetterUpperCase(this.localName);
        } else {
            this.javaType = StringUtilities.firstLetterUpperCase(this.localName);
            this.JavaSubType = "";
        }

        // simple field or object ?

        if (((isArray && jsonNode.elements().next().fields().hasNext())) || jsonNode.isObject()) {
            // field is an object or an array of object/field
            this.size = 0;
            this.decimals = 0;
            this.isObject = true;
            this.object = new ObjectMetaData(this.localName, jsonNode, jsonMetaData);
        } else {
            // field is a simple object (date, string, etc)
            this.size = 0;
            this.decimals = 0;

            setJavaType(jsonNode, isArray);
        }
    }

    private <T> void setJavaType(@NonNull final JsonNode jsonNode, final boolean isArray) {
        if (jsonNode.isBigDecimal()) {
            setType(BigDecimal.class, isArray);
        } else if (jsonNode.isBigInteger()) {
            setType(BigInteger.class, isArray);
        } else if (jsonNode.isBinary()) {
            setType(byte[].class, isArray);
        } else if (jsonNode.isBoolean()) {
            setType(Boolean.class, isArray);
        } else if (jsonNode.isDouble()) {
            setType(Double.class, isArray);
        } else if (jsonNode.isFloatingPointNumber()) {
            setType(Float.class, isArray);
        } else if (jsonNode.isInt()) {
            setType(Integer.class, isArray);
        } else if (jsonNode.isIntegralNumber()) {
            setType(Integer.class, isArray);
        } else if (jsonNode.isLong()) {
            setType(Long.class, isArray);
        } else if (jsonNode.isNumber()) {
            setType(Number.class, isArray);
        } else if (jsonNode.isBoolean()) {
            setType(Boolean.class, isArray);
        } else {
            // check if this is a date
            final String value = jsonNode.textValue();
            if (value == null) {
                setType(String.class, isArray);
            } else {
                final String dateName = DateHelper.parseDate(value);
                if (dateName == null) {
                    try {
                        final boolean isURL = URLHelper.isURL(value);
                        // check if this is an url string
                        if (isURL) {
                            setType(URI.class, isArray);
                            // add deserialiser
                            this.jsonMetaData.addDeserialiser("URL", "URL", null);
                            this.deserName = "URL";
                        } else {
                            setType(String.class, isArray);
                        }
                    } catch (final MalformedURLException e) {
                    } catch (final URISyntaxException e) {
                    }
                } else {
                    setType(Date.class, isArray);
                    // add deserialiser
                    final String pattern = DateHelper.DATENAMEFORMATMAP.get(dateName);
                    this.jsonMetaData.addDeserialiser("Date", dateName, pattern);
                    this.deserName = dateName;
                }
            }
        }
    }

    private <T> void setType(@NonNull final Class<T> className, final boolean isArray) {
        final String type = className.getSimpleName();
        if (isArray) {
            // this is a list
            final String temp = List.class.getSimpleName();
            this.javaType = temp;
            this.JavaSubType = type;
        } else {
            this.javaType = type;
        }
    }

    /**
     * @return int
     */
    public int getDecimals() {
        return this.decimals;
    }

    /**
     * @return String
     */
    @NonNull
    public String getJavaType() {
        return this.javaType;
    }

    /**
     * @return String
     */
    @Nullable
    public String getJavaSubType() {
        return this.JavaSubType;
    }

    /**
     * @return String
     */
    @NonNull
    public String getLocalName() {
        return this.localName;
    }

    /**
     * @return String
     */
    @NonNull
    public String getParamName() {
        return this.paramName;
    }

    /**
     * @return String
     */
    public String getSerName() {
        return this.serialisableName;
    }

    /**
     * @return String
     */
    @Nullable
    public String getDeserName() {
        return this.deserName;
    }

    /**
     * @return int
     */
    public int getSize() {
        return this.size;
    }

    public void setFieldName(@NonNull final String newName) {
        this.localName = StringUtilities.getLocalName(newName);
        this.paramName = StringUtilities.getLocalName(newName);

        if (List.class.getSimpleName().equals(this.javaType)) {
            // this is a list
            this.JavaSubType = StringUtilities.firstLetterUpperCase(this.localName);
        } else {
            this.javaType = StringUtilities.firstLetterUpperCase(this.localName);
        }
    }

    boolean isObject() {
        return this.isObject;
    }

    @NonNull
    public ObjectMetaData getParentObject() {
        return this.parentObject;
    }

    @Nullable
    public ObjectMetaData getObject() {
        return this.object;
    }

    public boolean isAList() {
        return List.class.getSimpleName().equals(this.javaType);
    }

    @NonNull
    public JsonMetaData getJsonMetaData() {
        return this.jsonMetaData;
    }
}
