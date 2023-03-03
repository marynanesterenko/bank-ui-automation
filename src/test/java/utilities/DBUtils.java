package utilities;

import java.sql.*;

public class DBUtils {
    // syntax of the DataBase Connection URL is the following: "jdbc:mysql://localhost:3306/bootcamp"
    // to break it down, the url contains of the following:
    // "jdbc"           - Java Database Connectivity, it's the API, which stands in between the Java program (like our Framework) and the RDBMS (like the MySQL Application);
    // "mysql"          - database itself (this is confusing to me... mysql vs MySQL? Probably just the way it's written - all lowercase,
    //                    but since database is something abstract, that we cannot see, so it is just represented by the UI of MySQL RBDMS, I guess, it is the same thing when we say database and RDBMS?...).
    // "localhost"      - server name on which the MySQL is running (MySQL is an App installed on our computer, and this is where it runs, so this makes sense),
    //                    we have also used "localhost", when we learnt SauceLabs, when we switched between the Object of the WebDriver, which was created on our localhost,
    //                    and the Object of the RemoteWebDriver, which was created on the SauceLabs's remote server "saucelabs".
    // "3306"           - port number.
    // "bootcamp"       - the name of our database, which we see at the top of the schema hierarchy (on the left).
    // "localhost:3306" - this is all together makes up the address to our database

    // !NOTE: HostName and Port are the two critical things for us to have, to be able to connect to the Database

    // AGENDA: understand how to connect our Framework with the Database
    // To process any SQL query with JDBC, we need to follow these steps:
    // STEP 1 - CONNECT(has 2 sub-steps): register the driver and connect to the driver;
    // STEP 2 - QUERY(has 2 sub-steps): create the statement and execute the query;
    // STEP 3 - PROCESS RESULTS: this probably happens simultaneously with the previous step (in our example)... but I am not sure, if we could maybe have a separate method for it?.. or maybe we could even have a separate Class "ResultSetHandler"?
    // STEP 4 - CLOSE: just like we quit the browser at the end of the test, we will close the database connection in the same way after we execute the query and process(retrieve) the results we need.

    // So what are we going to do in the class?
    // 1. Register the driver by simply adding the "mysql-connector-java" dependency to our pom.xml file.
    // 2. Connect to the driver by adding the database url and database access credentials ino the config.properties file, and creating a method to initialize them - .initializeDBProperties().
    // 3. Create methods to represent the 4 actions (steps) described above.

    // let's create instance variables, where we can store the database properties: url, username and password (these three things we will need to get the connection -> below, see what method we will pass it to)
    // since our config.property file stores data in the "key = value" format, we will use .getConfigProperty() method to retrieve the values of the specified keys.
    // !!REMEMBER: it is dangerous to have hard-coded values in our framework's Classes, therefore we should always store it in the config.property file.
    static String url = ConfigReader.getConfigProperty("db.url");
    static String username = ConfigReader.getConfigProperty("db.username");
    static String password = ConfigReader.getConfigProperty("db.password");

    // these are just other instance variables, that we want to be accessible by all the methods in the Class, pretty much - we want to be able to reuse these "storages":
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    // with the help of the below method we can begin our database connection:
    // we will call this method under @Before annotation in the Hooks
    public static void initializeDBProperties() {
        // !!REMEMBER: it is better to use "try catch" statement instead of "throws" keyword, so that in case we need to extend the Class and override it's methods, we don't need to add throws for every other Class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // .forName() methods throws Exception, so better catch it now, rather than delaying it with teh 'throws" keyword
            // connecting the driver with the database with the help of Connection Interface, DriverManager, and it's .getConnection() method:
            // what do we need to pass to the getConnection() method (or in other words: what do we need to get the connections)? -> database url, username and password!
            Thread.sleep(5000);
            connection = DriverManager.getConnection(url, username, password);

            // create the statement:
            // !!NOTE: Statement is an Interface, which defines methods, which we can use in order to send SQL commands to our database and retrieve back the data.
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // whenever we want to retrieve the data - we need to use the .executeQuery() method from the "Statement" Interface, and then
    // whatever we get as a result (whatever the method returns to us), after executing the query - we will store inside the "resultSet" variable.
    // !!NOTE: .executeSQLQuery() is our custom method, and the .executeQuery() is Java's Statement Interface method
    // .executeSQLQuery() is greyed out, since we did not use it anywhere yet, but we can utilize it anywhere across our Framework, wherever we need to
    // since it is a static method we can just call it by it's Class name DBUtils.executeSQLQuery();
    public static ResultSet executeSQLQuery(String query) {
        try {
            resultSet = statement.executeQuery(query); // .executeQuery() methods throws Exception, so better catch it now, rather than delaying it with teh 'throws" keyword
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // I am not sure whether I had to put this "while" loop in the method above, or if I can have it in a separate method - this is probably incorrect, but I will leave it here till better times... :)
    // in order to maneuver the methods we can add parameters to this method
    public static void processResults(){
        try {
            // ResultSet cursor is initially positioned before the first row
            // if we do not use this .next() method here - we weill get the SQLException, because without it - we are pointing at the "first_name" column header, and not at the value in it?...
            while (executeSQLQuery("select * from bootcamp.employees").next()) {
                System.out.println(resultSet.getString("first_name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // we will call this method under @After annotation in the Hooks
    public static void closeDBConnection() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// WAYS TO RETRIEVE DATA:

// if we want to retrieve ONE COLUMN, we can implement the while loop:
//        while(resultSet.next()) {
//        System.out.println(resultSet.getString("first_name"));
//    }

// if we want to retrieve MORE THAN ONE COLUMN:
// (while there is a next value in the column, print it)
//        while (resultSet.next()) {
//            System.out.print(resultSet.getString("first_name") + " ");
//            System.out.println(resultSet.getString("last_name"));
//        }

// if we want to return the ENTIRE TABLE:
//        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        int columnNum = resultSetMetaData.getColumnCount();
//        // whenever there is a nested for loop: the outer loop will be for the column, and inner - for the row:
//        while (resultSet.next()) {
//            for (int i = 1; i < columnNum; i++) {
//                System.out.println(resultSet.getString(i) + "|");
//            }
//            System.out.println();
//        }