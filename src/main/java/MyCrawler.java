import com.opencsv.CSVWriter;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

//    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
//            + "|png|mp3|mp3|zip|gz))$");

//    private static final Pattern filters = Pattern.compile(
//            ".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
//                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private static final Pattern docPatterns = Pattern.compile(".*(\\.(html?|php|pdf))(\\?|$)");
    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|ico|png|tiff?))(\\?|$)");
    private static final Pattern noExtensionFilters = Pattern.compile(".*(\\.[a-zA-Z]*)(\\?|$)");

    private static CSVWriter fetchWriter;
    private static CSVWriter visitWriter;
    private static CSVWriter urlsWriter;

    private static int fetchAttempted = 0;

    private static int totalUrls = 0;
    private static int uniqueUrlsInside = 0;
    private static int uniqueUrlsOutside = 0;

    public MyCrawler() throws Exception{
        fetchWriter = new CSVWriter(new FileWriter("data/fetch_NewsDay.csv"));
        visitWriter = new CSVWriter(new FileWriter("data/visit_NewsDay.csv"));
        urlsWriter = new CSVWriter(new FileWriter("data/urls_NewsDay.csv"));
    }

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.viterbi.usc.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if(!(href.startsWith("https://www.newsday.com/") || href.startsWith("http://www.newsday.com/"))) {
            uniqueUrlsOutside++;
            return false;
        }
        uniqueUrlsInside++;

        if(docPatterns.matcher(href).matches() || imgPatterns.matcher(href).matches()) {
            fetchAttempted++;
            String [] rowStrings = {href, "ok"};
            urlsWriter.writeNext(rowStrings);
            return true;
        }

        if(href.contains("/xml/")) {
            return false;
        }

        if(!noExtensionFilters.matcher(href).lookingAt()) {
            String [] rowStrings = {href, "ok"};
            urlsWriter.writeNext(rowStrings);
            fetchAttempted++;
            return true;
        }

        return false;
    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        //super.handlePageStatusCode(webUrl, statusCode, statusDescription);
        String url = webUrl.getURL();
        url = url.replace(',','-');
        String [] rowStrings = {url, String.valueOf(statusCode)};
        fetchWriter.writeNext(rowStrings);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        //System.out.println("URL: " + url);
        String contentType = page.getContentType();
        if(contentType.indexOf(';')>0) {
            contentType = contentType.substring(0,contentType.indexOf(';'));
        }

        int fileSize = page.getContentData().length;

        int outLinks = 0;
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            outLinks = links.size();
        }

        totalUrls+=outLinks;

        String [] rowStrings = {url, fileSize + " Byte", String.valueOf(outLinks), contentType};
        visitWriter.writeNext(rowStrings);
    }

    @Override
    public void onBeforeExit() {
        super.onBeforeExit();
        System.out.println("onBeforeExit");
        try {
//            String [] sss = {"fetchAttempted:", String.valueOf(fetchAttempted)};
//            String [] sss2 = {"totalUrls:", String.valueOf(totalUrls)};
//            String [] sss3 = {"uniqueUrlsInside:", String.valueOf(uniqueUrlsInside)};
//            String [] sss4 = {"uniqueUrlsOutside:", String.valueOf(uniqueUrlsOutside)};

            fetchWriter.close();
            visitWriter.close();
            urlsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
