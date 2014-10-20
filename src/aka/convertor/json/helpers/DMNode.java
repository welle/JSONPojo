package aka.convertor.json.helpers;

import org.w3c.dom.Element;

public class DMNode {

	private String name;
	private String type;
	private String nullable;
	private String autoIncrement;
	private String length;
	private final String pk;
	private final String defaultValue;
	private String subType;

	public DMNode(final Element node) {
		this.name = node.getAttribute("name");
		this.type = node.getAttribute("type");
		this.subType = node.getAttribute("subtype");
		this.nullable = node.getAttribute("nullable");
		this.autoIncrement = node.getAttribute("autoincrement");
		this.length = node.getAttribute("length");
		this.pk = node.getAttribute("pk");
		final String defVal = node.getAttribute("default");
		if (defVal == null) {
			this.defaultValue = "";
		} else {
			this.defaultValue = defVal;
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		if ("Date".equals(this.type)) {
			this.type = "java.util.Date";
		}
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(final String subType) {
		this.subType = subType;
	}

	public String getNullable() {
		return this.nullable;
	}

	public void setNullable(final String nullable) {
		this.nullable = nullable;
	}

	public String getAutoIncrement() {
		return this.autoIncrement;
	}

	public void setAutoIncrement(final String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(final String length) {
		this.length = length;
	}

	public String getPrimaryKey() {
		return this.pk;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

}
