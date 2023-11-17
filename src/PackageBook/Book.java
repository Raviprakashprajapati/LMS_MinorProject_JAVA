package PackageBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    public int BookId;
    public String BookName;
    public String BookAuthor;
    public int BookPages;
    public int BookPrice;
    public String BookAvailable;


    public int countIssueBook(){
        File fp = new File("Book.txt");
        int count = 0;
        try (Scanner scan = new Scanner(fp)) {
            while(scan.hasNextLine()){
                String[] split = scan.nextLine().split(" ");
                if(split[5].equals("NO"))
                {
                   
                    count++;
                }
            }


            
        } catch (FileNotFoundException e) {
          
        }
        return count;

    }

    public boolean checkBookId(int id) {
        boolean flag = false;
        try {
            File fp = new File("Book.txt"); // Read book data
            if (fp.exists()) {

                try (Scanner scan = new Scanner(fp)) { // Use try-with-resources for Scanner

                    while (scan.hasNextLine()) {

                        String str = scan.nextLine();
                        String split[] = str.split(" ");

                        if (Integer.parseInt(split[0]) == id) {
                            flag = true;
                        }
                    }
                } // Scanner is automatically closed here
            }
        } catch (Exception e) {
            System.out.println("ERROR IN CHECKING BOOK ID");
        }
        return flag;
    }

    public boolean checkBookAvailable(int id){
          boolean flag = false;
        try {
            File fp = new File("Book.txt"); // Read book data
            if (fp.exists()) {

                try (Scanner scan = new Scanner(fp)) { // Use try-with-resources for Scanner

                    while (scan.hasNextLine()) {

                        String str = scan.nextLine();
                        String split[] = str.split(" ");

                        if (Integer.parseInt(split[0]) == id && (split[5].equals("YES"))) {
                            flag = true;
                        }
                    }
                } // Scanner is automatically closed here
            }
        } catch (Exception e) {
            System.out.println("ERROR IN CHECKING BOOK Availability ID");
        }
        return flag;

    }

    public void addBook(int id, String b, String au, int page, int price) {

        ArrayList<String> ll = new ArrayList<>();
        try {
            File fp = new File("Book.txt");
            if (fp.exists()) {
                Scanner scan = new Scanner(fp);
                while (scan.hasNextLine()) {

                    ll.add(scan.nextLine());
                }

                for (String obj : ll) {

                    String str[] = obj.split(" ");
                    if (Integer.parseInt(str[0]) == id) {
                        System.out.println("\nBook with Same ID already exists");
                        return;
                    }

                }
            }

        } catch (Exception e) {
        }

        this.BookId = id;
        this.BookName = b;
        this.BookAuthor = au;
        this.BookPages = page;
        this.BookPrice = price;
        this.BookAvailable = "YES";

        // combined bookname without dot--
        String[] split = this.BookName.split(" ");
        String newStr = "";
        for (String string : split) {
            newStr += string;
        }
        // combined bookAuthor withour dot--
        String[] splitAuthor = this.BookAuthor.split(" ");
        String newStrAuthor = "";
        for (String string : splitAuthor) {
            newStrAuthor += string;
        }

        try {
            File fp = new File("Book.txt");
            if (fp.exists()) {
                
                try (Scanner scan = new Scanner(fp)) {
                    if(scan.hasNextLine())
                    {
                        FileWriter fa = new FileWriter(fp, true); // Use append mode
                        fa.write("\n" + this.BookId + " " + newStr + " " + newStrAuthor + " " + this.BookPages + " "
                                + this.BookPrice
                                + " " + this.BookAvailable);
                        fa.close(); // Close the FileWriter
   
                    }else{
                                        FileWriter fa = new FileWriter(fp, true); // Use append mode
                        fa.write( + this.BookId + " " + newStr + " " + newStrAuthor + " " + this.BookPages + " "
                                + this.BookPrice
                                + " " + this.BookAvailable);
                        fa.close(); // Close the FileWriter
   
                        
                    }
                }
                System.out.println("\nBook Added");
            } else {
                FileWriter fw = new FileWriter(fp);
                fw.write(this.BookId + " " + newStr + " " + newStrAuthor + " " + this.BookPages + " "
                        + this.BookPrice
                        + " " + this.BookAvailable);
                fw.close(); // Close the FileWriter
                System.out.println("\nBook Added");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayBook() {
        try {
            int countBook = 0;
            File fp = new File("Book.txt");
            if (fp.exists()) {
                Scanner scan = new Scanner(fp);

                if (scan.hasNextLine()) {

                    while (scan.hasNextLine()) {

                        countBook++;
                        String[] split = scan.nextLine().split(" ");
                        System.out.printf("%-10s%-20s%-20s%-15s%-20s%-10s", split[0], split[1], split[2], split[3],
                                split[4], split[5]);
                        System.out.println();

                    }
                    System.out.println("Issued Book : "+countIssueBook());
                    System.out.println("Total Books : " + countBook);

                    scan.close(); // Close the Scanner
                } else {
                    System.out.println("\nThere is nothing in File");
                }
            } else {
                System.out.println("\nFile not exists");
            }
        } catch (Exception e) {
            System.out.println("\nERROR IN DISPLAY " + e);
        }
    }

    
    public void searchBookById(int id) {
        try {
            File fp = new File("Book.txt");
            if (fp.exists()) {

                Scanner scan = new Scanner(fp);
                boolean found = false;

                // loop for per line
                while (scan.hasNextLine()) {

                    String line = scan.nextLine();
                    // store per line in array
                    String[] split = line.split(" ");
                    if (split.length > 0) {
                        int fileBookId = Integer.parseInt(split[0]);

                        if (id == fileBookId) {

                            System.out.printf("%-10s%-20s%-20s%-15s%-20s%-10s", split[0], split[1], split[2], split[3],
                                    split[4], split[5]);
                            System.out.println();

                            found = true;
                            break;

                        }

                    }
                }

                if (!found) {
                    System.out.println("\nBook with ID " + id + " not found.");
                }
                scan.close();

            }

            else {
                System.out.println("\nFile not found ");
            }

        } catch (Exception e) {
            System.out.println("\nSomething went wrong\n");

        }
    }

    public void deleteBookById(int id) {
        try {
            File fp = new File("Book.txt"); // Read book data
            ArrayList<String> ll = new ArrayList<>();
            boolean flag = false;

            if (fp.exists()) {

                
                //sure that book is free 
                if(checkBookAvailable(id)){}
                else{ 
                    System.out.println("\nBook cannot be deleted! Because it has already been issued by another Student");
                    return;
                }
        
                try (Scanner scan = new Scanner(fp)) { // Use try-with-resources for Scanner

                    while (scan.hasNextLine()) {

                        String str = scan.nextLine();
                        String split[] = str.split(" ");

                        if (Integer.parseInt(split[0]) == id) {
                            flag = true;

                        } else {
                            ll.add(str);
                        }

                    }
                } // Scanner is automatically closed here

                if (flag) {

                    FileWriter ffw = new FileWriter(fp);
                    for (String string : ll) {
                        ffw.write(string + "\n");
                    }
                    ffw.close();
                    System.out.println("\nBook with ID " + id + " deleted.");
                } else {
                    System.out.println("\nBook with ID " + id + " not present.");
                }


            } else {
                System.out.println("\nFile does not exist.");
            }
        } catch (Exception e) {
            System.out.println(" \nERROR IN DELETING BY ID " + e);
        }

        File ff = new File("NewBook.txt");
        if (ff.exists()) {
            ff.delete();
        }

    }

    public void modifyBookById(int id) {

        try {

            File fp = new File("Book.txt");

            // if file exists
            if (fp.exists()) {


                 //sure that book is free 
                if(checkBookAvailable(id)){}
                else{ 
                    System.out.println("\nBook cannot be Modify! Because it has already been issued by another Student");
                    return;
                }
        

                try (Scanner scan = new Scanner(fp)) {
                    // if file has nothing
                    if (scan.hasNextLine()) {

                        // loop for per line
                        ArrayList<String> ll = new ArrayList<>();
                        while (scan.hasNextLine()) {
                            ll.add(scan.nextLine());
                        }
                        scan.close();
                        int index = 0;
                        boolean flag = false;

                        // if book prestn
                        for (String obj : ll) {

                            String split[] = obj.split(" ");

                            if (Integer.parseInt(split[0]) == id) {

                                if (split[5].equals("YES")) {
                                    // display match book first---
                                    System.out.println("\nBook is present ");

                                    for (int i = 0; i < 100; i++) {
                                        System.out.print("=");
                                    }
                                    System.out.println();
                                    System.out.printf("%-10s%-20s%-20s%-15s%-20s%-10s", "I.D", "Book Name",
                                            "Book Author",
                                            "Book Pages", "Book Price", "Book Availability");
                                    System.out.println();
                                    for (int i = 0; i < 100; i++) {
                                        System.out.print("=");
                                    }
                                    System.out.println();
                                    System.out.printf("%-10s%-20s%-20s%-15s%-20s%-10s", split[0], split[1], split[2],
                                            split[3],
                                            split[4], split[5]);
                                    System.out.println();
                                    for (int i = 0; i < 100; i++) {
                                        System.out.print("=");
                                    }
                                    flag = true;

                                    break;

                                } else {
                                    System.out.println("\nBook is already issued cannot be delete");
                                    return;
                                }

                            } else {

                            }
                            index++;

                        }

                        // write
                        if (flag) {

                            // addind new field
                            Scanner sc = new Scanner(System.in);
                            System.out.println("\n[ Modify Book ]");
                            System.out.println("Enter New Book Name: ");
                            String name = sc.nextLine();
                            System.out.println("Enter New Book Author: ");
                            String author = sc.nextLine();
                            System.out.println("Enter New Book Pages: ");
                            int page = sc.nextInt();
                            System.out.println("Enter New Book Price: ");
                            int price = sc.nextInt();
                            String updateData = String.format("%d %s %s %d %d %s", id, name, author, page, price,
                                    "YES");
                            ll.set(index, updateData);
                            // System.out.println(ll);

                            // write all things in file
                            FileWriter fw = new FileWriter("Book.txt");
                            for (String obj : ll) {
                                fw.write(obj + "\n");
                            }
                            fw.close();

                            System.out.println("\nModify Completed...");

                        } else {
                            System.out.println("\nBook is not present");
                        }

                    } else {
                        System.out.println("\nFile has no Data");
                    }
                    // System.out.println("Book with "+id+ " ID is not present \n" );
                }

                // }
                // scan.close();

            }

            else {
                System.out.println("\nFile not found ");
            }

        } catch (Exception e) {
            System.out.println("\nSomething went wrong\n");

        }

    }



    public void deleteBookFile() {
        File fp = new File("Book.txt");
        if (fp.exists()) {
            fp.delete();
            System.out.println("\nBook.txt Deleted");
        } else {
            System.out.println("\nBook.txt is not exists");

        }

    }




}// Book class end here
