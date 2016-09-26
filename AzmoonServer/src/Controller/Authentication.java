package Controller;

import Model.BL.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
public class Authentication {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public String login(@QueryParam("user") String username, @QueryParam("pass") String password)
    {
        if( username.compareTo("admin")==0 && password.compareTo("admin")==0 )
        {
            return "-2";//admin
        }else
        {
            User user = new User();
            long user_ID = 0;//error
            try
            {
                user_ID = user.getUser(username,password).getId();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            return Long.toString(user_ID);
        }
    }

}
