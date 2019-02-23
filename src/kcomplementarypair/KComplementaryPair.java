package kcomplementarypair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 * @author Fulvius
 */
public class KComplementaryPair {

    private int theArray[] = {};
    private final int theNumber;
    private int theNumberOfPairs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int arr[] = {4, 5, 6, -4, 3, 1, 8, -7, -6};
        int k = 1;
        System.out.println(new KComplementaryPair(arr, k).process());
        if (args.length == 2) {
            int theArr[] = Arrays.asList(args[0]).stream().mapToInt(Integer::parseInt).toArray();
            int number = Integer.parseInt(args[1]);
            System.out.println(new KComplementaryPair(theArr, number).process());
        }
    }

    /**
     * Constructor for KComplementaryPair
     *
     * @param arr the int array to be processed.
     * @param number the number that is the sum of 2 elements in array (K-Complementary Pair) 
     */
    public KComplementaryPair(int arr[], int number) {
        this.theArray = arr;
        this.theNumber = number;
    }

    /**
     * Sets theNumberOfPairs with the quantity of itens that sum this.theNumber
     * in this.theArray
     *
     * @return theNumberOfPairs
     */
    public int process() {
        return this.theNumberOfPairs = this.noOfComplementaryPairs(this.theArray, this.theNumber);
    }

    /**
     * O(n) complexity
     *
     * Go through the array once, and store in a Map the difference of the
     * wanted sum and the current element mapped to how many times it occured.
     * Effectively, this map remembers how much we're missing for an element at
     * a given index so that the sum can be reached.
     *
     * Go through the array a second time, and check whether the map contains
     * this element. If it does, then it means that our map contains an element
     * e for which e = sum - arr[i], so it means that we've found a matching
     * pair. And the number of matching pair we found, is the number of times
     * this element appears in the array, which is the value of the map.
     *
     * @param arr
     * @param k
     * @return the quantity of itens that sum k in provided arr
     */
    private int noOfComplementaryPairs(int arr[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        IntStream.of(arr).forEach(value -> map.merge(k - value, 1, Integer::sum)); //1st passs
        return Arrays.stream(arr).map(element -> map.getOrDefault(element, 0)).sum(); //2nd pass
    }

}
