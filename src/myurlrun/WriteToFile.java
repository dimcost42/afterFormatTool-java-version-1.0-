
package myurlrun;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteToFile {
    
//private String path = "/%userprofile%/desktop/myLinks.txt";
private String path = "C:/Users/dimco/Desktop/myLinks.txt";

public void filePath()
{
    Scanner sc = new Scanner(System.in);
    System.out.println("Your default path is "+getPath());
    System.out.println("Do u want to change the default path?");
    String answer = sc.next().toUpperCase();
    if (answer.equals("YES"))
    {
        System.out.println("Give absolute path");
        answer=sc.next().toLowerCase();
        if (answer.endsWith(".txt"))
        {
        setPath(answer);
        System.out.println("Your path successfully change it to "+getPath()+"\n");
        }
        else
        {
            System.out.println("You need to give exact path like c:/Users/user/Desktop/FILENAME.txt\n");
            filePath();
         }
    }
    else if (answer.equals("NO"))
    {
        System.out.println("Just to remember, your path is "+getPath()+"\n");
    }
    else
    {
        System.out.println("Cant recognize the entered value "+answer+"\n");
        filePath();
    }
}

public void createFile() {
        {
            try {
                File file = new File(this.getPath());
                if (file.createNewFile()) {
                    System.out.println("\nFile created: " + file.getName() + " to " +this.getPath()+"\n");
                } else {
//                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
     
     
     public void writeToFile(ArrayList<myUrlsDAO> myUrlList) {
        try {
            FileWriter myWriter = new FileWriter(this.getPath());
            
            for (myUrlsDAO myUrl : myUrlList) {
                myWriter.append(myUrl.toString());
                
                
            }
            myWriter.write("\n\t\t\tYou have " + myUrlList.size() + " Links\n");
            myWriter.close();
//            System.out.println("Successfully wrote to the file all wrong files.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
