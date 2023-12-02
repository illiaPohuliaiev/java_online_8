package PathAndCost;

import db.Db;
import entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cost {

    final Db db = Db.getInstance();
    final List<Entity> allEntities = db.getAllLinks();
    final Integer maxCost = 200_000;


    private Integer costPerRoute(List<Integer> route) {
        Integer sum = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Integer indexA = route.get(i);
            Integer indexB = route.get(i + 1);
            for (Entity entity : allEntities) {
                if (entity.getIndexOfA().equals(indexA) && entity.getIndexOfB().equals(indexB) || entity.getIndexOfA().equals(indexB) && entity.getIndexOfB().equals(indexA)) {
                    sum += entity.getCost();
                    break;
                }
            }
        }
        return sum;
    }
    public Integer getCheapestCost(List<List<Integer>> possibleRoutes) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        possibleRoutes.forEach(r -> map.put(r, costPerRoute(r)));
        return map.values().stream().mapToInt(integer -> integer).filter(s -> s < maxCost).min().orElse(0);
    }
}
