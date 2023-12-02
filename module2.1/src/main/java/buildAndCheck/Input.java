package buildAndCheck;

import db.Db;
import entity.Entity;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    final Pattern patternCity = Pattern.compile("[а-яА-ЯёЁa-zA-Z+_.-]+");
    final Pattern patternOnlyNumber = Pattern.compile("[0-9]");
    final Pattern patternFromTo = Pattern.compile("[а-яА-ЯёЁa-zA-Z+_.-]+\\s[а-яА-ЯёЁa-zA-Z+_.-]+");
    final Pattern patternOtherCityPrice = Pattern.compile("[0-9]+\\s[0-9]+");

    final Db db = Db.getInstance();
    final Check check = new Check();
    final List<String> temp = db.getTemp();

    private void createLinksForCity(String cityName) {
        for (int i = temp.indexOf(cityName) + 2; i < temp.size(); i++) {
            String s = temp.get(i);
            Matcher otherCityPrice = patternOtherCityPrice.matcher(s);
            boolean isOtherCityPrice = otherCityPrice.matches();
            if (isOtherCityPrice) {
                Integer indexOfA = db.getAllCities().get(cityName);
                Entity entity = new Entity();
                entity.setIndexOfA(indexOfA);
                String[] words = StringUtils.split(s);
                entity.setIndexOfB(Integer.valueOf(words[0]));
                entity.setCost(Integer.valueOf(words[1]));
                if (check.isCloneExists(entity, db.getAllLinks())) {
                    db.addLink(entity);
                }
            } else {
                break;
            }
        }
    }
    public void divideInput() {
        Integer ordinalNumber = 1;
        for (int i = 0; i < temp.size(); i++) {
            String s = temp.get(i);
            Matcher city = patternCity.matcher(s);
            boolean isCity = city.matches();
            Matcher onlyNumber = patternOnlyNumber.matcher(s);
            boolean isOnlyNumber = onlyNumber.matches();
            Matcher fromTo = patternFromTo.matcher(s);
            boolean isFromTo = fromTo.matches();
            if (isCity) {
                db.addAllCities(s, ordinalNumber);
                createLinksForCity(s);
                ordinalNumber++;
            } else if (isOnlyNumber) {
                if (i == 0) {
                    db.setQtyOfCities(Integer.valueOf(s));
                } else if (!db.getAllCities().containsKey(temp.get(i - 1))) {
                    db.setQtyOfRequiredRoutes(Integer.valueOf(s));
                } else {
                    db.setQtyOfRelations(db.getQtyOfRelations() + Integer.parseInt(s));
                }
            } else if (isFromTo) {
                List<String> list = List.of(s.split("\\s"));
                db.addRoutesWeShouldBuild(list);
            }
        }
    }
}
