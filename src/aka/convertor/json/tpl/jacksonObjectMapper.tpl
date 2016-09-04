package ${package};

import java.io.IOException;
<#if isRootAnArray>
	<#if useList>
import java.util.ArrayList;	
import java.util.Arrays;
import java.util.List;		
	</#if>
</#if>

<#if comp.getAnnotation() == "eclipse">
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
<#elseif comp.getAnnotation() == "jsr">
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
</#if>

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is a generated file.
<#if comp.getAuthor()??>
 *
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
	<#if isRootAnArray>
		<#if useList>
			<#if comp.getAnnotation() == "eclipse">
	@NonNull
			<#elseif comp.getAnnotation() == "jsr">
	@Nonnull
			</#if>
		</#if>
	<#else>
		<#if comp.getAnnotation() == "eclipse">
	@Nullable
		<#elseif comp.getAnnotation() == "jsr">
	@Nullable
		</#if>
	</#if>
    public static <#if isRootAnArray><#if useList>List<${comp.getName()}><#else>${comp.getName()} <#if comp.getAnnotation() == "eclipse">@NonNull<#elseif comp.getAnnotation() == "jsr">@Nonnull</#if> []</#if><#else>${comp.getName()}</#if> readValue(<#if comp.getAnnotation() == "eclipse">@NonNull<#elseif comp.getAnnotation() == "jsr">@Nonnull</#if> final String jsonString) throws JsonParseException, IOException {
        <#if isRootAnArray><#if useList>List<${comp.getName()}><#else>${comp.getName()} <#if comp.getAnnotation() == "eclipse">@NonNull<#elseif comp.getAnnotation() == "jsr">@Nonnull</#if> []</#if><#else>${comp.getName()}</#if> result = <#if isRootAnArray><#if useList>new ArrayList<>()<#else>null</#if><#else>null</#if>;
        
        if (jsonString.trim().length() > 0) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final JsonFactory jsonFactory = new JsonFactory();
            
            final JsonParser jp = jsonFactory.createJsonParser(jsonString);
            <#if isRootAnArray>
				<#if useList>
			${comp.getName()}[] temp = objectMapper.readValue(jp, ${comp.getName()}[].class);
			result = Arrays.asList(temp);
			assert result != null;
				<#else>
			result = objectMapper.readValue(jp, ${comp.getName()}[].class);
				</#if>
			<#else>
            result = objectMapper.readValue(jp, ${comp.getName()}<#if isRootAnArray>[]</#if>.class);
			</#if>
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
	<#if comp.getAnnotation() == "eclipse">
    @Nullable
	<#elseif comp.getAnnotation() == "jsr">
	@Nullable
	</#if>
    public static String writeValue(<#if comp.getAnnotation() == "eclipse">@NonNull<#elseif comp.getAnnotation() == "jsr">@Nonnull</#if> final <#if isRootAnArray><#if useList>List<${comp.getName()}><#else>${comp.getName()}[]</#if><#else>${comp.getName()}</#if> object) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }    
}
