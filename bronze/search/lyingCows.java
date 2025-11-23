import java.util.Scanner;
//fix Bessie’s location, then calculating the number of liars given Bessie’s location.
//Think about the range of necessary possibilities to fix Bessie’s location.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] lessThan = new int[N];
        int[] greaterThan = new int[N];

        int lessThanLength = 0;
        int greaterThanLength = 0;

        int firstCow = 999999;
        int lastCow = -999999;
        String stringEntry;
        int numEntry;

	List<Integer> allPositions = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            stringEntry = sc.next();
            numEntry = sc.nextInt();
            if (stringEntry.contains("L")) {
                lessThan[lessThanLength] = numEntry;
                lessThanLength++;
            }
            else if (stringEntry.contains("G")) {
                greaterThan[greaterThanLength] = numEntry;
                greaterThanLength++;
            }
            if(numEntry < firstCow){firstCow = numEntry;}
            if(numEntry > lastCow){lastCow = numEntry;}

            allPositions.add(numEntry - 1);
            allPositions.add(numEntry);
            allPositions.add(numEntry + 1);

        }
        int minLiers = 999999999;
        int currentLiers = 0;
        for(int location : allPositions){
            for(int i = 0; i < lessThanLength; i++){
		int guess = lessThan[i];
                if(location > guess){
                    currentLiers++;
                }
            }
            for(int i = 0; i < greaterThanLength; i++){
int guess = greaterThan[i];
                if(location < guess){
                    currentLiers++;
                }
            }
            if(currentLiers < minLiers){minLiers=currentLiers;}
            currentLiers = 0;
        }
        System.out.println(minLiers);
    }
}