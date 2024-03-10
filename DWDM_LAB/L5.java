import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                List<String> transaction = Arrays.asList(parts);
                data.add(transaction.subList(1, transaction.size())); // Exclude the transaction ID
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Min Support");
        int minSupport = scanner.nextInt();

        List<Set<Set<String>>> candidateSet = new ArrayList<>();
        List<Set<Set<String>>> frequentSet = new ArrayList<>();

        Set<String> items = data.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        for (int iterNo = 1; iterNo <= items.size(); iterNo++) {
            Map<Set<String>, Integer> count = new HashMap<>();
            Set<Set<String>> intermediate = new HashSet<>();

            if (iterNo == 1) {
                candidateSet.add(items.stream().map(Collections::singleton).collect(Collectors.toSet()));
                for (String txn : items) {
                    int ctr = 0;
                    for (List<String> val : data) {
                        if (val.contains(txn)) {
                            ctr++;
                        }
                    }
                    count.put(Collections.singleton(txn), ctr);
                }
            } else {
                Set<Set<String>> lastFrequentSet = frequentSet.get(iterNo - 2);
                Set<Set<String>> combinations = getCombinations(lastFrequentSet, iterNo);
                candidateSet.add(combinations);
                for (Set<String> txn : combinations) {
                    int ctr = 0;
                    for (List<String> val : data) {
                        if (val.containsAll(txn)) {
                            ctr++;
                        }
                    }
                    count.put(txn, ctr);
                }
            }

            for (Map.Entry<Set<String>, Integer> entry : count.entrySet()) {
                if (entry.getValue() >= minSupport) {
                    intermediate.add(entry.getKey());
                }
            }

            if (intermediate.isEmpty()) {
                System.out.println(frequentSet);
                break;
            }

            frequentSet.add(intermediate);
        }
    }

    private static Set<Set<String>> getCombinations(Set<Set<String>> set, int size) {
        List<Set<String>> list = new ArrayList<>(set);
        Set<Set<String>> combinations = new HashSet<>();
        getCombinations(list, size, 0, new HashSet<>(), combinations);
        return combinations;
    }

    private static void getCombinations(List<Set<String>> list, int size, int index, Set<String> current, Set<Set<String>> combinations) {
        if (current.size() == size) {
            combinations.add(new HashSet<>(current));
            return;
        }
        if (index == list.size()) {
            return;
        }
        Set<String> element = list.get(index);
        current.addAll(element);
        getCombinations(list, size, index + 1, current, combinations);
        current.removeAll(element);
        getCombinations(list, size, index + 1, current, combinations);
    }
}
