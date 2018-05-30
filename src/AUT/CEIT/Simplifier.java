package AUT.CEIT;

import java.util.*;

public class Simplifier {
    private String[] given;
    private LinkedList<String>[] listFirst;
    private ArrayList<String> firstLevel;

    public ArrayList<String> getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(ArrayList<String> firstLevel) {
        this.firstLevel = firstLevel;
    }

    public Simplifier(int size) {
        given = new String[size];
        listFirst = new LinkedList[size];
        secondLevel = new ArrayList<>();
        thirdLevel = new ArrayList[size];
        thirdLevel2 = new ArrayList[size];
        mid = new ArrayList[size];
    }

    public String[] getGiven() {
        return given;
    }

    public LinkedList<String>[] getListFirst() {
        return listFirst;
    }

    public void setListFirst(LinkedList<String>[] listFirst) {
        this.listFirst = listFirst;
    }

    public void setGiven(String[] given) {
        this.given = given;
    }

    public boolean CGFdeterminer() {
        boolean decisionMaker = false;
        for (int i = 0; i < given.length; i++) {
            if (!uppercaseChecker(Character.toString(given[i].charAt(0))))
                decisionMaker = true;
        }
        return !decisionMaker;
    }

    public boolean uppercaseChecker(String s) {
        String temp = s.toUpperCase();
        String temp2 = s.toLowerCase();
        if (s.equals(temp) && !s.equals(temp2))
            return true;
        else return false;
    }


    public boolean lowercaseChecker(String s) {
        String temp = s.toUpperCase();
        String temp2 = s.toLowerCase();
        if (!s.equals(temp) && s.equals(temp2))
            return true;
        else return false;
    }

    private String separator(String s) {
        int loc = s.indexOf(":");
        String result = "";
        for (int i = loc + 1; i < s.length(); i++) {
            result += s.charAt(i);
        }
        return result;
    }

    public void listCreator() {
        for (int i = 0; i < given.length; i++) {
            listFirst[i] = new LinkedList<String>();
            listFirst[i].add(Character.toString(given[i].charAt(0)));
        }
        for (int i = 0; i < given.length; i++) {
            for (int j = 2; j < given[i].length(); j++) {
                if (uppercaseChecker(Character.toString(given[i].charAt(j))) && !Character.toString(given[i].charAt(j)).equals(listFirst[i].get(0))) {
                    listFirst[i].add(Character.toString(given[i].charAt(j)));
                }
            }
        }

        for (int i = 0; i < given.length; i++) {
            int t = 2;
            int s = 2;
            boolean dm = false;
            while (t < given[i].length() + 1) {
                if (t < given[i].length()) {
                    if ((given[i].charAt(t) == '|')) {
                        if (checker(s, t - 1, given[i]))
                            dm = true;
                        if (t < given[i].length())
                            if (given[i].charAt(t) == '|')
                                t++;
                        s = t;
                    }
                } else {
                    if (checker(s, t - 1, given[i]))
                        dm = true;
                }
                t++;
            }
            if (dm == true)
                listFirst[i].add("#");
        }

    }

    private boolean checker(int a1, int a2, String s) {
        boolean temp = true;
        for (int i = a1; i <= a2; i++) {
            if (uppercaseChecker(Character.toString(s.charAt(i)))) {

                temp = false;
            }
        }
        return temp;
    }

    public void graphChecker() {
        ArrayList<String> arrayList1 = new ArrayList<>();
        for (int i = 0; i < listFirst.length; i++) {
            String temp = listFirst[i].get(0);
            int counter = 0;
            for (int j = 0; j < listFirst.length; j++) {
                if (listFirst[j].contains(temp))
                    counter++;
            }
            if (counter < 2)
                arrayList1.add(temp);
        }
        setFirstLevel(arrayList1);
        d(listFirst[0].get(0));
        getFirstLevel().remove("S");

    }


