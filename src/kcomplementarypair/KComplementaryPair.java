/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcomplementarypair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 * @author fulvi
 */
public class KComplementaryPair {

    private int theArray[] = {};
    private int theNumber;
    private int theNumberOfPairs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int arr[] = {4, 5, 6, -4, 3, 1, 8, -7, -6};
        int k = 1;
        System.out.println(new KComplementaryPair(arr, k).process());
        if (args.length == 2 ) {
            int theArr[] = Arrays.asList(args[0]).stream().mapToInt(Integer::parseInt).toArray();
            int number = Integer.parseInt(args[1]);
            System.out.println(new KComplementaryPair(theArr, number).process());
        }
    }

    public KComplementaryPair(int arr[], int number) {
        this.theArray = arr;
        this.theNumber = number;
    }

    public int process() {
        return this.theNumberOfPairs = this.noOfComplementaryPairs(this.theArray, this.theNumber);
    }
/**
 * O(n) complexity
 * 
 * @param arr
 * @param k
 * @return the quantity of itens that sum k in provided arr
 */
    private int noOfComplementaryPairs(int arr[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        IntStream.of(arr).forEach(value -> map.merge(k - value, 1, Integer::sum));
        return Arrays.stream(arr).map(element -> map.getOrDefault(element, 0)).sum();
    }

}
