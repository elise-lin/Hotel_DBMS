/*
 * Template JAVA User Interface
 * =============================
 *
 * Database Management Systems
 * Department of Computer Science &amp; Engineering
 * University of California - Riverside
 *
 * Target DBMS: 'Postgres'
 *
 */


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class defines a simple embedded SQL utility class that is designed to
 * work with PostgreSQL JDBC drivers.
 *
 */
public class DBProject {

   // reference to physical database connection.
   private Connection _connection = null;

   // handling the keyboard inputs through a BufferedReader
   // This variable can be global for convenience.
   static BufferedReader in = new BufferedReader(
                                new InputStreamReader(System.in));

   /**
    * Creates a new instance of DBProject
    *
    * @param hostname the MySQL or PostgreSQL server hostname
    * @param database the name of the database
    * @param username the user name used to login to the database
    * @param password the user login password
    * @throws java.sql.SQLException when failed to make a connection.
    */
  // public class GeneralException extends Exception {

  //   public GeneralException(String message){
  //      super("error you suck");
  //   }s

  // }

   static void checkInt(int checkI){ 
      if(checkI < 0) {
         throw new ArithmeticException("ERROR: No negative inputs!\n");      
      }
   }

   static void checkStr(String checkS){ 
      if(checkS.matches(".*\\d.*")) {       
       // System.out.println("ERROR: No numbers in names!\n");
       throw new ArithmeticException("ERROR: No numbers in names!\n");
     }
    }
   

   public DBProject (String dbname, String dbport, String user, String passwd) throws SQLException {

      System.out.print("Connecting to database...");
      try{
         // constructs the connection URL
         String url = "jdbc:postgresql://localhost:" + dbport + "/" + dbname;
         System.out.println ("Connection URL: " + url + "\n");

         // obtain a physical connection
         this._connection = DriverManager.getConnection(url, user, passwd);
         System.out.println("Done");
      }catch (Exception e){
         System.err.println("Error - Unable to Connect to Database: " + e.getMessage() );
         System.out.println("Make sure you started postgres on this machine");
         System.exit(-1);
      }//end catch
   }//end DBProject

   /**
    * Method to execute an update SQL statement.  Update SQL instructions
    * includes CREATE, INSERT, UPDATE, DELETE, and DROP.
    *
    * @param sql the input SQL string
    * @throws java.sql.SQLException when update failed
    */
   public void executeUpdate (String sql) throws SQLException {
      // creates a statement object
      Statement stmt = this._connection.createStatement ();

      // issues the update instruction
      stmt.executeUpdate (sql);

      // close the instruction
      stmt.close ();
   }//end executeUpdate

   /**
    * Method to execute an input query SQL instruction (i.e. SELECT).  This
    * method issues the query to the DBMS and outputs the results to
    * standard out.
    *
    * @param query the input query string
    * @return the number of rows returned
    * @throws java.sql.SQLException when failed to execute the query
    */
   public int executeQuery (String query) throws SQLException {
      // creates a statement object
      Statement stmt = this._connection.createStatement ();

      // issues the query instruction
      ResultSet rs = stmt.executeQuery (query);

      /*
       ** obtains the metadata object for the returned result set.  The metadata
       ** contains row and column info.
       */
      ResultSetMetaData rsmd = rs.getMetaData ();
      int numCol = rsmd.getColumnCount ();
      int rowCount = 0;

      // iterates through the result set and output them to standard out.
      boolean outputHeader = true;
      while (rs.next()){
   if(outputHeader){
      for(int i = 1; i <= numCol; i++){
    System.out.print(rsmd.getColumnName(i) + "\t");
      }
      System.out.println();
      outputHeader = false;
   }
         for (int i=1; i<=numCol; ++i)
            System.out.print (rs.getString (i) + "\t");
         System.out.println ();
         ++rowCount;
      }//end while
      stmt.close ();
      return rowCount;
   }//end executeQuery

   /**
    * Method to close the physical connection if it is open.
    */
   public void cleanup(){
      try{
         if (this._connection != null){
            this._connection.close ();
         }//end if
      }catch (SQLException e){
         // ignored.
      }//end try
   }//end cleanup

