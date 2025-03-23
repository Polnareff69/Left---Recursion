import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Map<String, List<String>> grammar = new LinkedHashMap<>();
        grammar.put("S", Arrays.asList("Aa", "b"));
        grammar.put("A", Arrays.asList("Ac", "Sd", "m"));

        LeftRecursionEliminator eliminator = new LeftRecursionEliminator(grammar);
        eliminator.eliminateLeftRecursion();
        eliminator.printGrammar();
    }
}