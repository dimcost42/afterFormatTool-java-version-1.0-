package myurlrun;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyDatabaseAdapter {

    private static final String DB_URL = "localhost:3306";
    private static final String DBNAME = "url_store";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/" + DBNAME + "?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "1234";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    private myUrlsDAO myurl;
    
    final static ArrayList<myUrlsDAO> myUrlList = new ArrayList<>();
   ReadFromFile readFromFile;
   
//   public void connect()
//   {
//              try {
//            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
//            statement = connection.createStatement();
//            } catch (SQLException ex) {
//            System.out.println("Sorry, problems with the database connection!");
//            System.out.println(ex.toString());
//            System.exit(0);
//        }
//   }
//   public void disconnect()
//   {
//       try {
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//   }
   
    public void loadUrls() {

        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM urls;");

            while (resultSet.next()) {
                myurl = new myUrlsDAO();

                myurl.setUrlName(resultSet.getString("urlName"));
                myurl.setUrlLink(resultSet.getString("urlLink"));
                myUrlList.add(myurl);
//                System.out.println("\t" + resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());
            System.exit(0);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void printLinks() {
        if (myUrlList.isEmpty())
        {
       loadUrls();
        } 
       System.out.println("=====================");
        for (myUrlsDAO url : myUrlList) {
            
            System.out.println(url.getUrlName());
            System.out.println(url.getUrlLink());
            System.out.println("- - - - - - - - - - -");
        }
        System.out.println("=====================");
    }

    public void openUrls() throws IOException {
        loadUrls();
        Scanner sc = new Scanner(System.in);
        String open = "a";
        while (!open.equals("stop")) {
            for (int i = 0; i < myUrlList.size();) {
                System.out.println("You are going to run " + myUrlList.get(i).getUrlName().toUpperCase() + "\n do you want to run it now?");
                open = sc.next();
                if (open.equals("yes")) {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrlList.get(i).getUrlLink()));
                    i++;
                } else if (open.equals("no")) {
                    System.out.println("You just skipped " + myUrlList.get(i).getUrlName().toUpperCase() + "\n");
                    i++;
                } else if (open.equals("stop")) {
                    break;
                }

            }
            break;
        }

    }

    public void addUrls() {
        Scanner sc = new Scanner(System.in);

        try {
            String answer = "continue";
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();

            while (!answer.equals("exit")) {
                myurl = new myUrlsDAO();
                System.out.println("Enter Name of URL");
                myurl.setUrlName(sc.next());
                System.out.println("Enter URL PATH");
                myurl.setUrlLink(sc.next());
                System.out.println("\nType YES to confirm adding the " + myurl.getUrlName().toUpperCase() + " with path " + myurl.getUrlLink().toUpperCase());
                answer=sc.next();
                if (answer.equals("yes")) {
                    statement.executeUpdate("INSERT INTO urls (urlName, urlLink) VALUES ('" + myurl.getUrlName() + "','" + myurl.getUrlLink() + "')");
                    System.out.println("Just added\n");
                }
                System.out.println("if you dont want to continue type exit else write continue");
                answer=sc.next();

            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());
            System.exit(0);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void addUrlsFromTxT()
    {
        readFromFile= new ReadFromFile();
        readFromFile.readFile();
        readFromFile.getUrls();
    Scanner sc = new Scanner(System.in);

        try {
            String answer = "continue";
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();

            while (!answer.equals("exit")) {
                
                for (int i=0; i<readFromFile.getUrls().size();i++)
                {
                System.out.println("\nType YES to confirm adding the " + readFromFile.getUrls().get(i).getUrlName().toUpperCase() + " with path " + readFromFile.getUrls().get(i).getUrlLink().toUpperCase());
                answer=sc.next();
                if (answer.equals("yes")) {
                    statement.executeUpdate("INSERT INTO urls (urlName, urlLink) VALUES ('" + readFromFile.getUrls().get(i).getUrlName() + "','" + readFromFile.getUrls().get(i).getUrlLink() + "')");
                    System.out.println("Just added\n");
                }
                }
                System.out.println("if you dont want to continue type exit else write continue");
                answer=sc.next();

            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());
            System.exit(0);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}
