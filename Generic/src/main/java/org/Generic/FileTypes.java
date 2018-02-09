package org.Generic;

import org.Generic.model.CSVFile;
import org.Generic.model.DocFile;
import org.Generic.model.OdsFile;
import org.Generic.model.PdfFile;
import org.Generic.model.TxtFile;
import org.Generic.model.XlsFile;

/**
 * Enum type for File types
 * @author priyambadam
 *
 */
public enum FileTypes {
	CSV(new CSVFile()), 
	txt(new TxtFile()), 
	pdf(new PdfFile()), 
	ods(new OdsFile()), 
	xls(new XlsFile()), 
	doc(new DocFile());

	private Object value;

	private FileTypes(Object value) {
	      this.value = value;
	   }

	public Object getValue() {
		return value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
