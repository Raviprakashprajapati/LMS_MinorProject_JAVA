import java.util.Scanner;
import PackageBook.Book;
import PackageDB.LmsDatabase;
import PackageStudent.Student;
import PackageUtils.Features;
import PackageUtils.Utils;

public class Main {

    public static void main(String[] args) {

        Book store = new Book();
        Utils fn = new Utils();
        Student std = new Student();
        Features fea = new Features();
        LmsDatabase DB = new LmsDatabase();

        try {
            try (Scanner scan = new Scanner(System.in)) {

                while (true) {

                    System.out.println("\n=================================");
                    System.out.println("[ LIBRARY MANAGEMENT SYSTEM ]");
                    System.out.println("1: Admin Login");
                    System.out.println("2: Admin SignUp");
                    System.out.println("3: Exit");
                    int ch = scan.nextInt();
                    System.out.println(" ==================================");
                    switch (ch) {
                        case 1:
                            fn.loginAdminByMain(DB, fn, store, std, fea);
                            break;
                        case 2:
                            fn.signupAdminByMain(DB, fn, store, std);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Enter Valid Choice");
                            break;
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Enter Valid Choice \n");
        }





        // main end--
    }
}