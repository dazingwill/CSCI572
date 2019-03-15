import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class TiKaUtil {

    public static String getBody(File file) throws Exception {
        if(file==null||!file.exists()){
            return null;
        }
        ContentHandler handler = null;
        try {
            Parser parser = new AutoDetectParser();
            InputStream input = new FileInputStream(file);
            Metadata meta = new Metadata();
            handler = new BodyContentHandler();

            parser.parse(input, handler, meta, new ParseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler.toString();
    }
}
