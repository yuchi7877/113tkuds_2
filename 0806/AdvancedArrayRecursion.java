public class AdvancedArrayRecursion {
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeHelper(a, 0, b, 0);
    }

    private static int[] mergeHelper(int[] a, int i, int[] b, int j) {
        if (i == a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length) return Arrays.copyOfRange(a, i, a.length);

        if (a[i] <= b[j]) {
            int[] rest = mergeHelper(a, i + 1, b, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = mergeHelper(a, i, b, j + 1);
            return prepend(b[j], rest);
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = val;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    public static int quickSelect(int[] arr, int k) {
        return quickSelectHelper(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex)
            return arr[k];
        else if (k < pivotIndex)
            return quickSelectHelper(arr, left, pivotIndex - 1, k);
        else
            return quickSelectHelper(arr, pivotIndex + 1, right, k);
    }

    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumHelper(arr, 0, target);
    }

    private static boolean subsetSumHelper(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        return subsetSumHelper(arr, index + 1, target) ||
               subsetSumHelper(arr, index + 1, target - arr[index]);
    }

    public static void main(String[] args) {
        int[] arr1 = {8, 3, 1, 6, 5};
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("快速排序後：" + Arrays.toString(arr1));

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        System.out.println("合併後陣列：" + Arrays.toString(mergeSortedArrays(a, b)));

        int[] arr2 = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("第 " + k + " 小元素是：" + quickSelect(arr2.clone(), k));

        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("存在總和為 " + target + " 的子序列？ " + subsetSum(arr3, target));
    }
}
