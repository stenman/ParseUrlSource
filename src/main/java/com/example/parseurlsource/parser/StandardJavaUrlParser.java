package com.example.parseurlsource.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StandardJavaUrlParser extends UrlParser {

	private URL url;

	public List<String> getContent() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			List<String> content = new ArrayList<String>();
			while ((inputLine = in.readLine()) != null) {
				content.add(inputLine);
			}
			in.close();
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
