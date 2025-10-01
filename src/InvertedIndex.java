import java.util.*;

public class InvertedIndex {
    private Map<String, Map<String, Integer>> index = new HashMap<>();

    // Legger til et dokument og dets innhold i indeksen
    public void addToIndex(String docName, String content) {
        String[] words = content.toLowerCase()
                .replaceAll("[^a-zA-Z0-9 ]", "")
                .split("\\s+");
        for (String word : words) {
            index.computeIfAbsent(word, k -> new HashMap<>())
                    .merge(docName, 1, Integer::sum);
        }
    }

    // Skriver ut hele indeksen med ordfrekvenser
    public void printIndex() {
        for (String word : index.keySet()) {
            System.out.println("\"" + word + "\" → " + index.get(word));
        }
    }

    // Enkelt søk: returnerer dokumenter som inneholder ordet man leter etter
    public Set<String> search(String word) {
        return index.containsKey(word.toLowerCase())
                ? index.get(word.toLowerCase()).keySet()
                : new HashSet<>();
    }

    // Kombinert søk: AND
    public Set<String> searchAND(String word1, String word2) {
        Set<String> result = new HashSet<>(search(word1));
        result.retainAll(search(word2));
        return result;
    }

    // Kombinert søk: OR
    public Set<String> searchOR(String word1, String word2) {
        Set<String> result = new HashSet<>(search(word1));
        result.addAll(search(word2));
        return result;
    }

    // Kombinert søk: NOT
    public Set<String> searchNOT(String word1, String word2) {
        Set<String> result = new HashSet<>(search(word1));
        result.removeAll(search(word2));
        return result;
    }

    // Rangert søk: dokumenter sortert etter antall forekomster
    public List<Map.Entry<String, Integer>> searchRanked(String word) {
        Map<String, Integer> docMap = index.getOrDefault(word.toLowerCase(), new HashMap<>());
        List<Map.Entry<String, Integer>> ranked = new ArrayList<>(docMap.entrySet());
        ranked.sort((a, b) -> b.getValue().compareTo(a.getValue())); // fallende rekkefølge
        return ranked;
    }
}
