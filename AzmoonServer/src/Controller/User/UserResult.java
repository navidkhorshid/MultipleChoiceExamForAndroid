package Controller.User;

import Controller.Authentication;
import Model.BL.User;
import Model.TO.AzmoonTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/ures")
public class UserResult
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/info")
    public String getResult(@QueryParam("uid") long user_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                AzmoonTO azmoonTO = user.getAzmoon(user_id);
                return azmoonTO.getResult()+"::"+azmoonTO.getAccept();
            }catch (Exception e)
            {
                return "0";  //error
            }
        }else
            return "0";

    }

}
