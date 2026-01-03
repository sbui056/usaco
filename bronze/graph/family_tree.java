/*
hashmap stores cow->parent
considers going up this path

you want to check common ancestor!
if no common ancestor -> NOT RELATED
check relationship by distance to common ancestor
- sibling is they have same mother (both have parent as common ancestor)
- consider the aunt and mother, then distances from each determine the number of greats
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static HashMap<String, String> tree;
    public static ArrayList<String> directDecendents = new ArrayList<>();

    public static int climb(String point, String find){
        directDecendents.add(point);
        if(point.equals(find)){return 1;}
        if(!tree.containsKey(point)){return -1*Integer.MAX_VALUE;}

        return climb(tree.get(point), find) + 1;
    }
    public static int auntClimb(String point, String find){
        if(point.equals(find)){return 0;}
        if(!tree.containsKey(point)){return -1*Integer.MAX_VALUE;}

        return auntClimb(tree.get(point), find) + 1;
    }
    public static boolean cousinClimb(String point){
        if(directDecendents.contains(point)){return true;}
        if(!tree.containsKey(point)){return false;}

        return cousinClimb(tree.get(point));
    }


    public static String search(String cowA, String cowB){

        if(tree.containsKey(cowA) && tree.containsKey(cowB)) {
            if (tree.get(cowA).equals(tree.get(cowB))) { //siblings
                return ("SIBLINGS");
            }
        }

        int decendentCheck = -1;
        if(tree.containsKey(cowA)){
            decendentCheck = climb(tree.get(cowA), cowB);
        }

        String decendentPrintout;

        if(decendentCheck > 0) {
            decendentPrintout = cowB + " is the ";
            for (int i = 2; i < decendentCheck; i++) {
                decendentPrintout = decendentPrintout + "great-";
            }
            if (decendentCheck > 1) {
                return (decendentPrintout + "grand-mother of " + cowA);
            }
            return (decendentPrintout + "mother of " + cowA);
        }

        int auntCheck = -1;
        if(tree.containsKey(cowB)){
            auntCheck = auntClimb(cowA, tree.get(cowB));
        }
        String auntPrintout;

        if(auntCheck > 0) {
            auntPrintout = cowB + " is the ";
            for (int i = 2; i < auntCheck; i++) {
                auntPrintout = auntPrintout + "great-";
            }
            return (auntPrintout + "aunt of " + cowA);
        }

        if(cousinClimb(cowB)){
            return "COUSINS";
        }


        return "NOT RELATED";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
           sc = new Scanner(new File("family.in"));
        }catch (Exception e){}

        PrintWriter out = new PrintWriter(System.out);
        try{
            out = new PrintWriter(new File("family.out"));
        }catch (Exception e){}

        int N = sc.nextInt();

        String cowA = sc.next();
        String cowB = sc.next();

        tree = new HashMap<>();

        String cowX, cowY;
        for(int line = 0; line < N; line++){
            cowX = sc.next();
            cowY = sc.next();

            tree.put(cowY, cowX);
        }
        String search1 = search(cowA, cowB);
        directDecendents.clear();
        String search2 = search(cowB, cowA);

        if(cowA.equals(cowB)){out.println("SIBLINGS");}
        else if(!search1.equals("COUSINS") && !search1.equals("NOT RELATED")){out.println(search1);}
        else if(!search2.equals("COUSINS") && !search2.equals("NOT RELATED")){out.println(search2);}
        else if(!search1.equals("NOT RELATED")){out.println(search1);}
        else if(!search2.equals("NOT RELATED")){out.println(search2);}
        else{out.println(search1);}

        out.close();

        //direct decendent can be checked by going up both trees directly
        //aunt can just be to check all direct decendents for siblings
        //cousins can just be to go all the way up family trees and see if anything matches
        //else will be not related

        /*
        for(String i: tree.keySet()){
            System.out.println(tree.get(i) + " is the mother of " + i);
        }
         */

    }
}
