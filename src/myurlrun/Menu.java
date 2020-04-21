package myurlrun;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static myurlrun.MyDatabaseAdapter.myUrlList;

public class Menu {

    private MyDatabaseAdapter db = new MyDatabaseAdapter();
    private WriteToFile wtf = new WriteToFile();
//    private ReadFromFile rf = new ReadFromFile();

    public void myMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        try {
            while (option != 999) {
                System.out.println("999) to exit\n1) for load urls\n2) for add new urls \n3) to add from txt to database \n4)to see whats including in db\n5) to export to txt file database\n6) change path to extract txt");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        db.openUrls();
                        break;
                    case 2:
                        db.addUrls();
                        break;
                    case 3:
                        db.addUrlsFromTxT();
                        break;
                    case 4:
                        db.printLinks();
                        break;
                    case 5:
                        if (myUrlList.isEmpty()) {
                            db.loadUrls();
                        }
                        wtf.createFile();
                        wtf.writeToFile(myUrlList);
                        break;
                    case 6:
                        wtf.filePath();
                        break;
                }
            }
        } catch (InputMismatchException e) {
//    System.out.println(e);
        } catch (NullPointerException e) {
        }

    }
}
