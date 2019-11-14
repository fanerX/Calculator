package myclass;

import android.util.Log;

public class ToValue {
    private Stack OPND;
    private Stack OPTR;

    public ToValue(){
        OPND=new Stack();
        OPTR=new Stack();
    }

    public double toValue(String string){ //中缀表达式求值
        OPND.clear();
        OPTR.clear();

        OPTR.push("#");
        string+='#';
        int begin,length;
        char[] str=string.toCharArray();
        double a,b;
        int i=0;
        char theta,ch;
        boolean flag=true;
        Log.d("cout", "中缀表达式计算过程：");
        Log.d("cout", "表达式："+string.substring(i));
        Log.d("cout", "操作数栈："+OPND.toString());
        Log.d("cout", "运算符栈："+OPTR.toString());
        while (str[i]!='#'||!OPTR.getTop().equals("#")){
            if(!isOperator(str[i])||(flag&&str[i]=='-')){
                flag=false;
                begin=i++;
                length=1;
                while (!isOperator(str[i])){
                    length++;
                    i++;
                }
                OPND.push(String.valueOf(str,begin,length));

                Log.d("cout", "表达式："+string.substring(i));
                Log.d("cout", "操作数栈："+OPND.toString());
                Log.d("cout", "运算符栈："+OPTR.toString());

                continue;
            }
            ch=Priority(OPTR.getTop().charAt(0),str[i]);

            if(str[i]=='(')
                flag=true;
            else
                flag=false;

            switch (ch){
                case '<':
                    OPTR.push(str[i++]+"");
                    break;
                case'>':
                    theta=OPTR.pop().charAt(0);
                    b=Double.parseDouble(OPND.pop());
                    a=Double.parseDouble(OPND.pop());
                    OPND.push(String.valueOf(Operate(a,theta,b)));
                    break;
                case '=':
                    OPTR.pop();
                    i++;
                    break;
            }

            Log.d("cout", "表达式："+string.substring(i));
            Log.d("cout", "操作数栈："+OPND.toString());
            Log.d("cout", "运算符栈："+OPTR.toString());

        }
        return Double.parseDouble(OPND.getTop());
    }

    private boolean isOperator(char ch){ //判断ch是否为运算符
        return (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='#');
    }

    private double Operate(double a, char theta, double b){ //计算
        if(theta=='+')
            return a+b;
        else if(theta=='-')
            return a-b;
        else if(theta=='*')
            return a*b;
        else if(theta=='/') {
            if(b == 0)
                return 0;
            return a/b;
        }
        else
            return -1;
    }

    char Priority(char x,char y){ //比较运算符优先级
        if(  (x=='('&&y==')')|| (x=='#' && y == '#')  )
            return '=';
        if(x=='#')
            return '<';
        if(y=='('|| x=='(')
            return '<';
        if(x == ')' || y == ')'||y == '#')
            return '>';
        if(y == '+'||y == '-')
            return '>';
        if(x=='+'||x=='-')
            return '<';
        else
            return '>';
    }

}