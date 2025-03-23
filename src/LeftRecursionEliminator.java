import java.util.*;
public class LeftRecursionEliminator{
    private Map<String, List<String>> grammar;
    private List<String> nonTerminals;

    public LeftRecursionEliminator(Map<String, List<String>> grammar) {
        this.grammar = new LinkedHashMap<>(grammar);
        this.nonTerminals = new ArrayList<>(grammar.keySet());
    }

    public void eliminateLeftRecursion() {
        int n = nonTerminals.size();

        for (int i = 0; i < n; i++) {
            String Ai = nonTerminals.get(i);
            for (int j = 0; j < i; j++) {
                String Aj = nonTerminals.get(j);
                List<String> newProductions = new ArrayList<>();

                for (String production : grammar.get(Ai)) {
                    if (production.startsWith(Aj)) {
                        for (String AjProduction : grammar.get(Aj)) {
                            newProductions.add(AjProduction + production.substring(Aj.length()));
                        }
                    } else {
                        newProductions.add(production);
                    }
                }
                grammar.put(Ai, newProductions);
            }
            eliminateImmediateLeftRecursion(Ai);
        }
    }

    private void eliminateImmediateLeftRecursion(String nonTerminal) {
        List<String> productions = grammar.get(nonTerminal);
        List<String> alpha = new ArrayList<>();
        List<String> beta = new ArrayList<>();
        String newNonTerminal = nonTerminal + "'";

        for (String production : productions) {
            if (production.startsWith(nonTerminal)) {
                alpha.add(production.substring(nonTerminal.length()) + newNonTerminal);
            } else {
                beta.add(production + newNonTerminal);
            }
        }

        if (!alpha.isEmpty()) {
            grammar.put(nonTerminal, beta);
            grammar.put(newNonTerminal, alpha);
            nonTerminals.add(newNonTerminal); // Añadir nuevo no terminal
        }
    }

    public void printGrammar() {
        for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
            System.out.println(entry.getKey() + " -> " + String.join(" | ", entry.getValue()));
        }
    }
}
