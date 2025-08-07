import java.util.Random;
import java.util.Scanner;

public class ArraySorting{
    static void insertionSort(int[] arr){ //function header for insert ort declaring the array
        int len = arr.length; //gets length of the array
        for(int i = 1; i < len; i++){ //loop cunter to go through each element of the array
            int spot = arr[i]; //gets current element
            int j = i - 1; //j will be element before i
            while(j >= 0 && arr[j] > spot){ //checks if previous element is greater than current element
                arr[j+1] = arr[j]; //will switch the both elements 
                j--; //decrements j to get next element
            }
            arr[j+1] = spot; //puts current element in the spot
        }
    }

    static void quickSort(int[] arr, int left, int right, int cutoff){ //function header for quick sort
        if(right - left + 1 <= cutoff){ //when the size of array is less or equal to cutoff number, will call sub array function
            sortSubArray(arr, left, right);
        }
        else if(left < right){ //when the left index is less than right index, pivot will be the partition of the array
            int pivot = partition(arr, left, right); //
            quickSort(arr, left, pivot-1, cutoff); //calls quick sort for left side
            quickSort(arr, pivot+1, right, cutoff); //calls quick sort for right side
        }
    }
    static void sortSubArray(int[] arr, int left, int right){ //fucntion header for sub array sort
        for(int i = left + 1; i <= right; i++){  //loops through and gets the elements of the array
            int key = arr[i];
            int j = i - 1;
            while(j >= left && arr[j] > key){ //checks if last element is greater than current element and will loop thorugh until true
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; //intializes element of the array for the key
        }
    }
    static int partition(int[] arr, int left, int right){  //function header for partition
        int pivot = arr[right]; //delcares pivot with the right side of array
        int i = left-1;
        for(int j = left; j <= right-1; j++){
            if(arr[j] < pivot){ //checks if pivot is greater than the current element and will swithc elements when true
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, right); //goes to the next element in the array
        return i+1; //returns pivot index
    }
    static void swap(int[] arr, int i, int j){
        int tempArr = arr[i];
        arr[i] = arr[j];
        arr[j] = tempArr;
    }

    static void mergeSort(int[] arr, int left, int right, int split){ //function header for merge sort
        int leftLen = split - left + 1; //calculates left side of the array by removing the left index plus one from split index
        int rightLen = right - split; //calculates right side of the array by removing the split index 
        int[] leftArr = new int[leftLen]; //creates left array for left side
        int[] rightArr = new int[rightLen]; //creates right array for right side
        for(int i = 0; i < leftLen; ++i){ //loops through and gets left index of the array
            leftArr[i] = arr[left + i];
        }
        for(int j = 0; j < rightLen; ++j){ 
            rightArr[j] = arr[split + 1 + j]; //gets right index of the array
        }

        int i = 0;
        int j = 0;
        int k = left;
        while(i < leftLen && j < rightLen){ //checks if left/right indeices are less than their lengths
            if(leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i]; //left index less than right index, puts in left index of the array
                i++;
            }
            else{
                arr[k] = rightArr[j]; //puts in right index of the array
                j++;
            }
            k++;
        }
    }
    static void sort(int[] arr, int left, int right){
        if(left < right){ //checks if left index is less than right index
            int split = (left + right)/2; //calculates split index by adding left and right index and dividing by 2
            sort(arr, left, split); //calls sort function for left
            sort(arr, split + 1, right); //calls sort function for right
            mergeSort(arr, left, right, split); //calls merge sort function for left and right
        }
    }

    static void heapSort(int[] arr){ //function header for heap sort
        int len = arr.length;
        for(int i = len/2 - 1; i >= 0; i--){ //calculates the length of the array and decrements by 1 and calls heap function
            heap(arr, len, i);

        }
        for(int i = len-1; i >= 0; i--){ //loops through and puts elements in a temp array for sorting and calls heap function
            int tempArr = arr[0];
            arr[0] = arr[i];
            arr[i] = tempArr;
            heap(arr, i, 0);
        }
    }
    static void heap(int[] arr, int len, int i){ //function header for heap
        int biggest = i; //declares biggest as i
        int left = 2*i+1; //calculates left index by multiplying i by 2 and adding 1
        int right = 2*i+2; //calculates right index by multiplying i by 2 and adding 2
        if(left < len && arr[left] > arr[biggest]){ //when left is less than length of the array and left index is greater than biggest index
            biggest = left;
        }
        if(right < len && arr[right] > biggest){ //when right is less than length of the array and right index is greater than biggest index
            biggest = right;
        }
        if(biggest != i){ //when biggest index is not equal to i, will switch the elements and call heap function
            int swap = arr[i];
            arr[i] = arr[biggest];
            arr[biggest] = swap;
            heap(arr, len, biggest);
        }
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in); //delcares scanner and random object
        Random rand = new Random();

        System.out.println("Enter the amount of elements you want to see sorted: "); //prompts user for input
        int numElements = scnr.nextInt();
        int[] arr = new int[numElements];
        for(int i = 0; i < numElements; i++){ //loops through and gets the elements of the array
            arr[i] = rand.nextInt(numElements);
        }

        System.out.println("Insert Sort:"); 
        long startTime = System.nanoTime(); //timer start
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); //records memeroy
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); //calculates memory before sorting
        InsertionSort.insertionSort(arr); //does insertion sort
        long endTime = System.nanoTime(); //timer stop
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); //calculates memory after sorting
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms"); //gives time of calculation and prints it out
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB"); //gives memory used and prints it out

        //same process for quick sort
        System.out.println("Quick Sort cutoff 10: ");
        startTime = System.nanoTime();
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        quickSort(arr, 0, numElements - 1, 10);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms");
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB");

        System.out.println("Quick Sort cutoff 50: ");
        startTime = System.nanoTime();
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        quickSort(arr, 0, numElements - 1, 50);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms");
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB");

        System.out.println("Quick Sort cutoff 200: ");
        startTime = System.nanoTime();
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        quickSort(arr, 0, numElements - 1, 200);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms");
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB");

        System.out.println("Merge Sort: ");
        startTime = System.nanoTime();
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        mergeSort(arr, 0, numElements - 1, (numElements - 1)/2);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms");
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB");

        System.out.println("Heap Sort: ");
        startTime = System.nanoTime();
        runtime.gc();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        heapSort(arr);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime)/1000000.0 + " ms");
        System.out.println("Memory used: " + (memoryAfter - memoryBefore)/1024.0 + " KB");
    }
}