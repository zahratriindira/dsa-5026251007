package lw05.prelab;

import java.util.*;

public class Main {
    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsorder = new ArrayList<>();

    static void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    static void printPath(List<Integer> path) {
        for (int i=0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int i=0; i<size; i++) {
                if (graph[curr][i] > 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    static void dfs(int curr, Set<Integer> visited) {
        visited.add(curr);
        dfsorder.add(curr);

        for (int i=0; i<size; i++) {
            if (graph[curr][i] > 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void route(int[] prev, int current) {
        if (prev[current] != -1) {
            route(prev, prev[current]);
            System.out.print(" -> ");
        }
        System.out.print(names[current]);
    }
    
    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && graph[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                    }
                }
            }

            System.out.println("=== Shortest Time from " + names[start] + " ===");
            for (int i = 0; i < size; i++) {
                System.out.println(names[i] + " : " + dist[i] + " min");
            }

            System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");
            route(prev, destination);
            System.out.println("\nTotal Time: " + dist[destination] + " min");
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];
        graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int minutes = sc.nextInt();
            addEdge(v1, v2, minutes);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();
        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        List<Integer> bfsResult = bfs(start);
        printPath(bfsResult);

        System.out.println("=== DFS from " + names[start] + " ===");
        dfsorder.clear();
        dfs(start, new HashSet<>());
        printPath(dfsorder);

        dijkstraAlg(start, destination);
    }
}

