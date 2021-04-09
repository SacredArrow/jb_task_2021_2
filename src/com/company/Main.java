package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//	    matches("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "(x+x+)+y"); // Runs a long time
        matchesWithTimeout("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "(x+x+)+y", 1000); // Finishes
    }

    public static boolean matches(String text, String regex) {
        return Pattern.compile(regex).matcher(text).matches();
    }

    public static Matcher createMatcherWithTimeout(String text, String regex, int timeout) {
        Pattern pattern = Pattern.compile(regex);
        CharSequence timeoutCharSequence = new TimeoutCharSequence(text, timeout);
        return pattern.matcher(timeoutCharSequence);
    }

    public static boolean matchesWithTimeout(String text, String regex, int timeout) {
        Matcher matcher = createMatcherWithTimeout(text, regex, timeout);
        boolean result = false;
        try {
            result = matcher.matches();
        } catch (RegexpTimeoutException e) {
            System.out.println("Aborted due to exception");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Exception with message " + e.getMessage() + " happened.");
            System.exit(0);
        }
        return result;
    }
}
