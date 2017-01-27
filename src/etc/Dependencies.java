package etc;
/**
 * Created by mccccopley on 1/19/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Dependencies {
    public static class Dependency {
        int id;
        int dependsOnId;

        public Dependency(int id, int dependsOnId) {
            this.id = id;
            this.dependsOnId = dependsOnId;
        }
    }

    public static List<Integer> GetDependencyOrder(int[] ids, Dependency[] dependencies) {
        HashMap<Integer, List<Integer>> dependents = new HashMap<>();
        HashMap<Integer, Integer> dependencyCounts = new HashMap<>();

        LinkedList<Integer> order = new LinkedList<>();

        for (int id : ids) {
            dependencyCounts.put(id, 0);
            dependents.put(id, new LinkedList<>());
        }

        for (Dependency dependency : dependencies) {
            dependencyCounts.put(dependency.id, dependencyCounts.get(dependency.id) + 1);
            dependents.get(dependency.dependsOnId).add(dependency.id);
        }

        while (order.size() < ids.length) {
            boolean wereIdsAdded = false;
            for (int id : dependencyCounts.keySet()) {
                if (dependencyCounts.get(id) == 0) {
                    wereIdsAdded = true;
                    order.add(id);
                    dependencyCounts.put(id, -1);
                    for (int dependent : dependents.get(id)) {
                        dependencyCounts.put(dependent, dependencyCounts.get(dependent) - 1);
                    }
                }
            }

            if (!wereIdsAdded) {
                return null;
            }
        }

        return order;
    }
}
