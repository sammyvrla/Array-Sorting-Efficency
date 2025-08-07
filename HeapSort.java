public class HeapSort {
    public void heapSort(int[] arr){
        int len = arr.length;
        for(int i = len/2 - 1; i >= 0; i--){
            heap(arr, len, i);

        }
        for(int i = len-1; i >= 0; i--){
            int tempArr = arr[0];
            arr[0] = arr[i];
            arr[i] = tempArr;
            heap(arr, i, 0);
        }
    }
    void heap(int[] arr, int len, int i){
        int biggest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left < len && arr[left] > arr[biggest]){
            biggest = left;
        }
        if(right < len && arr[right] > biggest){
            biggest = right;
        }
        if(biggest != i){
            int swap = arr[i];
            arr[i] = arr[biggest];
            arr[biggest] = swap;
            heap(arr, len, biggest);
        }
    }
}
