package analyze;

import java.util.*;

public class Counter {

    // Count occurrences of items
    public static <K> Map<K, Integer> count(Iterable<K> items) {
        Map<K, Integer> m = new HashMap<>();
        for (K k : items) {
            m.put(k, m.getOrDefault(k, 0) + 1);
        }
        return m;
    }

    // Return top K entries by value (descending)
    public static <K> List<Map.Entry<K, Integer>> topK(Map<K, Integer> counts, int k) {
        List<Map.Entry<K, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        if (k < 0) k = 0;
        if (k < list.size()) return list.subList(0, k);
        return list;
    }

    // Pretty-print entries
    public static String formatTopK(List<? extends Map.Entry<?, Integer>> top) {
        if (top.isEmpty()) return "(no data)\n";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < top.size(); i++) {
            Map.Entry<?, Integer> en = top.get(i);
            sb.append(i + 1)
                    .append(") ")
                    .append(en.getKey())
                    .append(" -> ")
                    .append(en.getValue())
                    .append("\n");
        }
        return sb.toString();
    }
}
