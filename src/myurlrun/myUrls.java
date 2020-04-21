/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myurlrun;


public class myUrls {
    
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
        return "myUrls{" + "urlName=" + urlName + ", urlLink=" + urlLink + '}';
    }

    public myUrls(String urlName, String urlLink) {
        this.urlName = urlName;
        this.urlLink = urlLink;
    }

    public myUrls() {
    }
    
  
  
    
}
