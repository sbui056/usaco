import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] pastureCows = new int[1002][1002];
        int[][] pastureSurround = new int[1002][1002];

        int comfortableCows = 0;
        int prevComfortableCows = 0;
        int changeComfortableCows = 0;
        int surround, cowX, cowY;

        for(int i = 0; i < N; i++){
            changeComfortableCows = 0;
            prevComfortableCows = comfortableCows;
            cowX = sc.nextInt()+1;
            cowY = sc.nextInt()+1;
            pastureCows[cowX][cowY] = 1;
            pastureSurround[cowX][cowY] = pastureCows[cowX+1][cowY] + pastureCows[cowX-1][cowY] + pastureCows[cowX][cowY+1] + pastureCows[cowX][cowY-1];
            if(pastureSurround[cowX][cowY] == 3){changeComfortableCows++;}

            pastureSurround[cowX+1][cowY] ++;
            if(pastureSurround[cowX+1][cowY] == 4 && pastureCows[cowX+1][cowY] == 1){changeComfortableCows--;}
            else if(pastureSurround[cowX+1][cowY] == 3 && pastureCows[cowX+1][cowY] == 1){changeComfortableCows++;}
            pastureSurround[cowX-1][cowY] ++;
            if(pastureSurround[cowX-1][cowY] == 4 && pastureCows[cowX-1][cowY] == 1){changeComfortableCows--;}
            else if(pastureSurround[cowX-1][cowY] == 3 && pastureCows[cowX-1][cowY] == 1){changeComfortableCows++;}
            pastureSurround[cowX][cowY+1] ++;
            if(pastureSurround[cowX][cowY+1] == 4 && pastureCows[cowX][cowY+1] == 1){changeComfortableCows--;}
            else if(pastureSurround[cowX][cowY+1] == 3 && pastureCows[cowX][cowY+1] == 1){changeComfortableCows++;}
            pastureSurround[cowX][cowY-1] ++;
            if(pastureSurround[cowX][cowY-1] == 4 && pastureCows[cowX][cowY-1] == 1){changeComfortableCows--;}
            else if(pastureSurround[cowX][cowY-1] == 3 && pastureCows[cowX][cowY-1] == 1){changeComfortableCows++;}

            comfortableCows += changeComfortableCows;
            System.out.println(comfortableCows);
        }
    }
}