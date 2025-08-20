public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long totalTax = 0;

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n;
        System.out.println("Average: " + avg);
    }

    private static long calcTax(long income) {
        long tax = 0;

        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 對每筆收入，計算稅額需要檢查固定 4 個級距 → O(1)。
 * 2. 總共有 n 筆收入 → O(n)。
 * 3. 累加總稅額與計算平均同樣是 O(n)。
 * 因此整體時間複雜度為 O(n)，適用於 n ≤ 1000 的限制。
 */
