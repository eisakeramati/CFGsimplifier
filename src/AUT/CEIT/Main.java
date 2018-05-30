package AUT.CEIT;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of statements in your input grammar:");
        int sizeA = scanner.nextInt();
        Simplifier simplifier = new Simplifier(sizeA);


        String[] givenA = new String[sizeA];
        System.out.println("Now enter each statement in an individual line:(instaed of landa use ! and use uppercase letter for variables and lower case for terminals )");
        for (int i = 0; i < sizeA; i++) {
            givenA[i] = scanner.next();
        }
        simplifier.setGiven(givenA);


        if (simplifier.CGFdeterminer() == false) {
            System.out.println("The given grammar is not context free!");
        } else {

            System.out.println("Here is the simplified result:");
            System.out.println("\n");

            simplifier.secondList();
            simplifier.firstLevel(givenA);




            simplifier.secGraphCreator();



//        for (int i = 0; i <simplifier.thirdLevel.length ; i++) {
//            for (int j = 0; j <simplifier.thirdLevel[i].size(); j++) {
//                System.out.println(simplifier.thirdLevel[i].get(j));
//            }
//            //System.out.println(simplifier.thirdLevel.get(i));
//        }

//        for (int i = 0; i <simplifier.thirdLevel2.length ; i++) {
//            for (int j = 0; j <simplifier.thirdLevel2[i].size(); j++) {
//                System.out.println(simplifier.thirdLevel2[i].get(j));
//            }
//          //  System.out.println("+++++++++++++++++++");
//        }


            simplifier.listCreator();
//        for (int i = 0; i < simplifier.getListFirst().length; i++) {
//            for (int j = 0; j < simplifier.getListFirst()[i].size(); j++) {
//                System.out.print(simplifier.getListFirst()[i].get(j));
//            }
//            System.out.println(" ");
//
//        }
            simplifier.graphChecker();
//        ArrayList<String> temp = simplifier.getFirstLevel();
//        for (int i = 0; i < temp.size(); i++) {
//            System.out.println(temp.get(i));
//        }
            simplifier.clearerMain();
//        for (int i = 0; i <simplifier.getGiven().length ; i++) {
//            System.out.println(simplifier.getGiven()[i]);
//        }

            //   System.out.println(simplifier.separator(givenA[0]));


//            simplifier.trimmer();
            for (int i = 0; i < simplifier.getGiven().length; i++) {
                System.out.println(simplifier.getGiven()[i]);
            }

        }
    }


}
