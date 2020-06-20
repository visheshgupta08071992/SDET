import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;

//Connect to Database......retrieve result from from Database table and print on console

public class ConvertDBResultoJavaObject {
    @Test
    public void convertDbResultToJavaObject() throws SQLException, IOException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","password");
        Statement stmt=con.createStatement();
        String retrieveCustomerInfo="Select * from customerInfo limit 1";
        ResultSet customerInfoResult= stmt.executeQuery(retrieveCustomerInfo);
        CustomerDetails cd=new CustomerDetails();
        while(customerInfoResult.next()){
            String BookName=customerInfoResult.getString("BookName");
            String purchaseDate=customerInfoResult.getString("PurchasedDate");
            int amount=customerInfoResult.getInt("Amount");
            String location=customerInfoResult.getString("Location");

//            System.out.println(BookName + " " +purchaseDate+""+amount+""+location);
            cd.setBookName(BookName);
            cd.setPurchaseDate(purchaseDate);
            cd.setAmount(amount);
            cd.setLocation(location);
        }
//        System.out.println(cd.getBookName());
//        System.out.println(cd.getAmount());
        con.close();

        //Convert Java Object to Json using Jackson API
        //Dependencies:Jackson core,Jackson Databind,Jackson Annotation
        ObjectMapper om=new ObjectMapper();
        om.writeValue(new File(System.getProperty("user.dir")+"/customerInfo.json"),cd);

    }
}
