
package myurlrun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimco
 */
public class ReadFromFile {

    private String filePath = "C:/Users/dimco/Desktop/mylinks.txt";
    
    private myUrls myUrl = null;
    private ArrayList<myUrls> urls = new ArrayList<>();

    public void readFile() {

        try {
            LineNumberReader lineReader = null;
            try {
                lineReader = new LineNumberReader(new FileReader(filePath));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            String lineText = null;

            while ((lineText = lineReader.readLine()) != null) {

                if (lineText.toUpperCase().startsWith("NAME")) {
                    myUrl = new myUrls();
                    myUrl.setUrlName(lineText.substring(5));

                } else if (lineText.toUpperCase().startsWith("LINK")) {
                    myUrl.setUrlLink(lineText.substring(5));
                    urls.add(myUrl);
                }

            }

            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    
     

//    public void importFromArrayToDatabase() {
//        for (myUrls myurl : urls) {
//            System.out.println(myurl.getUrlName());
//            System.out.println(myurl.getUrlLink());
//        }
//    }

    public ArrayList<myUrls> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<myUrls> urls) {
        this.urls = urls;
    }

}
