package org.Custom_Annotation.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.Custom_Annotation.CustomAnnotationException;

/**
 * A Singleton class responsible for generating document set based on the input
 * files in a given folder
 * 
 * @author priyambadam
 *
 */
public enum DocumentCollectionGenerator {
	INSTANCE;

	public List<Document> generateDocumentSet() throws CustomAnnotationException {
		List<Document> documentCollection = new ArrayList();
		List<File> filesInFolder = null;
		try {
			filesInFolder = Files.walk(Paths.get("input")).filter(Files::isRegularFile).map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("Problem with reading the inout files from folder : " + e.getMessage());
			e.printStackTrace();
		}

		if (filesInFolder.isEmpty())
			throw new CustomAnnotationException("No files available in folder");
		for (File file : filesInFolder) {
			//Can be implemented multi threading in this section for creating multiple document set 
			//Current implementation only creates single document set 
			Function<File, Optional<Document>> function = new DocumentFunction();
			Optional<Document> document = function.apply(file);
			if (document.isPresent()) {
				documentCollection.add(document.get());
			} else {
				throw new CustomAnnotationException("Empty document object generated . ");
			}
		}
		return documentCollection;
	}
}
