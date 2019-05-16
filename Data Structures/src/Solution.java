class Solution {
    /**
     * Sort elements with the quicksort algorithm.
     * @param elements the elements to be sorted
     */
    public static void quickSort(int[] elements) {
        quickSort(elements, 0, elements.length - 1);
    }

    /**
     * The recursive quicksort method.
     * @param elements the elements to be sorted
     * @param low low index boundary
     * @param high high index boundary
     */
    public static void quickSort(int[] elements, int low, int high) {
        if(elements == null || elements.length == 0 || high <= low){
            return;
        }
        int pivot = partition(elements, low, high);
        quickSort(elements, low, pivot-1);
        quickSort(elements, pivot + 1, high);
    }

    /**
     * Pick a pivot, partition the elements according to that, and return
     * the position `p` of the pivot in the partitioned array. All elements
     * before `p` will be smaller than `element[p]` and all elements after
     * position `p` will be larger than `element[p]`.
     * @param elements the array with elements
     * @param low low index boundary
     * @param high high index boundary
     * @return the position of the element that is in its final position.
     */
    public static int partition(int[] elements, int low, int high) {
        int pivot = elements[high];
        int l = low;
        int h = high-1;

        while(l <= h){
            while(elements[l] <= pivot && l < high){
                l++;
            }
            while(elements[h] > pivot && h > low ){
                h--;
            }
            if(h < l){
                swap(elements, l, h);
            }
        }
        swap(elements, l, high);
        return l;
    }

    /**
     * In-place array swap
     * @param elements the array with elements
     * @param a index of first element to swap
     * @param b index of second element to swap
     */
    public static void swap(int[] elements, int a, int b) {
        int temp = elements[a];
        elements[a] = elements[b];
        elements[b] = temp;
    }

    public static void main(String[] args){
        int[] a = {3, 15, 2, 65, 34, 2, 54};
        quickSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
