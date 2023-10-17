import java.util.Scanner;
import PackageBook.Book;
import PackageStudent.Student;

public class App {

    static void headerMain() {
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

    static void headerStudentMain() {
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

    static void displayStudentMain(Student std) {

        headerStudentMain();
        std.displayStudent();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
    }

    static void addBookMain(Book store) {
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

    }

    static void searchBookByIdMain(Book store) {

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

    static void displayBookMain(Book store) {
        headerMain();
        store.displayBook();
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
    }

    static void deleteFileMain(Book store) {
        store.deleteFile();
    }

    static void deleteBookByIdMain(Book store) {

        try {
            System.out.println("[ Deleting Book By ID ]");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        store.deleteBookById(id);
        } catch (Exception e) {
        System.out.println("\nEnter valid detail");
        }
       
    }

    static void modifyBookByIdMain(Book store) {
        try {
            System.out.println("[ Enter Book Id For Modification ]");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        store.modifyBookById(id);
        } catch (Exception e) {
        System.out.println("\nEnter valid details");}
    }

    static void addStudentMain(Student std) {
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
       

    }

    static void issueBook(Student std, Book store) {
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

    }

    static void deleteStudentMain(Book store, Student std) {
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
      

    }

    static void returnBookByMain(Student std,Book store)
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

    }


    // main
    public static void main(String[] args) {

        Book store = new Book();
        Student std = new Student();
       
      try {
          try (Scanner scan = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--------------------------------");
                System.out.println("1: Display Books");
                System.out.println("2: Display Students");
                System.out.println("3: Add Book");
                System.out.println("4: Add Student");
                System.out.println("5: Issue Book");
                System.out.println("6: Return Book");
                System.out.println("7: Search Book By ID");
                System.out.println("8: Modify Book By ID");
                System.out.println("9: Delete Book By ID");
                System.out.println("10: Delete Student");
                System.out.println("11: Exit Code");
                System.out.println("Enter your choice:");
                int choice = scan.nextInt();
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1:
                        displayBookMain(store);
                        break;
                    case 2:
                        displayStudentMain(std);
                        break;
                    case 3:
                        addBookMain(store);
                        break;

                    case 4:
                        addStudentMain(std);
                        break;

                    case 5:
                        issueBook(std, store);
                        break;

                    case 6:
                        returnBookByMain(std,store);
                        break;

                    case 7:
                        searchBookByIdMain(store);
                        break;

                    case 8:
                        modifyBookByIdMain(store);
                        break;

                    case 9:
                        deleteBookByIdMain(store);
                        break;

                    case 10:
                        deleteStudentMain(store, std);
                        break;
                    case 11:
                        return;

                    default:
                        System.out.println("Enter a valid choice");
                        break;

                }
            }
        }

         
    

      } catch (Exception e) {
        System.out.println("\nEnter Valid Choice ");
       
    
      }


       



    }

}