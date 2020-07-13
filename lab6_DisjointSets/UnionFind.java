import java.security.InvalidParameterException;

public class UnionFind {
    private int[] disjointSet;
    private int numOfVertices;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if(n<=0) {
            throw new InvalidParameterException("The number of vertices must be greater than 0");
        }
        numOfVertices = n;
        disjointSet = new int[n];
        while (n > 0) {
            disjointSet[n-1] = -1;
            n--;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex > numOfVertices - 1) {
            throw new IndexOutOfBoundsException("The input is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -this.disjointSet[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return this.disjointSet[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v2's root to v1's root. Unioning a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) return;
        int rootOfV1 = find(v1);
        int rootOfV2 = find(v2);
        if (sizeOf(v1) < sizeOf(v2)) {
            this.disjointSet[rootOfV2] += this.disjointSet[rootOfV1];
            this.disjointSet[rootOfV1] = rootOfV2;
        } else {
            this.disjointSet[rootOfV1] += this.disjointSet[rootOfV2];
            this.disjointSet[rootOfV2] = rootOfV1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (disjointSet[root] >= 0) root = disjointSet[root];
        int nextVertex;
        while (vertex != root) {
            nextVertex = disjointSet[vertex];
            disjointSet[vertex] = root;
            vertex = nextVertex;
        }
        return root;
    }
}
