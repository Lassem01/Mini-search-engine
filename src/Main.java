public class Main {
    public static void main(String[] args) {
        InvertedIndex index = new InvertedIndex();

        long startTime = System.nanoTime();

        // Legger til dokumenter
        String doc1 = "Java is fun and powerful, Java is also popular";
        String doc2 = "Java and data science";
        String doc3 = "Data is everywhere and Java is useful";

        index.addToIndex("Doc1", doc1);
        index.addToIndex("Doc2", doc2);
        index.addToIndex("Doc3", doc3);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Byggetid: " + duration + " ns");

        // Skriver ut hele indeksen
        System.out.println("\n=== Invertert indeks ===");
        index.printIndex();

        // Søketester
        System.out.println("\n=== Enkle og kombinerte søk ===");
        System.out.println("Søker etter 'java' → " + index.search("java"));
        System.out.println("Søker etter 'java AND data' → " + index.searchAND("java", "data"));
        System.out.println("Søker etter 'java OR data' → " + index.searchOR("java", "data"));
        System.out.println("Søker etter 'java NOT data' → " + index.searchNOT("java", "data"));

        // Rangert søk
        System.out.println("\n=== Rangert søk etter 'java' ===");
        for (var entry : index.searchRanked("java")) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ganger");
        }
    }
}
