public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxFreq = 0, mostFrequent = arr[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public static int[][] splitArray(int[] arr) {
        Arrays.sort(arr);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int sumLeft = 0, sumRight = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (sumLeft <= sumRight) {
                left.add(arr[i]);
                sumLeft += arr[i];
            } else {
                right.add(arr[i]);
                sumRight += arr[i];
            }
        }

        return new int[][] {
            left.stream().mapToInt(Integer::intValue).toArray(),
            right.stream().mapToInt(Integer::intValue).toArray()
        };
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 5, 6, 7};
        int[] arr3 = {1, 2, 2, 3, 3, 3, 4};

        System.out.println("=== 移除重複元素 ===");
        System.out.println(Arrays.toString(removeDuplicates(arr3)));

        System.out.println("=== 合併排序陣列 ===");
        System.out.println(Arrays.toString(mergeSortedArrays(arr1, arr2)));

        System.out.println("=== 出現最頻繁的元素 ===");
        System.out.println(findMostFrequent(arr3));

        System.out.println("=== 分割陣列 ===");
        int[][] split = splitArray(arr2);
        System.out.println("Left: " + Arrays.toString(split[0]));
        System.out.println("Right: " + Arrays.toString(split[1]));
    }
}
