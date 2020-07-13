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

    public int[] getDisjointSet() {
        return disjointSet;
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex > numOfVertices - 1) {
            throw new IndexOutOfBoundsException("The input is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        v1 = parent(v1);
        if(v1 >= 0) {
            return -this.disjointSet[v1];
        }
        return -v1;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        v1 = this.disjointSet[v1];
        if(v1 < 0) return v1;

        while (disjointSet[v1] >= 0) v1 = disjointSet[v1];

        return v1;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (this.disjointSet[v1] >= 0) {
            v1 = parent(v1);
        }

        if (this.disjointSet[v2] >= 0) {
            v2 = parent(v2);
        }

        return v1 == v2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        UnionFind disjointSet = new UnionFind(7);
        disjointSet.disjointSet = new int[] {-4, 0, 1, -2, 0, 3, -1};
        System.out.println(disjointSet.connected(4, 2));
    }
}
