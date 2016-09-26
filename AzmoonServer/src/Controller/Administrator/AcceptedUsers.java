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

@Path("/au")
public class AcceptedUsers
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getAcceptedUsers")
    public String getUsers(@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate == -2)
        {
            Administrator administrator = new Administrator();
            try
            {
                String data = "";
                ArrayList<AzmoonTO> azmoonTOs = administrator.getAzmoonsAccepted();
                for(AzmoonTO azmoonTO : azmoonTOs)
                {
                    data += administrator.getUser(azmoonTO.getUserid()).getId()+"::"+administrator.getUser(azmoonTO.getUserid()).getName()+"::"+administrator.getUser(azmoonTO.getUserid()).getFamily()+";;";
                }
                return data;
            }catch (Exception e)
            {
                return "0";
            }
        }return "0";
    }




}
