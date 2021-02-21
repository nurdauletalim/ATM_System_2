package kz.iitu.assignment1;

import java.sql.*;
import java.util.List;

public class Bank {

    public Bank(){}
    private List<Account> accounts;
    private BankService bankService;

    Statement statement = null;
    Connection connection = null;

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public Bank(List<Account> accounts, BankService bankService) {
        this.accounts = accounts;
        this.bankService = bankService;
    }

    public BankService getBankService() { return bankService; }

    public void setBankService(BankService bankService) { this.bankService = bankService; }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getDbUrl() { return dbUrl; }

    public void setDbUrl(String dbUrl) { this.dbUrl = dbUrl; }

    public String getDbUsername() { return dbUsername; }

    public void setDbUsername(String dbUsername) { this.dbUsername = dbUsername; }

    public String getDbPassword() { return dbPassword; }

    public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

    public Connection DBcon() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (connection != null) {
                System.out.println("Connection successfull!");
            }
            else {
                System.out.println("Connection failed!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    public void init() throws SQLException {
        Connection connection = this.DBcon();
        ResultSet set = null;
        String query = "SELECT * FROM accounts";
        statement = connection.createStatement();
        set = statement.executeQuery(query);
        while (set.next()){
            Account acc = new Account(set.getInt(1), set.getDouble(2), set.getInt(3), set.getInt(4),
                    set.getString(5));
            getAccounts().add(acc);
        }}

    public void destroy() throws SQLException{
        try {
            connection.close();
            if (connection != null) {
                System.out.println("Connection is closed!");
            }
            else {
                System.out.println("Something went wrong!");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
