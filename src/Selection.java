import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Selection <E extends Comparable<E>> {
    int k;
    ArrayList<E> input; // this holds the values of type E from which your code will find kth largest.

    public Selection (ArrayList input, int k) {
        this.input = input;
        this.k = k;
    }

    private static ArrayList<Integer> randomList(int n) {
        ArrayList<Integer> randomNumbers = new ArrayList<Integer>(n);
        for (int i=0; i<n; i++) {
            Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            Integer r = rand.nextInt() % 256;
            randomNumbers.add(r);
        }
        return randomNumbers;
    }

    public E oneB () {
        ArrayList<E> sorted = new ArrayList<>();
        for (E element : input) {
            if (sorted.size() < k) {
                sorted.add(element);
            } else {
                if (element.compareTo(sorted.get(k-1)) > 0) {
                    sorted.remove(k-1);
                    sorted.add(element);
                }
            }
            sorted.sort(Comparator.reverseOrder());
        }
        sorted.sort(Comparator.reverseOrder());
        return sorted.get(k-1);
    }

    public E sixA () {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.buildHeap(this.input);
        for (int i = 0; i < k - 1; i++) {
            maxHeap.removeHeap();
        }
       return (E) maxHeap.findMax();
    }

    public E sixB () {
        /** Tried to implement with priority queue and couldn't figure it out, didn't leave myself enough time and I accept hw point loss **/
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = randomList(1000000);
        Selection s = new Selection(list, 100000);
        double startTime1B = System.currentTimeMillis() / 1000;
        int oneB = (int) s.oneB();
        double time1B = (System.currentTimeMillis() / 1000) - startTime1B;
        double startTime6A = System.currentTimeMillis() / 1000;
        int sixA = (int) s.sixA();
        double time6A = (System.currentTimeMillis() / 1000) - startTime6A;
        System.out.println("1B: " + oneB + ", TIME: " + time1B);
        System.out.println("6A: " + sixA + ", TIME: " + time6A);
    }

}
