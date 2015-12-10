package aka.convertor.json.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * List of reserved word that can not be used as variable name.
 *
 * @author Welle Charlotte
 */
public final class ReservedWord {

    /**
     * Reserved words.
     */
    public static final Set<String> RESERVED_WORDS = new HashSet<String>(Arrays.asList(new String[] { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default",
            "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if",
            "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package",
            "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while", "list",
    "array" }));

}