   /**
    * The main execution method
    *
    * @param args the command line arguments this inclues the <mysql|pgsql> <login file>
    */
   public static void main (String[] args) {
      if (args.length != 3) {
         System.err.println (
            "Usage: " +
            "java [-classpath <classpath>] " +
            DBProject.class.getName () +
            " <dbname> <port> <user>");
         return;
      }//end if
      
      Greeting();
      DBProject esql = null;
      try{
         // use postgres JDBC driver.
         Class.forName ("org.postgresql.Driver").newInstance ();
         // instantiate the DBProject object and creates a physical
         // connection.
         String dbname = args[0];
         String dbport = args[1];
         String user = args[2];
         esql = new DBProject (dbname, dbport, user, "");

         boolean keepon = true;
         while(keepon) {
            
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            System.out.println("                             @@@@@@@@@@@ ");
            System.out.println("                            ,@@@@@@@@@@@,");
            System.out.println("                            &           & ");
            System.out.println("                           ,&@@@@@@@@@@@@,");
            System.out.println("                           #@@@*.    ,@@@# ");
            System.out.println("                           /@@         @@/            ,-------------------------.");
            System.out.println("                                      #&.            ( How can I help you today? )");
            System.out.println("                          / &           & %           `-------------------------y'");
            System.out.println("                           *&.         .&/         ");
            System.out.println("                             %.       .%       ");
            System.out.println("                               @.   .@ ");
            System.out.println("                              *((   ((/ ");
            System.out.println("                        /@@@@@%   &   %@@@@@/");
            System.out.println("                      .@@@@@@@@@@(%/@@@@@@@@@@.");
            System.out.println("                      @@@@@@@@@@@@.@@@@@@@@@@@@");
            System.out.println("                     #@@@&@@@,.&@@.@@&.,@@@@@@@#");
            System.out.println("                    .@@@*/@@%  /@@.@@/  %@@/*@@@.");
            System.out.println("                    %@@@*/@@@&@@@@.@@&&@@@@/*@@@%             __!__  ");
            System.out.println("                    @@@@*/@&@#/@@@.@@@/#@@@/*@@@@            ,**,*// ");
            System.out.println("                   #@@@@*/@@(  ,@@.@@.  (@@/*@@@@#          &&%%%%&&@");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            // These are sample SQL statements
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("1. Add new customer");
        System.out.println("2. Add new room");
        System.out.println("3. Add new maintenance company");
        System.out.println("4. Add new repair");
        System.out.println("5. Add new Booking"); 
        System.out.println("6. Assign house cleaning staff to a room");
        System.out.println("7. Raise a repair request");
        System.out.println("8. Get number of available rooms");
        System.out.println("9. Get number of booked rooms");
        System.out.println("10. Get hotel bookings for a week");
        System.out.println("11. Get top k rooms with highest price for a date range");
        System.out.println("12. Get top k highest booking price for a customer");
        System.out.println("13. Get customer total cost occurred for a give date range"); 
        System.out.println("14. List the repairs made by maintenance company");
        System.out.println("15. Get top k maintenance companies based on repair count");
        System.out.println("16. Get number of repairs occurred per year for a given hotel room");
        System.out.println("17. < EXIT");

            switch (readChoice()){
           case 1: addCustomer(esql); break;
           case 2: addRoom(esql); break;
           case 3: addMaintenanceCompany(esql); break;
           case 4: addRepair(esql); break;
           case 5: bookRoom(esql); break;
           case 6: assignHouseCleaningToRoom(esql); break;
           case 7: repairRequest(esql); break;
           case 8: numberOfAvailableRooms(esql); break;
           case 9: numberOfBookedRooms(esql); break;
           case 10: listHotelRoomBookingsForAWeek(esql); break;
           case 11: topKHighestRoomPriceForADateRange(esql); break;
           case 12: topKHighestPriceBookingsForACustomer(esql); break;
           case 13: totalCostForCustomer(esql); break;
           case 14: listRepairsMade(esql); break;
           case 15: topKMaintenanceCompany(esql); break;
           case 16: numberOfRepairsForEachRoomPerYear(esql); break;
           case 17: keepon = false; break;
           default : System.out.println("Unrecognized choice!"); break;
            }//end switch
         }//end while
      }catch(Exception e) {
         System.err.println (e.getMessage ());
      }finally{
         // make sure to cleanup the created table and close the connection.
         try{
            if(esql != null) {
               System.out.print("Disconnecting from database...");
               esql.cleanup ();
               System.out.println("Done\n\nBye !");
            }//end if
         }catch (Exception e) {
            // ignored.
         }//end try
      }//end try
   }//end main
   
