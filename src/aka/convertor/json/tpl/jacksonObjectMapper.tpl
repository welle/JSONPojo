package ${package};

import java.io.IOException;

<#if comp.getAnnotations() == "eclipse">
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
<#elseif comp.getAnnotations() == "jsr">
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
</#if>

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is a generated file.
 *
<#if comp.getAuthor()??>
 * @author ${comp.getAuthor()}
</#if>
 */
public final class ${comp.getName()}JacksonMapper {

    /**
	 * Method to deserialize JSON content into a non-container type.
     *
     * @param jsonString
	 *            JSon string to be deserialize.
     * @return ${comp.getName()}.
	 * @throws IOException
	 * @throws JsonParseException
	 *             when non-well-formed content (content that does not conform
	 *             to JSON syntax as per specification) is encountered.
     */
	<#if comp.getAnnotations() == "eclipse">
    @Nullable
	<#elseif comp.getAnnotations() == "jsr">
	@Nullable
	</#if>
    public static ${comp.getName()} readValue(<#if comp.getAnnotations() == "eclipse">@NonNull<#elseif comp.getAnnotations() == "jsr">@Nonnull</#if> final String jsonString) throws JsonParseException, IOException {
        ${comp.getName()} result = null;
        
        if (jsonString.trim().length() > 0) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final JsonFactory jsonFactory = new JsonFactory();
            
            final JsonParser jp = jsonFactory.createJsonParser(jsonString);
            result = objectMapper.readValue(jp, ${comp.getName()}.class);
        }
        
        return result;
    }

	/**
	 * Method that can be used to serialize given object as a JSon String.
	 *
	 * @param object
	 *            Object to be serialize.
	 * @return JSon String.
	 * @throws IOException
	 *             signal fatal problems with mapping of content.
	 */
	<#if comp.getAnnotations() == "eclipse">
    @Nullable
	<#elseif comp.getAnnotations() == "jsr">
	@Nullable
	</#if>
    public static String writeValue(<#if comp.getAnnotations() == "eclipse">@NonNull<#elseif comp.getAnnotations() == "jsr">@Nonnull</#if> final ${comp.getName()} object) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }    
}
