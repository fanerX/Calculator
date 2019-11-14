package myclass;

import android.util.Log;

public class Transformation {
    Stack S;
    public Transformation(){
        S = new Stack();
    }


    public String InfixToSuffix(String string){ //中缀转后缀
        Log.d("InfixToSuffix", string);
        Log.d("cout", "\n\n中缀转后缀计算过程：");
        string+='#';
        char [] data=string.toCharArray();
        char ch;
        char []  result=new char[100];
        int i=0;
        boolean flag =true;
        S.clear();
        S.push("#");
        Log.d("cout", "\n表达式："+string.substring(i));
        Log.d("cout", "运算符栈："+S.toString());
        Log.d("cout", "后缀表达式: "+String.valueOf(result).substring(0,i));
        int j;
        for(j=0;j<data.length;j++){
            if( (data[j]>='0' && data[j]<='9')|| (flag&&data[j]=='-')){
                flag=false;
                result[i++]=data[j++];
                while ((data[j]>='0' && data[j]<='9')||data[j]=='.')
                    result[i++]=data[j++];
                result[i++]=' ';
                j--;
            }else {
                if(data[j]=='('){
                    S.push(data[j]+"");
                    flag=true;
                }else if(data[j]==')'){
                    while ((ch= S.pop().charAt(0)) != '('){
                        result[i++]=ch;
                        result[i++]=' ';
                    }
                    flag=false;
                }else {
                    flag=false;
                    ch = S.getTop().charAt(0);
                    while (ch != '#' && compare(ch,data[j])){
                        result[i++]=S.pop().charAt(0);
                        result[i++]=' ';
                        ch=S.getTop().charAt(0);
                    }
                    S.push(data[j]+"");
                }
            }

            Log.d("cout", "\n表达式："+string.substring(j+1));
            Log.d("cout", "运算符栈："+S.toString());
            Log.d("cout", "后缀表达式: "+String.valueOf(result).substring(0,i));
        }
        return String.valueOf(result,0,i);
    }
    boolean compare(char x1,char x2){ //比较运算符的优先级
        if(x2=='#')
            return true;
        if(x1=='(')
            return false;
        if(x2=='+'||x2=='-'){
            return true;
        }else{
            if(x1=='*'||x1=='/')
                return true;
            else
                return false;
        }
    }

    public double SuffixExpressionEvaluation(String string){ //后缀表达式求值
        string+=" #";
        char [] data=string.toCharArray();
        S.clear();
        Log.d("Suffix", "begin"+S.getLength());
        int i=0,begin,length;
        double x,y;
        Log.d("cout", "\n\n后缀表达式计算过程: ");
        Log.d("cout", "表达式: "+string.substring(i));
        Log.d("cout", "操作数和运算符结果栈: "+S.toString());

        while (data[i]!='#'){
            if((!isOperator(data[i])&&data[i]!=' ')||(data[i]=='-'&& data[i+1] != ' ') ){
                begin=i++;
                length=1;
                while ((data[i]>='0' && data[i]<='9')||data[i]=='.'){
                    length++;
                    i++;
                }
                Log.d("Suffix", String.valueOf(data,begin,length)+" data[i]:"+"<"+data[i]+">"+i);
                S.push(String.valueOf(data,begin,length));
            }else if(isOperator(data[i])){
                y=Double.parseDouble(S.pop());
                x=Double.parseDouble(S.pop());
                Log.d("Suffix", (Calculation(x,data[i],y)+" data[i]:"+"<"+data[i]+">"+i));
                S.push(String.valueOf(Calculation(x,data[i],y)));
            }
            i++;
            //Log.d("Suffix", S.toString());
            Log.d("cout", "表达式: "+string.substring(i));
            Log.d("cout", "操作数和运算符结果栈: "+S.toString());
        }
        Log.d("Suffix", S.toString()+S.getLength());
        return Double.parseDouble(S.pop());
    }

    double Calculation(double x,char op,double y){ //计算
        switch(op){
            case '+':
                return x+y;
            case '-':
                return x-y;
            case '*':
                return x*y;
            case '/':
                if(y == 0)
                    return 0;
                return x/y;
            default:
                return 0;
        }
    }//Calculation

    private boolean isOperator(char ch){ //判断是否为运算符
        return (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='#');
    }

}