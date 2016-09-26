package Model.DA;

import Model.TO.AzmoonTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AzmoonDA
{
    private Connection connection;
    private PreparedStatement statement;

    public AzmoonDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid");
        connection.setAutoCommit(false);
    }

    public void insert(AzmoonTO azmoonTO) throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO AZMOON (AZMOONID,USERID,NATIJE,ACCEPT) VALUES (?,?,?,?)");
        statement.setLong(1, azmoonTO.getId());
        statement.setLong(2, azmoonTO.getUserid());
        statement.setString(3, azmoonTO.getResult());
        statement.setString(4, azmoonTO.getAccept());
        statement.executeUpdate();
    }

    public void updateByUserId(AzmoonTO azmoonTO) throws Exception
    {
        statement = connection.prepareStatement("UPDATE AZMOON SET NATIJE = ?,ACCEPT = ? WHERE USERID = ?");
        statement.setString(1, azmoonTO.getResult());
        statement.setString(2, azmoonTO.getAccept());
        statement.setLong(3, azmoonTO.getUserid());
        statement.executeUpdate();
    }

    public ArrayList<AzmoonTO> selectAccepts() throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM AZMOON WHERE ACCEPT LIKE '%YES%'");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<AzmoonTO> azmoonTOs = new ArrayList<AzmoonTO>();
        while (resultSet.next())
        {
            AzmoonTO azmoonTO = new AzmoonTO();
            azmoonTO.setId(resultSet.getLong("AZMOONID"));
            azmoonTO.setUserid(resultSet.getLong("USERID"));
            azmoonTO.setResult(resultSet.getString("NATIJE"));
            azmoonTO.setAccept(resultSet.getString("ACCEPT"));
            azmoonTOs.add(azmoonTO);
        }
        return azmoonTOs;
    }

    public AzmoonTO selectByUser (long user_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM AZMOON WHERE USERID = ?");
        statement.setLong(1,user_id);
        ResultSet resultSet =  statement.executeQuery();
        AzmoonTO azmoonTO = new AzmoonTO();
        while (resultSet.next())
        {
            azmoonTO.setId(resultSet.getLong("AZMOONID"));
            azmoonTO.setUserid(resultSet.getLong("USERID"));
            azmoonTO.setResult(resultSet.getString("NATIJE"));
            azmoonTO.setAccept(resultSet.getString("ACCEPT"));
        }
        return azmoonTO;
    }

    public long selectCount() throws Exception
    {
        statement = connection.prepareStatement("SELECT COUNT(*) FROM AZMOON");
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
        connection.commit();
        statement.close();
        connection.close();
    }
}
