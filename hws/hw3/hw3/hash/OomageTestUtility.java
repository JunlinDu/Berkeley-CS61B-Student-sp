package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {

    /* A utility function that returns true if the given oomages
     * have hashCodes that would distribute them fairly evenly across
     * M buckets.
     */
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        ArrayList<Oomage>[]buckets = new ArrayList[M];

        for (int i = 0; i < M; i++) {
            buckets[i] = new ArrayList<Oomage>();
        }

        int bucketNum;
        int n = 0;
        for(Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum].add(o);
            n++;
        }

        for (ArrayList<Oomage> list : buckets) {
            if (list.size() < n / 50 || list.size() > n / 2.5) {
                return false;
            }
        }

        return true;
    }
}
