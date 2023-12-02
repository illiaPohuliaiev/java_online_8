package PathAndCost;

import db.Db;
import entity.Entity;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Integer> pathIndexes = new ArrayList<>();
    private final List<Integer> currentRoute = new ArrayList<>();
    private Integer currentPosition;
    final Db db = Db.getInstance();
    final List<Entity> allEntities = db.getAllLinks();
    Entity entity = new Entity();


    private boolean linkIsSuitableToChange() {
        return entity.getIndexOfA().equals(currentPosition) || entity.getIndexOfB().equals(currentPosition);
    }

    private int startFromTheBeginning() {
        return -1;
    }

    private boolean notBanned() {
        return !pathIndexes.contains(allEntities.indexOf(entity));
    }

    private void stepForward() {
        currentPosition = entity.getIndexOfA().equals(currentPosition) ? entity.getIndexOfB() : entity.getIndexOfA();
    }

    private void stepBack() {
        currentPosition = currentRoute.get(currentRoute.size() - 1);
        removeBannedPosition();
    }

    private void addStepToRoute(Integer newStep) {
        currentRoute.add(newStep);
    }

    private void removeStepFromRoute() {
        currentRoute.remove(currentRoute.size() - 1);
    }

    private void addBannedPosition() {
        pathIndexes.add(allEntities.indexOf(entity));
    }

    private void removeBannedPosition() {
        pathIndexes.remove(pathIndexes.size() - 1);
    }

    private boolean endOfList(Integer i) {
        return i == allEntities.size() - 1;
    }

    private boolean linkIsFinish(Integer finish) {
        return (entity.getIndexOfA().equals(currentPosition) && entity.getIndexOfB().equals(finish)) || (entity.getIndexOfB().equals(currentPosition) && entity.getIndexOfA().equals(finish));
    }
    public List<List<Integer>> createAllPossibleRoutes(List<Integer> startFinishList) {
        List<List<Integer>> possibleRoutes = new ArrayList<>();
        Integer start = startFinishList.get(0);
        Integer finish = startFinishList.get(1);
        for (int i = 0; i < allEntities.size(); i++) {
            entity = allEntities.get(i);
            if (currentRoute.isEmpty()) {
                currentPosition = start;
                addStepToRoute(currentPosition);
            }
            if (linkIsSuitableToChange() && notBanned() && !linkIsFinish(finish)) {
                stepForward();
                addStepToRoute(currentPosition);
                addBannedPosition();
                i = startFromTheBeginning();
            } else if (linkIsFinish(finish)) {
                addStepToRoute(finish);
                addBannedPosition();
                possibleRoutes.add(new ArrayList<>(currentRoute));
                i = pathIndexes.get(pathIndexes.size() - 1);
                removeBannedPosition();
                removeStepFromRoute();
                if (endOfList(i) && pathIndexes.size() > 0) {
                    i = pathIndexes.get(pathIndexes.size() - 1);
                    removeStepFromRoute();
                    stepBack();
                } else if (pathIndexes.size() == 0) {
                }
            } else if (endOfList(i) && (currentRoute.size() > 1)) {
                i = pathIndexes.get(pathIndexes.size() - 1);
                removeStepFromRoute();
                stepBack();
                if (endOfList(i) && pathIndexes.size() > 0) {
                    i = pathIndexes.get(pathIndexes.size() - 1);
                    removeStepFromRoute();
                    stepBack();
                } else if (pathIndexes.size() == 0) {
                }
            } else if (endOfList(i)) {
            }
        }
        currentRoute.clear();
        return possibleRoutes;
    }
}
