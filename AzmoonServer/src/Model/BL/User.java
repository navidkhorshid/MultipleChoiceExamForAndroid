package Model.BL;

import Model.DA.QuestionDA;
import Model.DA.UsersDA;
import Model.DA.AzmoonDA;
import Model.DA.CategoryDA;
import Model.TO.AzmoonTO;
import Model.TO.UsersTO;
import Model.TO.QuestionTO;
import Model.TO.CategoryTO;

import java.util.ArrayList;
import java.util.Date;

public class User
{
    //Authorization
    public UsersTO getUser(String username, String password) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        UsersTO usersTO = usersDA.selectByUP(username, password);
        usersDA.close();
        return usersTO;
    }
    //UserRegistration
    public void setUser(String name, String family, String username, String password, String address, String email, String tel) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        UsersTO usersTO = new UsersTO();

        usersTO.setId(usersDA.selectCount()+1);
        usersTO.setName(name);
        usersTO.setFamily(family);
        usersTO.setUsername(username);
        usersTO.setPassword(password);
        usersTO.setAddress(address);
        usersTO.setEmail(email);
        usersTO.setTel(tel);

        usersDA.insert(usersTO);
        usersDA.close();
    }
    //Edit User
    public void updateUser(long user_id, String name, String family, String username, String password, String address, String email, String tel) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        UsersTO usersTO = new UsersTO();

        usersTO.setId(user_id);
        usersTO.setName(name);
        usersTO.setFamily(family);
        usersTO.setUsername(username);
        usersTO.setPassword(password);
        usersTO.setAddress(address);
        usersTO.setEmail(email);
        usersTO.setTel(tel);

        usersDA.update(usersTO);
        usersDA.close();
    }

    public UsersTO getUser(long user_id) throws Exception
    {
        UsersDA usersDA = new UsersDA();
        UsersTO usersTO = usersDA.selectByID(user_id);
        usersDA.close();
        return usersTO;
    }

    //giving test
    public AzmoonTO getAzmoon(long user_id) throws Exception //ID for checking 1 time, others for results of exam
    {
        //+Results of exam
        AzmoonDA azmoonDA = new AzmoonDA();
        AzmoonTO azmoonTO = azmoonDA.selectByUser(user_id);
        azmoonDA.close();
        return azmoonTO;
    }

    public ArrayList<CategoryTO> getCategories() throws Exception
    {
        CategoryDA categoryDA = new CategoryDA();
        ArrayList<CategoryTO> categoryTOs = categoryDA.select();
        categoryDA.close();
        return categoryTOs;
    }

    public ArrayList<QuestionTO> getQuestions(long category_id) throws Exception
    {
        QuestionDA questionDA = new QuestionDA();
        ArrayList<QuestionTO> questionTOs = questionDA.selectByCategory(category_id);
        questionDA.close();
        return questionTOs;
    }

    public void setAzmoon(long user_id) throws Exception
    {
        AzmoonDA azmoonDA = new AzmoonDA();
        AzmoonTO azmoonTO = new AzmoonTO();

        azmoonTO.setId(azmoonDA.selectCount()+1);
        azmoonTO.setUserid(user_id);

        azmoonDA.insert(azmoonTO);
        azmoonDA.close();
    }

    public void setAzmoonDetails(long user_id, String result, String accept) throws Exception
    {
        AzmoonDA azmoonDA = new AzmoonDA();
        AzmoonTO azmoonTO = new AzmoonTO();

        azmoonTO.setUserid(user_id);
        azmoonTO.setResult(result);
        azmoonTO.setAccept(accept);

        azmoonDA.updateByUserId(azmoonTO);
        azmoonDA.close();
    }


}
