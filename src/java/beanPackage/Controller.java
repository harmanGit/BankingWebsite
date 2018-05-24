package beanPackage;

import java.io.IOException;
import static java.lang.Math.abs;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to as servlet to Access the Database Class. The Access
 * class is a class connect to a database which simulates a Bank. Connect to a
 * mySql Server on BU campus.
 *
 * @author hsd77849
 */
@WebServlet(name = "Controller", urlPatterns =
{
    "/Controller"
})
public class Controller extends HttpServlet
{

    Access bank = new Access(); //creates a Access object
    private String username; //user entered username
    private String password;//user entered password
    private double balance;//user entered balance
    private String acctType;//user entered account type
    balanceBean bb = new balanceBean(); //Creating a balanceBean object
    transBean tb = new transBean(); //Creating a transBean Object

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException
    {

        // getting the value for the parameter named action
        // The value of action equals the buttons value clicked in the jsp/html
        String action = request.getParameter("action");

        // default link, incase something breaks
        String jspLink = "/ErrorPage.jsp";

        // If the login button is clicked
        if (action.equalsIgnoreCase("login"))
        {
            // getting the username and password from the login page
            username = request.getParameter("username");
            password = request.getParameter("password");
            // checking to see if the username and password match with the ones
            // in the database. Done using a loginCheck method from the access
            // class
            if (bank.loginChecker(username, password) == true)
            {
                jspLink = "/MenuJsp.jsp";
            } else
            {
                // if the password and username are wrong then its linked 
                // to a error page informing the user
                jspLink = "/wrongLogin.jsp";
            }
        }
        // If the Go Back button is clicked
        if (action.equalsIgnoreCase("Go Back"))
        {
            // Links back to the menu
            jspLink = "/MenuJsp.jsp";
        }

        // If the new customer button is clicked
        if (action.equalsIgnoreCase("New Customer"))
        {
            // Links to the new Customer page
            jspLink = "/NewCustomer.jsp";
        }

        // If the Leave page button is clicked
        if (action.equalsIgnoreCase("Leave Page"))
        {
            // Links back to the index.html page which is the login page
            jspLink = "/index.html";
        }

        // If the Logout button is clicked
        if (action.equalsIgnoreCase("Logout"))
        {
            // removes username and password
            username = null;
            password = null;
            // Links back to the index.html page which is the login page
            jspLink = "/index.html";
        }

        // If the Check All Accounts button is clicked
        if (action.equalsIgnoreCase("Check All Accounts"))
        {
            // The resultset is stored with a method from the access class
            ResultSet rs = bank.checkAccountBalance(username, password);
            // getting the metadata for the resultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            // getting the number of columns
            int cn = rsmd.getColumnCount();
            // using the htmlTableDisplay method to create a string containing
            // html code for the table
            String s = htmlTableDisplay(rs, rsmd, cn);
            // setting the table in the transbean
            tb.setTable(s);
            // setting the table so its visible on the page
            request.setAttribute("Table_Name", tb.getTable());
            // linking to the page with the table
            jspLink = "/BankBalance.jsp";
        }

        // If the Become A Member is clicked
        if (action.equalsIgnoreCase("Become A Member"))
        {
            // getting all the parameters from the page
            String newUser = request.getParameter("newUsername");
            String newPassword = request.getParameter("newPassword");
            String firstName = request.getParameter("newFname");
            String lastName = request.getParameter("newLname");
            String address = request.getParameter("newAddress");
            String city = request.getParameter("newCity");
            String state = request.getParameter("newState");
            String zip = request.getParameter("newZip");
            String acct = request.getParameter("MAccounts");
            // parsing becasue a string is entered
            double amt = Double.parseDouble(request.getParameter("newAmount"));
            amt = abs(amt); // making sure the user does not input negative
            // checking if the user entered more that 50 dollars

            if (bank.loginChecker(newUser, newPassword) == true)
            {
                jspLink = "/infoTaken.jsp";
                //actually using the link set in the if statements to move to the next page
                getServletContext().getRequestDispatcher(jspLink).forward(request, response);
                return;
            } else
            {
                if (amt > 50)
                {
                    // if so, then the member is created by using the method from
                    // the access class
                    bank.newAccount(newUser, newPassword, firstName, lastName,
                            address, city, state, zip, acct, amt);
                    // Links back to the index.html page which is the login page
                    jspLink = "/index.html";
                } else
                {
                    // Linking to a low funds error page if the amount entered is
                    // less than 50
                    jspLink = "/lowFunds.jsp";
                }
            }

        }

        // If the deposit button is clicked
        if (action.equalsIgnoreCase("Deposit"))
        {
            // getting what account was selected, checking, savings, money market
            String acctType = request.getParameter("Accounts");
            // parsing becasue a string is entered
            double amt = Double.parseDouble(request.getParameter("dMoney"));
            amt = abs(amt); // making sure the user does not input negative
            // using a method from the access class to deposit the money
            bank.transAction(username, password, acctType, amt);
            // linking to the deposit sucessful page
            jspLink = "/DepositCompleted.jsp";

        }

        // If the withdraw button is clicked
        if (action.equalsIgnoreCase("Withdraw"))
        {
            // getting what account was selected, checking, savings, money market
            String acctType = request.getParameter("Accounts");
            // parsing becasue a string is entered
            double amt = Double.parseDouble(request.getParameter("wMoney"));
            amt = abs(amt); // making sure the user does not input negative
            // turning the amount entered into a negative becasue the method
            // used to complete a transaction only adds to the current balance.
            amt = -1 * amt;
            // using a method from the access class to withdraw the money
            bank.transAction(username, password, acctType, amt);
            // linking to the withdraw sucessful page
            jspLink = "/WithdrawCompleted.jsp";

        }

        // If the New Account button is clicked
        if (action.equalsIgnoreCase("New Account"))
        {
            // getting what account was selected, checking, savings, money market
            String acctType = request.getParameter("Accounts");
            // using a method from the access class to create a bank account
            bank.addAccount(username, password, acctType, 0);
            // linking to a sucess page
            jspLink = "/NewAccountCompleted.jsp";
        }

        //If the Transfer button was clicked
        if (action.equalsIgnoreCase("Transfer"))
        {
            // parsing becasue a string is entered
            double amt = Double.parseDouble(request.getParameter("tMoney"));
            // getting the account to transfer money from
            String acctTypeF = request.getParameter("FAccounts");
            // getting the account to transfer money to
            String acctTypeT = request.getParameter("TAccounts");
            // using a method from the access class to transfer the money
            bank.tranferBetweenAccount(username, password, amt, acctTypeF, acctTypeT);
            // Linking to a sucess page
            jspLink = "/TransferCompleted.jsp";

        }

        // If the Transaction page was clicked
        if (action.equalsIgnoreCase("Transactions"))
        {
            // getting the date to start looking from
            String dateF = request.getParameter("fDate");
            // getting the date to stop looking at
            String dateT = request.getParameter("tDate");
            // getting what account they want to look at
            String acctT = request.getParameter("TrAccounts");
            // getting the result set, by using a method from the access class
            ResultSet rs = bank.checkTransactions(username, password, acctT,
                    dateT, dateF);
            // getting the metadata for the resultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            // getting the number of columns
            int cn = rsmd.getColumnCount();
            // using the htmlTableDisplay method to create a string containing
            // html code for the table
            String s = htmlTableDisplay(rs, rsmd, cn);
            // setting the table in the balancebean
            tb.setTable(s);
            // setting the table so its visible on the page
            request.setAttribute("Table_Name", tb.getTable());
            // linking to the page with the table
            jspLink = "/TransactionsCompleted.jsp";
        }

        //actually using the link set in the if statements to move to the next page
        getServletContext().getRequestDispatcher(jspLink).forward(request, response);
    }

    public String htmlTableDisplay(ResultSet results,
            ResultSetMetaData metaData, int columnNumber)
            throws SQLException
    {

        // using the stringBuilder 
        StringBuilder table = new StringBuilder();
        table.append("<table>"); // starting the table html code

        // display header
        table.append("<tr>");
        // creating the header names
        for (int i = 1; i <= columnNumber; i++)
        {
            table.append("<th>");
            // using the headers names from the table
            table.append(metaData.getColumnName(i).toUpperCase());
            table.append("</th>");
        }
        // ending the header html code
        table.append("</tr>");

        // display all tables
        while (results.next())
        {
            table.append("<tr>");
            // getting the info from the tables
            for (int i = 1; i <= columnNumber; i++)
            {
                table.append("<td>");
                // adding to table info to the table
                table.append(results.getString(i));
                table.append("</td>");
            }
            // ending the discription
            table.append("</tr>");
        }
        table.append("</table>");// ending the table html code
        return table.toString(); // return the table as html as a string
    }

    /**
     * Public method, returns the bank balance of the user logged in as a
     * double.
     *
     * @return a double, the bank balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Public method, returns account of the user logged in as a string
     *
     * @return string account type as a string
     */
    public String getAcct()
    {
        return acctType;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