   public static void Greeting(){
      System.out.println(
         "\n\n*******************************************************\n" +
         "              User Interface                       \n" +
         "*******************************************************\n");
      System.out.println(" W E L C O M E  T O  H O T E L  S T A N L E Y");
      System.out.println("  __                   ___        ");
      System.out.println(" |\"\"|  ___    _   __  |\"\"\"|  __   ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println(" |\"\"| |\"\"\"|  |\"| |\"\"| |\"\"\"| |\"\"|  ");
      System.out.println("\"\'\'\'\"\'\'\"\'\"\"\'\"\"\"\'\'\"\'\'\'\'\"\"\"\'\"\"\'\"\"\"");
      
      System.out.println(" \n \n W E L C O M E  T O  H O T E L  S T A N L E Y & E L I S E \n\n\n");
      System.out.println("                   \\  |  /         ___________");
      System.out.println("    ____________  \\ \\_# /         |  ___      |       _________");
      System.out.println("   |            |  \\  #/          | |   |     |      | = = = = |");
      System.out.println("   | |   |   |  |   \\\\#           | |`v'|     |      |         |");
      System.out.println("   |            |    \\#  //       |  --- ___  |      | |  || | |");
      System.out.println("   | |   |   |  |     #_//        |     |   | |      |         |");
      System.out.println("   |            |  \\\\ #_/_______  |     |   | |      | |  || | |");
      System.out.println("   | |   |   |  |   \\\\# /_____/ \\ |      ---  |      |         |");
      System.out.println("   |            |    \\# |+ ++|  | |  |^^^^^^| |      | |  || | |");
      System.out.println("   |            |    \\# |+ ++|  | |  |^^^^^^| |      | |  || | |");
      System.out.println("^^^|    (^^^^^) |^^^^^#^| H  |_ |^|  | |||| | |^^^^^^|         |");
      System.out.println("   |    ( ||| ) |     # ^^^^^^    |  | |||| | |      | ||||||| |");
      System.out.println("   ^^^^^^^^^^^^^________/  /_____ |  | |||| | |      | ||||||| |");
      System.out.println("        `v'-                      ^^^^^^^^^^^^^      | ||||||| |");
      System.out.println("         || |`.      (__)    (__)                          ( )");
      System.out.println("                     (oo)    (oo)                       /---V");
      System.out.println("              /-------\\/      \\/ --------\\             * |  |");
      System.out.println("             / |     ||        ||_______| \\ ");
      System.out.println("            *  ||W---||        ||      ||  *");
      System.out.println("               ^^    ^^        ^^      ^^");
      System.out.println("\n \n ~ O P E N  D U R I N G  T H E  H O L I D A Y  S E A S O N! ~\n \n \n");
   }//end Greeting

   /*
    * Reads the users choice given from the keyboard
    * @int
    **/
   public static int readChoice() {
      int input;
      // returns only if a correct value is given.
      do {
         System.out.print("Please make your choice: ");
         try { // read the integer, parse it and break.
            input = Integer.parseInt(in.readLine());
            break;
         }catch (Exception e) {
            System.out.println("Your input is invalid!");
            continue;
         }//end try
      }while (true);
      return input;
   }//end readChoice

   
   public static void addCustomer(DBProject esql){
    // Given customer details add the customer in the DB 
      // Your code goes here.
      try {
         System.out.println("\n\n");
         System.out.println("            O      ~O");
        System.out.println("           <|\\     /|\\");
        System.out.println("            |   ~o/ | \\o    ~o/    _o      ");
        System.out.println("            |\\  /|  |\\ |\\   /|      |\\");
        System.out.println("           / |  / \\ |// >   / \\    / > \n ");

        System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t |    Adding a new customer.  |");
        System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      String input = "";
      String query = "INSERT INTO Customer VALUES (";
      String[] custString = {"Customer ID", "First Name", "Last Name", "Address", "Phone Number", "Date of Birth", "Gender"};
      
      for (int i = 0; i < 7; i++) {
      System.out.print("Enter " + custString[i] + ": ");
      input = in.readLine();
    
      while (input.equals("") && i < 3) {
        System.out.println("ERROR: " + custString[i] + " cannot be empty. Please try again");
        System.out.print("Enter " + custString[i] + ": ");
        input = in.readLine();
      }
      
      if (i == 6) { 
        query += "'" + input + "')";
      } else {
        query += "'" + input + "'" + ", ";
      }
      }
      esql.executeUpdate(query);
          } catch (Exception e) {
              System.err.println (e.getMessage());
            }
   }//end addCustomer

