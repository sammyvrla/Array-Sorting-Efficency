public class InsertionSort {
    public static void insertionSort(int[] arr){
        int len = arr.length;
        for(int i = 1; i < len; i++){
            int spot = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > spot){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = spot;
        }
    }
}
