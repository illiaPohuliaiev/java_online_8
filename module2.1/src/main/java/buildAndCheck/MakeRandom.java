package buildAndCheck;

import db.Db;
import entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeRandom {

    final Db db = Db.getInstance();
    final Check check = new Check();

    private Entity generateOneRandomLink() {
        return new Entity(randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator());
    }

    private int randomNumberGenerator() {
        final int limit = 4;
        Random r = new Random();
        return r.nextInt(limit) + 1;
    }

    public List<Integer> generateRandomRoute() {
        List<Integer> list = new ArrayList<>();
        list.add(randomNumberGenerator());
        list.add(randomNumberGenerator());
        System.out.println("random route=" + list);
        return list;
    }

    public List<Entity> generateListOfRandomAndCheckedLinks() {
        List<Entity> list = new ArrayList<>();
        int numberOfCitiesToGenerate = 10;
        while (numberOfCitiesToGenerate > 0) {
            Entity entity = generateOneRandomLink();
            if (check.isCloneExists(entity, list) && !check.linkIsConnectedToItself(entity)) {
                list.add(entity);
                numberOfCitiesToGenerate -= 2;
            } else {
                generateOneRandomLink();
                System.out.println("list=" + list);
                System.out.println("link=" + entity);
                generateOneRandomLink();
                System.out.println("numberOfCitiesToGenerate=" + numberOfCitiesToGenerate);
            }
        }
        db.setQtyOfRelations(list.size() * 2);
        for (Entity entity : list) {
            System.out.println("random link=" + entity);
        }
        return list;
    }
}