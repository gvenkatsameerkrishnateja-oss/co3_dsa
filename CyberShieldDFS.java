import java.util.*;

public class CyberShieldDFS {
    private int vertices;
    private LinkedList<Integer>[] adj;

    CyberShieldDFS(int v) {
        vertices = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    void addConnection(int source, int destination) {
        adj[source].add(destination);
        adj[destination].add(source);
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal: ");
        DFSUtil(start, visited);
    }

    public static void main(String[] args) {
        CyberShieldDFS network = new CyberShieldDFS(6);

        network.addConnection(0, 1);
        network.addConnection(0, 2);
        network.addConnection(1, 3);
        network.addConnection(2, 4);
        network.addConnection(4, 5);

        network.DFS(0);
    }
}