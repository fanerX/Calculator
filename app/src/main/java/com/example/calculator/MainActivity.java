package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import myclass.ToValue;
import myclass.Transformation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[] buttons=new Button[10];
    private TextView[] textViews=new TextView[3];
    private Button clear,back,except,multiply,reduce,add,equal,left,right;
    private ToValue toValue=new ToValue();
    private Transformation transformation=new Transformation();
    private String string="";
    private boolean flag=false;
    private int brackets=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViews[0]=findViewById(R.id.text_0);
        textViews[1]=findViewById(R.id.text_1);
        textViews[2]=findViewById(R.id.text_2);
        InitButton_number();
        InitBotton();



    }

    private void InitButton_number(){
        buttons[0]=findViewById(R.id.number_0);
        buttons[1]=findViewById(R.id.number_1);
        buttons[2]=findViewById(R.id.number_2);
        buttons[3]= findViewById(R.id.number_3);
        buttons[4]= findViewById(R.id.number_4);
        buttons[5]=findViewById(R.id.number_5);
        buttons[6]=findViewById(R.id.number_6);
        buttons[7]=findViewById(R.id.number_7);
        buttons[8]=findViewById(R.id.number_8);
        buttons[9]=findViewById(R.id.number_9);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+0;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+1;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+2;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+3;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+4;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+5;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+6;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+7;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+8;
                textViews[0].setText(string);
                flag=true;
            }
        });
        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = textViews[0].getText().toString()+9;
                textViews[0].setText(string);
                flag=true;
            }
        });
    }
    private void InitBotton(){
        clear=findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews[0].setText("");
                flag=false;
                brackets=0;
            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textViews[0].getText().toString();
                if(!string.equals("")){
                    if(string.charAt(string.length()-1)=='('){
                        brackets--;
                    }else if(string.charAt(string.length()-1)==')'){
                        brackets++;
                    }
                    textViews[0].setText(string.substring(0,string.length()-1));
                    if(string.length()==1||isOperator(string.charAt(string.length()-2)))
                        flag=false;
                    else
                        flag=true;
                }else
                    flag = false;
            }
        });

        except=findViewById(R.id.except);
        except.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag)
                    return;
                string=textViews[0].getText().toString()+'/';
                Log.d("MainActivity", "/" + (int)'/');
                textViews[0].setText(string);
                flag=false;
            }
        });

        multiply=findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag)
                    return;
                string=textViews[0].getText().toString()+'*';
                Log.d("MainActivity", "*"+(int)'*');
                textViews[0].setText(string);
                flag=false;
            }
        });


        reduce=findViewById(R.id.reduce);
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textViews[0].getText().toString();
                if(string.equals("")||string.charAt(string.length()-1)=='('|| flag){
                    string+='-';
                    textViews[0].setText(string);
                    flag=false;
                }
                Log.d("MainActivity", "-"+(int)'-');
            }
        });

        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                    return;
                string=textViews[0].getText().toString()+'+';
                Log.d("MainActivity", "+"+(int)'+');
                textViews[0].setText(string);
                flag=false;
            }
        });

        equal=findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag||brackets!=0){
                    Toast.makeText(MainActivity.this,"表达式错误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                string=textViews[0].getText().toString();
                if(string.equals(""))
                    return;
                String result=transformation.InfixToSuffix(string);
                double value_2=transformation.SuffixExpressionEvaluation(result);
                //result += " = "+transformation.SuffixExpressionEvaluation(result);
                if( Math.abs(value_2-(int)value_2)>0.00001){
                    result+=" = "+value_2;
                } else {
                    result+=" = "+(int)value_2;
                }
                Log.d("ToValue", string);
                double value_0=toValue.toValue(string);
                int value_int= (int)value_0;
                if(Math.abs(value_0-value_int)>=0.00001){
                    string+=" = "+value_0;
                    textViews[0].setText(String.valueOf(value_0));
                }else {
                    string+=" = "+value_int;
                    textViews[0].setText(value_int+"");
                }
                //string+="="+value_0;
                textViews[2].setText(result);
                textViews[1].setText(string);

                flag=true;
            }
        });

        left=findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                string=textViews[0].getText().toString();

                char ch;
                if(string.equals("")){
                    ch =' ';
                }else {
                    ch = string.charAt(string.length()-1);
                }

                if( (ch >= '0'&& ch <='9' )||ch ==')'){
                    if(brackets>0){
                        string+=')';
                        brackets--;
                    }
                }else {
                    string+='(';
                    brackets++;
                }
                Log.d("MainActivity", "("+(int)'(');
                textViews[0].setText(string);
            }
        });

        right=findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                    return;
                string=textViews[0].getText().toString();
                for(int i=(string.length()-1) ; i>0&&!isOperator(string.charAt(i)); i-- ){
                    if(string.charAt(i)=='.'){
                        return;
                    }
                }
                Log.d("MainActivity", ")"+(int)'.');
                string+='.';
                textViews[0].setText(string);
                flag=false;
            }
        });

    }


    private boolean isOperator(char ch){
        return (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='#');
    }

}

