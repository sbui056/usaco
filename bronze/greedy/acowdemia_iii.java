/*

Look at every single grass
See if it can be used
Edge case: more optimal in one way or another to get the max number of cow friends
GREEDY: form a pair when possible
The pair that we use is the cows opposite of each other (if possible)
2 cows is special case: consider that opposite sides (if possible) first
This is because l-shaped cows could be matched to another grass
3 or 4 cows means a pair is possible (guaranteed that opposite cows exist)

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    /**
     * Helper class to represent coordinates (x, y) that can be reliably used as 
     * keys in a HashMap because Vector correctly implements hashCode() and equals().
     */
    private static Vector<Integer> createCoords(int x, int y) {
        // Using a Vector of Integers for the coordinates
        Vector<Integer> coords = new Vector<>();
        coords.add(x); // x is index 0
        coords.add(y); // y is index 1
        return coords;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read dimensions (Y is height, X is width)
        int Y = sc.nextInt();
        int X = sc.nextInt();

        // pen[x][y] stores the grid content
        Character[][] pen = new Character[X][Y];

        // HashMap to store friendships: Cow_Coord -> List of Cow_Coords they are friends with
        // Key is a Vector<Integer> (x, y) for the cow's position.
        HashMap<Vector<Integer>, ArrayList<Vector<Integer>>> friendships = new HashMap<>();

        // 1. Read the grid and initialize the HashMap keys for every position
        for(int ypos = 0; ypos < Y; ypos++){
            String line = sc.next();
            for(int xpos = 0; xpos < X; xpos++){
                pen[xpos][ypos] = line.charAt(xpos);
                // Initialize every position as a key in the friendship map
                friendships.put(createCoords(xpos, ypos), new ArrayList<>());
            }
        }

        int friendCount = 0;

        // 2. Iterate through the grid to find Grass patches ('G') and establish friendships
        for(int ypos = 0; ypos < Y; ypos++){
            for(int xpos = 0; xpos < X; xpos++){
                
                if (pen[xpos][ypos] == 'G'){ // If it's a grass patch
                    
                    // List to hold the coordinates of Cows adjacent to this grass patch
                    ArrayList<Vector<Integer>> adjacentCows = new ArrayList<>();

                    // Helper array for directional offsets (dx, dy)
                    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

                    for (int[] dir : directions) {
                        int nx = xpos + dir[0];
                        int ny = ypos + dir[1];

                        // Check bounds and if the adjacent cell is a Cow ('C')
                        if (nx >= 0 && nx < X && ny >= 0 && ny < Y && pen[nx][ny] == 'C') {
                            adjacentCows.add(createCoords(nx, ny));
                        }
                    }
                    
                    int cow_count = adjacentCows.size();

                    if (cow_count >= 2) {
                        // Case 1: Three or more cows adjacent to this grass patch
                        if (cow_count >= 3) {
                            friendCount++;
                        }
                        
                        // Case 2: Exactly two cows adjacent to this grass patch (New friendship rule)
                        if (cow_count == 2) {
                            Vector<Integer> cowA = adjacentCows.get(0);
                            Vector<Integer> cowB = adjacentCows.get(1);
                            
                            // Check if cowA already lists cowB as a friend
                            // Since we ensure both directions are recorded (A->B and B->A), 
                            // we only need to check one side to see if the pair exists.
                            if (!friendships.get(cowA).contains(cowB)) {
                                // Establish friendship (A <-> B)
                                friendships.get(cowA).add(cowB);
                                friendships.get(cowB).add(cowA);
                                friendCount++;
                            }
                            // Else: They already made friends, so we don't increment friendCount
                        }
                    }
                }
            }
        }

        System.out.println(friendCount);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int Y = sc.nextInt();
        int X = sc.nextInt();

        Character[][] pen = new Character[X][Y];


        HashMap<int[],ArrayList<int[]>> friendships = new HashMap<>();

        String line;
        int[] placeholder = new int[2];
        ArrayList<int[]> blankFriends = new ArrayList<>();
        for(int ypos = 0; ypos < Y; ypos++){
            line = sc.next();
            for(int xpos = 0; xpos < X; xpos++){
                pen[xpos][ypos] = line.charAt(xpos);
                placeholder[0] = xpos;
                placeholder[1] = ypos;
                friendships.put(placeholder, blankFriends);
                //System.out.println(line.charAt(xpos));
            }
        }

        ArrayList<int[]> localCows = new ArrayList<>();
        ArrayList<int[]> cowFriendsPlaceholder = new ArrayList<>();
        int[] placeholderCow = new int[2];

        boolean left,right,up,down;

        int friendCount = 0;


        for(int ypos = 0; ypos < Y; ypos++){
            for(int xpos = 0; xpos < X; xpos++){
                left = false;
                right = false;
                up = false;
                down = false;
                if (pen[xpos][ypos] == 'G'){ //If its a grass
                    localCows.clear();

                    if(xpos >= 1 && pen[xpos-1][ypos] == 'C'){ //Check to the left
                        placeholderCow[0] = xpos-1;
                        placeholderCow[1] = ypos;
                        localCows.add(placeholderCow);
                        left = true;
                    }

                    if(ypos >= 1 && pen[xpos][ypos-1] == 'C'){ //Check above
                        placeholderCow[0] = xpos;
                        placeholderCow[1] = ypos-1;
                        localCows.add(placeholderCow);
                        up = true;
                    }

                    if(xpos < X - 1 && pen[xpos+1][ypos] == 'C'){ //Check to the right
                        placeholderCow[0] = xpos+1;
                        placeholderCow[1] = ypos;
                        localCows.add(placeholderCow);
                        right = true;
                    }

                    if(ypos < Y - 1 && pen[xpos][ypos+1] == 'C'){ //Check below
                        placeholderCow[0] = xpos;
                        placeholderCow[1] = ypos+1;
                        localCows.add(placeholderCow);
                        down = true;
                    }

                    if(localCows.size() == 2){
                        System.out.println("Searching a 2 cow grass...");
                        if(friendships.containsKey(localCows.get(0))){
                            System.out.println("The grass contains a popular cow...");
                            if(!friendships.get(localCows.get(0)).contains(localCows.get(1))){
                                System.out.println("The popular cow has made another friend.");
                                cowFriendsPlaceholder.clear();
                                cowFriendsPlaceholder.add(localCows.get(1));
                                friendships.put(localCows.get(0),cowFriendsPlaceholder);
                                cowFriendsPlaceholder.clear();
                                cowFriendsPlaceholder.add(localCows.get(0));
                                friendships.put(localCows.get(1),cowFriendsPlaceholder);
                                friendCount++;
                            }
                            else{
                                System.out.println("They already made friends.");
                            }
                        }
                        else {
                            System.out.println("The grass only had never before seen cows");
                            cowFriendsPlaceholder.clear();
                            cowFriendsPlaceholder.add(localCows.get(1));
                            friendships.put(localCows.get(0), cowFriendsPlaceholder);
                            cowFriendsPlaceholder.clear();
                            cowFriendsPlaceholder.add(localCows.get(0));
                            friendships.put(localCows.get(1), cowFriendsPlaceholder);
                            friendCount++;
                        }

                    }
                    else if(localCows.size() >= 3){
                        friendCount ++;
                        //System.out.println(localCows.size());
                    }

                }
            }
        }
        System.out.println(friendCount);
        /*
        for(int ypos = 0; ypos < Y; ypos++){
            for(int xpos = 0; xpos < X; xpos++){
                System.out.print(pen[xpos][ypos] + " ");
            }
            System.out.println();
        }
         */


    }
}