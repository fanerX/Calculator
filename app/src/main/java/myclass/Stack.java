package myclass;

public class Stack {
    String[] data;
    int top;

    public Stack(){
        data=new String[100];
        top=0;
    }

    public void clear(){ //清空栈
        top=0;
    }

    public boolean empty(){ //判断栈空
        return top==0;
    }

    public int getLength(){ //获得栈中元素的个数
        return top;
    }

    public String getTop(){ //获得栈顶元素
        if(top==0)
            return "empty";
        return data[top-1];
    }

    public void push(String e){ //入栈
        data[top++]=e;
    }

    public String pop(){ //出栈
        if(top==0)
            return "empty";
        return data[--top];
    }

    @Override
    public String toString() { //获得栈转成的字符串
        String result="";
        for(int i=0;i<top;i++){
            result+=data[i]+" ";
        }
        return result;
    }
}