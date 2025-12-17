import java.util.Scanner;

public class Main {
    public static int normalRound(int num, int p) {
        return (int)(Math.round(num/(Math.pow(10,p)))*Math.pow(10,p));
    }
    public static int chainRound(int num, int p){
        for(int i = 1; i <=p; i++){
            num = normalRound(num, i);
        }
        return num;
    }

    public static boolean isSame(int num, int p){
        return(normalRound(num, p) == chainRound(num, p));
    }

    public static int tenFind(int num){
        int p = 1;
        int pTotal = 10;
        while(!(pTotal >= num)){
            pTotal = pTotal * 10;
            p++;
        }
        return(p);
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int N;
        int total;

        int start;
        int end;

        int[] totals = new int[T];

        for(int testCase = 0; testCase < T; testCase++){
            N = sc.nextInt();
            total = 0;

            for(int i = 2; i <= N; i++){if(!isSame(i,tenFind(i))){total++;}}
            totals[testCase] = total;
        }

        for(int result = 0; result < T; result++){
            System.out.println(totals[result]);
        }

    }
}