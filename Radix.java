import java.util.Iterator;

public class Radix {
    public static void radixsort(int[] data){
        LinkedList<Integer> dataList = new LinkedList<>();
        LinkedList<Integer>[] temp = new LinkedList[10]; //0 to 9

        for (int i = 0; i < temp.length; i++) {
            temp[i] = new LinkedList<>();
        }

        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            max = Math.max(max, data[i]);
            dataList.addAsTail(data[i]);
        }

        int passes = (int) Math.log10(max) + 1;

        for (int i = 0; i < passes; i++) {
            //System.out.println(i);
            for (int j = 0; j < temp.length; j++) {
                temp[j] = new LinkedList<>();
            }
            for (int num : dataList) {
                temp[Math.abs(getDigitFromRight(num, i))].addAsTail(num);
            }
            LinkedList<Integer> total = new LinkedList<>();
            for (LinkedList<Integer> slot : temp) {
                total.extend(slot);
            }
            dataList = total;
        }

//        for (int j = 0; j < temp.length; j++) {
//            temp[j] = new LinkedList<>();
//        }

        //System.out.println("goo");

//        for (int num : dataList) {
//            temp[num < 0 ? 0 : 1].addAsTail(num, true);
//        }
//        LinkedList<Integer> total = new LinkedList<>();
//        temp[0].reverse();
//        total.extend(temp[0]);
//        total.extend(temp[1]);
        //dataList = total;

        //System.out.println("foo");

        Iterator<Integer> iter = dataList.iterator();
        int count = 0;
        while (iter.hasNext()) {
            Integer val = iter.next();
            //System.out.println("bro " + val);
            if (count < data.length)
            data[count] = val;
            count++;
        }
        //System.out.println("Final Count: " + count);

    }

    private static int getDigitFromRight(int number, int digit) {
        for (int i = 0; i < digit; i++, number/=10);
        return number%10;
    }
}
