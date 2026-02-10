package testdbconnection;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestDBConnection {
    public static void main(String[] args) 
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver Loaded");
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");
            //System.out.println("Database Connected");
            String query="";
            
            st=con.createStatement();
            int option;
            do
            {
                System.out.println("Choose any one option to proceed further :😁😁");
                System.out.println("Enter 1 to display the entries");
                System.out.println("Enter 2 to insert new entry");
                System.out.println("Enter 3 to update number from any existing entry");
                System.out.println("Enter 4 to delete any entry");
                System.out.println("Enter 0 to exit");

                Scanner sc=new Scanner(System.in);
                option=sc.nextInt();
                switch(option)
            {
                case 0:
                    System.out.println("Goodbye");
                    break;
                case 1:
                    System.out.println("You have selected option 1");
                    query="SELECT * FROM random";
                    rs= st.executeQuery(query);
                    while(rs.next())
                    {
                        System.out.print(rs.getString(1)+"\t");
                        System.out.print(rs.getString(2)+"\t");
                        System.out.print(rs.getString(3)+"\t");
                        System.out.println();
                    }
                    break;
                    
                case 2:
                    System.out.println("You have selected option 2");
                    String name;
                    int age;
                    long number;
                    System.out.println("Enter age");
                    age=sc.nextInt();
                    System.out.println("Enter Mobile number");
                    number=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Enter name");
                    name=sc.next();                                 
                    
                    query="INSERT INTO random VALUES('"+name+"','"+age+"','"+number+"')";
                    
                    int i=st.executeUpdate(query);
                    break;
                    
                case 3:
                    System.out.println("You have selected option 3");
                    System.out.println("First enter which name to be changed");
                    String oname,nname;
                    oname=sc.next();
                    System.out.println("Now enter new name");
                    sc.nextLine();
                    nname=sc.next();
                    System.out.println("Now age");
                    age=sc.nextInt();
                    query="UPDATE random SET name='"+nname+"' WHERE name='"+oname+"' AND age='"+age+"'";
                    i=st.executeUpdate(query);
                    break;
                    
                case 4:
                    System.out.println("You have selected option 4");
                    System.out.println("Enter name to be deleted");
                    name=sc.next();
                    query="DELETE FROM random WHERE name='"+name+"'";
                    i=st.executeUpdate(query);
                    break;
                default:
                    System.out.println("Wrong input😒😒");
                    break;
            }
            }
            while(option!=0);           
            con.close();          
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class not found:");
            System.out.println(e);
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception");
            System.out.println(e);
        }
        
    }
    
}
