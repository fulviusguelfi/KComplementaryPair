package kcomplementarypair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * KComplementaryPair was specifically maded for a job test
 * 
 * @author Fulvius
 */
public class KComplementaryPair {

    private int theArray[] = {};
    private final int theNumber;
    private Map thePairs;

    /**
     * arr is a integer array.
     * number is the sum to be chcked agais arr.
     * Usage:
     * Comnadline: java KComplementaryPair arr number
     * (output goes to console)
     * Object: 
     * List l = new KComplementaryPair(arr, number).process();
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2 && args[0].contains(",")) {
            int[] argArray = Arrays.stream(args[0].split(Pattern.quote(","))).mapToInt(item -> Integer.parseInt(item.trim())).toArray();
            System.out.print("For: ");
            System.out.println(args[0]);
            System.out.print("The K-Complementary Pairs are ");
            System.out.println(new KComplementaryPair(argArray, Integer.parseInt(args[1])).process());
            System.out.println("Complexity Big-O = O(n) ");
            System.out.println("");
        }
    }

    /**
     * Constructor for KComplementaryPair
     *
     * @param arr the int array to be processed.
     * @param number the number that is the sum of 2 elements in array
     * (K-Complementary Pair)
     */
    public KComplementaryPair(int arr[], int number) {
        this.theArray = arr;
        this.theNumber = number;
    }

    /**
     * Sets theNumberOfPairs with the quantity of itens that sum this.theNumber
     * in this.theArray
     *
     * @return thePairs printed in console
     */
    public Map process() {
        this.thePairs = this.getKComplementaryPairs(this.theArray, this.theNumber);
        return this.thePairs;
    }

    /**
     * Big-O = O(n)
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
     * @param arr to be verifyede
     * @param k sum to check in arr
     * @return the quantity of itens that sum k in provided arr
     */
    private Map<Integer, Integer> getKComplementaryPairs(int arr[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        IntStream.of(arr).forEach(value -> map.merge(k - value, 1, Integer::sum)); //1st passs
        return Arrays.stream(arr).filter((element) -> map.containsKey(element)) //2nd pass
                .collect(HashMap<Integer, Integer>::new, (mapIndex, element) -> mapIndex.put(element, k - element), Map::putAll);//to show
    }
}
