package ua.com.alevel.db;

import ua.com.alevel.entity.Footballer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FootballerDb {

    private Footballer[] footballers = new Footballer[10];
    private int lastPlayerNumber = 0;

    public void create(Footballer footballer) {
        if (lastPlayerNumber == footballers.length - 1) {
            Footballer[] newStudents = new Footballer[footballers.length * 2];
            System.arraycopy(footballers, 0, newStudents, 0, footballers.length);
            footballers = newStudents;
            add(footballer);
        } else {
            add(footballer);
        }
    }

    public void add(Footballer footballer) {
        footballer.setId(String.valueOf(lastPlayerNumber + 1));
        footballers[lastPlayerNumber] = footballer;
        lastPlayerNumber++;
    }


    public Footballer[] findAll() {
        return footballers;
    }

    public Footballer findOne(String id) throws IOException {
        int k = 0;
        for (int i = 0; i < footballers.length; i++) {
            if (footballers[i] != null) {
                if (footballers[i].getId().equals(id)) {
                    k = i;
                }
            }
        }
        return footballers[k];
    }

    public Footballer update(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter player number");
        String pn = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        int k = 0;
        for (int i = 0; i < footballers.length; i++) {
            if (footballers[i] != null) {
                if (footballers[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        footballers[k].setAge(age);
        footballers[k].setName(n);
        footballers[k].setPlayerNumber(pn);
        return footballers[k];
    }

    public Footballer[] delete(String id) throws IOException {
        int k = 0;
        for (int i = 0; i < this.footballers.length; i++) {
            if (this.footballers[i] != null) {
                System.out.println(this.footballers[i].getId().equalsIgnoreCase(id));
                if (this.footballers[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        System.out.println("k = " + k);
        Footballer[] a = Arrays.copyOfRange(this.footballers, 0, k);
        Footballer[] b = Arrays.copyOfRange(this.footballers, k + 1, this.footballers.length);
        Footballer[] footballers = Arrays.copyOf(a, a.length + b.length + 1);
        System.arraycopy(b, 0, footballers, a.length, b.length);
        return this.footballers = footballers;
    }
}