   public static void addRoom(DBProject esql){
    // Given room details add the room in the DB
      // Your code goes here.
      try {
         System.out.println("\n\n");
      System.out.println("               .--.");
      System.out.println("              /.-. '----------.");
      System.out.println("              \\'-' .--\"--\"\"-\"-'");
      System.out.println("               '--'");
      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\t |    Adding a room.    |");
      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~");
         
      System.out.print("Enter Hotel ID: ");
      String hotelID = in.readLine();
      checkInt(Integer.parseInt(hotelID));
      String query = "INSERT INTO Room (hotelID, roomNo, roomType) VALUES ('" + hotelID + "', ";

      System.out.print("Enter Room Number: ");
      String roomNo = in.readLine();
      checkInt(Integer.parseInt(roomNo));
      query += "'" + roomNo + "'" + ", ";

      System.out.print("Enter Room Type: ");
      String roomType = in.readLine();
      query += "'" + roomType + "'" + ");";
      esql.executeUpdate(query);
      } catch(Exception e) {
          System.err.println (e.getMessage());
        }
   }//end addRoom

   public static void addMaintenanceCompany(DBProject esql){
      // Given maintenance Company details add the maintenance company in the DB
    try {
       System.out.println("\n\n\n");
      System.out.println("\t                      @/#    ");
      System.out.println("\t                     (@/   /%");
      System.out.println("\t                     #&@/////");
      System.out.println("\t                     *,,*%%@/");
      System.out.println("\t                   ##(*@      ");
      System.out.println("\t                 @,(*@ ");
      System.out.println("\t                *#(*@ ");
      System.out.println("\t              %*(,@ ");
      System.out.println("\t          .//##,% ");
      System.out.println("\t          /@/@/@%");
      System.out.println("\t          @   /%@ \n");

      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\t |    Adding a maintenance company  |");
      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.print("Enter Maintenance Company ID: ");
    String cmpID = in.readLine();
    checkInt(Integer.parseInt(cmpID));
    String query = "INSERT INTO MaintenanceCompany (cmpID, name, address, isCertified) VALUES ('" + cmpID + "', ";

    System.out.print("Enter Name: ");
    String name = in.readLine();
    checkStr(name);
    query += "'" + name + "'" + ", ";

    System.out.print("Enter Address: ");
    String address = in.readLine();
    checkStr(address);
    query += "'" + address + "'" + ", ";

    System.out.print("Enter Certificaiton TRUE/FALSE: ");
    String isCertified = in.readLine();
    query += "'" + isCertified + "'" + ");";

    esql.executeUpdate(query);
    } catch(Exception e) {
        System.err.println (e.getMessage());
      }
   }//end addMaintenanceCompany

