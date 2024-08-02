package com.example.tohand;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result, solution;
    MaterialButton buttonC, buttonOpen, buttonClose;
    MaterialButton buttondiv, buttonmul, buttonadd, buttonsub, buttonmod,buttondepo,buttondue,buttonequal;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonAC,buttonDot,buttonres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result_tv);
        solution = findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.button_c);
        assignId(buttonOpen,R.id.button_open_bracket);
        assignId(buttonClose,R.id.button_close_bracket);
        assignId(buttondiv,R.id.button_divide);
        assignId(buttonmul,R.id.button_multiply);
        assignId(buttonadd,R.id.button_adition);
        assignId(buttonsub,R.id.button_subtract);
        assignId(buttonmod,R.id.button_percentage);
        assignId(buttondepo,R.id.button_deposite);
        assignId(buttondue,R.id.button_due);
        assignId(buttonequal,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonDot,R.id.button_dot);

    }
    void assignId(MaterialButton btn, int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttontext = button.getText().toString();
        String dataToCalculate = solution.getText().toString();
        switch (buttontext) {
            case "AC":
                solution.setText("");
                result.setText("0");
                break;
            case "=":
                if (!solution.getText().toString().equals("")) {
                    String finalResult = getResult(solution.getText().toString());
                    if (!finalResult.equals("Error")) {
                        solution.setText(result.getText());
                        result.setText(finalResult);
                    } else {
                        Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case "DP":

                //saveData(phonenumber,finalResult,null);
                Intent intent;
                intent = new Intent(MainActivity.this, deposite.class);
                startActivity(intent);
                int finalResult = Integer.parseInt(getResult(solution.getText().toString()));
                intent.putExtra("deposite",finalResult);
                Log.e("Myapp","value " + finalResult);
                break;
            case "DU" :
                int finalResult1 = Integer.parseInt(getResult(solution.getText().toString()));
                Intent intent1;
                intent1 = new Intent(MainActivity.this, dueActivity.class);
                startActivity(intent1);

                intent1.putExtra("due",finalResult1);
                break;
            case "C":
                if (solution.getText().toString().length() > 0) {
                    solution.setText(solution.getText().toString().substring(0, solution.getText().toString().length() - 1));
                }
                break;
            default:
                solution.setText(solution.getText().toString() + buttontext);
                break;
        }
    }

    String getResult(String data)
    {
        try{
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
                if(finalResult.endsWith(".0"))
                {
                    finalResult = finalResult.replace(".0","");
                }
                return finalResult;
            }
        catch (Exception e)
        {
            return "Error";
        }
    }

}