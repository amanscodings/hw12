import java.util.*;

public class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> allPipes = new ArrayList<>();
        for (int[] pipe : pipes) {
            allPipes.add(pipe);
        }
        
        for (int i = 0; i < n; i++) {
            allPipes.add(new int[]{0, i + 1, wells[i]});
        }
        
        Collections.sort(allPipes, (a, b) -> a[2] - b[2]);
        
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        int minCost = 0;
        for (int[] pipe : allPipes) {
            int u = find(parent, pipe[0]);
            int v = find(parent, pipe[1]);
            if (u != v) {
                parent[u] = v;
                minCost += pipe[2];
            }
        }
        
        return minCost;
    }
    
    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[] wells = {1, 2, 2};
        int[][] pipes = {{1, 2, 1}, {2, 3, 1}};
        int minCost = solution.minCostToSupplyWater(n, wells, pipes);
        System.out.println("Minimum total cost to supply water to all houses: " + minCost);
    }
}
