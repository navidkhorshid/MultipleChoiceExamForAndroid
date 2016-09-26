package Model.BL;

import Model.DA.AzmoonDA;
import Model.DA.CategoryDA;
import Model.DA.QuestionDA;
import Model.DA.UsersDA;
import Model.TO.AzmoonTO;
import Model.TO.CategoryTO;
import Model.TO.QuestionTO;
import Model.TO.UsersTO;

import java.util.ArrayList;

public class Administrator
{
    //editing questions
    public void setQuestion(Long category_id,String question, String a, String b, String c, String d, long true_answer) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        QuestionTO questionTO = new QuestionTO();

        questionTO.setQuestion(question);
        questionTO.setCategoryid(category_id);
        questionTO.setAnswer1(a);
        questionTO.setAnswer2(b);
        questionTO.setAnswer3(c);
        questionTO.setAnswer4(d);
        questionTO.setTrueanswer(true_answer);

        questionDA.insert(questionTO);
        questionDA.close();
    }

    public void updateQuestion(Long question_id,String question, String a, String b, String c, String d, long true_answer) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        QuestionTO questionTO = new QuestionTO();

        questionTO.setQuestion(question);
        questionTO.setId(question_id);
        questionTO.setAnswer1(a);
        questionTO.setAnswer2(b);
        questionTO.setAnswer3(c);
        questionTO.setAnswer4(d);
        questionTO.setTrueanswer(true_answer);

        questionDA.update(questionTO);
        questionDA.close();
    }

    public void removeQuestion(long question_id) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        questionDA.deleteByID(question_id);
        questionDA.close();
    }

    public ArrayList<QuestionTO> getQuestions(long category_id) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        ArrayList<QuestionTO> questionTOs = questionDA.selectByCategory(category_id);
        questionDA.close();
        return questionTOs;
    }

    public QuestionTO getQuestion(long question_id) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        QuestionTO questionTO = questionDA.selectByID(question_id);
        questionDA.close();
        return  questionTO;
    }

    //find result of a user
    public ArrayList<UsersTO> getUsers(String search_phrase) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        ArrayList<UsersTO> usersTOs = usersDA.selectBySearch(search_phrase);
        usersDA.close();
        return usersTOs;
    }

    public AzmoonTO getAzmoon(long user_id) throws Exception
    {
        AzmoonDA azmoonDA = new AzmoonDA();
        AzmoonTO azmoonTO = azmoonDA.selectByUser(user_id);
        azmoonDA.close();
        return  azmoonTO;
    }

    //Accepted users
    public ArrayList<AzmoonTO> getAzmoonsAccepted() throws Exception
    {
        AzmoonDA azmoonDA = new AzmoonDA();
        ArrayList<AzmoonTO> azmoonTOs = azmoonDA.selectAccepts();
        azmoonDA.close();
        return azmoonTOs;
    }

    public UsersTO getUser(long user_id) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        UsersTO usersTO = usersDA.selectByID(user_id);
        usersDA.close();
        return usersTO;
    }

    public ArrayList<CategoryTO> getCategories() throws Exception
    {
        CategoryDA categoryDA = new CategoryDA();
        ArrayList<CategoryTO> categoryTOs = categoryDA.select();
        categoryDA.close();
        return categoryTOs;
    }

}
