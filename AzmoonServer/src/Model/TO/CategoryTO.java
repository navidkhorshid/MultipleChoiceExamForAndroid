package Model.TO;

public class CategoryTO
{
    private long categoryid;
    private String name;

    public long getId()
    {
        return categoryid;
    }

    public void setId(long id)
    {
        this.categoryid = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
