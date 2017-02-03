package etc;

/**
 * Created by mccccopley on 1/31/2017.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CountOccurences {
    static class HashTableElement {
        String key;
        int occurences;

        HashTableElement(String key, int occurences) {
            this.key = key;
            this.occurences = occurences;
        }
    }

    static class HashTable {
        private static final int tableSize = 10;
        private static ArrayList<LinkedList<HashTableElement>> table = null;

        static int getBucketForKey(String key) {
            int hashCode = key.hashCode();
            int bucket = hashCode % tableSize;
            bucket = bucket < 0 ? - bucket : bucket;
            return bucket;
        }

        static int getOccurences(String key) {
            int bucket = getBucketForKey(key);
            LinkedList<HashTableElement> bucketList = table.get(bucket);
            if (bucketList == null) {
                return 0;
            }
            HashTableElement listElement = null;
            Iterator<HashTableElement> iter = bucketList.iterator();
            while (iter.hasNext()) {
                listElement = iter.next();
                if (listElement.key.equals(key)) {
                    return listElement.occurences;
                }
            }
            return 0;
        }

        static void incrementOccurences(String key) {
            if (table == null) {
                table = new ArrayList<>(tableSize);
                while(table.size() < tableSize) {
                    table.add(null);
                }
            }

            int bucket = getBucketForKey(key);
            LinkedList<HashTableElement> bucketList = table.get(bucket);
            if (bucketList == null) {
                bucketList = new LinkedList<>();
                table.set(bucket, bucketList);
            }
            HashTableElement listElement = null;
            Iterator<HashTableElement> iter = bucketList.iterator();
            while (iter.hasNext()) {
                HashTableElement nextElement = iter.next();
                if (nextElement.key.equals(key)) {
                    listElement = nextElement;
                    break;
                }
            }

            if (listElement == null) {
                listElement = new HashTableElement(key, 0);
                bucketList.add(listElement);
            }
            ++listElement.occurences;
        }
    }

    public static void countOccurences(String[] array, String[] queries) {
        for (int index = 0; index < array.length; ++index) {
            HashTable.incrementOccurences(array[index]);
        }
        for (int index = 0; index < queries.length; ++index) {
            System.out.println(HashTable.getOccurences(queries[index]));
        }
    }
}