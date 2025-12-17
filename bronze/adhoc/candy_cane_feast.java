import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        long[] cows = new long[N];

        for(int i = 0; i < N; i++){
            cows[i] = sc.nextInt();
        }

        long originalCowHeight;
        long originalCaneHeight;
        long originalCaneBase;
        for(int caneNum = 0; caneNum < M; caneNum++){ //iterate through each turn
            originalCaneHeight = sc.nextInt();
            originalCaneBase = 0;
            for(int cow = 0; cow < N; cow++){ //each cow gets its turn in order
                originalCowHeight = cows[cow];

                //if the base is the same height as the top or greater than its fully eaten so don't run this any more
               if(originalCaneBase >= originalCaneHeight){break;}

                if(originalCowHeight < originalCaneBase){
                    //Cow can't reach
                    cows[cow] = originalCowHeight;
                }
                else if(originalCowHeight >= originalCaneHeight){
                    //Cow can eat the full cane
                    cows[cow] = originalCowHeight + (originalCaneHeight - originalCaneBase);
                    originalCaneBase = originalCaneHeight;
                }
                else{
                    //Cow eats the cane partially
                    cows[cow] = originalCowHeight + (originalCowHeight-originalCaneBase);
                    originalCaneBase = originalCowHeight;
                }

            }

        }

        for(int cow = 0; cow < cows.length; cow++){
            System.out.println(cows[cow]);
        }

    }
}
