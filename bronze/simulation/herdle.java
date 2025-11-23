import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] correct = new String[3];
        String[] guess = new String[3];
        String guessLine;
        String correctLine;

        String currentLine;

        Character[] alphabet = new Character[26];
        alphabet[0] = 'A';
        alphabet[1] = 'B';
        alphabet[2] = 'C';
        alphabet[3] = 'D';
        alphabet[4] = 'E';
        alphabet[5] = 'F';
        alphabet[6] = 'G';
        alphabet[7] = 'H';
        alphabet[8] = 'I';
        alphabet[9] = 'J';
        alphabet[10] = 'K';
        alphabet[11] = 'L';
        alphabet[12] = 'M';
        alphabet[13] = 'N';
        alphabet[14] = 'O';
        alphabet[15] = 'P';
        alphabet[16] = 'Q';
        alphabet[17] = 'R';
        alphabet[18] = 'S';
        alphabet[19] = 'T';
        alphabet[20] = 'U';
        alphabet[21] = 'V';
        alphabet[22] = 'W';
        alphabet[23] = 'X';
        alphabet[24] = 'Y';
        alphabet[25] = 'Z';

        HashMap<Character,Integer> guessBreeds = new HashMap<>();
        HashMap<Character,Integer> correctBreeds = new HashMap<>();

        HashMap<Character,Integer> alphabetGreens = new HashMap<>();

        for(Character c: alphabet){
            guessBreeds.put(c,0);
            correctBreeds.put(c,0);
            alphabetGreens.put(c,0);
        }

        int yellows = 0;
        int greens = 0;

        for(int lineNum = 0; lineNum < 3; lineNum++){
            currentLine = sc.nextLine();
            correct[lineNum] = currentLine;
        }


        for(int lineNum = 0; lineNum < 3; lineNum++){
            currentLine = sc.nextLine();
            guess[lineNum] = currentLine;
        }

        for(int lineNum = 0; lineNum < 3; lineNum++){
            guessLine = guess[lineNum];
            correctLine = correct[lineNum];
            for(int xpos = 0; xpos < 3; xpos++){//iterate through each line to check for greens and add to species number
                if(guessLine.charAt(xpos) == correctLine.charAt(xpos)){
                    greens++;
                    char ch = correctLine.charAt(xpos);
                    alphabetGreens.put(ch, alphabetGreens.get(ch) + 1);
                }
                guessBreeds.put(guessLine.charAt(xpos),(guessBreeds.get(guessLine.charAt(xpos)))+1);
                //System.out.println(guessBreeds.get(guessLine.charAt(xpos)));
                correctBreeds.put(correctLine.charAt(xpos),(correctBreeds.get(correctLine.charAt(xpos)))+1);
            }
        }

        int guessNum;
        int correctNum;
        int greenNum;
        int currentYellows;
        for(Character c: alphabet){
            guessNum = guessBreeds.get(c);
            correctNum = correctBreeds.get(c);
            greenNum = alphabetGreens.get(c);
            currentYellows = 0;

            while((currentYellows < (correctNum)-greenNum && currentYellows < (guessNum)-greenNum)){
                currentYellows++;
            }

            yellows += currentYellows;
        }


        System.out.println(greens);
        System.out.println(yellows);


    }
}