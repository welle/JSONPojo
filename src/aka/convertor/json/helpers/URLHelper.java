package aka.convertor.json.helpers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jdt.annotation.NonNull;

/**
 * URL helper.</br>
 * Class to manage and test URL(s).
 *
 * @author Welle Charlotte
 */
public final class URLHelper {

    /**
     * Test if the given String parameter is a valid URL.
     *
     * @param urlStr string to test
     * @return <code>true</code> if the given string is a valid URL
     * @throws MalformedURLException indicate that a malformed URL has occurred. Either no legal protocol could be found in a specification string or the string could not be parsed.
     * @throws URISyntaxException indicate that a string could not be parsed as a URI reference.
     */
    public final static boolean isURL(@NonNull final String urlStr) throws MalformedURLException, URISyntaxException {
        try {
            URL url = new URL(urlStr);
            final URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            url = uri.toURL();
            return true;
        } catch (final MalformedURLException | URISyntaxException e) {
            throw e;
        }
    }

    private URLHelper() {
        // singleton
    }
}
