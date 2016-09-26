package Model.DA;


import Model.TO.UsersTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDA
{
    private Connection connection;
    private PreparedStatement statement;

    public UsersDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid");

    }
    public void insert(UsersTO usersTO)throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO USERS (USERID,NAME,FAMILY,USERNAME,PASSWORD,SHSH,EMAIL,TEL) VALUES (?,?,?,?,?,?,?,?)");
        statement.setLong(1, usersTO.getId());
        statement.setString(2, usersTO.getName());
        statement.setString(3, usersTO.getFamily());
        statement.setString(4, usersTO.getUsername());
        statement.setString(5, usersTO.getPassword());
        statement.setString(6, usersTO.getAddress());
        statement.setString(7, usersTO.getEmail());
        statement.setString(8, usersTO.getTel());
        statement.executeUpdate();
    }

    public void update(UsersTO usersTO) throws Exception
    {
        statement = connection.prepareStatement("UPDATE USERS SET NAME = ?,FAMILY = ?,USERNAME = ?,PASSWORD = ?,SHSH = ?,EMAIL = ?,TEL = ? WHERE USERID = ?");
        statement.setString(1, usersTO.getName());
        statement.setString(2, usersTO.getFamily());
        statement.setString(3, usersTO.getUsername());
        statement.setString(4, usersTO.getPassword());
        statement.setString(5, usersTO.getAddress());
        statement.setString(6, usersTO.getEmail());
        statement.setString(7, usersTO.getTel());
        statement.setLong(8, usersTO.getId());
        statement.executeUpdate();
    }

    public ArrayList<UsersTO> selectBySearch(String search_phrase)throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM USERS WHERE (NAME LIKE '%"+search_phrase+"%' OR FAMILY LIKE '%"+search_phrase+"%' OR USERID LIKE '%"+search_phrase+"%')");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<UsersTO> usersTOs = new ArrayList<UsersTO>();
        while (resultSet.next())
        {
            UsersTO userTO = new UsersTO();
            userTO.setId(resultSet.getLong("USERID"));
            userTO.setName(resultSet.getString("NAME"));
            userTO.setFamily(resultSet.getString("FAMILY"));
            userTO.setUsername(resultSet.getString("USERNAME"));
            userTO.setPassword(resultSet.getString("PASSWORD"));
            userTO.setAddress(resultSet.getString("SHSH"));
            userTO.setEmail(resultSet.getString("EMAIL"));
            userTO.setTel(resultSet.getString("TEL"));
            usersTOs.add(userTO);
        }
        return usersTOs;
    }
    public UsersTO selectByUP(String username, String password)throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?");
        statement.setString(1,username);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        UsersTO usersTO = new UsersTO();
        while(resultSet.next())
        {
            usersTO.setId(resultSet.getLong("USERID"));
            usersTO.setName(resultSet.getString("NAME"));
            usersTO.setFamily(resultSet.getString("FAMILY"));
            usersTO.setUsername(resultSet.getString("USERNAME"));
            usersTO.setPassword(resultSet.getString("PASSWORD"));
            usersTO.setAddress(resultSet.getString("SHSH"));
            usersTO.setEmail(resultSet.getString("EMAIL"));
            usersTO.setTel(resultSet.getString("TEL"));
        }
        return usersTO;
    }

    public UsersTO selectByID(long user_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM USERS WHERE USERID = ?");
        statement.setLong(1,user_id);
        ResultSet resultSet =  statement.executeQuery();
        UsersTO usersTO = new UsersTO();
        while (resultSet.next())
        {
            usersTO.setId(resultSet.getLong("USERID"));
            usersTO.setName(resultSet.getString("NAME"));
            usersTO.setFamily(resultSet.getString("FAMILY"));
            usersTO.setUsername(resultSet.getString("USERNAME"));
            usersTO.setPassword(resultSet.getString("PASSWORD"));
            usersTO.setAddress(resultSet.getString("SHSH"));
            usersTO.setEmail(resultSet.getString("EMAIL"));
            usersTO.setTel(resultSet.getString("TEL"));
        }
        return usersTO;
    }

    public long selectCount() throws Exception
    {
        statement = connection.prepareStatement("SELECT COUNT(*) FROM USERS");
        ResultSet resultSet = statement.executeQuery();
        long count = 0;
        while(resultSet.next())
        {
            count = resultSet.getLong("COUNT(*)");
        }
        return  count;
    }

    public void close() throws Exception
    {
        statement.close();
        connection.close();
    }
}
