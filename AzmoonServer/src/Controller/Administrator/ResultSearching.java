package Controller.Administrator;

import Controller.Authentication;
import Model.BL.Administrator;
import Model.TO.AzmoonTO;
import Model.TO.UsersTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/rs")
public class ResultSearching
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getUserAzmoon")
    public String getUserAzmoon(@QueryParam("sp") String search_phrase,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                ArrayList<UsersTO> usersTOs = administrator.getUsers(search_phrase);
                String data = "";
                for (UsersTO usersTO : usersTOs)
                {
                    data += usersTO.getId()+"::"+usersTO.getName()+"::"+usersTO.getFamily()+"::"+administrator.getAzmoon(usersTO.getId()).getResult()+"::"+administrator.getAzmoon(usersTO.getId()).getAccept()+";;";
                }
                return data;
            }catch (Exception e)
            {
                return "0";
            }
        }else return "0";
    }
}
