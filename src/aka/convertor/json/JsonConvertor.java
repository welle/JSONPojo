package aka.convertor.json;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

import aka.convertor.json.constants.AnnotationType;
import aka.convertor.json.data.Component;
import aka.convertor.json.helpers.StringUtility;

import com.sun.istack.internal.Nullable;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JsonConvertor {

	public JsonConvertor(@NonNull String name, @NonNull String jsonToParse, @NonNull String packageName, @NonNull String path, @NonNull String subPath, @Nullable String author,
			@NonNull AnnotationType annotationType) {
		final JsonMetaData jsonMetaData = new JsonMetaData(name, name, "NEW", jsonToParse);

		// objects
		final Configuration cfg = new Configuration();
		// Configure Freemarker
		cfg.setClassForTemplateLoading(getClass(), "./tpl");
		try {
			// Load the template
			final Template template = cfg.getTemplate("jacksonPojo.tpl");
			final ArrayList<ObjectMetaData> objects = jsonMetaData.getObjects();
			System.out.println("[JSON2XMLToolNut] componentClicked - Processing tables :: " + objects.size());
			for (final ObjectMetaData object : objects) {
				System.out.println("[JSON2XMLToolNut] componentClicked - Processing table :: " + object.getObjectName());
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("package", packageName);
				final Component component = new Component(annotationType);
				component.setName(object.getJavaObjectName());
				component.setNodes(object.getFields());
				component.setAuthor(author);
				data.put("comp", component);
				data.put("deserialisers", subPath);

				final FileOutputStream fos = new FileOutputStream(path + "/" + StringUtility.firstLetterUpperCase(object.getJavaObjectName()) + ".java");
				final Writer out = new OutputStreamWriter(fos);
				template.process(data, out);
				out.flush();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}

		// object mapper
		try {
			final Template templateMapper = cfg.getTemplate("jacksonObjectMapper.tpl");
			System.out.println("[JSON2XMLToolNut] componentClicked - Processing Object mapper");
			// Load the template
			final Map<String, Object> data = new HashMap<String, Object>();
			data.put("package", packageName);
			final Component component = new Component(annotationType);
			component.setName(name);
			component.setAuthor(author);
			data.put("comp", component);

			final FileOutputStream fos = new FileOutputStream(path + "/" + StringUtility.firstLetterUpperCase(name) + "JacksonMapper.java");
			final Writer out = new OutputStreamWriter(fos);
			templateMapper.process(data, out);
			out.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}
		final OutputStreamWriter osw = null;

		// Deserialisers
		try {
			// Load the template

			final List<Deserialiser> deserializers = jsonMetaData.getDeserialises();
			System.out.println("Processing Deserializers :: " + deserializers.size());

			String filePath = path + "/";
			if (!deserializers.isEmpty()) {
				if (subPath != null && subPath.trim().length() > 0) {
					filePath = filePath + subPath + "/";
					final File theDir = new File(filePath);

					// if the directory does not exist, create it
					if (!theDir.exists()) {
						try {
							theDir.mkdir();
						} catch (final SecurityException se) {
							// handle it
						}
					}
				}
			}

			String deserializersString = packageName;
			if (subPath != null && subPath.trim().length() > 0) {
				deserializersString = deserializersString + "." + subPath;
			}
			for (final Deserialiser deserialiser : deserializers) {
				Template template = null;
				System.out.println("[JSON2XMLToolNut] componentClicked - Processing table :: " + deserialiser.getType());
				if ("Date".equals(deserialiser.getType())) {
					template = cfg.getTemplate("jacksonDateDeserialiser.tpl");
				} else if ("URL".equals(deserialiser.getType())) {
					template = cfg.getTemplate("jacksonURLDeserialiser.tpl");
				}
				if (template != null) {
					final List<DeserialiseItem> subDeserializers = deserialiser.getItems();
					for (final DeserialiseItem deserialiseItem : subDeserializers) {
						final Map<String, Object> data = new HashMap<String, Object>();
						data.put("package", deserializersString);
						final Component component = new Component(annotationType);
						component.setName(deserialiseItem.getName());
						component.setAuthor(author);
						data.put("comp", component);
						data.put("deserialiser", deserialiseItem);

						final FileOutputStream fos = new FileOutputStream(filePath + StringUtility.firstLetterUpperCase(deserialiseItem.getName()) + "Deserializer.java");
						final Writer out = new OutputStreamWriter(fos);
						template.process(data, out);
						out.flush();
					}
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generateFromDMComponent(final String project, final String pojoPackage, final File srcDir, final String dstBasePath, final String dmPath, final Map<String, Model> dmModelMap)
	        throws JETException {
		if (this.fileCtxt.isDirectory(srcDir)) {
			final File[] files = this.fileCtxt.listFiles(srcDir);
			for (int i = 0; i < files.length; i++) {
				final File file = files[i];
				if (this.fileCtxt.isDirectory(file)) {
					// go into sub folder
					generateFromDMComponent(project, pojoPackage, file, dstBasePath, dmPath + "/" + file.getName(), dmModelMap);
				} else {
					final String fileName = file.getName();
					final String extension = fileName.substring(fileName.length() - 4);

					if (extension.equalsIgnoreCase(".xml")) {

						final Model dmModel = getModelFromFile(file);

						final String rootTag = dmModel.getTagName();
						if ("DATAMODEL".equals(rootTag)) {
							final DMComponent dmComponent = new DMComponent(dmModel);
							dmModelMap.put(dmComponent.getName(), dmModel);

							// generate pojos
							if (this.pojoCB.isChecked()) {
								if (pojoPackage != null) {
									generatePojoFromDM(dmComponent, dstBasePath, pojoPackage + dmPath + "/", dmPath);
								}
							}
						}
					}
				}
			}

			if (this.pojoCB.isChecked()) {
				if (pojoPackage != null) {
					final Map<String, Object> context = new HashMap<String, Object>();
					context.put("util", new StringUtility());
					context.put("package", pojoPackage);

					this.fileCtxt.createDirectories(dstBasePath + JAVASOURCE_PLAIN + pojoPackage + dmPath);

					mergeTemplate("JsonTool", "JsonPlugin/vtl/java/pojo/package.vm", context, dstBasePath + JAVASOURCE_PLAIN + pojoPackage + dmPath + "/package.html");
				}

				final String parser = this.parserFormat.getSelectedItemName();
				if ("GSON".equals(parser)) {
					// mergeTemplate("JsonTool",
					// "JsonPlugin/vtl/java/pojo/gsonContainer.vm", context,
					// javasourcePath + pkgPath + ".java");
				} else if ("JACKSON".equals(parser)) {
					final String srcBasePath = this.srcBasePathText.getText();
					final String nameToUseStr = this.nameToUse.getText();
					final File rootFile = new File(srcBasePath + XML_METADATA + project + "/" + nameToUseStr + "/ejbroot/RootComponent.xml");

					final Model dmModel = getModelFromFile(rootFile);
					final RootComponent rootComponent = new RootComponent(dmModel);
					generateJacksonMapper(rootComponent, dstBasePath, pojoPackage + dmPath + "/", dmPath);

				}
			}
		}
	}

	private void generateJacksonMapper(final RootComponent rootComponent, final String basePath, final String pkgPath, final String dmPath) {
		final Map<String, Object> context = new HashMap<String, Object>();
		context.put("comp", rootComponent);
		context.put("util", new StringUtility());
		final String pkgExtra = dmPath.replaceAll("/", "\\.");
		final String parser = this.parserFormat.getSelectedItemName();
		final String nameToUseStr = this.nameToUse.getText();
		if ("GSON".equals(parser)) {
			context.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".gson");
		} else if ("JACKSON".equals(parser)) {
			context.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".jackson");
		}

		final String javasourcePath = basePath + JAVASOURCE_PLAIN;

		logp(JETLevel.INFO, "DataModelUtilsToolNut", "generateJacksonMapper", "Generating Jackson Mapper [" + rootComponent.getRoot() + "] basePath [" + basePath + "] pkgPath [" + pkgPath
		        + "] dmPath [" + dmPath + "]");

		mergeTemplate("JsonTool", "JsonPlugin/vtl/java/pojo/jacksonObjectMapper.vm", context, javasourcePath + pkgPath + "/jackson/" + rootComponent.getRoot() + "JacksonMapper.java");

		// generate deserialiser
		String deserialiserPath = null;
		if (!rootComponent.getDeserialisers().isEmpty()) {
			if ("GSON".equals(parser)) {
				this.fileCtxt.createDirectories(javasourcePath + pkgPath + "/gson/deserialisers/");
				deserialiserPath = javasourcePath + pkgPath + "/gson/deserialisers/";
			} else if ("JACKSON".equals(parser)) {
				this.fileCtxt.createDirectories(javasourcePath + pkgPath + "/jackson/deserialisers/");
				deserialiserPath = javasourcePath + pkgPath + "/jackson/deserialisers/";
			}
		}

		for (final Deserialiser deserialiser : rootComponent.getDeserialisers()) {
			final List<DeserialiseItem> itemList = deserialiser.getItems();
			for (final DeserialiseItem deserialiseItem : itemList) {
				final Map<String, Object> contextDeserialiser = new HashMap<String, Object>();
				contextDeserialiser.put("deserialiser", deserialiseItem);
				contextDeserialiser.put("util", new StringUtility());
				if ("GSON".equals(parser)) {
					contextDeserialiser.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".gson.deserialisers");
				} else if ("JACKSON".equals(parser)) {
					contextDeserialiser.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".jackson.deserialisers");
				}

				logp(JETLevel.INFO, "DataModelUtilsToolNut", "generateJacksonMapper", "Generating Jackson Deserialiser [" + deserialiseItem.getName() + "] basePath [" + basePath + "] pkgPath ["
				        + pkgPath + "] dmPath [" + dmPath + "]");

				if (deserialiser.getType().equals("Date")) {
					mergeTemplate("JsonTool", "JsonPlugin/vtl/java/pojo/jacksonDateDeserialiser.vm", contextDeserialiser, deserialiserPath + deserialiseItem.getName() + "Deserializer.java");
				}
			}
		}
	}

	private void generatePojoFromDM(final DMComponent dmComponent, final String basePath, final String pkgPath, final String dmPath) {
		final String name = dmComponent.getName();
		if (this.exclusionList != null && this.exclusionList.contains(name)) {
			System.out.println("[DataModelUtilsToolNut] generatePojoFromDM - Component in exclusion list, skipping : " + name);
			return;
		}

		logp(JETLevel.INFO, "DataModelUtilsToolNut", "generatePojoFromDM", "Generating pojo [" + dmComponent.getName() + "] basePath [" + basePath + "] pkgPath [" + pkgPath + "] dmPath [" + dmPath
		        + "]");

		final Map<String, Object> context = new HashMap<String, Object>();
		context.put("comp", dmComponent);
		context.put("util", new StringUtility());
		final String pkgExtra = dmPath.replaceAll("/", "\\.");

		final String javasourcePath = basePath + JAVASOURCE_PLAIN;

		final String parser = this.parserFormat.getSelectedItemName();
		final String nameToUseStr = this.nameToUse.getText();
		if ("GSON".equals(parser)) {
			context.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".gson");
			this.fileCtxt.createDirectories(javasourcePath + pkgPath + "gson/");
		} else if ("JACKSON".equals(parser)) {
			context.put("package", this.pojoPackageText.getText() + pkgExtra + "." + nameToUseStr + ".jackson");
			this.fileCtxt.createDirectories(javasourcePath + pkgPath + "jackson/");
		}

		if ("GSON".equals(parser)) {
			mergeTemplate("JsonTool", "JsonPlugin/vtl/java/pojo/gsonPojoFromDM.vm", context, javasourcePath + pkgPath + "gson/" + dmComponent.getName() + ".java");
		} else if ("JACKSON".equals(parser)) {
			mergeTemplate("JsonTool", "JsonPlugin/vtl/java/pojo/jacksonPojoFromDM.vm", context, javasourcePath + pkgPath + "jackson/" + dmComponent.getName() + ".java");
		}

	}

	private void mergeTemplate(final String velocityKey, final String templateName, final Map<String, Object> context, final String dstFile) {
		try {
			final OutputStream os = this.fileCtxt.getOutputStream(dstFile);
			final OutputStreamWriter osw = new OutputStreamWriter(os);

			this.velocityCtxt.mergeTemplate(velocityKey, templateName, context, osw);

			osw.flush();
			osw.close();
		} catch (final JETException e) {
			logp(JETLevel.SEVERE, "DataModelUtilsToolNut", "mergeTemplate", e.getMessage(), e);
		} catch (final IOException e) {
			logp(JETLevel.SEVERE, "DataModelUtilsToolNut", "mergeTemplate", e.getMessage(), e);
		}
	}

	private void generateFromEJBComponent(final String project, final String srcBasePath, final Map<String, EJBComponent> ejbCompMap) throws JETException {
		final File srcDir = new File(srcBasePath + "/ejb/");

		if (this.fileCtxt.isDirectory(srcDir)) {

			final Model dataModelsModel = new SimpleModelImpl();
			dataModelsModel.setTagName("DATA_MODELS");

			generateDataModels(srcDir, project, srcBasePath + "/datamodel/", dataModelsModel, 0, ejbCompMap);

			final String outName = srcBasePath + "/datamodel/DataModelList.xml";
			try {
				final Writer os = this.fileCtxt.getWriter(outName);

				this.xmlCtxt.writeModel(dataModelsModel, "", os);

				os.flush();
				os.close();
			} catch (final IOException e) {
				throw new JETException("error processing file : " + outName + " :: " + e.getMessage(), e);
			}

		} else {
			throw new JETException("Source Base path is not a directory : " + srcDir);
		}

	}

	private void refreshExclusionList(final String srcBasePath) throws JETException {
		this.exclusionList = new ArrayList<String>();
		final String exclusionFile = srcBasePath + XML_METADATA_DMEXCLUSIONLISL_XML;
		if (this.fileCtxt.exists(exclusionFile)) {
			Model exclusionsModel;
			try {
				final InputStream is = this.fileCtxt.getInputStream(exclusionFile);
				exclusionsModel = this.xmlCtxt.parse(exclusionFile, is, null);
				is.close();
			} catch (final FileNotFoundException e) {
				// should be impossible as it is checked just above
				throw new JETException("error processing file : " + exclusionFile + " :: " + e.getMessage(), e);
			} catch (final IOException e) {
				throw new JETException("error processing file : " + exclusionFile + " :: " + e.getMessage(), e);
			} catch (final SAXException e) {
				throw new JETException("error processing file : " + exclusionFile + " :: " + e.getMessage(), e);
			}

			final ModelList ml = exclusionsModel.getChildNodes();
			while (ml.hasNext()) {
				final Model exclusionModel = ml.next();
				final String name = (String) exclusionModel.getAttribute("name");
				this.exclusionList.add(name);
			}
		}
	}

	private void updateDTD(final String dtdName, final String dstDir, final boolean recursive) throws JETException {
		updateDTD(dtdName, dstDir);
		if (recursive) {
			final File[] files = this.fileCtxt.listFiles(dstDir);
			for (int i = 0; i < files.length; i++) {
				final File file = files[i];
				if (this.fileCtxt.isDirectory(file)) {
					final String dirName = file.getName();

					if (!dirName.equals("CVS")) {
						final String path = file.getAbsolutePath() + "/";
						updateDTD(dtdName, path, recursive);
					}
				}
			}
		}
	}

	private void updateDTD(final String dtdName, final String dstDir) throws JETException {
		logp(JETLevel.INFO, "DataModelUtilsToolNut", "updateDTD", "Updating DTDs in the project folders... " + dtdName);
		final String classLoaderName = getApplicationProxy().getApplicationName();
		try {
			final InputStream is = this.fileCtxt.getInputStream(classLoaderName, dtdName);
			final BufferedInputStream bis = new BufferedInputStream(is);

			this.fileCtxt.createDirectories(dstDir);

			final OutputStream os = this.fileCtxt.getOutputStream(dstDir + dtdName);
			final BufferedOutputStream bos = new BufferedOutputStream(os);

			int c = bis.read();
			while (c != -1) {
				bos.write(c);
				c = bis.read();
			}

			bos.flush();
			bos.close();

			bis.close();
		} catch (final FileNotFoundException e) {
			logp(JETLevel.INFO, "DataModelUtilsToolNut", "updateDTD", "Unable to find file : " + dtdName, e);
			throw new JETException("Unable to find file : " + e.getMessage(), e);
		} catch (final IOException e) {
			logp(JETLevel.INFO, "DataModelUtilsToolNut", "updateDTD", "Error reading/writing file : " + dtdName + " / " + dstDir + dtdName, e);
			throw new JETException("Unable to find file : " + e.getMessage(), e);
		} catch (final JETException e) {
			throw e;
		}
	}

	private void generateDataModels(final File srcDir, final String scrPath, final String dstBasePath, final Model dataModelsModel, final int level, final Map<String, EJBComponent> ejbCompMap)
	        throws JETException {
		final File[] files = this.fileCtxt.listFiles(srcDir);
		for (int i = 0; i < files.length; i++) {
			final File file = files[i];
			if (this.fileCtxt.isDirectory(file)) {
				final String dirName = file.getName();

				if (!dirName.equals("CVS")) {

					final String newBasePath = dstBasePath + dirName + "/";
					this.fileCtxt.createDirectories(newBasePath);
					generateDataModels(file, scrPath, newBasePath, dataModelsModel, level + 1, ejbCompMap);

				}
			} else {

				final String docName = file.getAbsolutePath();
				final String fileName = file.getName();

				final String extension = fileName.substring(fileName.length() - 4);

				if (extension.equalsIgnoreCase(".xml")) {
					System.out.println("[DataModelUtilsToolNut] generateDataModels - Processing file : " + fileName);

					final String newFilePath = dstBasePath + "dm_" + fileName;
					this.fileCtxt.createDirectories(dstBasePath);

					final Model listItemModel = new SimpleModelImpl();
					listItemModel.setTagName("DATA_MODEL");

					final String localPath = newFilePath.substring(newFilePath.indexOf(scrPath));

					listItemModel.setAttribute("xmlfile", localPath);
					listItemModel.setAttribute("application", scrPath);
					try {
						generateJetDM(file, newFilePath, dataModelsModel, listItemModel, ejbCompMap);
					} catch (final IOException e) {
						throw new JETException("error processing file : " + docName + " :: " + e.getMessage(), e);
					}
				}
			}
		}
	}

	private Model getModelFromFile(final File file) throws JETException {
		Model srcModel = null;
		try {
			final InputStream is = this.fileCtxt.getInputStream(file);
			srcModel = this.xmlCtxt.parse(file.getAbsolutePath(), is, null);
			is.close();
		} catch (final IOException e) {
			throw new JETException("error processing file : " + file.getAbsolutePath() + " :: " + e.getMessage(), e);
		} catch (final SAXException e) {
			throw new JETException("error processing file : " + file.getAbsolutePath() + " :: " + e.getMessage(), e);
		}
		return srcModel;
	}

	private void generateJetDM(final File file, final String newFilePath, final Model dataModelsModel, final Model listItemModel, final Map<String, EJBComponent> ejbCompMap) throws JETException,
	        IOException {
		final EJBComponent ejbComponent = getEJBComponent(file, ejbCompMap);

		final String name = ejbComponent.getName();
		if (this.exclusionList != null && this.exclusionList.contains(name)) {
			System.out.println("[DataModelUtilsToolNut] generateJetDM - Component in exclusion list, skipping : " + name);
			return;
		}

		final String persistenceType = ejbComponent.getBeanDefinition().getPersistenceType();
		final String ejbType = ejbComponent.getBeanDefinition().getEjbType();
		if ("entity".equals(ejbType) && "Container".equals(persistenceType)) {
			final Map<String, Object> context = new HashMap<String, Object>();
			context.put("comp", ejbComponent);
			context.put("serialID", ejbComponent.getSerialVersionUID());
			context.put("util", new StringUtility());

			mergeTemplate("JsonTool", "JsonPlugin/vtl/xml/datamodel.vm", context, newFilePath);

			dataModelsModel.appendChild(listItemModel);
		}
	}
}
