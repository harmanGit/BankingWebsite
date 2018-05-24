package beanPackage;

import com.mysql.jdbc.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class creates a bank that allows users to create a membership, transfer money
 * between accounts, create more accounts with the same customer, check balance
 * of accounts, and check transactions made between two dates.
 *
 * @author hsd77849
 */
public class Access
{

    private Statement stateMint; // used to excute sql statement
    private DatabaseMetaData dbmd; // metadata for the database in use
    private Connection conNet; // allows a connection to database

    // Constructor
    Access()
    {
        try
        {
            // Loading Driver
            new com.mysql.jdbc.Driver();
            // Connecting to the server
            conNet = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://******",
                    "******", "*******");
            // Turning of auto commit
            conNet.setAutoCommit(false);
            //getting from connection object, allows excute sql statement
            stateMint = conNet.createStatement();
            //Getting metadata for the database
            dbmd = conNet.getMetaData();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Method transfers money from one account to another account of the same
     * customer.
     *
     * @param user String username
     * @param pass String password
     * @param amount Double Amount to transfer
     * @param fromType String what account to transfer from
     * @param toType String what account to transfer to
     */
    public void tranferBetweenAccount(String user, String pass, double amount,
            String fromType, String toType)
    {

        // Using the getCustID method to get the customer id from the username
        // and password.
        int custID = getCustID(user, pass);
        // Using the getAcctId method to get the account id from the
        // customer if and account type(Savings, Checking, Money Market)
        int fromAcct = getAcctID(custID, fromType);

        // Using the balanceCheck method to get the balance of a account by
        // using the account ID
        double fromAccTrans = balanceCheck(fromAcct);

        // Making sure the amount is more than 0 and less then the amount
        // money in the account to transfer
        if (fromAccTrans >= amount && amount > 0)
        {
            // Using the transAccess method to make the transaction and actually
            // removing money from the account the user wanted to transfer from
            transAccess(fromAcct, (-1 * amount), "Transfer");

            // Using the getAcctId method to get the account id from the
            // customer if and account type(Savings, Checking, Money Market)
            int toAcct = getAcctID(custID, toType);

            // Using the transAccess method to make the transaction and actually
            // Adding money to  the account the user wanted to transfer to
            transAccess(toAcct, amount, "Transfer");
            System.out.println("Transfer Complete");
            commit();
        } else
        {
            System.out.println("Not Enough Money To Transfer.");
        }

    }

    /**
     * Method creates a new customer and then creates a new account for them,
     * and requires them to insert at least 50 dollars.
     *
     * @param un String username
     * @param pass String Password
     * @param first String first name
     * @param last String last name
     * @param address String street address
     * @param city String city
     * @param state String State, has to be two characters, not more or less
     * @param zip String zip code, has to be five characters, not more or less
     * @param acct String account type (Savings, Checking, Money Market)
     * @param amt Double Amount, the amount of money they wanted to add to the
     * new account they just created
     */
    public void newAccount(String un, String pass, String first, String last,
            String address, String city, String state, String zip, String acct,
            double amt)
    {

        // Using the loginCheck method to see if the username and password
        // combinations isn't already taken by someone
        if (!(loginCheck(un, pass)))
        {
            try
            {
                //Creating a new customer and inserting it into the table
                String insertCust = "INSERT INTO CUSTOMERS VALUES(default,'" + un + "','"
                        + pass + "','"
                        + first + "','"
                        + last + "','"
                        + address + "','"
                        + city + "','"
                        + state + "','"
                        + zip + "')";
                // executing the sql statement
                stateMint.executeUpdate(insertCust);
                //Getting customer id from the table with the username and password
                int custID = getCustID(un, pass);
                //Creating a account
                String insertAcct = "INSERT INTO ACCOUNT VALUES(default," + custID
                        + ",'" + acct + "'," + 0 + ");";
                stateMint.executeUpdate(insertAcct);
                // Getting account id with the customer id
                int accID = getAcctID(custID, acct);
                // Using the transAccess method a transaction is made, using
                // the user given account ID, amount, and type
                transAccess(accID, amt, "Deposit");
                commit();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        } else
        {
            System.out.println("Login In. Already a Member.");
        }
    }

    /**
     * Allows users to check the amount of money in all open accounts.
     *
     * @param user String username
     * @param pass String password
     * @return a ResultSet representing the bank account table
     */
    public ResultSet checkAccountBalance(String user, String pass)
    {
        //Getting customer id from the table with the username and password
        int custID = getCustID(user, pass);
        // sql statement to select everything from account table acccording
        // to the customer id
        String sql = "SELECT * FROM ACCOUNT WHERE "
                + "cust_id = " + custID + ";";
        ResultSet rs = null;
        try
        {
            // executing the sql statement
            rs = stateMint.executeQuery(sql);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Method is used to get all transactions that accorded on that given day.
     * The methods accesses info from the transactions table. The date has to be
     * entered in YYYY-MM-DD format.
     *
     * @param user string username
     * @param pass string password
     * @param type string account type
     * @param dateFrom string date to look from
     * @param dateTo string date to end at
     * @return a ResultSet representing the transaction table
     */
    public ResultSet checkTransactions(String user, String pass, String type,
            String dateFrom, String dateTo)
    {
        // Using the getCustID method to get the customer id from the username
        // and password.
        int custID = getCustID(user, pass);
        // Using the getAcctId method to get the account id from the
        // customer if and account type(Savings, Checking, Money Market)
        int acctID = getAcctID(custID, type);
        //sql statement that selects everything from transactions where account
        // id matches and the its between user entered dates
        String sql = "SELECT * FROM TRANSACTIONS WHERE "
                + "acc_id=" + acctID + " AND CAST('" + dateFrom + "' AS DATE)"
                + " AND CAST('" + dateTo + "'AS DATE);";
        ResultSet rs = null;
        try
        {
            // executes sql statement
            rs = stateMint.executeQuery(sql);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Method returns the metaData for the database
     *
     * @return type metaData from the database
     */
    public DatabaseMetaData metaData()
    {
        DatabaseMetaData dbmd = null;
        try
        {
            dbmd = conNet.getMetaData();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return dbmd;
    }

    /**
     * Method checks if the username and password exist in the database, by
     * seeing if the username and password entered has a customer Id value
     *
     * @param user String username
     * @param pass String password
     * @return boolean true if the username and password exist, false if they
     * dont
     */
    public boolean loginChecker(String user, String pass)
    {
        int custID = getCustID(user, pass);
        if (custID > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Method creates an account for the users once they already have a
     * membership
     *
     * @param un String username
     * @param pass String password
     * @param type String account type(Savings, Checking, Money Market)
     * @param amount Double amount the user wants to add to the new account
     */
    public void addAccount(String un, String pass, String type, double amount)
    {
        try
        {
            //Getting customer id from the table with the username and password
            int custID = getCustID(un, pass);

            //Creating a account with the customer ID and account type
            String insertAcct = "INSERT INTO ACCOUNT VALUES(default," + custID
                    + ",'" + type + "'," + 0 + ");";
            //updates account table
            stateMint.executeUpdate(insertAcct);
            commit(); // saves
            // Using the getAcctId method to get the account id from the
            // customer if and account type(Savings, Checking, Money Market)
            int accID = getAcctID(custID, type);
            // Using the transAccess method a transaction is made, using
            // the user given account ID, amount, and type
            transAccess(accID, amount, type);
            commit();
            System.out.println("Account Created");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * Method makes a transaction, with user given information. Username,
     * Password, Account type and Amount
     *
     * @param un String username
     * @param pass String password
     * @param type String account type(Savings, Checking, Money Market)
     * @param money Double amount of money the user wants deposit, or withdraw
     */
    public void transAction(String un, String pass, String type, double money)
    {
        //Getting customer id from the table with the username and password
        int custID = getCustID(un, pass);
        // Using the transAccess method a transaction is made, using
        // the user given account ID, amount, and type
        int acctID = getAcctID(custID, type);
        // Using the private method to make the transaction
        transAccess(acctID, money, type);
    }

    /**
     * Method is private and is used to get the customer ID by using the
     * username and password and then going through the Customers table and
     * finding customer id and saving it. It compares the username and password
     * till a match is made.
     *
     * @param un String username
     * @param pass String password
     * @return Integer representing the customer ID found
     */
    private int getCustID(String un, String pass)
    {
        int custID = 0;
        try
        {
            // sql statement to find the customer ID from the customers table
            // where the username and password match up
            String sql = "SELECT cust_id FROM CUSTOMERS WHERE "
                    + "cust_username = '" + un + "' AND cust_password = '"
                    + pass + "';";
            // Stroing the sql statement in resultSet
            ResultSet rs = stateMint.executeQuery(sql);
            // Going into the resultSet and finding the custID and saving it
            while (rs.next())
            {
                // Get the integer that has the match table name(in our case
                // cust_id)
                custID = rs.getInt("cust_id");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        // returning the new found customer id
        return custID;
    }

    /**
     * Method is private and simply checks if the username and password given
     * are already in the table. Returns true if the are and false if they
     * aren't. Method can be used to check if a person password is correct and
     * or used to check if the username and password combination is already
     * taken
     *
     * @param user String username
     * @param pass String password
     * @return Boolean true if the username and password match up with something
     * inside the table, and false if they don't
     */
    private boolean loginCheck(String user, String pass)
    {
        // Using the getCustID method to get the customer id from the username
        // and password.
        int custID = getCustID(user, pass);
        // If statement checks if the customer ID has a value, which is more than
        // zero becasue the custID starts from the 1 when the automatically created
        if (custID > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Method is private, and is simply used to get the account ID from the
     * customer id and account type(Checking, Savings, Money Market).
     *
     * @param custID Integer Customer ID
     * @param type String account type(Checking, Savings, Money Market)
     * @return Integer representing the account id
     */
    private int getAcctID(int custID, String type)
    {
        int acctID = 0;
        try
        {
            // Sql statement that selects the account id from the account table
            // where the customer id and the account type match up.
            String sql = "SELECT acc_id FROM ACCOUNT WHERE "
                    + "cust_id =" + custID + " AND acc_type = '"
                    + type + "';";
            // Storing the sql statement as a resultSet
            ResultSet rs = stateMint.executeQuery(sql);
            // Whileloop for looping and finding the account ID
            while (rs.next())
            {
                // gets the int value of the table with the column header name of
                // "acc_id"
                acctID = rs.getInt("acc_id");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        //returns account id
        return acctID;
    }

    /**
     * Method is private and allows a transaction to happens. A account id and
     * money to be transfer and account type(Checking, Savings, Money Market)
     * are all required for this method. The method could be used to add money
     * to a account and remove money from a account.
     *
     * @param accID Integer Account ID to add and remove money from
     * @param money Double money to added or removed from the account
     * @param type String account type, to add and remove from
     */
    private void transAccess(int accID, double money, String type)
    {
        try
        {
            // sql statement inserts a new transaction into the Transactions
            // table with all the parameters placed
            String insertTrans = "INSERT INTO TRANSACTIONS VALUES(default,"
                    + accID + ",'" + type + "'," + money + ",now());";
            //Executing the insert statement into the table
            stateMint.executeUpdate(insertTrans);
            // Using the balanceCheck() method to get the money in a account with
            // the matching account Id, then the user given amount of money is
            // add and then saved
            double total = balanceCheck(accID) + money;

            // sql statement which updates the account table with the new balance
            // could be reducing the bank balane of increasing it.
            String updateAcct = "UPDATE ACCOUNT SET acc_balance =" + total
                    + " WHERE acc_ID=" + accID + ";";
            //Excuting the update
            stateMint.executeUpdate(updateAcct);
            commit(); // Saving the update
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * Method is private and simply check the balance of an account by using the
     * account id
     *
     * @param accID Integer Account ID
     * @return Double representing the balance in the account
     */
    private double balanceCheck(int accID)
    {

        double bal = 0;
        try
        {
            // sql statement selects the account balance from the account
            // table where the account id matches the parameter account id
            String sql = "SELECT acc_balance FROM ACCOUNT WHERE "
                    + "acc_id=" + accID + ";";
            // Saving the sql statement as a resultSet
            ResultSet rs = stateMint.executeQuery(sql);
            // while loop for getting the balance
            while (rs.next())
            {
                // gets the int value of the table with the column header name of
                // "acc_balance"
                bal = rs.getInt("acc_balance");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        // returns the balance
        return bal;
    }

    /**
     * Method commits the sql statement to the database
     */
    private void commit()
    {
        try
        {
            // connection class was used
            conNet.commit();
        } catch (SQLException ex)
        {
            System.out.println("COMMIT CRASHED");
        }
    }

    /**
     * Method creates all the tables
     *
     * @param aL
     */
    private void createTable()
    {
        // Checking the admin key

        try
        {
            // sql statement
            String sql = "CREATE TABLE CUSTOMERS(cust_id INT NOT NULL AUTO_INCREMENT,"
                    + "cust_username VARCHAR(15),cust_password VARCHAR(15),"
                    + "cust_first VARCHAR(20),"
                    + "cust_last VARCHAR(20),cust_address VARCHAR(50),"
                    + "cust_city VARCHAR(25),"
                    + "cust_state CHAR(2),cust_zip VARCHAR(5),PRIMARY KEY(cust_id))";
            // sql statement executed
            stateMint.execute(sql);
            System.out.println("1 table");
            // sql statement
            sql = "CREATE TABLE ACCOUNT(acc_id INT NOT NULL AUTO_INCREMENT,"
                    + " cust_id INT,"
                    + "acc_type VARCHAR(15), acc_balance DOUBLE,"
                    + "FOREIGN KEY(cust_id) REFERENCES CUSTOMERS(cust_id) "
                    + "ON DELETE CASCADE , PRIMARY KEY(acc_id))";
            stateMint.execute(sql);
            // sql statement
            System.out.println("2 table");
            // sql statement
            sql = "CREATE TABLE TRANSACTIONS(trans_id INT NOT NULL AUTO_INCREMENT"
                    + ",acc_id INT,trans_type VARCHAR(15),trans_amount DOUBLE,"
                    + "trans_date DATE,FOREIGN KEY(acc_id) REFERENCES ACCOUNT(acc_id) "
                    + "ON DELETE CASCADE, PRIMARY KEY(trans_id))";
            stateMint.execute(sql);
            System.out.println("3 table");
            // sql statement
            System.out.println("Creation COMPLETE");

        } catch (Exception E)
        {
            System.out.println("CREATION BROKE");
        }

    }
}
