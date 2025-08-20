public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int[] times = new int[n];  
        String[] raw = new String[n];
        for (int i = 0; i < n; i++) {
            raw[i] = sc.nextLine().trim();
            times[i] = toMinutes(raw[i]);
        }

        String queryStr = sc.nextLine().trim();
        int query = toMinutes(queryStr);

        int idx = upperBound(times, query);
        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(raw[idx]);
        }
    }
    private static int toMinutes(String t) {
        String[] parts = t.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    private static int upperBound(int[] arr, int key) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l; 
    }
}
