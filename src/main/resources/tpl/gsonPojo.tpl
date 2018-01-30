package ${package};

import com.google.gson.annotations.SerializedName;

<#if comp.containList() == true>
import java.util.List;
</#if>

/**
 * This is a generated file.
<#if comp.getAuthor()??>
 *
 * @author ${comp.getAuthor()}
</#if>
 */ 
public final class ${comp.getName()?cap_first} {

<#list comp.getNodes() as column> 
<#if (column.getSerName() != column.getParamName())>
    @SerializedName("${column.getSerName()}")
</#if>
<#if (column.getJavaType() == "List" )>
    private List<${column.getJavaSubType()}> ${column.getParamName()};
<#elseif (column.getJavaType() == "java.util.Date" )>
    private String ${column.getParamName()};
<#else>
    private ${column.getJavaType()} ${column.getParamName()};
</#if>
</#list>
<#list comp.getNodes() as column>

    /**
     * Get value of ${column.getParamName()} 
     *
<#if (column.getJavaType() == "List" )>
     * @return List<${column.getJavaSubType()}> value of ${column.getParamName()}
     */
    public List<${column.getJavaSubType()}> get${column.getParamName()?cap_first}() {
<#elseif (column.getJavaType() == "java.util.Date" )>
     * @return String value of ${column.getName()}
     */
    public String get${column.getParamName()?cap_first}() {
<#else>
     * @return ${column.getJavaType()} value of ${column.getParamName()}
     */
    public ${column.getJavaType()} get${column.getParamName()?cap_first}() {
</#if>
       return this.${column.getParamName()};
    }
</#list>
}
