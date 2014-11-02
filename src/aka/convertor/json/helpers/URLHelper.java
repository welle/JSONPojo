package aka.convertor.json.helpers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jdt.annotation.NonNull;

public class URLHelper {

	public static boolean isURL(@NonNull String urlStr) {
		try {
			URL url = new URL(urlStr);
			final URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			url = uri.toURL();
			return true;
		} catch (final MalformedURLException e) {
			return false;
		} catch (final URISyntaxException e) {
			return false;
		}
	}

	private URLHelper() {
		// singleton
	}
}
