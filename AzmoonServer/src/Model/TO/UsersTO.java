package Model.TO;

public class UsersTO
{
    private long userid;
    private String name;
    private String family;
    private String username;
    private String password;
    private String shsh;
    private String email;
    private String tel;

    public long getId()
    {
        return userid;
    }

    public void setId(long id)
    {
        this.userid = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFamily()
    {
        return family;
    }

    public void setFamily(String family)
    {
        this.family = family;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAddress()
    {
        return shsh;
    }

    public void setAddress(String address)
    {
        this.shsh = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String telephone)
    {
        this.tel = telephone;
    }

}
