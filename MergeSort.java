public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right, int split){
        int leftLen = split - left + 1;
        int rightLen = right - split;
        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];
        for(int i = 0; i < leftLen; ++i){
            leftArr[i] = arr[left + i];
        }
        for(int j = 0; j < rightLen; ++j){
            rightArr[j] = arr[split + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;
        while(i < leftLen && j < rightLen){
            if(leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i];
                i++;
            }
            else{
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
    }
    public static void sort(int[] arr, int left, int right){
        if(left < right){
            int split = (left + right)/2;
            sort(arr, left, split);
            sort(arr, split + 1, right);
            mergeSort(arr, left, right, split);
        }
    }
}
