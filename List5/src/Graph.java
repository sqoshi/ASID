import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Graph {
    public int maxFlow;
    public int paths;
    public double time;
    int k;
   // final int V = (int) Math.pow(2, k);
    int cap[][];
    ArrayList<Integer> Lotery;
    int[][] adjmatrix;
    int[][] flows;
    int[][] residualCap;
    int[] P;

    public Graph(int k) {
        this.k = k;

        P = new int[(int) Math.pow(2, k)];
        adjmatrix = new int[(int) Math.pow(2, k)][(int) Math.pow(2, k)];
        flows = new int[(int) Math.pow(2, k)][(int) Math.pow(2, k)];
        residualCap = new int[(int) Math.pow(2, k)][(int) Math.pow(2, k)];
        cap = new int[(int) Math.pow(2, k)][(int) Math.pow(2, k)];
        Lotery = new ArrayList<>();

        for (int i = 0; i <= Math.pow(2, k) - 1; i++) {
            for (int j = 0; j <= Math.pow(2, k) - 1; j++) {
                if (i < j) {
                    if (differAtOneBitPos(j, i)) {
                        adjmatrix[i][j] = 1;
                        int l = maxFromHandZ(i, j);
                        System.out.println(l + " is max");
                        for (int m = 1; m <= (int) Math.pow(2, l); m++) {
                            Lotery.add(m);
                            Collections.shuffle(Lotery);
                            // System.out.println(m);
                        }
                        cap[i][j] = Lotery.get(0);
                    }
                }

            }
        }
        //   for (int i = 0; i <= Math.pow(2, k) - 1; i++) {
        //       for (int j = 0; j <= Math.pow(2, k) - 1; j++) {
        //           residualCap[i][j] = cap[i][j] - flows[i][j];
//
        //       }
        //   }
//

        printer(adjmatrix);
        System.out.println();
        printer(cap);
        System.out.println();

        System.out.println();
        System.out.println();
        System.out.println(fordFulkerson(cap,0,7));
        System.out.println();
        //breadthFirst(0, 7);
        //   System.out.println(Lotery(2));


    }

    static boolean isPowerOfTwo(int x) {

        return x != 0 && ((x & (x - 1)) == 0);
    }

    static boolean differAtOneBitPos(int a, int b) {
        return isPowerOfTwo(a ^ b);
    }

    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        int V=(int) Math.pow(2, k);
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;


        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;


        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }


        return (visited[t] == true);
    }

    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;
        int V=(int) Math.pow(2, k);

        int[][] rGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[V];

        int max_flow = 0;

        while (bfs(rGraph, s, t, parent)) {

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }


            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        return max_flow;
    }


    int maxFromHandZ(int v, int u) {
        return Math.max(H(v), Math.max(H(u), Math.max(Z(v), Z(u))));
    }

    //zero
    private int Z(int n) {
        int count0 = 0;
        while (n != 0) {
            if (n % 2 == 0)
                count0++;
            n /= 2;
        }
        return (count0);
    }

    //one
    private int H(int n) {
        int count1 = 0;
        while (n != 0) {
            if (n % 2 != 0)
                count1++;
            n /= 2;
        }
        return (count1);
    }


    public void printer(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }
}
