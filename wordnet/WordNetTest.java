import java.util.Arrays;

public class WordNetTest {
    public static void main(String[] args) {
        // Create a WordNet object
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");

        // Test nouns() method
        System.out.println("All WordNet nouns: " + wordNet.nouns());

        // Test isNoun() method
        System.out.println("Is 'dog' a WordNet noun? " + wordNet.isNoun("dog"));
        System.out.println("Is 'cat' a WordNet noun? " + wordNet.isNoun("cat"));
        System.out.println("Is 'apple' a WordNet noun? " + wordNet.isNoun("apple"));

        // Test distance() method
        System.out.println("Distance between 'dog' and 'cat': " + wordNet.distance("dog", "cat"));
        System.out.println("Distance between 'apple' and 'cat': " + wordNet.distance("apple", "cat"));

        // Test sap() method
        System.out.println("SAP between 'dog' and 'cat': " + wordNet.sap("dog", "cat"));
        System.out.println("SAP between 'apple' and 'cat': " + wordNet.sap("apple", "cat"));
    }
}