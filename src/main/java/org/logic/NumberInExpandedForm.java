package org.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class NumberInExpandedForm {

    public String getNumber(int number) {
        StringJoiner joiner = new StringJoiner(" + "); // join 1000x, 100x, 10x and etc.
        String[] num = String.valueOf(number).split(""); // parse int to string array for loop
        List<String> res = new ArrayList<>(); // list for add numbers
        for (int i = 0; i < num.length; i++) {
            if (Integer.parseInt(num[i]) > 0) {      // if digit greater then 0,
                String string = num[i] +            //  append it to string number
                        "0".repeat(Math.max(0, num.length - (i + 1))); // add 0 if need
                res.add(string);  // add result number part in list
            }
        }
        res.forEach(joiner::add); // append all number parts to joiner
        return joiner.toString(); // profit
    }
}
