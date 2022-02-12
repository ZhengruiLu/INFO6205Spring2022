package edu.zhengrui;

import java.util.Comparator;

public class Main {
    public static void main(String[] args){
        LinkList<Integer> list = new LinkList<>();
        list.add(1, true);
        list.add(2, false);
        list.add(3, false);
        list.add(4, false);
        list.add(5, false);
        list.add(6, false);
        list.add(7, false);

        list.printList();

        Comparator<Integer> compareVal = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (s1.grades == s2.grades) {
                    return s1.name.compareTo();
                } else {
                    return s1.grades - s2.grades;
                }
            }
        }
    }


}
