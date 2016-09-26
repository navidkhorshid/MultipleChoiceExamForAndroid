package Model.TO;

public class QuestionTO
{
    private long questionid;
    private long categoryid;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private long trueanswer;

    public long getId()
    {
        return questionid;
    }

    public void setId(long id)
    {
        this.questionid = id;
    }

    public long getCategoryid()
    {
        return categoryid;
    }

    public void setCategoryid(long category_id)
    {
        this.categoryid = category_id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getAnswer1()
    {
        return answer1;
    }

    public void setAnswer1 (String answer_a)
    {
        this.answer1 = answer_a;
    }

    public String getAnswer2()
    {
        return answer2;
    }

    public void setAnswer2 (String answer_b)
    {
        this.answer2 = answer_b;
    }

    public String getAnswer3()
    {
        return answer3;
    }

    public void setAnswer3 (String answer_c)
    {
        this.answer3 = answer_c;
    }

    public String getAnswer4()
    {
        return answer4;
    }

    public void setAnswer4 (String answer_d)
    {
        this.answer4 = answer_d;
    }

    public long getTrueanswer()
    {
        return trueanswer;
    }

    public void setTrueanswer(long true_answer)
    {
        this.trueanswer = true_answer;
    }


}
