package org.Custom_Annotation;

/**
 * A custom exception created cfor annotation failures
 * @author priyambadam
 *
 */
public class CustomAnnotationException extends Exception{

	private static final long serialVersionUID = -3759693023539242414L;

	public CustomAnnotationException(String message) {
		super(message);
	}

}
