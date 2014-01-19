package com.example.parseurlsource.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.parseurlsource.domainmodel.AgdqSchedule;
import com.example.parseurlsource.utils.DateConverter;
import com.example.parseurlsource.view.AgdqScheduleView;

/**
 * Parses an HTML page source
 * 
 * @author Roger
 * 
 */
@Component
@Scope("prototype")
public class JsoupUrlParser extends UrlParser {

	private static final Logger logger = LoggerFactory.getLogger(AgdqScheduleView.class);

	@Inject
	private DateConverter dateConverter;

	private String url;

	/**
	 * Connects to the url (set by setUrl() in this class) and generates a response.
	 * 
	 * @return A parsed Response object in form of a Document.
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public Document setDoc() throws HttpStatusException, IOException {
		Response response = Jsoup.connect(url).ignoreContentType(true)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com")
				.timeout(12000).followRedirects(true).execute();
		return response.parse();
	}

	/**
	 * Fetches a list of AgdqSchedule items by parsing the HTML source and reading the table row tag elements.
	 * 
	 * @param doc
	 *            The Jsoup Document to parse.
	 * @return A List of AGDQSchedule objects that were parsed from the provided Document.
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public List<AgdqSchedule> getScheduleItems(Document doc) throws HttpStatusException, IOException {
		boolean isFirstRowProcessed = false;
		List<AgdqSchedule> agdqSchedules = new ArrayList<AgdqSchedule>();

		Element elementsByTag = doc.getElementsByTag("table").get(0);
		logger.debug(String.format("Table fetched from source: %S", elementsByTag.childNodeSize() > 0));
		Elements scheduleRows = elementsByTag.getElementsByTag("tr");
		logger.debug(String.format("Table rows fetched from table: %S", scheduleRows.size() > 0));

		for (Element row : scheduleRows) {

			// Skipping first row, because it is the header of the table
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
				logger.trace("Added [%s, %s, %s, %s, %s, %s, %s] to schedule", as.getStartTime(), as.getGame(), as.getRunners(), as.getRunTime(),
						as.getDetails(), as.getCommentators(), as.getPrizes());
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
