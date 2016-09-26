package Controller.Administrator;


import Controller.Authentication;
import Model.BL.Administrator;
import Model.TO.CategoryTO;
import Model.TO.QuestionTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/qe")
public class QuestionEditing
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/set")
    public String insert(@QueryParam("cid") long category_id,@QueryParam("q") String question,@QueryParam("a") String answer1,@QueryParam("b") String answer2,@QueryParam("c") String answer3,@QueryParam("d") String answer4,@QueryParam("ta") long true_answer,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                administrator.setQuestion(category_id,question,answer1,answer2,answer3,answer4,true_answer);
                return "1";
            }catch (Exception e)
            {
                return "0";
            }
        }else return "0";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/remove")
    public String delete(@QueryParam("qid") long question_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                administrator.removeQuestion(question_id);
                return "1";
            }catch (Exception e)
            {
                return "0";
            }
        }else return "0";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getQuestions")
    public String getQuestions(@QueryParam("cid") long category_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                String data = "";
                ArrayList<QuestionTO> questionTOs = administrator.getQuestions(category_id);
                for (QuestionTO qt : questionTOs)
                {
                    data += qt.getId()+"::"+qt.getQuestion()+"::"+qt.getAnswer1()+"::"+qt.getAnswer2()+"::"+qt.getAnswer3()+"::"+qt.getAnswer4()+"::"+qt.getTrueanswer()+";;";
                }
                return data;
            }catch (Exception e)
            {
                return "0";
            }
        }else return "0";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getCategories")
    public String getCategories(@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                String data = "";
                ArrayList<CategoryTO> categoryTOs = administrator.getCategories();
                for (CategoryTO ct : categoryTOs)
                {
                    data += ct.getId()+"::"+ct.getName()+";;";
                }
                return data;
            }catch (Exception e)
            {
                return "0";
            }
        }return "0";
    }

}
