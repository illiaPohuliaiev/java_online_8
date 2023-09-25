package ua.com.alevel;

public class Handshaking {
    public static void main(String[] args) {
        Human[] human = createHuman();
        Handshaking(human);
    }

    private static Human[] createHuman() {
        Human[] people = new Human[6];
        for (int i = 0; i < 6; i++) {
            people[i] = new Human("Человек " + (i + 1));
        }
        return people;
    }

    private static void Handshaking(Human[] human) {
        do {
            for (int i = 0; i < human.length - 1; i++) {
                human[i].sendMessage(human[i + 1]);
            }

        } while (!human[5].hasTakeMessage());
        {
            // а що цей вайл робить?
        }
    }
}