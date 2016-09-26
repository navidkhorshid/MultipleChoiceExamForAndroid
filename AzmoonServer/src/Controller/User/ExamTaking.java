package Controller.User;

import Controller.Authentication;
import Model.BL.User;
import Model.TO.AzmoonTO;
import Model.TO.CategoryTO;
import Model.TO.QuestionTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Random;

@Path("/et")
public class ExamTaking
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getQuestions")
    public String getQuestions(@QueryParam("uid") long user_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                AzmoonTO azmoonTO = user.getAzmoon(user_id);
                if (azmoonTO.getId()!=0)
                {
                    return "2";//2nd time and cannot take the exam
                }
                //random category
                ArrayList<CategoryTO> categoryTOs = user.getCategories();
                Random generator = new Random();
                int roll = generator.nextInt(categoryTOs.size());
                long cat_id_random = categoryTOs.get(roll).getId();
                //quiz of cat_id
                ArrayList<QuestionTO> questionTOs = user.getQuestions(cat_id_random);
                String data = categoryTOs.get(roll).getName()+"////";
                for (QuestionTO qt : questionTOs)
                {
                    data+= qt.getQuestion()+"::"+qt.getAnswer1()+"::"+qt.getAnswer2()+"::"+qt.getAnswer3()+"::"+qt.getAnswer4()+"::"+qt.getTrueanswer()+";;";
                }
                return data;

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                return "0";
            }
        }else return "0";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/setTest")
    public String setTest(@QueryParam("uid") long user_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                user.setAzmoon(user_id);
                return "1";//done
            }catch (Exception e)
            {
                return "0";//error
            }
        }else return "0";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/setResult")
    public String setResult(@QueryParam("uid") long user_id, @QueryParam("r") String result,@QueryParam("a") String accept,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                user.setAzmoonDetails(user_id,result,accept);
                return "1";//done
            }catch (Exception e)
            {
                return "0";//error
            }
        }else return "0";
    }

}

