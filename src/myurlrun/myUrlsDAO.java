/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myurlrun;


public class myUrlsDAO {
      private String urlName;
      private String urlLink;

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public String toString() {
        return  "NAME "+urlName + "\nLINK " + urlLink+"\n";
    }
    
    
}
