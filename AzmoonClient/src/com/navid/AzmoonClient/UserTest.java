package com.navid.AzmoonClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.navid.AzmoonClient.data.Data;
import com.navid.AzmoonClient.data.QuestionData;

import java.util.ArrayList;

public class UserTest extends Activity
{
    private ArrayList<QuestionData> questions;
    private int questionNumber;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_test);
        questions = new ArrayList<QuestionData>();
        questionNumber = 0;
        final TextView textCnt = (TextView) findViewById(R.id.textQuesCnt);
        final TextView text = (TextView) findViewById(R.id.textQ_Text);
        final RadioButton rBtnA = (RadioButton) findViewById(R.id.radioButtonA);
        final RadioButton rBtnB = (RadioButton) findViewById(R.id.radioButtonB);
        final RadioButton rBtnC = (RadioButton) findViewById(R.id.radioButtonC);
        final RadioButton rBtnD = (RadioButton) findViewById(R.id.radioButtonD);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        String query = new SignIn().getFromSocket("/et/getQuestions?uid="+ Data.getUser_id()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
        long tmp = 1;
        try
        {
            tmp = Long.parseLong(query);
        } catch (Exception e)
        {
        }
        if (tmp == 0) {
            Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
        } else if (query == "")
        {
            Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
        }else if (tmp == 2)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserTest.this);
            builder.setMessage("شما نمی توانید دوباره امتحان دهید!");
            builder.setCancelable(false);
            builder.setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getBaseContext(), UserMain.class));
                    UserTest.this.finish();
                }
            });
            builder.show();
        }else
        {
            //First time to take the exam
            try
            {
                //Have to setTest first
                String query2 = new SignIn().getFromSocket("/et/setTest?uid="+ Data.getUser_id()+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
                long tmp2 = 1;
                try
                {
                    tmp2 = Long.parseLong(query2);
                } catch (Exception e)
                {
                }
                if (tmp2 == 0) {
                    Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
                } else if (query2 == "")
                {
                    Toast.makeText(getBaseContext(), "داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                }else if(tmp2==1)//setTest Done!
                {
                    Toast.makeText(getBaseContext(), "امتحان شما آغاز گردید.", Toast.LENGTH_LONG).show();
                    String[] category_questions = query.split("////");
                    Data.setCategory_name(category_questions[0]);
                    String[] list_questions = category_questions[1].split(";;");

                    for(String t_q : list_questions)
                    {
                        String[] temp = t_q.split("::");
                        QuestionData questionData = new QuestionData(temp[0],temp[1],temp[2],temp[3],temp[4],Long.parseLong(temp[5]));
                        questions.add(questionData);
                    }
                    //Start of Copy
                    /////////////////Display first question
                    if(questions.size() > 0)
                    {
                        textCnt.setText(questions.size()+" سوال ");
                        text.setText("  #"+(questionNumber+1)+": "+questions.get(questionNumber).getQuestion());
                        rBtnA.setText("  A) " + questions.get(questionNumber).getAnswer1());
                        rBtnB.setText("  B) " + questions.get(questionNumber).getAnswer2());
                        rBtnC.setText("  C) " + questions.get(questionNumber).getAnswer3());
                        rBtnD.setText("  D) " + questions.get(questionNumber).getAnswer4());
                        seekBar.setMax(questions.size());
                        seekBar.setProgress(questionNumber+1);
                    }
                    else
                    {
                        btnNext.setEnabled(false);
                        text.setText("سوالی یافت نشده");
                    }
                    seekBar.setEnabled(false);
                    /////////////////Submit button
                    btnNext.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            /////////////////save current radio buttons
                            if(rBtnA.isChecked())
                            {
                                questions.get(questionNumber).setQ_SEL(1);
                            }else if(rBtnB.isChecked())
                            {
                                questions.get(questionNumber).setQ_SEL(2);
                            }else if(rBtnC.isChecked())
                            {
                                questions.get(questionNumber).setQ_SEL(3);
                            }else if(rBtnD.isChecked())
                            {
                                questions.get(questionNumber).setQ_SEL(4);
                            }

                            /////////////////display next question
                            if(questions.size() > ++questionNumber)
                            {
                                text.setText("  #"+(questionNumber+1)+": "+questions.get(questionNumber).getQuestion());
                                rBtnA.setText("  A) " + questions.get(questionNumber).getAnswer1());
                                rBtnB.setText("  B) " + questions.get(questionNumber).getAnswer2());
                                rBtnC.setText("  C) " + questions.get(questionNumber).getAnswer3());
                                rBtnD.setText("  D) " + questions.get(questionNumber).getAnswer4());
                                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioG);
                                radioGroup.clearCheck();
                                seekBar.setProgress(questionNumber+1);
                            }else
                            /////////////////end of questions
                            {
                                try
                                {
                                    int correct = 0;
                                    int incorrect = 0;
                                    int unsolved = 0;
                                    for(QuestionData QD : questions)
                                    {
                                        if(QD.getQ_SEL() == 0)
                                        {
                                            unsolved++;
                                        }else
                                        {
                                            if(QD.getQ_SEL() == QD.getTrue_answer())
                                                correct++;
                                            else
                                                incorrect++;
                                        }
                                    }
                                    int score = 100*(correct*3-incorrect)/(3*(correct+incorrect+unsolved));
                                    //setResult
                                    String result = score+"/100";
                                    String accept = "";
                                    if(score >= 80)
                                        accept = "YES in " + Data.getCategory_name();
                                    else
                                        accept = "NO in " + Data.getCategory_name();
                                    String query3 = new SignIn().getFromSocket("/et/setResult?uid="+ Data.getUser_id()+"&r="+result+"&a="+accept+"&un="+Data.getUsername()+"&pw="+Data.getPassword());
                                    long tmp3 = -1;
                                    try
                                    {
                                        tmp3 = Long.parseLong(query3);
                                    } catch (Exception e)
                                    {
                                    }
                                    if (tmp3 == 0) {
                                        Toast.makeText(getBaseContext(), "خطا در سیستم...", Toast.LENGTH_LONG).show();
                                    } else if (query3 == "")
                                    {
                                        Toast.makeText(getBaseContext(),"داده ای دریافت نشده...", Toast.LENGTH_LONG).show();
                                    }else if(tmp3==1)//setResult Done!
                                    {
                                        //show score
                                        Toast.makeText(getBaseContext(),"نتیجه شما: "+ result,Toast.LENGTH_SHORT).show();
                                        if(score > 80)
                                            Toast.makeText(getBaseContext(),"تبریک میگوییم، شما پذیرفته شدید."+ result,Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getBaseContext(), SignIn.class));
                                        UserTest.this.finish();
                                    }
                                }catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    });
                    // END OF COPY
                }//end of if(setTest)


            }catch (Exception e)
            {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
