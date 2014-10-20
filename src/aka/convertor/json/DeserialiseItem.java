package aka.convertor.json;

/**
 * see DatabaseMetaData.html#getColumns(java.lang.String,%20java.lang.String,%20
 * java.lang.String,%20java.lang.String) for details on the available metadata
 *
 * @author daniel
 *
 */
public class DeserialiseItem {

	private final String name;
	private final String format;

	/**
	 * @param name
	 * @param format
	 */
	public DeserialiseItem(final String name, final String format) {
		this.name = name;
		this.format = format;
	}

	public String getFormat() {
		return this.format;
	}

	public String getName() {
		return this.name;
	}

}
