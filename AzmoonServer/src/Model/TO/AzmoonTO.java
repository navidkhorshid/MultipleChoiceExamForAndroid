package Model.TO;

public class AzmoonTO
{
    private long azmoonid;
    private long userid;
    private String natije;
    private String accept;

    public long getId()
    {
        return azmoonid;
    }

    public void setId(long id)
    {
        this.azmoonid = id;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long user_id)
    {
        this.userid = user_id;
    }

    public String getResult()
    {
        return natije;
    }

    public void setResult(String result)
    {
        this.natije = result;
    }

    public String getAccept()
    {
        return accept;
    }

    public void setAccept(String accept)
    {
        this.accept = accept;
    }

}
