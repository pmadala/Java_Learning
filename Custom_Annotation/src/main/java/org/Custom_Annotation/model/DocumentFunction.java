package org.Custom_Annotation.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.function.Function;

import org.Custom_Annotation.CustomAnnotationException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class DocumentFunction implements Function<File, Optional<Document>> {

	@Override
	public Optional<Document> apply(File fileToProcess) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = new JsonObject();
		try {
			jsonElement = parser.parse(new String(Files.readAllBytes(fileToProcess.toPath())));
		} catch (JsonSyntaxException | IOException e) {
			System.err.println("Exception occured at parsing file content to json object : " + e.getMessage());
			e.printStackTrace();
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		try {
			Optional<Document> document = DocumentFactory.INSTANCE.getDocumentInstance(jsonObject, fileToProcess.getName());
			return document;
		} catch (InstantiationException | IllegalAccessException | CustomAnnotationException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
