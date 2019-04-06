import java.util.Arrays;

public class RadixDriver {
    public static void main(String[] args) {
        int[] randomNums = generateIntArray(100000, 0, 999);
        printArr(randomNums);
        Radix.radixsort(randomNums);
        printArr(randomNums);
    }

    public static int[] generateIntArray(int count, int start, int end) {
        int[] elementsArr = new int[count];
        for (int i = 0; i < elementsArr.length; i++) {
            elementsArr[i] = (int)(Math.random() * (end - start + 1)) + start;
        }
        return elementsArr;
    }

    public static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
