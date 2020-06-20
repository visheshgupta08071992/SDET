import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertDBResultIntoJavaObjectNdthenMultipleJsonFile {
    @Test
    public void convertDbResultToJavaObject() throws SQLException, IOException {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","password");
        Statement stmt=con.createStatement();
        String retrieveCustomerInfo="Select * from customerInfo";
        ResultSet customerInfoResult= stmt.executeQuery(retrieveCustomerInfo);
        List<CustomerDetails> ar=new ArrayList<CustomerDetails>();
        while(customerInfoResult.next()){
            String BookName=customerInfoResult.getString("BookName");
            String purchaseDate=customerInfoResult.getString("PurchasedDate");
            int amount=customerInfoResult.getInt("Amount");
            String location=customerInfoResult.getString("Location");
            CustomerDetails cd=new CustomerDetails();
            cd.setBookName(BookName);
            cd.setPurchaseDate(purchaseDate);
            cd.setAmount(amount);
            cd.setLocation(location);
            ar.add(cd);
        }
        con.close();

        //Convert Java Object to Json using Jackson API
        //Dependencies:Jackson core,Jackson Databind,Jackson Annotation
        ObjectMapper om=new ObjectMapper();
        for(int i=0;i<ar.size();i++) {
            om.writeValue(new File(System.getProperty("user.dir") + "/customerInfo.json"+i+""), ar.get(i));
        }

    }
}
