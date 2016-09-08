package ${package};
<#assign myList = []>
<#assign compSize = comp.getFields()?size - 1>

<#list comp.getFields() as column>  
	<#if column.getJavaType() == "URI">
import java.net.URI;
	<#break>
	</#if>
</#list>
<#if comp.containList() == true>
import java.util.ArrayList;
</#if>
<#list comp.getFields() as column>  
	<#if column.getJavaType() == "Date">
import java.util.Date;
	<#break>
	</#if>
</#list>
<#if comp.containList() == true>
import java.util.List;

</#if>
<#if comp.getAnnotation() == "eclipse">
	<#if comp.containList() == true>
import org.eclipse.jdt.annotation.NonNull;
	</#if>
	<#elseif comp.getAnnotation() == "jsr">
	<#if comp.containList() == true>
import javax.annotation.Nonnull;
	</#if>
</#if>
<#list comp.getFields() as column>  
	<#if column.getJavaType() != "List">
		<#if comp.getAnnotation() == "eclipse">
import org.eclipse.jdt.annotation.Nullable;
		<#elseif comp.getAnnotation() == "jsr">
import javax.annotation.Nullable;
		</#if>
		<#break>
	</#if>
</#list>
<#list comp.getFields() as column>  
	<#if column.getJavaType() == "Date" || column.getJavaType() == "URI">
		<#if (!myList?seq_contains(column.getDeserName()))>
			<#assign myList = myList + [column.getDeserName()]>
			<#if deserialisers??>
import ${package}.${deserialisers}.${column.getDeserName()}Deserializer;
			</#if>
		</#if>
	</#if>
</#list>

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<#if comp.containJsonProperty()>
import com.fasterxml.jackson.annotation.JsonProperty;
</#if>
<#if myList?has_content>
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
</#if>

/**
 * This is a generated file.
<#if comp.getAuthor()??>
 *
 * @author ${comp.getAuthor()}
</#if>
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class ${comp.getName()?cap_first} {

<#list comp.getFields() as column> 
	<#if (column.getSerName() != column.getParamName())>
    @JsonProperty("${column.getSerName()}")
	</#if>
	<#if (column.getJavaType() == "List" )>
		<#if comp.getAnnotation() == "eclipse">
	@NonNull
		<#elseif comp.getAnnotation() == "jsr">
	@Nonnull
		</#if>
    private List<${column.getJavaSubType()}> ${column.getParamName()} = new ArrayList<>();
	<#elseif (column.getJavaType() == "Date" )>
    @JsonDeserialize(using = ${column.getDeserName()}Deserializer.class)
    private ${column.getJavaType()} ${column.getParamName()};
    <#elseif (column.getJavaType() == "URI" )>
    @JsonDeserialize(using = ${column.getDeserName()}Deserializer.class)
    private ${column.getJavaType()} ${column.getParamName()};
	<#else>
		<#if comp.getAnnotation() == "eclipse">
	@Nullable
		<#elseif comp.getAnnotation() == "jsr">
	@Nullable
		</#if>
    private ${column.getJavaType()} ${column.getParamName()};
	</#if>
</#list>

	/**
     * Empty Constructor.
     */
    public ${comp.getName()?cap_first}() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
<#list comp.getFields() as column> 
     * @param ${column.getParamName()}Param <#if (column.getJavaType() == "List")>List<${column.getJavaSubType()}><#else>${column.getJavaType()}</#if>
</#list>
     */
    public ${comp.getName()?cap_first}(<#list comp.getFields() as column><#if comp.getAnnotation() == "eclipse"><#if (column.getJavaType() == "List" )>@NonNull<#else>@Nullable</#if><#elseif comp.getAnnotation() == "jsr"><#if (column.getJavaType() == "List" )>@Nonnull<#else>@Nullable</#if></#if> final <#if (column.getJavaType() == "List" )>List<${column.getJavaSubType()}> ${column.getParamName()}Param<#else>${column.getJavaType()} ${column.getParamName()}Param</#if><#if (column_index != compSize)>, </#if></#list>) {
<#list comp.getFields() as column> 
        this.${column.getParamName()} = ${column.getParamName()}Param;
</#list>
    }
<#list comp.getFields() as column>
    
    /**
     * Get value of ${column.getParamName()}.
     *
<#if (column.getJavaType() == "List")>     
     * @return List<${column.getJavaSubType()}> value of ${column.getParamName()}
     */
    <#if comp.getAnnotation() == "eclipse">
    @NonNull
	<#elseif comp.getAnnotation() == "jsr">
	@Nonnull
	</#if>
    public final List<${column.getJavaSubType()}> get${column.getParamName()?cap_first}() {
<#else> 
     * @return ${column.getJavaType()} value of ${column.getParamName()}
     */
    <#if comp.getAnnotation() == "eclipse">
    @Nullable
	<#elseif comp.getAnnotation() == "jsr">
	@Nullable
	</#if>
    public final ${column.getJavaType()} get${column.getParamName()?cap_first}() {
</#if>
        return this.${column.getParamName()};
    }
</#list>

<#list comp.getFields() as column>
    
    /**
     * Set value of ${column.getParamName()}.
     *
     * @param ${column.getParamName()}Param
<#if (column.getJavaType() == "List")>     
     */
    public final void get${column.getParamName()?cap_first}(<#if comp.getAnnotation() == "eclipse">@NonNull<#elseif comp.getAnnotation() == "jsr">@Nonnull</#if> final List<${column.getJavaSubType()}> ${column.getParamName()}Param) {
<#else> 
     */
    public final void set${column.getParamName()?cap_first}(<#if comp.getAnnotation() == "eclipse">@Nullable<#elseif comp.getAnnotation() == "jsr">@Nullable</#if> final ${column.getJavaType()} ${column.getParamName()}Param) {
</#if>
        this.${column.getParamName()} = ${column.getParamName()}Param;
    }
</#list>
}
