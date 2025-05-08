 
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Controller {
     Connection connection ;
         Statement statement ;
    public void connect() throws SQLException{
        
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory-system","root","");
            statement = connection.createStatement();
    
    }
    public void disconnect() throws SQLException{
     statement.close();
     connection.close();
    }
    public void addProduct(int PID, String Pname, String ExpirationDate, int Temperature, int Capacity) {
    String query = "INSERT INTO Product VALUES("+PID+" ,'"+Pname+"' ,'"+ExpirationDate+"' ,"+Temperature+" ,"+Capacity+")";
    try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }
   public void addMachine(int MID, String MNAME, 
            String  Maintenance)  {
       String query ="insert into machine values ("+MID+",'"+MNAME+"', '"+Maintenance+"')";
        try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public ArrayList<Integer> getAllMIDS() {
         ArrayList<Integer> allmids = new ArrayList<>();
        String query="select MID from machine";
        try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           int mn = rs.getInt("MID");
           allmids.add(mn);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return allmids;
    }
    public String getMaintenance(String id) {
        String query ="select maintenance from machine where mid = "+id;
        String mt="";
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         mt += rs.getString("maintenance");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return mt;
    }

    public ArrayList<Integer> getProductsbyMachine(int machine) {
       ArrayList<Integer> allpdts = new ArrayList<>();
        String query="SELECT PID  FROM (SELECT PID FROM Product) AS P NATURAL JOIN Produce WHERE MID = "+machine;
        try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           int pn = rs.getInt("PID");
           allpdts.add(pn);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return allpdts; 
        
    }

    public double getCostbyMachine(int machine,int product) {
         String query ="SELECT Cost FROM Produce WHERE PID = "+product+" AND MID = "+machine;
        double cost=0;
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         cost += rs.getDouble("Cost");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return cost;
        
    }

    public String getMachineName(int id) {
         String query ="select mname from machine where mid = "+id;
        String mt="";
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         mt += rs.getString("mname");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return mt;
    }

    public ArrayList<Integer> getAllPIDS() {
        ArrayList<Integer> allPIDS = new ArrayList<>();
        String query="select PID from PRODUCT";
        try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           int mn = rs.getInt("PID");
           allPIDS.add(mn);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return allPIDS;  
    }

    public String getProductName(int id) {
      String query ="select pname from product where pid = "+id;
        String pn="";
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         pn += rs.getString("pname");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return pn;
                
                
    }

    public double getPrice(int id ) {
        String query ="select price  from product where pid = "+id;
        double price=0;
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         price += rs.getDouble("price");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return price;
    }

    public int getQuantity(int id) {
      String query ="select quantity  from product where pid = "+id;
        int q=0;
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         q += rs.getDouble("quantity");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return q;  
       
    }

    public int[] findExpDate(String year,String month) {
       String query = "SELECT Pid FROM Product WHERE YEAR(ExpirationDate) = '"+year+"' AND MONTH(ExpirationDate) = '"+month+"'"; 
       ArrayList<Integer> allPIDS = new ArrayList<>();
        try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           int pi = rs.getInt("PID");
           allPIDS.add(pi);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] pid = new int[allPIDS.size()];
        for(int i =0;i<pid.length;i++){
        pid[i]=allPIDS.get(i);
        
        
        
        }
        
        
        return pid;  
       
        
        
        
    }

    public void deleteProduct(int id) {
       String query = "delete from product where pid="+id;
        
        try {
            connect();
             statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean decrypt(String password,String name) {
        
      String query = "select pass from login where name= '"+name+"'";
      String p=null;
      boolean valid = false;
       try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
            p = rs.getString("pass");
          
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
       StringBuilder decryptedWord = new StringBuilder();
          int shift = 2;
         for (int i = 0; i < p.length(); i++) {
            char before = p.charAt(i);
            char decrypt;
            
            if (Character.isUpperCase(before)) {
                decrypt = (char) ((before - 'A' - shift + 26) % 26 + 'A');
            } else if (Character.isLowerCase(before)) {
                decrypt = (char) ((before - 'a' - shift + 26) % 26 + 'a');
            } else {
                decrypt = before; // Leave non-alphabetic characters unchanged
            }
            
            decryptedWord.append(decrypt);
        String decryptedResult = decryptedWord.toString();
        if(decryptedResult.equals(password)){
       valid =true;
        
        
        }}
        
        
        return valid;  }

    public void addOrder(String date) {
         String query = "INSERT INTO `ORDER` (OrderDate) VALUES('"+date+"')";
    try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
        
        
    }

    public ArrayList<String> getProducts() {
    String query ="Select pname from product"   ;
    ArrayList<String> pnames = new ArrayList<>();
     try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           String pn = rs.getString("pname");
           pnames.add(pn);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    
    
    
    
    return pnames;
    
    }

    public void addToHas(int product, int quantity,int ordernum) {
         
       String query = "insert into has (ordernumber,pid,quantity) values ("+ordernum+","+product+","+quantity+")";
       
        
       
        try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
       
    }
    public int getCurrentOrder() {
    int ordernum = 0;
    String query = "SELECT MAX(OrderNumber) AS MaxOrderNumber FROM `Order`";
    try {
        connect();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            ordernum = rs.getInt("MaxOrderNumber");
        }
        disconnect();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return ordernum;
}

    public void addShipment(String address, String date, int con) {
       String query = "insert into shipping (address,shipmentdate,ordernumber) values ('"+address+"','"+date+"',"+con+")";
       
        
       
        try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }

    public double CalculatePrice(int quantity, int product) {
        String query = "select price from product where pid = "+product;
        double price =0;
        double total=0;
        try {
        connect();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
           price  = rs.getDouble("price");
        }
        disconnect();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    total =price*quantity;    
    return total;    
    }
    
    public ArrayList<String> getMachines() {
    String query ="Select mname from machine"   ;
    ArrayList<String> mnames = new ArrayList<>();
     try {
            connect();
           ResultSet rs= statement.executeQuery(query);
           while(rs.next()){
           String mn = rs.getString("mname");
           mnames.add(mn);
           
           }
           disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return mnames;
    
    }

    public int getPId(String pname) {
        String query ="select pid from product where pname = '"+pname + "'";
        int pid=0;
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         pid += rs.getInt("pid");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return pid;
                
                
    }

    public int getMId(String mname) {
        
        String query ="select mid from machine where mname = '"+ mname + "'";
        int mid=0;
        try{
        connect();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
         mid += rs.getInt("mid");
        
        
        }
        disconnect();
        
        } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        return mid;
                
                
    
    }
        

    public void NewProduction(int pid, int mid, int quantity, int cost, String date) {
    String query = "INSERT INTO Produce (pid,mid,quantity,cost,productiondate) VALUES(" + pid + " ,"+ mid + " ,"+ quantity +" ,"+ cost +" ,'"+ date +"')";
    try {
            connect();
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  
    }   
   

    public ArrayList<String> getOutProducts() {
        String query = "Select pname from product where quantity = 0 ";
        ArrayList <String> outProducts = new ArrayList<>();
        
         try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             String pname = rs.getString("pname");
             outProducts.add(pname);
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         return outProducts;
    }

    public ArrayList<Integer> getMostStockedProduct() {
        String query = "Select pid from product where quantity = (Select max(quantity) from product)";
        ArrayList<Integer> mostid = new ArrayList<>();
        
        try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             int id = rs.getInt("pid");
             mostid.add(id);
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
              return mostid;
    }

    public ArrayList<Integer> getLessStockedProduct() {
       String query = "Select pid from product where quantity = (Select min(quantity) from product)";
        ArrayList<Integer> lessid = new ArrayList<>();
        
        try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             int id = rs.getInt("pid");
             lessid.add(id);
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
              return lessid;
    }

    public int getTotalCost(String name) {
        String query = "Select sum(cost) from produce where pid IN (Select pid from product where pname = '" + name + "')";
        int total = 0;
        try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             total = rs.getInt("sum(cost)");
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return total;
    }


    public int getProductRevenue(String pname) {
       String query = "Select subtract(price,cost) from produce natural join product where pname = '" + pname + "'";
       int prevenue = 0;
       try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             prevenue = rs.getInt("subtract(price,cost");
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
       return prevenue;
    }

    public int getBestSellerProduct(String date) {
        String query = "Select PID from ((SELECT PID,SUM(quantity) as sum FROM `has` natural join `order`where orderdate = '" + date + "' GROUP BY PID)) as sub where sum = (Select MAX(summ) from (SELECT PID,SUM(quantity) as summ FROM `has` natural join `order`where orderdate = '" + date + "' GROUP BY PID) as subb)";
        int id=0;
        try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             id = rs.getInt("pid");
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return id;
    }

    public int getLessSellerProduct(String date) {
        String query ="Select PID from ((SELECT PID,SUM(quantity) as sum FROM `has` natural join `order`where orderdate = '" + date + "' GROUP BY PID)) as sub where sum = (Select MIN(summ) from (SELECT PID,SUM(quantity) as summ FROM `has` natural join `order`where orderdate = '" + date + "' GROUP BY PID) as subb)";
        int id =0;
        try {
             connect();
             statement.execute(query);
             
             ResultSet rs = statement.executeQuery(query);
             while(rs.next()){
             id = rs.getInt("pid");
        }
        
             disconnect();
             
         } catch (SQLException ex) {
             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return id;
    }
    }

    

    
    
    
    