   public static void addRepair(DBProject esql){
    // Given repair details add repair in the DB
      // Your code goes here.
       try {
          System.out.println("\n\n\n");
          System.out.println("\t                      @/#    ");
          System.out.println("\t                     (@/   /%");
          System.out.println("\t                     #&@/////");
          System.out.println("\t                     *,,*%%@/");
          System.out.println("\t                   ##(*@      ");
          System.out.println("\t                 @,(*@ ");
          System.out.println("\t                *#(*@ ");
          System.out.println("\t              %*(,@ ");
          System.out.println("\t          .//##,% ");
          System.out.println("\t          /@/@/@%");
          System.out.println("\t          @   /%@ \n");

          System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println("\t |    Adding a repair   |");
          System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Enter Repair ID: ");
        String rID = in.readLine();
        checkInt(Integer.parseInt(rID));
        String query = "INSERT INTO Repair (rID, hotelID, roomNo, mCompany, repairDate, description, repairtype) VALUES ('" + rID + "', ";

        System.out.print("Enter Hotel ID: ");
        String hotelID = in.readLine();
        checkInt(Integer.parseInt(hotelID));
        query += "'" + hotelID + "'" + ", ";

        System.out.print("Enter Room Number: ");
        String roomNo = in.readLine();
        checkInt(Integer.parseInt(roomNo));
        query += "'" + roomNo + "'" + ", ";

        System.out.print("Enter Maintenance Company ID: ");
        String mCompany = in.readLine();
        checkInt(Integer.parseInt(mCompany));
        query += "'" + mCompany + "'" + ", ";

        System.out.print("Enter Repair Date (YYYYMMDD): ");
        String repairDate = in.readLine();
        checkInt(Integer.parseInt(repairDate));
        query += "'" + repairDate + "'" + ", ";

        System.out.print("Enter Description: ");
        String description = in.readLine();
        query += "'" + description + "'" + ", ";

        System.out.print("Enter Repair Type: ");
        String repairType = in.readLine();
        query += "'" + repairType + "'" + ");";

        esql.executeUpdate(query);
        } catch(Exception e) {
            System.err.println (e.getMessage());
          }
   }//end addRepair

   public static void bookRoom(DBProject esql){
      System.out.println("\n\n");
      System.out.println("\t      ______ ______");
      System.out.println("\t    _/      Y      \\_");
      System.out.println("\t   // ~~ ~~ | ~~ ~  \\\\");
      System.out.println("\t  // ~ ~ ~~ | ~~~ ~~ \\\\");
      System.out.println("\t //________.|.________\\\\");
      System.out.println("\t`----------`-'----------'");
      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\t |    Adding a booking.   |");
      System.out.println("\t ~~~~~~~~~~~~~~~~~~~~~~~~~~");
    // Given hotelID, roomNo and customer Name create a booking in the DB 
      // Your code goes here.
      // ...
      // ...
   }//end bookRoom

   public static void assignHouseCleaningToRoom(DBProject esql){
    // Given Staff SSN, HotelID, roomNo Assign the staff to the room 
      // Your code goes here.
      // ...
      // ...
   }//end assignHouseCleaningToRoom
   
   public static void repairRequest(DBProject esql){
    // Given a hotelID, Staff SSN, roomNo, repairID , date create a repair request in the DB
      // Your code goes here.
      // ...
      // ...
   }//end repairRequest
   
   public static void numberOfAvailableRooms(DBProject esql){
    // Given a hotelID, get the count of rooms available 
    try{
      System.out.print("Enter the hotelID: ");

      String input;
      input = in.readLine();
      checkInt(Integer.parseInt(input));
      
        String query = "SELECT COUNT(r.roomNo) FROM (SELECT roomNo FROM Room WHERE hotelID = ";
        query+=input + ") AS r WHERE r.roomNo NOT IN (SELECT b1.roomNo FROM Booking b1 WHERE b1.hotelID = ";
        query += input + ")";


         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println(e.getMessage());
        }//end numberOfAvailableRoom
  }
   
