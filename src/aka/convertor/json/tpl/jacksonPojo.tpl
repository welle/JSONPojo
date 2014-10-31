package ${package};
<#assign myList = []>
<#assign compSize = comp.getNodes()?size - 1>
<#assign importedDate = false>

<#if comp.getAnnotations() == "eclipse">
	<#if comp.containList() == true>
import org.eclipse.jdt.annotation.NonNull;
	</#if>
import org.eclipse.jdt.annotation.Nullable;
	<#elseif comp.getAnnotations() == "jsr">
	<#if comp.containList() == true>
import javax.annotation.Nonnull;
	</#if>
import javax.annotation.Nullable;
</#if>
<#list comp.getNodes() as column>  
	<#if column.getJavaType() == "Date">
		<#if importedDate == false>
			<#assign importedDate = true>
import java.util.Date;
		</#if>
		<#if (!myList?seq_contains(column.getDeserName()))>
			<#assign myList = myList + [column.getDeserName()]>
import ${package}.deserialisers.${column.getDeserName()}Deserializer;
		</#if>
	</#if>
</#list>

<#if comp.containList() == true>
import java.util.ArrayList;
import java.util.List;
</#if>
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<#if comp.containJsonProperty()>
import com.fasterxml.jackson.annotation.JsonProperty;
</#if>
<#if (comp.containDeserialiser() == true)>
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
</#if>

/**
 * This is a generated file.
 *
<#if comp.getAuthor()??>
 * @author ${comp.getAuthor()}
</#if>
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class ${comp.getName()?cap_first} {

<#list comp.getNodes() as column> 
	<#if (column.getSerName() != column.getParamName())>
    @JsonProperty("${column.getSerName()}")
	</#if>
	<#if (column.getJavaType() == "List" )>
		<#if comp.getAnnotations() == "eclipse">
	@NonNull
		<#elseif comp.getAnnotations() == "jsr">
	@Nonnull
		</#if>
    private List<${column.getJavaSubType()}> ${column.getParamName()} = new ArrayList<>();
	<#elseif (column.getJavaType() == "Date" )>
    @JsonDeserialize(using = ${column.getDeserName()}Deserializer.class)
    private ${column.getJavaType()} ${column.getParamName()};
	<#else>
		<#if comp.getAnnotations() == "eclipse">
	@Nullable
		<#elseif comp.getAnnotations() == "jsr">
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
<#list comp.getNodes() as column> 
     * @param ${column.getParamName()}Param <#if (column.getJavaType() == "List")>List<${column.getJavaSubType()}<#else>${column.getJavaType()}</#if>
</#list>
     */
    public ${comp.getName()?cap_first}(<#list comp.getNodes() as column><#if comp.getAnnotations() == "eclipse"><#if (column.getJavaType() == "List" )>@NonNull<#else>@Nullable</#if><#elseif comp.getAnnotations() == "jsr"><#if (column.getJavaType() == "List" )>@Nonnull<#else>@Nullable</#if></#if> final <#if (column.getJavaType() == "List" )>List<${column.getJavaSubType()}> ${column.getParamName()}Param<#else>${column.getJavaType()} ${column.getParamName()}Param</#if><#if (column_index != compSize)>, </#if></#list>) {
<#list comp.getNodes() as column> 
        this.${column.getParamName()} = ${column.getParamName()}Param;
</#list>
    }
<#list comp.getNodes() as column>
    
    /**
     * Get value of ${column.getParamName()}.
     *
<#if (column.getJavaType() == "List")>     
     * @return List<${column.getJavaSubType()}> value of ${column.getParamName()}
     */
    <#if comp.getAnnotations() == "eclipse">
    @NonNull
	<#elseif comp.getAnnotations() == "jsr">
	@Nonnull
	</#if>
    public final List<${column.getJavaSubType()}> get${column.getParamName()?cap_first}() {
<#else> 
     * @return ${column.getJavaType()} value of ${column.getParamName()}
     */
    <#if comp.getAnnotations() == "eclipse">
    @Nullable
	<#elseif comp.getAnnotations() == "jsr">
	@Nullable
	</#if>
    public final ${column.getJavaType()} get${column.getParamName()?cap_first}() {
</#if>
        return this.${column.getParamName()};
    }
</#list>

<#list comp.getNodes() as column>
    
    /**
     * Set value of ${column.getParamName()}.
     *
     * @param ${column.getParamName()}Param
<#if (column.getJavaType() == "List")>     
     */
    public final void get${column.getParamName()?cap_first}(<#if comp.getAnnotations() == "eclipse">@NonNull<#elseif comp.getAnnotations() == "jsr">@Nonnull</#if> final List<${column.getJavaSubType()}> ${column.getParamName()}Param) {
<#else> 
     */
    public final void set${column.getParamName()?cap_first}(<#if comp.getAnnotations() == "eclipse">@Nullable<#elseif comp.getAnnotations() == "jsr">@Nullable</#if> final ${column.getJavaType()} ${column.getParamName()}Param) {
</#if>
        this.${column.getParamName()} = ${column.getParamName()}Param;
    }
</#list>
}
