package buildAndCheck;

import db.Db;
import entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Check {

    static final Db db = Db.getInstance();

    public void checkForInput() {
        if (!isNumberOfCitiesOk(db.getAllCities().size(), db.getQtyOfCities())) {
            throw new RuntimeException("Number of cities is not correct or extra information is present before number of cities.Please check input");
        }
        if (!isPricesBetween2CitiesSame(db.getAllLinks().size(), db.getQtyOfRelations())) {
            throw new RuntimeException("Price between two cities or quantity of proposed routes in one of the cities is incorrect.Please check input");
        }
        if (!isQtyOfRoutesOk(db.getRoutesWeShouldBuild().size(), db.getQtyOfRequiredRoutes())) {
            throw new RuntimeException("Quantity of proposed routes is incorrect.Please check input");
        }
        if (!isCityFromRoutesHaveRelations(db.getAllCities(), db.getRoutesWeShouldBuild())) {
            throw new RuntimeException("One or more cities in proposed routes have no relations with other cities.Please check input");
        }
    }

    private boolean isNumberOfCitiesOk(Integer allCitiesListSize, Integer qtyOfCities) {
        return Objects.equals(allCitiesListSize, qtyOfCities);
    }
    public boolean isCloneExists(Entity freshEntity, List<Entity> allEntities) {
        return allEntities
                .stream()
                .noneMatch(link -> link.getIndexOfA().equals(freshEntity.getIndexOfB())
                        && link.getIndexOfB().equals(freshEntity.getIndexOfA())
                        && link.getCost().equals(freshEntity.getCost()));
    }

    public boolean linkIsConnectedToItself(Entity entity) {
        return entity.getIndexOfA().equals(entity.getIndexOfB());
    }

    private boolean isPricesBetween2CitiesSame(Integer allLinksListSize, Integer qtyOfRelations) {
        return Objects.equals((double) allLinksListSize, (double) qtyOfRelations / 2);
    }

    private boolean isQtyOfRoutesOk(Integer allRoutesListSize, Integer qtyOfRequiredRoutes) {
        return allRoutesListSize.equals(qtyOfRequiredRoutes);
    }

    private boolean isCityFromRoutesHaveRelations(Map<String, Integer> allCities, List<List<String>> routesWeShouldBuild) {
        for (List<String> list : routesWeShouldBuild) {
            for (String s : list) {
                if (!allCities.containsKey(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}