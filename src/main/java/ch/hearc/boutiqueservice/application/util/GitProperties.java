package ch.hearc.boutiqueservice.application.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GitProperties {

	public String readGitProperties() {
	    ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("git.properties");
	    try {
	        return readFromInputStream(inputStream);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Version information could not be retrieved";
	    }
	}
	
	private String readFromInputStream(InputStream inputStream)
	throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }
	    return resultStringBuilder.toString();
	}
}
