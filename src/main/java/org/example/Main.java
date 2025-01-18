package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String i = "Java 123 is a Programming 456 Language.";

        // Compile the regex to match integers
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(i);

        // Extract integers
        List<Integer> n = new ArrayList<>();
        while (matcher.find()) {
            n.add(Integer.parseInt(matcher.group()));
        }

        System.out.println("" + n);
    }
}