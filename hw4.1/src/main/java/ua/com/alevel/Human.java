package ua.com.alevel;

class Human {
    private final String name;
    private boolean takeMessage = false;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasTakeMessage() {
        return takeMessage;
    }

    public void sendMessage(Human receiver) {
        if (!takeMessage) {
            System.out.println(name + " отправил сообщение  " + receiver.getName());
            receiver.takeMessage();
        } else {
            System.out.println(name + " получил сообщение."); }
    }

    public void takeMessage() {
        System.out.println(name + " получил сообщение.");
        takeMessage = true;
    }
}

