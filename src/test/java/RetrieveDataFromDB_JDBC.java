import org.testng.annotations.Test;

import java.sql.*;

//Connect to Database......retrieve result from from Database table and print on console

public class RetrieveDataFromDB_JDBC {
    @Test
    public void setDBConnection() throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","password");
        Statement stmt=con.createStatement();
        String retrieveCustomerInfo="Select * from customerInfo";
        ResultSet customerInfoResult= stmt.executeQuery(retrieveCustomerInfo);
        while(customerInfoResult.next()){
            String BookName=customerInfoResult.getString("BookName");
            String purchaseDate=customerInfoResult.getString("PurchasedDate");
            int amount=customerInfoResult.getInt("Amount");
            String location=customerInfoResult.getString("Location");

            System.out.println(BookName + " " +purchaseDate+""+amount+""+location);
        }
con.close();
    }
}
