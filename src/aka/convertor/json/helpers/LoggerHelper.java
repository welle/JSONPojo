package aka.convertor.json.helpers;

import java.util.logging.Logger;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Logger helper.
 *
 * @author Welle Charlotte
 */
public final class LoggerHelper {

    @NonNull
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("JSONPojo");
    }

    /**
     * Get logger.
     *
     * @return Logger
     */
    @NonNull
    public static Logger getLogger() {
        return LOGGER;
    }

}
