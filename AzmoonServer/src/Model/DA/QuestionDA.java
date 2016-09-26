package Model.DA;

import Model.TO.QuestionTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionDA
{
    private Connection connection;
    private PreparedStatement statement;

    public QuestionDA()throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "navid", "navid");
        connection.setAutoCommit(false);
    }
    public void insert(QuestionTO questionTO)throws Exception
    {
        statement = connection.prepareStatement("INSERT INTO QUESTION(QUESTIONID,CATEGORYID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,TRUEANSWER) VALUES (seq_question.nextval,?,?,?,?,?,?,?)");

        statement.setLong(1, questionTO.getCategoryid());
        statement.setString(2, questionTO.getQuestion());
        statement.setString(3, questionTO.getAnswer1());
        statement.setString(4, questionTO.getAnswer2());
        statement.setString(5, questionTO.getAnswer3());
        statement.setString(6, questionTO.getAnswer4());
        statement.setLong(7, questionTO.getTrueanswer());
        statement.executeUpdate();
    }

    public void update(QuestionTO questionTO) throws Exception
    {
        statement = connection.prepareStatement("UPDATE QUESTION SET QUESTION = ?,ANSWER1 = ?,ANSWER2 = ?,ANSWER3 = ?,ANSWER4 = ?, TRUEANSWER = ? WHERE QUESTIONID = ?");
        statement.setString(1, questionTO.getQuestion());
        statement.setString(2, questionTO.getAnswer1());
        statement.setString(3, questionTO.getAnswer2());
        statement.setString(4, questionTO.getAnswer3());
        statement.setString(5, questionTO.getAnswer4());
        statement.setLong(6, questionTO.getTrueanswer());
        statement.setLong(7, questionTO.getId());
        statement.executeUpdate();
    }

    public ArrayList<QuestionTO> selectByCategory(long category_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM QUESTION WHERE CATEGORYID = ?");
        statement.setLong(1,category_id);
        ResultSet resultSet =  statement.executeQuery();
        ArrayList<QuestionTO> questionTOs = new ArrayList<QuestionTO>();
        while (resultSet.next())
        {
            QuestionTO questionTO = new QuestionTO();
            questionTO.setId(resultSet.getLong("QUESTIONID"));
            questionTO.setCategoryid(resultSet.getLong("CATEGORYID"));
            questionTO.setQuestion(resultSet.getString("QUESTION"));
            questionTO.setAnswer1(resultSet.getString("ANSWER1"));
            questionTO.setAnswer2(resultSet.getString("ANSWER2"));
            questionTO.setAnswer3(resultSet.getString("ANSWER3"));
            questionTO.setAnswer4(resultSet.getString("ANSWER4"));
            questionTO.setTrueanswer(resultSet.getLong("TRUEANSWER"));
            questionTOs.add(questionTO);
        }
        return questionTOs;
    }

    public QuestionTO selectByID(long question_id) throws Exception
    {
        statement = connection.prepareStatement("SELECT * FROM QUESTION WHERE QUESTIONID = ?");
        statement.setLong(1,question_id);
        ResultSet resultSet =  statement.executeQuery();
        QuestionTO questionTO = new QuestionTO();
        while (resultSet.next())
        {
            questionTO.setId(resultSet.getLong("QUESTIONID"));
            questionTO.setCategoryid(resultSet.getLong("CATEGORYID"));
            questionTO.setQuestion(resultSet.getString("QUESTION"));
            questionTO.setAnswer1(resultSet.getString("ANSWER1"));
            questionTO.setAnswer2(resultSet.getString("ANSWER2"));
            questionTO.setAnswer3(resultSet.getString("ANSWER3"));
            questionTO.setAnswer4(resultSet.getString("ANSWER4"));
            questionTO.setTrueanswer(resultSet.getLong("TRUEANSWER"));
        }
        return questionTO;
    }

    public void deleteByID(long question_id) throws Exception
    {
        statement = connection.prepareStatement("DELETE FROM QUESTION WHERE QUESTIONID = ?");
        statement.setLong(1,question_id);
        statement.executeUpdate();
    }

    public void close() throws Exception
    {
        connection.commit();
        statement.close();
        connection.close();
    }
}
