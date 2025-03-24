import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(scanner.nextLine());
            Map<String, List<String>> grammar = new LinkedHashMap<>();
            for(int j = 0; j  < k; j++){
                String input = scanner.nextLine();
                String[] parts = input.split("-:");
                String nonterminal = parts[0].trim();
                List<String> productions = Arrays.asList(parts[1].trim().split("\\s+"));
                grammar.put(nonterminal, productions);

            }
            LeftRecursionEliminator eliminator = new LeftRecursionEliminator(grammar);
            eliminator.eliminateLeftRecursion();
            eliminator.printGrammar();
        }
    }
}