package com.navid.AzmoonClient.data;

public class QuestionData
{
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private long true_answer;
    private long Q_SEL;

    public QuestionData(String question, String answer1, String answer2, String answer3, String answer4, long true_answer)
    {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.true_answer = true_answer;
        Q_SEL = 0;
    }

    public String getQuestion()
    {
        return question;
    }
    public String getAnswer1()
    {
        return answer1;
    }
    public String getAnswer2()
    {
        return answer2;
    }
    public String getAnswer3()
    {
        return answer3;
    }
    public String getAnswer4()
    {
        return answer4;
    }
    public long getTrue_answer()
    {
        return true_answer;
    }
    public long getQ_SEL()
    {
        return Q_SEL;
    }
    public void setQ_SEL(long selected)
    {
        Q_SEL = selected;
    }
}
