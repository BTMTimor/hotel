package org.tm.common.annotation.service;

/*
    author: Timor
    date: 2020/2/23 0023
*/
public class WebNameFormatter implements NameFormatter {
    @Override
    public String format(String name) {
        return lowerCharAndAddHyphenBeforeIt(name);
    }

    public static String lowerCharAndAddHyphenBeforeIt(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 3);
        sb.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
