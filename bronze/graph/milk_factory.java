import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static HashMap<Integer, ArrayList<Integer>> map;

    public static boolean solve(int position, int target, ArrayList<Integer> movesSoFar){

        if(position == target) { //base case
            return true;
        }

        if(!map.containsKey(position)){return false;} //dead end

        ArrayList<Integer> nextMoves = map.get(position);

        if(movesSoFar.contains(position)){return false;} //already been here


        if(nextMoves.isEmpty()){return false;} //dead end

        ArrayList<Integer> new_movesSoFar = new ArrayList<>();
        for(int move: movesSoFar){new_movesSoFar.add(move);}

        //go to next positions

        boolean check = false;
        boolean placeholderBool;

        for(int move: nextMoves){
            new_movesSoFar.add(move);
            placeholderBool = solve(move, target, movesSoFar);
            if(placeholderBool){
                check = true;
                break;
            }
            new_movesSoFar.remove(new_movesSoFar.size()-1);
        }
        
        return check;

    }

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            try{
                sc = new Scanner(new File("factory.in"));

            }catch (Exception e){

            }

            int N = sc.nextInt();

            map = new HashMap<>();


            int walkWayNum = 0;

            //setup map
            int a = 0;
            int b = 0;
            for(int i = 0; i < N -1; i++) {
                a = sc.nextInt();
                b = sc.nextInt();

                if(a > walkWayNum){walkWayNum = a;}
                if(b > walkWayNum){walkWayNum = b;}

                ArrayList<Integer> placeholder = new ArrayList<>();
                if(map.containsKey(a)) {
                    for(int adder: map.get(a)) {
                        placeholder.add(adder);
                    }
                    placeholder.add(b);
                    map.replace(a, placeholder);
                }
                else {
                    placeholder.add(b);
                    map.put(a,placeholder);
                }
            }

            PrintWriter out = new PrintWriter(System.out);
            try{
                out = new PrintWriter(new File("factory.out"));
            }catch (Exception e){
            }

            boolean getsThere;
            boolean done = false;
            boolean current;
            for(int station = 1; station <= walkWayNum; station++){ //for each station

                getsThere = true;
                for(int startStation = 1; startStation <= walkWayNum; startStation++){ //iterate through all stations

                    if(startStation != station){
                        current = solve(startStation, station, new ArrayList<Integer>());

                        //System.out.println(station);
                        //System.out.println(startStation);
                        //System.out.println(current);
                        //System.out.println();
                        if(!current){
                            getsThere = false;
                        }
                    }

                }
                if(getsThere) {
                    done = true;
                    out.println(station);
                    break;
                }
            }
            if(!done){out.println(-1);}
            out.close();
        }
}