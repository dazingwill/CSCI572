import com.opencsv.CSVReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExtractLinks {

    public static void main(String[] args) throws IOException {
        String dirPath = "./data/HTML Files";
        String mapFilePath = "./data/UrlToHtml_Newday.csv";
        String outputFilePath = "./data/edgeList.txt";

        // Read mapping relationships between file name and URL
        Map<String, String> fileUrlMap = new HashMap<>();
        Map<String, String> urlFileMap = new HashMap<>();
        CSVReader csvReader = new CSVReader(new FileReader(mapFilePath));
        String[] csvRow;
        while ((csvRow = csvReader.readNext()) != null) {
            fileUrlMap.put(csvRow[0], csvRow[1]);
            urlFileMap.put(csvRow[1], csvRow[0]);
        }
        csvReader.close();

        /* Extract links */
        File dir = new File(dirPath);
        Set<String> edges = new HashSet<>();
        for(File file: dir.listFiles()) {
            Document doc = Jsoup.parse(file, "UTF-8", fileUrlMap.get(file.getName()));
            Elements links = doc.select("a[href]");
            for(Element link: links){
                String url = link.attr("abs:href").trim();
                if(urlFileMap.containsKey(url)) {
                    edges.add(file.getName() + " " + urlFileMap.get(url));
                }
            }
        }

        File outputFile = new File(outputFilePath);
        PrintWriter writer = new PrintWriter(new FileOutputStream(outputFile));
        for(String s: edges){
            writer.println(s);
        }
        writer.flush();
        writer.close();
    }

}
