import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class CrawlerController {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "./data/crawl";
        int numberOfCrawlers = 16;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /* basic configuration */
        config.setMaxDepthOfCrawling(16);
        config.setMaxPagesToFetch(20000);
        config.setPolitenessDelay(203);
        config.setSocketTimeout(30000);
        config.setUserAgentString("csci572");
        config.setIncludeBinaryContentInCrawling(true);

        /* Instantiate the controller for this crawl. */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /* add seed url */
        controller.addSeed("https://www.newsday.com/");
        /* Start the crawl */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
