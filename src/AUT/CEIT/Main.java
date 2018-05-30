package AUT.CEIT;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of statements in your input grammar:");
        int sizeA = scanner.nextInt();
        Simplifier simplifier = new Simplifier(sizeA);


        String[] givenA = new String[sizeA];
        System.out.println("Now enter each statement in an individual line(instead of landa use ! and use uppercase letter for variables and lower case for terminals ):");
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


            simplifier.listCreator();

            simplifier.graphChecker();
            simplifier.clearerMain();

            for (int i = 0; i < simplifier.getGiven().length; i++) {
                System.out.println(simplifier.getGiven()[i]);
            }

        }
    }


}
