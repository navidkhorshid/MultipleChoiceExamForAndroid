package Model.DA;

import Model.TO.CategoryTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDA
{
    private Connection connection;
    private PreparedStatement statement;

    public CategoryDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid");
    }

    public ArrayList<CategoryTO> select() throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM CATEGORY");
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<CategoryTO> categoryTOs = new ArrayList<CategoryTO>();
        while (resultSet.next())
        {
            CategoryTO categoryTO = new CategoryTO();
            categoryTO.setId(resultSet.getLong("CATEGORYID"));
            categoryTO.setName(resultSet.getString("NAME"));
            categoryTOs.add(categoryTO);
        }
        return categoryTOs;
    }

    public void close() throws Exception
    {
        statement.close();
        connection.close();
    }
}
