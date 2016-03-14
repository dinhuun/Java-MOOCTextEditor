package demos;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {
	
	public static ArrayList<String> getTokens(String text, String pattern) {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    public static void main(String[] args) {
        String s = "this is a test.23,54,390.";
        for (String w : getTokens(s, "[a-z ]+|[0-9]+")) {
            System.out.println(w);
        }
        String t = "Hello";
        t.concat(" World!");
        System.out.println(t);
    }

}