    public boolean d(String s) {
        int place = finder(s);

//        if (place == -1)
//            System.out.println("===" + s);

        if (listFirst[place].size() == 1)
            return false;
        else if (listFirst[place].get(1).equals("#"))
            return true;


        boolean dec = false;
        for (int i = 1; i < listFirst[place].size(); i++) {
            boolean temp = false;
            if (listFirst[place].get(i).equals("#"))
                temp = true;
            else
                temp = d(listFirst[place].get(i));
            // System.out.println("++++" + listFirst[place].get(i) + temp);
            if (temp == false) {
                firstLevel.add(listFirst[place].get(i));
                // System.out.println("yes");
            }
            dec = dec || temp;

        }
        return dec;
    }

    private int finder(String s) {
        int ans = -1;
        for (int i = 0; i < listFirst.length; i++) {
            if (listFirst[i].get(0).equals(s))
                ans = i;
        }
        return ans;
    }

    public void clearerMain() {
        for (int i = 0; i < firstLevel.size(); i++) {
            clearer(firstLevel.get(i));
        }
        for (int i = 0; i < firstLevel.size(); i++) {
            for (int j = 0; j < given.length; j++) {
                if (given[j] != "")
                    if (Character.toString(given[j].charAt(0)).equals(firstLevel.get(i)))
                        given[j] = "";
            }
        }
    }

    private void clearer(String s) {
        for (int i = 0; i < given.length; i++) {
            for (int j = 0; j < given[i].length(); j++) {
                if (Character.toString(given[i].charAt(j)).equals(s)) {
                    given[i] = remover(given[i], down(i, j), up(i, j));
                }

            }
        }
    }

    private int up(int a1, int a2) {
        for (int i = a2; i < given[a1].length(); i++) {
            if (Character.toString(given[a1].charAt(i)).equals("|"))
                return i;
        }
        return given[a1].length() - 1;
    }

    private int down(int a1, int a2) {
        for (int i = a2; i >= 0; i--) {
            if (Character.toString(given[a1].charAt(i)).equals("|"))
                return i;
        }
        return 2;
    }

    private String remover(String s, int a1, int a2) {
        String temp = s.substring(0, a1);
        return temp.concat(s.substring(a2 + 1, s.length()));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private ArrayList<String> secondLevel;

    public ArrayList<String> getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(ArrayList<String> secondLevel) {
        this.secondLevel = secondLevel;
    }

    public void secondList() {

        for (int i = 0; i < given.length; i++) {
            if (given[i].contains("!")) {
                //System.out.println("++"+given[i].charAt(0));
                secondLevel.add(Character.toString(given[i].charAt(0)));
            }
        }

        for (int i = 0; i < secondLevel.size(); i++) {
            for (int j = 0; j < given.length; j++) {
                if (given[j].contains(secondLevel.get(i)) && given[j].indexOf(secondLevel.get(i)) != 0) {
                    int upp = up(j, given[j].indexOf(secondLevel.get(i)));
                    if (upp >= given[j].length() - 1)
                        upp++;
                    String s = given[j].substring(down(j, given[j].indexOf(secondLevel.get(i))), upp);
                    // System.out.println(s);
                    int temp = 0;
                    for (int k = 0; k < s.length(); k++) {
                        if (!secondLevel.contains(Character.toString(s.charAt(k))))
                            temp++;
                    }
                    if (temp == 0)
                        if (!secondLevel.contains(Character.toString(given[j].charAt(0))))
                            secondLevel.add(Character.toString(given[j].charAt(0)));
                }
            }
        }

    }


    public String SubString(String str, int n, ArrayList<String> notOkay) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < str.length(); i++) {
            map.put(Character.toString(str.charAt(i)), i);
        }

        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        String res = "";

        for (int i = 0; i < str.length(); i++) {
            if (!notOkay.contains(Character.toString(str.charAt(i))))
                arr.add(Character.toString(str.charAt(i)));
        }

        // System.out.println(arr.toString());

        String str1 = "";
        for (int i = 0; i < str.length(); i++) {
            if (notOkay.contains(Character.toString(str.charAt(i))))
                str1 += Character.toString(str.charAt(i));
        }
        // System.out.println(str1);

