package PackageDB;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LmsDatabase {

  
    
    public void databaseBook(){
        try {

            String url = "jdbc:mysql://localhost:3306/LMS";
            String userName = "root";
            String password = "root123";
            Connection conn = DriverManager.getConnection(url, userName, password);
            
             // Clear existing data from the 'book' table
            String clearQuery = "DELETE FROM book";
            Statement clearStatement = conn.createStatement();
            clearStatement.executeUpdate(clearQuery);
            clearStatement.close();
            


            //add Books
            File fw = new File("Book.txt");
            ArrayList<String> ll = new ArrayList<>();
            Scanner scan  = new Scanner(fw);
            while (scan.hasNextLine()) {
                ll.add(scan.nextLine());
            }
            
            String query = "Insert INTO book values (?,?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(query);
            for (String book : ll) {
                String[] split = book.split(" ");
                pre.setInt(1,Integer.parseInt(split[0]) );
                pre.setString(2, split[1]);
                pre.setString(3, split[2]);
                pre.setInt(4, Integer.parseInt(split[3]));
                pre.setInt(5, Integer.parseInt(split[4]));
                pre.setString(6, split[5]);
                pre.executeUpdate();
            }
            pre.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("ERROR IN DATABASE");
            e.printStackTrace();
        }

    }


    public boolean addNewAdmin(String name, String pass) {
        boolean adminAdded = false;
    
        try {
            String url = "jdbc:mysql://localhost:3306/LMS";
            String username = "root";
            String password = "root123";
    
            Connection conn = DriverManager.getConnection(url, username, password);
    
            String query = "INSERT INTO admin VALUES (?,?)";
            PreparedStatement pre = conn.prepareStatement(query);
            pre.setString(1, name);
            pre.setString(2, pass);
    
            int rowsAffected = pre.executeUpdate();
            adminAdded = (rowsAffected > 0); // Check if a row was inserted

            pre.close();
            conn.close();
        } catch (SQLException e) {

            if(e.getErrorCode() == 1062 )
            {
                //handle duplicate entry and print nothing
            }
        

        }catch(Exception e)
        {
            System.out.println("ERROR IN DATABASE");
        }
    
        return adminAdded;
    }
    


    public boolean getAdminUser(String username, String password) {
        boolean isValidUser = false;
        try {
            String url = "jdbc:mysql://localhost:3306/LMS";
            String dbUsername = "root";
            String dbPassword = "root123";
    
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
    
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
    
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                isValidUser = true;
            }
    
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error accessing database: " + e.getMessage());
        }
    
        return isValidUser;
    }
    

    public void databaseStudent(){
          try {

            String url = "jdbc:mysql://localhost:3306/LMS";
            String userName = "root";
            String password = "root123";
            Connection conn = DriverManager.getConnection(url, userName, password);
            
             // Clear existing data from the 'stduent' table
            String clearQuery = "DELETE FROM student";
            Statement clearStatement = conn.createStatement();
            clearStatement.executeUpdate(clearQuery);
            clearStatement.close();
            

            //add students
            File fw = new File("Student.txt");
            ArrayList<String> ll = new ArrayList<>();
            Scanner scan  = new Scanner(fw);
            while (scan.hasNextLine()) {
                ll.add(scan.nextLine());
            }
            
            String query = "Insert INTO student values (?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(query);
            for (String book : ll) {
                String[] split = book.split(" ");
                pre.setInt(1,Integer.parseInt(split[0]) );
                pre.setString(2, split[1]);
                pre.setString(3, split[2]);
                pre.setString(4, split[3]);

                if(split[4].equals("null"))
                {
                    pre.setString(5, split[4]);
                    
                }else{
                    String splitId[] = split[4].split(",");
                    // ArrayList<String> bookId = new ArrayList<String>();
                    String updatedForDB = "";
                    for (String id : splitId) {
                        updatedForDB+=id+"I";
                    }
                    pre.setString(5, updatedForDB);
                    

                }

                pre.executeUpdate();
            }
            // System.out.println("add student");
            pre.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("ERROR IN DATABASE");
            e.printStackTrace();
        }

    }
   
    public void retrieveBookDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/LMS";
            String userName = "root";
            String password = "root123";
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM book";
            ResultSet data = stm.executeQuery(query);
    
            File fp = new File("Book.txt");
            FileWriter fw = new FileWriter(fp, true); // Append mode
            boolean firstLine = true;
    
            while (data.next()) {
                if (firstLine) {
                    fw.write(data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getInt(4) + " " + data.getInt(5) + " " + data.getString(6));
                    firstLine = false;
                } else {
                    fw.write("\n" + data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getInt(4) + " " + data.getInt(5) + " " + data.getString(6));
                }
            }
    
            System.out.println("Data retrieved and written to Book.txt");
    
            fw.close();
            stm.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("ERROR IN DATABASE");
            e.printStackTrace();
        }
    }
    
    public void retrieveStudentDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/LMS";
            String userName = "root";
            String password = "root123";
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM student";
            ResultSet data = stm.executeQuery(query);
    
            File fp = new File("Student.txt");
            FileWriter fw = new FileWriter(fp, true); // Append mode
            boolean firstLine = true;
    
            while (data.next()) {
                if (firstLine) {

                    if(data.getString(5).equals("null")){
                        
                    fw.write(data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+" "+data.getString(5));
                    }
                    else{

                        String split[] = data.getString(5).split("I");
                        String updated = "";
                        for (String book : split) {
                            updated+=book+",";
                        }
                              fw.write(data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+" "+updated);
                    }


                    firstLine = false;
                } else {
                    if(data.getString(5).equals("null")){
                        
                    fw.write("\n"+data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+" "+data.getString(5));
                    }
                    else{

                        String split[] = data.getString(5).split("I");
                        String updated = "";
                        for (String book : split) {
                            updated+=book+",";
                        }
                              fw.write("\n"+data.getInt(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+" "+updated);
                    }
                }
            }
    
            System.out.println("\nData retrieved and written to Student.txt");
    
            fw.close();
            stm.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("ERROR IN DATABASE");
            e.printStackTrace();
        }
    }
    
    
   
    
}
