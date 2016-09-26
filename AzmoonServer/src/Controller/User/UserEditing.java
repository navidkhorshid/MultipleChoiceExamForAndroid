package Controller.User;

import Controller.Authentication;
import Model.BL.User;
import Model.TO.UsersTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/ue")
public class UserEditing
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/updateUser")
    public String userEdit(@QueryParam("uid") Long user_id,@QueryParam("n") String name,@QueryParam("f") String family,@QueryParam("u") String username,@QueryParam("p") String password,@QueryParam("a") String address,@QueryParam("e") String email,@QueryParam("t") String tel,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                user.updateUser(user_id,name,family,username,password,address,email,tel);
                return "1";  //done
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                return "0";  //error
            }
        }else
            return "0";

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getUser")
    public String getUser(@QueryParam("uid") Long user_id,@QueryParam("un") String user_name,@QueryParam("pw") String pass_word)
    {
        long authenticate;
        authenticate = Long.parseLong(new Authentication().login(user_name,pass_word));
        if(authenticate != 0 && authenticate != -2)
        {
            User user = new User();
            try
            {
                UsersTO usersTO = user.getUser(user_id);
                return usersTO.getName()+"::"+usersTO.getFamily()+"::"+usersTO.getUsername()+"::"+usersTO.getPassword()+"::"+usersTO.getAddress()+"::"+usersTO.getEmail()+"::"+usersTO.getTel();
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                return "0";  //error
            }
        }else
            return "0";

    }
}
