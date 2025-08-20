public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b; 

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：
 * 1. gcd 遞迴每次把 (x, y) → (y, x % y)，數值快速縮小。
 * 2. 最壞情況發生在 Fibonacci 數對，但仍是 O(log(min(a, b)))。
 * 3. LCM 計算只需一次乘法與除法 → O(1)。
 * 因此總時間複雜度為 O(log(min(a, b))).
 */
