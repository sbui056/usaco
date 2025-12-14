import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.*;
public class Main {

    public static char[] alphabet;

    public static int getFrequency(String str, char letter){
        if(!str.contains(Character.toString(letter))){return 0;}

        return 1+getFrequency(str.substring(0,str.indexOf(letter))+str.substring(str.indexOf(letter)+1,str.length()), letter);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try{
            sc = new Scanner(new File("blocks.in"));
        }catch(Exception e){

        }

        //For each letter, find the combination of sides with the most appearances
        alphabet = new char[27];

        alphabet[1] = 'a';
        alphabet[2] = 'b';
        alphabet[3] = 'c';
        alphabet[4] = 'd';
        alphabet[5] = 'e';
        alphabet[6] = 'f';
        alphabet[7] = 'g';
        alphabet[8] = 'h';
        alphabet[9] = 'i';
        alphabet[10] = 'j';
        alphabet[11] = 'k';
        alphabet[12] = 'l';
        alphabet[13] = 'm';
        alphabet[14] = 'n';
        alphabet[15] = 'o';
        alphabet[16] = 'p';
        alphabet[17] = 'q';
        alphabet[18] = 'r';
        alphabet[19] = 's';
        alphabet[20] = 't';
        alphabet[21] = 'u';
        alphabet[22] = 'v';
        alphabet[23] = 'w';
        alphabet[24] = 'x';
        alphabet[25] = 'y';
        alphabet[26] = 'z';

        int[] letters = new int[27]; //each index is going to be a letter, value is # of apperances


        ArrayList<String[]> whiteBoards = new ArrayList<>();

        int N = sc.nextInt();

        String side1, side2;
        for(int line = 0; line < N; line++){
            side1 = sc.next();
            side2 = sc.next();

            String[] placeholder = new String[2];
            placeholder[0] = side1;
            placeholder[1] = side2;

            whiteBoards.add(placeholder);
        }

        for(int letter = 1; letter <= 26; letter++){//iterate through all letters

            for(String[] sides: whiteBoards){//Each whiteboard for each letter
                letters[letter] = letters[letter] + Math.max(getFrequency(sides[0], alphabet[letter]),getFrequency(sides[1], alphabet[letter]));
            }

        }

        PrintWriter out = new PrintWriter(System.out);

        try{
            out = new PrintWriter(new File("blocks.out"));
        }catch (Exception e){

        }
        for(int letter = 1; letter <= 26; letter++){
            out.println(letters[letter]);
        }
        
        out.close();

    }
}