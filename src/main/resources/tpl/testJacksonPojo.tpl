package ${package};
<#assign myList = []>
<#assign compSize = comp.getFields()?size - 1>

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

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
<#list comp.getFields() as column>  
	<#if column.getJavaType() == "Date" || column.getJavaType() == "URI">
		<#if (!myList?seq_contains(column.getDeserName()))>
			<#assign myList = myList + [column.getDeserName()]>
		</#if>
	</#if>
</#list>

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * JUnit skeleton for the ${comp.getName()?cap_first} object.
<#if comp.getAuthor()??>
 *
 * @author ${comp.getAuthor()}
</#if>
 */ 
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ${comp.getName()?cap_first}.class })
public class ${comp.getName()?cap_first}_JUnitTest {

<#list comp.getFields() as column>
    /**
     * get${column.getParamName()?cap_first} with an new ${comp.getName()?cap_first}.
     */
    @org.junit.Test
    public void testGet${column.getParamName()?cap_first}() {
        // arrange : set up the test
        final ${comp.getName()?cap_first} ${comp.getName()} = mock(${comp.getName()?cap_first}.class);
        assert ${comp.getName()} != null;

        // act : run the test
        final ${column.getJavaType()} result = ${comp.getName()}.get${column.getParamName()?cap_first}();

        // assert : verify that the test run correctly
        assertNotNull(result);
    }

    /**
     * set${column.getParamName()?cap_first} with an new ${comp.getName()?cap_first}.
     */
    @org.junit.Test
    public void testSet${column.getParamName()?cap_first}() {
        // arrange : set up the test
        final ${comp.getName()?cap_first} ${comp.getName()} = mock(${comp.getName()?cap_first}.class);
        assert ${comp.getName()} != null;

        // act : run the test
        // TODO set proper expected value
<#if column.getJavaType() == "Date">
        final ${column.getJavaType()} expectedValue = new Date();
<#elseif column.getJavaType() == "String">
        final ${column.getJavaType()} expectedValue = "X";
<#elseif column.getJavaType() == "BigDecimal">
        final ${column.getJavaType()} expectedValue = new BigDecimal("42");
<#elseif column.getJavaType() == "byte[]">
        final ${column.getJavaType()} expectedValue = new byte[27];
<#elseif column.getJavaType() == "Double">
        final ${column.getJavaType()} expectedValue = Double.valueOf(24);
<#elseif column.getJavaType() == "Long">
        final ${column.getJavaType()} expectedValue = Long.valueOf(1);
<#elseif column.getJavaType() == "List">
        final List<${column.getJavaSubType()}> expectedValue = new ArrayList<>();
        ${column.getJavaSubType()} ${column.getJavaSubType()?uncap_first}Item = new ${column.getJavaSubType()}();
        expectedValue.add(${column.getJavaSubType()?uncap_first}Item);
<#else>
        final ${column.getJavaType()} expectedValue = null;
</#if>        
        ${comp.getName()}.set${column.getParamName()?cap_first}(expectedValue);
<#if column.getJavaType() == "List">
        final List<${column.getJavaSubType()}> result = ${comp.getName()}.get${column.getParamName()?cap_first}();
<#else>
        final ${column.getJavaType()} result = ${comp.getName()}.get${column.getParamName()?cap_first}();
</#if>

        // assert : verify that the test run correctly
        assertEquals(expectedValue, result);
        // TODO add extra validations
    }
</#list>
}