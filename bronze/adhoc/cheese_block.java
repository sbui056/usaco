import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static boolean[][][] space;
    public static int N;

    public static boolean[][] xSolutions;
    public static boolean[][] ySolutions;
    public static boolean[][] zSolutions;

    public static int findOpenLanes(int xpos, int ypos, int zpos){

        int total = 0;
        boolean runIt;

        if(!space[xpos][ypos][zpos]){return 0;} //if the block is full

        //Find Z pos lanes
        if(!zSolutions[xpos][ypos] && space[xpos][ypos][N-1] && space[xpos][ypos][0]){
            runIt=true;
            for(int testz = 0; testz < N; testz++){
                if(!space[xpos][ypos][testz]){runIt = false; break;}
            }
            if(runIt){
                total++;
                zSolutions[xpos][ypos] = true;
            }
        }

        //Find xpos lanes
        if(!xSolutions[ypos][zpos] && space[N-1][ypos][zpos] && space[0][ypos][zpos]){
            runIt=true;
            for(int testx = 0; testx < N; testx++){
                if(!space[testx][ypos][zpos]){runIt = false; break;}
            }
            if(runIt){
                total++;
                xSolutions[ypos][zpos] = true;
            }
        }

        //Find ypos lanes
        if(!ySolutions[xpos][zpos] && space[xpos][N-1][zpos] && space[xpos][0][zpos]) {
            runIt = true;
            for (int testy = 0; testy < N; testy++) {
                if (!space[xpos][testy][zpos]) {runIt = false; break;}
            }
            if (runIt) {
                total++;
                ySolutions[xpos][zpos] = true;
            }
        }
        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int Q = sc.nextInt();

        space = new boolean[N][N][N]; //false is full, true is empty

        xSolutions = new boolean[N][N];
        ySolutions = new boolean[N][N];
        zSolutions = new boolean[N][N];

        int[] printOuts = new int[Q];

        int x,y,z;
        int total = 0;
        for(int line = 0; line < Q; line++){
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();

            space[x][y][z] = true;
            total += findOpenLanes(x,y,z);
            printOuts[line] = total;
        }

        for(int i = 0; i < Q; i++){
            System.out.println(printOuts[i]);
        }

    }
}