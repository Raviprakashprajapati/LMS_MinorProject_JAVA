package PackageStudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import PackageBook.Book;

public class Student {

    public int studentId;
    public String studentName;
    public String studentContact;
    public String studentIssueOrNot;
    public String studentTotleBookId;

    public Student() {
    }

    public boolean checkStudentId(int id) {
        boolean flag = false;
        try {
            File fp = new File("Student.txt"); // Read book data
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
            System.out.println("ERROR IN CHECKING STUDENT ID");
        }
        return flag;
    }

    public void displayStudent() {

        try {
            int countBook = 0;
            File fp = new File("Student.txt");
            if (fp.exists()) {
                Scanner scan = new Scanner(fp);

                if (scan.hasNextLine()) {

                    while (scan.hasNextLine()) {

                        countBook++;
                        String[] split = scan.nextLine().split(" ");
                        System.out.printf("%-10s%-20s%-20s%-20s%-30s", split[0], split[1], split[2], split[3],
                                split[4]);
                        System.out.println();

                    }
                    System.out.println("Total Students : " + countBook);

                    scan.close(); // Close the Scanner
                } else {
                    System.out.println("There is nothing in File");
                }
            } else {
                System.out.println("File not exists");
            }
        } catch (Exception e) {
            System.out.println("ERROR IN DISPLAY " + e);
        }

    }

