package PackageUtils;
import java.util.Scanner;
import PackageBook.Book;
import PackageDB.LmsDatabase;
import PackageStudent.Student;

public class Utils {

     public  void headerMain() {
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("%-10s%-20s%-20s%-15s%-20s%-10s", "I.D", "Book Name", "Book Author", "Book Pages",
                "Book Price", "Book Availability");
        System.out.println();

        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public  void headerStudentMain() {
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("%-10s%-20s%-20s%-20s%-30s", "I.D", "Student Name", "Student Contact", "Total Books",
                "Books IDS");
        System.out.println();

        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public  void displayStudentMain(Student std) {

        headerStudentMain();
        std.displayStudent();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public  void addBookMain(Book store,LmsDatabase DB) {
       try {
         System.out.println("[ Adding Book ]");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Book Id: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Book Name: ");
        String name = scan.nextLine();
        System.out.println("Enter Book Author: ");
        String author = scan.nextLine();
        System.out.println("Enter Book Pages: ");
        int page = scan.nextInt();
        System.out.println("Enter Book Price: ");
        int price = scan.nextInt();
        store.addBook(id, name, author, page, price);
       } catch (Exception e) {
      System.out.println("\nEnter valid detail");   
    }

    DB.databaseBook();

    }

    public  void searchBookByIdMain(Book store) {

       try {
         System.out.println("[ Enter Book ID For Searching ]");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        headerMain();
        store.searchBookById(id);
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        
       } catch (Exception e) {
        System.out.println("\nEnter Valid Details");
    }

    }

    public  void displayBookMain(Book store) {
        headerMain();
        store.displayBook();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public  void deleteBookByIdMain(Book store , LmsDatabase DB) {

        try {
            System.out.println("[ Deleting Book By ID ]");
            System.out.println("\nEnter Book ID to delete:");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        store.deleteBookById(id);
        } catch (Exception e) {
        System.out.println("\nEnter valid detail");
        }
        
    DB.databaseBook();

       
    }

    public  void modifyBookByIdMain(Book store, LmsDatabase DB) {
        try {
            System.out.println("[ Enter Book Id For Modification ]");
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter Book Id For Modification:");
        int id = scan.nextInt();
        store.modifyBookById(id);
        } catch (Exception e) {
        System.out.println("\nEnter valid details");}
        
    DB.databaseBook();

    }

    public  void addStudentMain(Student std, LmsDatabase DB) {
      try {
          System.out.println("[ Add Student ]");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Student Contact No.:");
        String phone = sc.nextLine();
        std.addStudent(id, name, phone, "0", "null");
      } catch (Exception e) {
    System.out.println("Enter valid detail");  
    }

    DB.databaseStudent();
       

    }

    public  void issueBook(Student std, Book store, LmsDatabase DB) {
        System.out.println("[ Issue Book TO Student ]\n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        int stuId = scan.nextInt();
        System.out.println("Enter Book Id to be issue: ");
        int bookId = scan.nextInt();
     

        // check if anyone not present---
        if (std.checkStudentId(stuId) == true && store.checkBookId(bookId) == true) {

            std.issueBook(stuId, bookId, store);

        } else {
            if (std.checkStudentId(stuId) == false) {
                System.out.println("Student does not exist");
            } else {

                System.out.println("Book does not exist");
            }
        }

        DB.databaseBook();
        DB.databaseStudent();

    }

    public  void deleteStudentMain(Book store, Student std, LmsDatabase DB) {
        System.out.println("[ Deleting Student By ID ]");
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter the Student ID: ");
        int id = scan.nextInt();
        boolean checkStudent = std.checkStudentId(id);
        if (checkStudent) {
            std.deleteStudentById(id);
           

        } else {
            System.out.println("Student with ID " + id + " not exists ");
        }
      
        DB.databaseStudent();

    }

    public  void returnBookByMain(Student std,Book store,LmsDatabase DB)
    {
       try {
         System.out.println(" [ Return Book ] \n" );
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Student Id: ");
        int stdId = scan.nextInt();
        System.out.println("Enter Book Id: ");
        int bookId = scan.nextInt();

           // check if anyone not present---
        if (std.checkStudentId(stdId) == true && store.checkBookId(bookId) == true) {

            std.returnBook(store,stdId,bookId);

        } else {
            if (std.checkStudentId(stdId) == false) {
                System.out.println("\nStudent does not exist");
            } else {

                System.out.println("\nBook does not exist");
            }
        }
       } catch (Exception e) {
        System.out.println("\nEnter valid detail");
    }

       DB.databaseBook();
       DB.databaseStudent();

    }


    //Admin 
    public void signupAdminByMain(LmsDatabase DB,Utils fn,Book store,Student std){
        System.out.println("\n[ SignUp ]\n");
         Scanner scan = new Scanner(System.in);
        System.out.println("Enter new username: ");
        String name = scan.nextLine();
        System.out.println("Enter new password: ");
        String pass = scan.nextLine();

        if(DB.addNewAdmin(name, pass))
        {
            System.out.println("\nNew Admin added\n");


        }else{
            System.out.println(" \nSignUp failed!");
            System.out.println(" Use difference values\n");
        }

    }

    public void loginAdminByMain(LmsDatabase DB,Utils fn,Book store,Student std,Features fea){
        System.out.println("\n[ Login ]\n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String name = scan.nextLine();
        System.out.println("Enter your password: ");
        String pass = scan.nextLine();

        if(DB.getAdminUser(name, pass))
        {
            
            System.out.println("\nLogin Done\n");

            fea.menu(DB,fn,store,std,fea);
        

        }else{
            System.out.println("\nLogin failed!\n");
        }
    }


    //delete file
      public  void deleteBookFileMain(Book store) {
        store.deleteBookFile();
    }

    public void deleteStudentFileMain(Student std){
        std.deleteStudentFile();
    }

   

    
}
