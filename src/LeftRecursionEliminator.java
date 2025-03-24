import java.util.*;

public class LeftRecursionEliminator {
    private Map<String, List<String>> grammar;
    private List<String> nonTerminals;
    private Deque<Character> availableLetters;

    public LeftRecursionEliminator(Map<String, List<String>> grammar) {
        this.grammar = new LinkedHashMap<>(grammar);
        this.nonTerminals = new ArrayList<>(grammar.keySet());
        this.availableLetters = new ArrayDeque<>();
        for (char c = 'Z'; c >= 'A'; c--) {
            availableLetters.add(c);
        }
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

        for (String production : productions) {
            if (production.startsWith(nonTerminal)) {
                alpha.add(production.substring(nonTerminal.length())); // Remover el no terminal
            } else {
                beta.add(production);
            }
        }

        if (!alpha.isEmpty()) {
            if (availableLetters.isEmpty()) {
                System.err.println("Error: Se agotaron las letras para los nuevos no terminales.");
                return;
            }
            String newNonTerminal = String.valueOf(availableLetters.poll());
            List<String> newAlpha = new ArrayList<>();
            for (String rule : alpha) {
                newAlpha.add(rule + newNonTerminal);
            }
            newAlpha.add("e");
            List<String> newBeta = new ArrayList<>();
            for (String rule : beta) {
                newBeta.add(rule + newNonTerminal);
            }
            grammar.put(nonTerminal, newBeta);
            grammar.put(newNonTerminal, newAlpha);
            nonTerminals.add(newNonTerminal);
        }
    }

    public void printGrammar() {
        for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
            System.out.println(entry.getKey() + " -> " + String.join(" | ", entry.getValue()));
        }
    }
}