    public void addStudent(int id, String name, String contact, String issueornot, String totalBook) {

        ArrayList<String> ll = new ArrayList<>();
        try {
            File fp = new File("Student.txt");
            if (fp.exists()) {
                try (Scanner scan = new Scanner(fp)) {
                    while (scan.hasNextLine()) {

                        ll.add(scan.nextLine());
                    }
                }
                for (String obj : ll) {

                    String str[] = obj.split(" ");
                    if (Integer.parseInt(str[0]) == id) {
                        System.out.println("\nStudent with Same ID already exists");
                        return;
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("ERROR IN ADDING STUDENT");
        }

        this.studentId = id;
        this.studentName = name;
        this.studentContact = contact;
        this.studentTotleBookId = totalBook;
        this.studentIssueOrNot = issueornot;

        // combined bookname without dot--
        String[] split = this.studentName.split(" ");
        String newStr = "";
        for (String string : split) {
            newStr += string;
        }

        // add in student file
        try {
            File fp = new File("Student.txt");
            // append
            if (fp.exists()) {
                FileWriter fa = new FileWriter(fp, true); // Use append mode

                fa.write("\n" + this.studentId + " " + newStr + " " + this.studentContact + " "
                        + this.studentIssueOrNot + " " + this.studentTotleBookId);
                fa.close(); // Close the FileWriter
                System.out.println("\nStudent Added");
            } else {
                // first student write
                FileWriter fw = new FileWriter(fp);
                fw.write("\n" + this.studentId + " " + newStr + " " + this.studentContact + " "
                        + this.studentIssueOrNot + " " + this.studentTotleBookId);
                fw.close(); // Close the FileWriter
                System.out.println("\nStudent Added");
            }
        } catch (Exception e) {
            System.out.println("ERROR IN ADDING STUDENT " + e);
        }

    }

    public void issueBook(int stdId, int bookId, Book store) {

        try {

            ArrayList<String> ll = new ArrayList<>();
            File studentFp = new File("Student.txt");
            try (Scanner scan = new Scanner(studentFp)) {
                while (scan.hasNextLine()) {
                    ll.add(scan.nextLine());
                }
            }
           

            // if studetnid equal
            for (int i = 0; i < ll.size(); i++) {
                String str = ll.get(i);
                String[] split = str.split(" ");
                if (Integer.parseInt(split[0]) == stdId) {
                    // if no book present
                    if (split[3].equals("0")) {

                        ll.set(i, split[0] + " " + split[1] + " " + split[2] + " " + "1" + " " + bookId);
                    } else {

                        if(Integer.parseInt(split[3])<3)
                        {
                            System.out.println(Integer.parseInt(split[3]));
                            // coolection of bookID
                        String bookCollection[] = split[4].split(",");
                        ArrayList<String> bookCollectList = new ArrayList<String>();
                        for (String bbb : bookCollection) {
                            bookCollectList.add(bbb);
                        }
                        bookCollectList.add(String.valueOf(bookId));
                        String allBookIdInRow = "";
                        for (String bb : bookCollectList) {
                            if (bb.equals("null")) {

                            } else {

                                allBookIdInRow += bb + ",";
                            }
                        }

                        ll.set(i, split[0] + " " + split[1] + " " + split[2] + " " + (Integer.parseInt(split[3]) + 1)
                                + " " + allBookIdInRow);



                        }
                        else{
                            System.out.println("\nWe cannot Issue Book! Because you have already 3 Book Issued");
                            return;
                        }

                    }

                }

            }



            //put updated all student in file
            FileWriter fw = new FileWriter("Student.txt");
            int sizeOfStudent = ll.size();
            for(int i=0; i<sizeOfStudent; i++)
            {
                fw.write(ll.get(i));
                if(i<sizeOfStudent-1)
                {
                    fw.write("\n");
                }
            }
            fw.close();


            // put no in bookID
            ArrayList<String> bookLL = new ArrayList<>();
            File bookFw = new File("Book.txt");
            Scanner sc = new Scanner(bookFw);
            while (sc.hasNextLine()) {
                bookLL.add(sc.nextLine());

            }

            // split list into per book----
            int indexOfBookId = 0;
            for (String list : bookLL) {
                String[] split = list.split(" ");
                // if book equal to BOOKID
                if (Integer.parseInt(split[0]) == bookId) {
                    String updateData = String.format("%s %s %s %s %s %s ", split[0], split[1], split[2], split[3], split[4],  "NO");
                    
                    bookLL.set(indexOfBookId, updateData);
                    break;
                }
                indexOfBookId++;

            }

            FileWriter bookWrite = new FileWriter(bookFw);
            int sizeOfBook = bookLL.size();
            for(int i=0; i<sizeOfBook; i++)
            {
                bookWrite.write(bookLL.get(i));
                if(i<sizeOfBook-1)
                {
                    bookWrite.write("\n");
                }
            }
            bookWrite.close();
            // System.out.println(bookLL);
            System.out.println("\nBook Issued");

        } catch (Exception e) {
            System.out.println("ERROR IN ISSUING BOOK - " + e);
        }

    }

    public void deleteStudentById(int stdId) {

        try {
            File fp = new File("Student.txt"); // Read book data
            ArrayList<String> ll = new ArrayList<>();
            boolean flag = false;
            // String storeTotalBook = "";
            if (fp.exists()) {

                try (Scanner scan = new Scanner(fp)) { // Use try-with-resources for Scanner

                    while (scan.hasNextLine()) {

                        String str = scan.nextLine();
                        String split[] = str.split(" ");

                        if (Integer.parseInt(split[0]) == stdId &&  ( Integer.parseInt(split[3])==0 )) {
                            flag = true;

                        } else {
                            ll.add(str);
                        }

                    }
                } // Scanner is automatically closed here

                if (flag) {

                    FileWriter ffw = new FileWriter(fp);
                    int sizeOfStudent = ll.size();
                    for(int i = 0; i < sizeOfStudent; i++) {
                        ffw.write(ll.get(i));
                        if(i<sizeOfStudent-1)
                        {
                            ffw.write("\n");
                        }
                    }
                    ffw.close();
                    System.out.println("Student with ID " + stdId + " deleted.");
                } else {
                    System.out.println("\nStudent with ID " + stdId + " cannot be delete! he did not return all Books yet.");
                }

            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            System.out.println(" ERROR IN DELETING BY ID " + e);
        }

    }


    public void returnBook(Book store,int stdId,int bookId)
    {
        File stdFp = new File("Student.txt");
        File bookFp = new File("Book.txt");
        try {
            Scanner scan = new Scanner(stdFp);
            Scanner scBook = new Scanner(bookFp);
            ArrayList<String> stdList = new ArrayList<>();
            ArrayList<String> bookList = new ArrayList<>();

            //first add all student to the stdList
            while(scan.hasNextLine()) 
            {
                stdList.add(scan.nextLine());
            }

            while(scBook.hasNextLine())
            {
                bookList.add(scBook.nextLine());
            }

            

            //Second- findout index of student
          int studentIndex = -1;
          for (int i = 0; i < stdList.size(); i++) {
            String[] studentData = stdList.get(i).split(" ");
            if (Integer.parseInt(studentData[0]) == stdId) {
                studentIndex = i;
                break;
              }
            }

            int bookIndex = -1;
            for (int i = 0; i < bookList.size(); i++) {
            String[] bookData = bookList.get(i).split(" ");
            if (Integer.parseInt(bookData[0]) == bookId) {
                bookIndex = i;
                break;
              }
            }


           
        
        //if present then split
        if(studentIndex!=-1)
        {
            String[] splitPerRow = stdList.get(studentIndex).split(" ");
            ArrayList<String> allBook = new ArrayList<>(Arrays.asList(splitPerRow[4].split(",")));

            String[] splitBook = bookList.get(bookIndex).split(" ");

            //if student totals book empty return it
            if(Integer.parseInt(splitPerRow[3])==0)
            {
                System.out.println("\nStudent has not issued any book");
                return;
            }

            //if student has not that book
            if(allBook.contains(Integer.toString(bookId)))
            {

                //if student has only one book ==1
                if(Integer.parseInt(splitPerRow[3])==1)
                {
                    stdList.set(studentIndex, splitPerRow[0]+" "+splitPerRow[1]+ " "+splitPerRow[2]+" "+"0"+" " +"null");

                    bookList.set(bookIndex, splitBook[0]+" "+splitBook[1]+" "+splitBook[2]+" "+splitBook[3]+" "+splitBook[4]+" "+"YES");

                    //now write it into both files
                    FileWriter sFw = new FileWriter(stdFp);
                    FileWriter bFw = new FileWriter(bookFp);

                    int sizeStudent = stdList.size();
                    int sizeBook = bookList.size();

                    for(int i = 0; i < sizeStudent; i++)
                    {
                        sFw.write(stdList.get(i));
                        if(i<sizeStudent-1)
                        {
                            sFw.write("\n");
                        }
                    }
                    sFw.close();

                      for(int i = 0; i < sizeBook; i++)
                    {
                        bFw.write(bookList.get(i));
                        if(i<sizeBook-1)
                        {
                            bFw.write("\n");
                        }
                    }
                    bFw.close();

                    System.out.println("\nBook Returned");
                    return;                 

                    
                }
                else if(Integer.parseInt(splitPerRow[3])==2)
                {
                        allBook.remove(Integer.toString(bookId));

                        // System.out.println(bookList);
                    stdList.set(studentIndex, splitPerRow[0]+" "+splitPerRow[1]+ " "+splitPerRow[2]+" "+"1"+" " +allBook.get(0));

                    bookList.set(bookIndex, splitBook[0]+" "+splitBook[1]+" "+splitBook[2]+" "+splitBook[3]+" "+splitBook[4]+" "+"YES");

                    //now write it into both files
                    FileWriter sFw = new FileWriter(stdFp);
                    FileWriter bFw = new FileWriter(bookFp);

                    int sizeStudent = stdList.size();
                    int sizeBook = bookList.size();

                    for(int i = 0; i < sizeStudent; i++)
                    {
                        sFw.write(stdList.get(i));
                        if(i<sizeStudent-1)
                        {
                            sFw.write("\n");
                        }
                    }
                    sFw.close();

                      for(int i = 0; i < sizeBook; i++)
                    {
                        bFw.write(bookList.get(i));
                        if(i<sizeBook-1)
                        {
                            bFw.write("\n");
                        }
                    }
                    bFw.close();

                    System.out.println("\nBook Returned");
                    return;          
                }
                 else if(Integer.parseInt(splitPerRow[3])==3)
                {
                        allBook.remove(Integer.toString(bookId));

                    stdList.set(studentIndex, splitPerRow[0]+" "+splitPerRow[1]+ " "+splitPerRow[2]+" "+"2"+" " +allBook.get(0)+","+allBook.get(1)+",");

                    bookList.set(bookIndex, splitBook[0]+" "+splitBook[1]+" "+splitBook[2]+" "+splitBook[3]+" "+splitBook[4]+" "+"YES");

                    //now write it into both files
                    FileWriter sFw = new FileWriter(stdFp);
                    FileWriter bFw = new FileWriter(bookFp);

                    int sizeStudent = stdList.size();
                    int sizeBook = bookList.size();

                    for(int i = 0; i < sizeStudent; i++)
                    {
                        sFw.write(stdList.get(i));
                        if(i<sizeStudent-1)
                        {
                            sFw.write("\n");
                        }
                    }
                    sFw.close();

                      for(int i = 0; i < sizeBook; i++)
                    {
                        bFw.write(bookList.get(i));
                        if(i<sizeBook-1)
                        {
                            bFw.write("\n");
                        }
                    }
                    bFw.close();

                    System.out.println("\nBook Returned");
                    return;          
                }
                



            }else{
                System.out.println("\nStudent does not have That Book");
                return;
            }
            
        }






        } catch (Exception e) {
            System.out.println("ERROR IN RETURNING THE BOOK ");
        }
        

    }


}