        String s = "";
        subsetGen gen = new subsetGen(str1);
        gen.getMyList().add("");
        for (int i = 0; i < gen.getMyList().size(); i++) {
            // System.out.println(gen.getMyList().size()+"   m");
            String temp = gen.getMyList().get(i);
            // System.out.println(temp);
            if (arranger(arr, temp, map) != "")
                s = s + arranger(arr, temp, map) + "|";
            //System.out.println(s);
        }
        if (gen.getMyList().size() == 0) {
            for (int i = 0; i < arr.size(); i++) {
                s += arr.get(i);
            }
            s += "|";
        }

        return s.substring(0, s.length() - 1);
    }

    public String arranger(ArrayList<String> arr, String s, Map<String, Integer> map) {
        String res = s;
        for (int i = arr.size() - 1; i >= 0; i--) {
            int place = 0;
            for (int j = 0; j < res.length(); j++) {
                if (map.get(Character.toString(res.charAt(j))) < map.get(Character.toString(arr.get(i).charAt(0))))
                    place++;
            }
            String temp = res.substring(0, place);
            if (place == res.length())
                res = res + Character.toString(arr.get(i).charAt(0));
            else
                res = temp + Character.toString(arr.get(i).charAt(0)) + res.substring(place, res.length());

        }

        return res;
    }


    public void firstLevel(String[] s) {
        ArrayList<String>[] mid = new ArrayList[s.length];
        for (int i = 0; i < s.length; i++) {
            ArrayList<String> m = new ArrayList<>();
            // String level = s[i];
            int index = 2;
            for (int j = 0; j < s[i].length(); j++) {
                if (Character.toString(s[i].charAt(j)).equals("|")) {
                    //System.out.println(s[i].substring(index, j));
                    m.add(s[i].substring(0, 2) + s[i].substring(index, j));
                    index = j + 1;
                }
            }
            m.add(s[i].substring(0, 2) + s[i].substring(index, s[i].length()));
            mid[i] = m;

        }
//        System.out.println("+++++++++++++++++++");
//        for (int i = 0; i < mid.length; i++) {
//            System.out.println(mid[i].toString());
//        }


        for (int i = 0; i < mid.length; i++) {
            String midRes = mid[i].get(0).substring(0, 2);
            for (int j = 0; j < mid[i].size(); j++) {
                String temp = SubString(mid[i].get(j).substring(2, mid[i].get(j).length()), mid[i].get(j).length() - 2, getSecondLevel());
                if (!temp.equals("!"))
                    midRes += temp + "|";
            }
            s[i] = midRes.substring(0, midRes.length() - 1);
        }
        //return s;
        setGiven(s);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ArrayList<String>[] thirdLevel;
    ArrayList<String>[] thirdLevel2;
    ArrayList<String>[] mid;


    public void secGraphCreator() {
        for (int i = 0; i < given.length; i++) {
            int index = 2;
            String s = "";
            thirdLevel[i] = new ArrayList<>();
            mid[i] = new ArrayList<>();
            for (int j = 2; j < given[i].length(); j++) {

                if (Character.toString(given[i].charAt(j)).equals("|")) {

                    s += given[i].substring(index, j);

                    if (s.length() == 1 && uppercaseChecker(s)) {
                        //if (uppercaseChecker(s))
                        thirdLevel[i].add(given[i].substring(0, 2) + s);

                    } else {
                        mid[i].add(given[i].substring(0, 2) + s);
                    }
                    index = j + 1;
                }


                s = "";
            }
            if (given[i].substring(index, given[i].length()).length() == 1 && uppercaseChecker(given[i].substring(index, given[i].length())))
                thirdLevel[i].add(given[i].substring(0, 2) + given[i].substring(index, given[i].length()));
            else
                mid[i].add(given[i].substring(0, 2) + given[i].substring(index, given[i].length()));

            if (thirdLevel[i].size() == 0) {
                thirdLevel[i].add(given[i].substring(0, 2));
            }

        }


//        for (int i = 0; i < thirdLevel.length; i++) {
//            System.out.println(thirdLevel[i]);
//        }


        for (int i = 0; i < thirdLevel.length; i++) {
            thirdLevel2[i] = new ArrayList<>();
            thirdLevel2[i].add(thirdLevel[i].get(0).substring(0, 1));
            // System.out.println("temp    " + thirdLevel[i].get(0).substring(0, 1));
            // if (thirdLevel[i].size()!=0)
            finisher(thirdLevel2[i], thirdLevel[i].get(0).substring(0, 1));
        }


        ArrayList<String> t = new ArrayList<>();
        String[] res = new String[given.length];
        for (int i = 0; i < thirdLevel2.length; i++) {
            String temp = thirdLevel2[i].get(0);
            String s = thirdLevel2[i].get(0) + ":";
            //int index = getFromGraph2(thirdLevel2[i].get(0));
            for (int j = 0; j < thirdLevel2[i].size(); j++) {
                for (int k = 0; k < mid[getFromGraph2(thirdLevel2[i].get(j))].size(); k++) {
                    String fin = mid[getFromGraph2(thirdLevel2[i].get(j))].get(k).substring(2, mid[getFromGraph2(thirdLevel2[i].get(j))].get(k).length());
                    //System.out.println(fin+"     MMMM");
                    //System.out.println(t.toString());
                    if (!t.contains(fin)) {
                        s += fin + "|";
                        t.add(fin);
                    }

                }

            }
            res[i] = s.substring(0, s.length() - 1);
            t.clear();
        }

        setGiven(res);
    }


    private int getFromGraph(String s) {
        int res = -1;
        for (int i = 0; i < thirdLevel.length; i++) {
            if (s.equals(Character.toString(thirdLevel[i].get(0).charAt(0)))) {
                res = i;
                break;
            }
        }
        return res;
    }

    private void finisher(ArrayList<String> arr, String s) {
        ArrayList<String> temp = new ArrayList<>();
        // System.out.println("mmmm      " + s);
        int index = getFromGraph(s);
        // System.out.println(index);
        for (int i = 0; i < thirdLevel[index].size(); i++) {
            if (thirdLevel[index].get(i).length() >= 3) {
                if (!arr.contains(thirdLevel[index].get(i).substring(2, 3))) {
                    arr.add(thirdLevel[index].get(i).substring(2, 3));
                    temp.add(thirdLevel[index].get(i).substring(2, 3));
                }
            }
        }
        // System.out.println("end");
        // System.out.println("size    "+ temp.size());
        for (int i = 0; i < temp.size(); i++) {
            finisher(arr, temp.get(i));
        }
    }

    private int getFromGraph2(String s) {
        int res = -1;
        for (int i = 0; i < thirdLevel2.length; i++) {
            if (thirdLevel2[i].get(0).equals(s))
                res = i;
        }
        return res;
    }

//    public void trimmer() {
//        for (int i = 0; i < given.length; i++) {
//            int index = 2;
//            String s = "";
//            ArrayList<String> arr = new ArrayList<>();
//            for (int j = 2; j < given[i].length(); j++) {
//                if (Character.toString(given[i].charAt(j)).equals("|")){
//                   // System.out.println(j+given[i]);
//                    s =given[i].substring(index, j);
//                   // System.out.println(index);
//                    //System.out.println(given[i].substring(index, j));
//                    //System.out.println("+++   "+s);
//
//                    if (!arr.contains(s))
//                        arr.add(s);
//                    else{
//                       given[i] =given[i].replaceFirst(s+"|", "");
//                        // arr.remove(s);
//                    }
//
//                    index=j+1;
//                }
//
//
//              //  s="";
//            }
//            if (arr.contains(given[i].substring(given[i].lastIndexOf("|"), given[i].length())))
//                given[i] = given[i].replace(given[i].substring(index, given[i].length())+"|", "");
//        }
//    }


}
