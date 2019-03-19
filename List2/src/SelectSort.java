public class SelectSort {
    private int comparisionsCounter = 0;
    private int swapsCounter = 0;

    SelectSort(int[] arrayToSort) {
        double startTime = System.nanoTime();
        for (int i = 0; i < arrayToSort.length; i++) {
            int min = arrayToSort[i];
            int minIndex = i;
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (min >= arrayToSort[j]) {
                    minIndex = j;
                    min = arrayToSort[j];
                }
                comparisionsCounter++;
            }
            int tmp = arrayToSort[i];
            arrayToSort[i] = min;
            arrayToSort[minIndex] = tmp;
            swapsCounter++;

        }
        System.out.println("Comparisions: " + comparisionsCounter);
        System.out.println("Swaps: " + swapsCounter);
        double stopTime = System.nanoTime();
        double elapsedTime = (stopTime - startTime)/(10^9);
        System.out.println("SelectSort Execution time: " + elapsedTime+"s");
    }
}
