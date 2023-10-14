package ua.com.alevel;

public class Main {
    public static void main(String[] args) {
        Dictionary<String, Integer> dict = new Dictionary<>();
        dict.put("one", 1);
        dict.put("two", 2);
        dict.put("three", 3);

        System.out.println("Size: " + dict.size());
        System.out.println("Contains key 'two': " + dict.containsKey("two"));
        System.out.println("Contains value 4: " + dict.containsValue(4));

        System.out.println("Get 'two': " + dict.get("two"));

        System.out.println("Removing 'two': " + dict.remove("two"));
        System.out.println("Contains key 'two' after removal: " + dict.containsKey("two"));

        Dictionary<String, Integer> otherDict = new Dictionary<>();
        otherDict.put("three", 33);
        otherDict.put("four", 4);

        System.out.println("PutAll result: " + dict.putAll(otherDict));
        System.out.println("After putAll:");
        System.out.println("Size: " + dict.size());
        System.out.println("Contains key 'three': " + dict.containsKey("three"));
        System.out.println("Value for key 'three': " + dict.get("three"));

        dict.clear();
        System.out.println("Size after clear: " + dict.size());
    }
}
