package ua.com.alevel;

import ua.com.alevel.test.MessageTwo;
import ua.com.alevel.depends.DependsTwo;

public class HelloTwo {

    public static void main(String[] args) {
//        ua.com.alevel.test.Message m = new ua.com.alevel.test.Message();

        MessageTwo m = new MessageTwo();
        byte b = Byte.MAX_VALUE;
        byte b1 = Byte.MIN_VALUE;
        short s = Short.MAX_VALUE;
        int a = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        m.print(String.valueOf(b));
        m.print(String.valueOf(b1));
        m.print(String.valueOf(s));
        m.print(String.valueOf(a));
        m.print(String.valueOf(l));

        System.out.println();
        DependsTwo d = new DependsTwo();
        d.console("Hello World");
    }
}
