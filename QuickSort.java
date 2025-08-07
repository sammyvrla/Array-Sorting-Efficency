public class QuickSort {
    public static void quickSort(int[] arr, int left, int right){
        if(left < right){
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }
    public static int partition(int[] arr, int left, int right){
        int pivot = arr[right];
        int i = left-1;
        
        for(int j = left; j <= right-1; j++){
            if(arr[i] < pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, right);
        return i+1;
    }
    public static void swap(int[] arr, int i, int j){
        int tempArr = arr[i];
        arr[i] = arr[j];
        arr[j] = tempArr;
    }
}
