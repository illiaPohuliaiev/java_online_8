package service;

import db.Db;
import inputAndOutput.ReadAndWrite;
import PathAndCost.Cost;
import buildAndCheck.Input;
import buildAndCheck.MakeRandom;
import inputAndOutput.impl.ReadAndWriteImpl;
import PathAndCost.Path;
import buildAndCheck.Check;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {

    final ReadAndWrite readAndWriteImpl = new ReadAndWriteImpl();
    final MakeRandom makeRandom = new MakeRandom();
    final Check check = new Check();
    final Db db = Db.getInstance();
    final Cost cost = new Cost();
    final Path path = new Path();
    final Input input = new Input();



    public List<List<Integer>> transformCityIntoIndexNumber(List<List<String>> listOfCities, Map<String, Integer> citiesIndex) {
        List<List<Integer>> fromToInNumbers = new ArrayList<>();
        for (List<String> strings : listOfCities) {
            List<Integer> list = new ArrayList<>();
            list.add(citiesIndex.get(strings.get(0)));
            list.add(citiesIndex.get(strings.get(1)));
            fromToInNumbers.add(list);
        }
        return fromToInNumbers;
    }

    public void start() {
        if (readAndWriteImpl.isInputExists()) {
            db.addTemp(readAndWriteImpl.readInput());
            input.divideInput();
            check.checkForInput();
            for (List<Integer> fromXtoY : transformCityIntoIndexNumber(db.getRoutesWeShouldBuild(), db.getAllCities())) {
                readAndWriteImpl.writeOutput(cost.getCheapestCost(path.createAllPossibleRoutes(fromXtoY)));
            }
        } else {
            System.out.println("создаем из random");
            db.addAllLinks(makeRandom.generateListOfRandomAndCheckedLinks());
            readAndWriteImpl.writeOutput(cost.getCheapestCost(path.createAllPossibleRoutes(makeRandom.generateRandomRoute())));
        }
    }
}
