package ${package};

import com.google.gson.Gson;

/**
 * This is a generated file.
<#if comp.getAuthor()??>
 *
 * @author ${comp.getAuthor()}
</#if>
 */ 
public final class ${comp.Root}GsonMapper {

    final static Logger logger;
    static {
        logger = Logger.getLogger(${comp.Root}GsonMapper.class.getName());
    }

    /**
     * @param jsonString String to parse
     * @return ${comp.Root}
     */
    public static ${comp.Root} readValue(String jsonString) {
        final Gson gson = new Gson();
        final ${comp.Root}MainComponent temp = gson.fromJson(jsonString, ${comp.Root}MainComponent.class);
        if (temp == null) {
            return null;
        } else {
            return temp.getMainComponent();
        }
    }
}