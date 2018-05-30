package AUT.CEIT;

import java.util.ArrayList;

public class subsetGen
{
    private String original;
    private StringBuilder current;
    ArrayList<String> myList;
    int index;

    public ArrayList<String> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<String> myList) {
        this.myList = myList;
    }

    public subsetGen(String given)
    {
        original = given;
        current = new StringBuilder();
        myList = new ArrayList<>();
        for(int i=0; i<original.length(); ++i)
            genSubs(original, current, myList, i);

    }



    public void genSubs(String original, StringBuilder current, ArrayList<String> myList, int index){

        current.append(original.charAt(index));

       // System.out.println(current.toString() + index);

        myList.add(current.toString());


        for(int i=index+1; i<original.length(); ++i)
            genSubs(original, current, myList, i);

        current.deleteCharAt(current.toString().length()-1);


        return;
    }
}