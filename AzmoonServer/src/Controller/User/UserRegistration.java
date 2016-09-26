package Controller.User;

import Model.BL.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/ur")
public class UserRegistration
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/new")
    public String newUser(@QueryParam("n") String name,@QueryParam("f") String family,@QueryParam("u") String username,@QueryParam("p") String password,@QueryParam("a") String address,@QueryParam("e") String email,@QueryParam("t") String tel)
    {
        User user = new User();
        try
        {
            user.setUser(name,family,username,password,address,email,tel);
            return "1";  //done
        }catch (Exception e)
        {
            return "0";  //error
        }

    }
}