   public static void numberOfBookedRooms(DBProject esql){
    // Given a hotelID, get the count of rooms booked
    try{ 
   String query = "SELECT DISTINCT count(b.roomNo) FROM Booking b WHERE b.hotelID = ";
         System.out.print("\tPlease enter hotel ID: ");
         String input = in.readLine();
         checkInt(Integer.parseInt(input));
         query += input;

         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
  }//end numberOfBookedRooms
   
   public static void listHotelRoomBookingsForAWeek(DBProject esql){
    //Given a hotelID, date - list all the rooms available for a week(including the input date)
    try{
    System.out.print("\tPlease enter hotel ID: ");
    String input1 = in.readLine();
    checkInt(Integer.parseInt(input1));
    System.out.print("\tPlease enter a date (YYYYMMDD): ");
    String input2 = in.readLine();
    checkInt(Integer.parseInt(input2));
    String query = "SELECT roomNo FROM Booking WHERE " + input1 + " = hotelID AND bookingDate BETWEEN TO_DATE('" + input2 + "', 'YYYYMMDD') AND TO_DATE('" + input2 + "', 'YYYYMMDD') + 7 ";
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      } 
   }//end listHotelRoomBookingsForAWeek
   
   public static void topKHighestRoomPriceForADateRange(DBProject esql){
    // List Top K Rooms with the highest price for a given date range
      // Your code goes here.
      try{ 
        System.out.print("\tEnter number of rooms K: ");
         String k = in.readLine();
         checkInt(Integer.parseInt(k));
        System.out.print("\tPlease enter a starting date (YYYYMMDD): ");
         String sdate = in.readLine();
         checkInt(Integer.parseInt(sdate));
        System.out.print("\tPlease enter an ending date (YYYYMMDD): ");
         String edate = in.readLine();
         checkInt(Integer.parseInt(edate));
      
   String query = "SELECT roomNo FROM Booking WHERE bookingDate BETWEEN TO_DATE('" + sdate + "' , 'YYYYMMDD') AND TO_DATE('" + edate + "' , 'YYYYMMDD') ORDER BY price DESC LIMIT " + k;

         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
   }//end topKHighestRoomPriceForADateRange
   
   public static void topKHighestPriceBookingsForACustomer(DBProject esql){
    // Given a customer Name, List Top K highest booking price for a customer 
      // Your code goes here.
      try{ 
        System.out.print("\tEnter number of prices K: ");
         String k = in.readLine();
         checkInt(Integer.parseInt(k));
        System.out.print("\tPlease enter customer first name: ");
         String fName = in.readLine();
         checkStr(fName);
        System.out.print("\tPlease enter an customer last name: ");
         String lName = in.readLine();
         checkStr(fName);
      
   String query = "SELECT b.price FROM Booking b, Customer c WHERE c.fName = '" + fName + "' AND c.lName = '" + lName + "' AND b.customer=c.customerID ORDER BY price DESC LIMIT " + k;

         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
   }//end topKHighestPriceBookingsForACustomer
   
   public static void totalCostForCustomer(DBProject esql){
    // Given a hotelID, customer Name and date range get the total cost incurred by the customer
      // Your code goes here.
      try{ 
        System.out.println(" ______________________________________________________________________");
        System.out.println(" |.============[_F_E_D_E_R_A_L___R_E_S_E_R_V_E___N_O_T_E_]=============.|");
        System.out.println(" ||%&%&%&%_    _        _ _ _   _ _  _ _ _     _       _    _  %&%&%&%&||");
        System.out.println(" ||%&.-.&/||_||_ | ||\||||_| \ (_ ||\||_(_  /\|_ |\|V||_|)|/ |\ %&.-.&&||");
        System.out.println(" ||&// |\ || ||_ \_/| ||||_|_/ ,_)|||||_,_) \/|  ||| ||_|\|\_|| &// |\%||");
        System.out.println(" ||| | | |%               ,-----,-'____'-,-----,               %| | | |||");
        System.out.println(" ||| | | |&% """"""""""  [    .-;\"`___ `\";-.    ]             &%| | | |||");
        System.out.println(" ||&\===//                `).'' .'`_.- `. '.'.(`  A 76355942 J  \\===/&||");
        System.out.println(" ||&%'-'%/1                // .' /`     \    \\                  \%'-'%||");
        System.out.println(" ||%&%&%/`   d8888b       // /   \  _  _;,    \\      .-\"\"\"-.  1 `&%&%%||");
        System.out.println(" ||&%&%&    8P |) Yb     ;; (     > a  a| \    ;;    //A`Y A\\    &%&%&||");
        System.out.println(" ||&%&%|    8b |) d8     || (    ,\   \ |  )   ||    ||.-'-.||    |%&%&||");
        System.out.println(" ||%&%&|     Y8888P      ||  '--'/`  -- /-'    ||    \\_/~\_//    |&%&%||");
        System.out.println(" ||%&%&|                 ||     |\`-.__/       ||     '-...-'     |&%&%||");
        System.out.println(" ||%%%%|                 ||    /` |._ .|-.     ||                 |%&%&||");
        System.out.println(" ||%&%&|  A 76355942 J  /;\ _.'   \  } \  '-.  /;\                |%&%&||");
        System.out.println(" ||&%.-;               (,  '.      \  } `\   \'  ,)   ,.,.,.,.,   ;-.%&||");
        System.out.println(" ||%( | ) 1  \"\"\"\"\"\"\"   _( \  ;...---------.;.; / )_ ```\"\"\"\"\"\"\" 1 ( | )%||");
        System.out.println(" ||&%'-'==================\`------------------`/=================='-'%&||");
        System.out.println(" ||%&JGS&%&%&%&%%&%&&&%&%%&)O N E  D O L L A R(%&%&%&%&%&%&%%&%&&&%&%%&||");
        System.out.println(" '""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""`");                   

         
        System.out.print("\tPlease enter hotel ID: ");
           String hID = in.readLine();
           checkInt(Integer.parseInt(hID));
        System.out.print("\tPlease enter the customer's first name: ");
           String firstName = in.readLine();
           checkStr(firstName);
        System.out.print("\tPlease enter the customer's last name: ");
           String lastName = in.readLine();
           checkStr(lastName);
        System.out.print("\tPlease enter the starting date (YYYYMMDD): ");
           String sDate = in.readLine();
           checkInt(Integer.parseInt(sDate));
        System.out.print("\tPlease enter the ending date (YYYYMMDD): ");
           String eDate = in.readLine();
           checkInt(Integer.parseInt(eDate));
     String query = "SELECT SUM(b.price) total FROM Customer c, Booking b WHERE c.fName = '" + firstName + "' AND c.lName = '" + lastName + "' AND c.customerID = b.customer AND b.hotelID = '" + hID + "' AND b.bookingDate BETWEEN TO_DATE('" + sDate + "', 'YYYYMMDD') AND TO_DATE('" + eDate + "', 'YYYYMMDD') ";

         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
   }//end totalCostForCustomer
   
   public static void listRepairsMade(DBProject esql){
    // Given a Maintenance company name list all the repairs along with repairType, hotelID and roomNo
      // Your code goes here.
      try{ 
        System.out.print("\tEnter Maintenance company name: ");
         String mcName = in.readLine();
         checkStr(mcName);

   String query = "SELECT rep.rID, rep.repairType, rep.hotelID, rep.roomNo FROM Repair rep, MaintenanceCompany mc WHERE '" + mcName + "' = mc.name AND rep.mCompany = mc.cmpID";

         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      }catch(Exception e){
         System.err.println (e.getMessage());
      }
   }//end listRepairsMade
   
   public static void topKMaintenanceCompany(DBProject esql){
    // List Top K Maintenance Company Names based on total repair count (descending order)
      // Your code goes here.
      try {
        System.out.print("\tEnter maintenance company k-th value: ");
        String k = in.readLine();
        checkInt(Integer.parseInt(k));

        String query = "SELECT mc.name, COUNT(rep.rID) FROM MaintenanceCompany mc, Repair rep WHERE mc.cmpID=rep.mCompany GROUP BY mc.name ORDER BY COUNT(rep.rID) DESC LIMIT " + k;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      } catch(Exception e) {
        System.err.println (e.getMessage());
      }
      
   }//end topKMaintenanceCompany
   
   public static void numberOfRepairsForEachRoomPerYear(DBProject esql){
    // Given a hotelID, roomNo, get the count of repairs per year
    // Your code goes here.
      try {
        System.out.print("\t Enter hotel ID: ");
        String hID = in.readLine();
        checkInt(Integer.parseInt(hID));
        System.out.print("\t Enter room number: ");
        String rNo = in.readLine();
        checkInt(Integer.parseInt(rNo));

        String query = "SELECT EXTRACT(YEAR FROM rep.repairDate), COUNT(rep.rID) FROM Repair rep WHERE rep.roomNo = '" + rNo + "' AND rep.hotelID = '" + hID + "' GROUP BY EXTRACT(YEAR FROM rep.repairDate) ORDER BY EXTRACT(YEAR FROM rep.repairDate) DESC";

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         int rowCount = esql.executeQuery(query);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println ("total row(s): " + rowCount);
      } catch(Exception e) {
        System.err.println (e.getMessage());
      }
   }//end listRepairsMade

}//end DBProject
