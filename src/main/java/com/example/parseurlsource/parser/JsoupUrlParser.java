package com.example.parseurlsource.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.parseurlsource.domainmodel.AgdqSchedule;
import com.example.parseurlsource.utils.DateConverter;

/**
 * Parses an HTML page source
 * 
 * @author Roger
 * 
 */
public class JsoupUrlParser extends UrlParser {

	private String url;
	private Document doc;

	private void setDoc() throws HttpStatusException, IOException {
		doc = null;
		Response response = Jsoup.connect(url).ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com")
				.timeout(12000).followRedirects(true).execute();
		doc = response.parse();
	}

	/**
	 * Fetches a list of AgdqSchedule items by parsing the HTML source and reading the table row tag elements.
	 * 
	 * @return A List of AgdqSchedule items
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public List<AgdqSchedule> getScheduleItems() throws HttpStatusException, IOException {

		setDoc();

		DateConverter dateConverter = new DateConverter();

		boolean isFirstRowProcessed = false;

		List<AgdqSchedule> agdqSchedules = new ArrayList<AgdqSchedule>();

		Element elementsByTag = doc.getElementsByTag("table").get(0);
		Elements scheduleRows = elementsByTag.getElementsByTag("tr");

		for (Element row : scheduleRows) {

			if (isFirstRowProcessed) {
				List<String> temp = new ArrayList<String>();
				Elements cells = row.getElementsByTag("td");

				for (Element cell : cells) {
					temp.add(cell.getElementsByTag("td").get(0).text());
				}

				AgdqSchedule as = new AgdqSchedule();

				as.setStartTime(dateConverter.convertStringToDate(temp.get(0)));
				as.setGame(temp.get(1));
				as.setRunners(temp.get(2));
				as.setRunTime(temp.get(3));
				as.setDetails(temp.get(4));
				as.setCommentators(temp.get(5));
				as.setPrizes(temp.get(6));

				agdqSchedules.add(as);
			}

			isFirstRowProcessed = true;
		}
		return agdqSchedules;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

}
