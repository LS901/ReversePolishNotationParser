import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;

public class polishParser {

    Stack<String> org = new Stack<String>();
    int resultSetIncrementer = 0;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        ArrayList<String> arg = new ArrayList<String>();
        while(s.hasNext()){
            arg.add(s.next());
        }
        System.out.println(new polishParser().processRoot(arg));
    };

    public int processRoot(ArrayList<String> args){

        int result = 0;
        int[] resultSet = processBranches(args);
        boolean firstFlag = true;
        if(org.empty() && firstFlag == true){
            return resultSet[0];
        }else if(org.empty() && firstFlag == false){
            return result;
        }else{
            for(int i = resultSetIncrementer-1; i > -1; i--){
                String op = org.pop();
                if(firstFlag){
                    result = calculate(resultSet[i],resultSet[i+1],op);
                    firstFlag = false;
                }else{
                    result = calculate(resultSet[i],result,op);
                };
            };
        };
        return result;
    };


    public int[] processBranches(ArrayList<String> args){
        org.push(args.get(0));
        int[] resultSet = new int[args.size()];
        int i = 1;
        int y = 0;
        do{

            while(args.get(i).equals("+") || args.get(i).equals("x") || args.get(i).equals("-")){
                org.push(args.get(i));
                i++;

            }
            String op = org.pop();
            resultSet[y] = calculate(Integer.parseInt(args.get(i)),Integer.parseInt(args.get(i+1)),op);
            if(endOfArrayCheck(args,i+2)){ return resultSet; };
            i = i + 2;
            while(!args.get(i).equals("+") && !args.get(i).equals("x") && !args.get(i).equals("-")){
                op = org.pop();
                resultSet[y] = calculate(resultSet[y],Integer.parseInt(args.get(i)),op);
                i++;
                if(endOfArrayCheck(args,i)){ return resultSet; };

            };
            y++;
            incrementer();
        }while(i < args.size());
        return resultSet;
    };

    public void incrementer(){

        resultSetIncrementer++;

    };


    public boolean endOfArrayCheck(ArrayList<String> args, int i){
        if(i == args.size()){
            return true;
        }else{
            return false;
        }
    };


    public int calculate(int x,int y,String op){
        int result=0;
        switch(op){
            case "+":
                result = x+y;
                break;
            case "-":
                result = x-y;
                break;
            case "x":
                result = x*y;
                break;
        };
        return result;
    };
};};