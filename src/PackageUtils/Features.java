package PackageUtils;
import java.util.Scanner;
import PackageBook.Book;
import PackageDB.LmsDatabase;
import PackageStudent.Student;

public class Features {

    public void menu(LmsDatabase DB,Utils fn,Book store,Student std,Features fea)
    {
        
      try {
          try (Scanner scan = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--------------------------------");
                System.out.println("[ LIBRARY MANAGEMENT SYSTEM ]");
                System.out.println("1: Display Book | 2: Display Student ");
                System.out.println("3: Add     Book | 4: Add     Student ");
                System.out.println("5: Delete  Book | 6: Delete  Student ");
                System.out.println("7:  Issue Book");
                System.out.println("8:  Return Book");
                System.out.println("9:  Search Book ");
                System.out.println("10: Modify Book");
                System.out.println("11: LogOut");
                System.out.println("12: Backup Books");
                System.out.println("13: Backup Students ");
                System.out.println("14: Delete Both Files ");
                System.out.println("Enter your choice:");
                int choice = scan.nextInt();
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1:
                        fn.displayBookMain(store);
                        break;
                    case 2:
                        fn.displayStudentMain(std);
                        break;
                    case 3:
                        fn.addBookMain(store,DB);
                        break;
                    case 4:
                        fn.addStudentMain(std,DB);
                        break;
                    case 5:
                        fn.deleteBookByIdMain(store,DB);
                        break;
                    case 6:
                         fn.deleteStudentMain(store, std,DB);
                        break;
                    case 7:
                        fn.issueBook(std, store,DB);
                        break;
                    case 8:
                        fn.returnBookByMain(std,store,DB);
                        break;
                    case 9:
                        fn.searchBookByIdMain(store);
                        break;
                    case 10:
                        fn.modifyBookByIdMain(store,DB);
                        break;
                    case 11: System.exit(0);
                    case 12:
                        DB.retrieveBookDatabase();
                        break;
                    case 13:
                        DB.retrieveStudentDatabase();
                        break;
                    case 14:
                            fn.deleteBookFileMain(store);
                            fn.deleteStudentFileMain(std);
                            break;
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





//    System.out.println("\n--------------------------------");
//                 System.out.println("[ LIBRARY MANAGEMENT SYSTEM ]");
//                 System.out.println("1: Display Book | 2: Display Student ");
//                 System.out.println("3: Add     Book | 4: Add     Student ");
//                 System.out.println("5: Delete  Book | 6: Delete  Student ");
//                 System.out.println("7:  Issue Book");
//                 System.out.println("8:  Return Book");
//                 System.out.println("9:  Search Book ");
//                 System.out.println("10: Modify Book");
//                 System.out.println("11: LogOut");
//                 System.out.println("12: Backup Books");
//                 System.out.println("13: Backup Students ");
//                 System.out.println("14: Delete Both Files ");
//                 System.out.println("Enter your choice:");
//                 int choice = scan.nextInt();
//                 System.out.println("--------------------------------");