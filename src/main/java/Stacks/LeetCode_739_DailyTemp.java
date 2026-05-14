package Stacks;

public class LeetCode_739_DailyTemp {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length]; // Initialize the result array with zeros
        java.util.Stack<Integer> stack = new java.util.Stack<>(); // Stack to keep track of indices

        for (int i = 0; i < temperatures.length; i++) {
            int val = temperatures[i];
            // While stack is not empty and current temperature is greater than
            // the temperature represented by the index at the top of the stack
            while (!stack.isEmpty() && val > temperatures[stack.peek()]) {
                int index = stack.pop(); // Pop from stack
                result[index] = i - index; // Update the result
            }
            stack.push(i); // Add current temperature index to stack
        }
        return result;
    }

    // ── Helper: compare two int arrays ────────────────────────────────────
    private static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // ── Helper: format int array as readable string ────────────────────────
    private static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    // ── Helper: run one test and print result ──────────────────────────────
    private static boolean runTest(LeetCode_739_DailyTemp sol, String label,
                                   int[] input, int[] expected) {
        int[] got = sol.dailyTemperatures(input);
        boolean pass = arraysEqual(got, expected);
        System.out.println(label);
        System.out.println("  Input    : " + arrToString(input));
        System.out.println("  Expected : " + arrToString(expected));
        System.out.println("  Got      : " + arrToString(got) + "  " + (pass ? "✅" : "❌"));
        System.out.println();
        return pass;
    }

    // ── Main — test scenarios ──────────────────────────────────────────────
    public static void main(String[] args) {
        LeetCode_739_DailyTemp sol = new LeetCode_739_DailyTemp();
        int passed = 0, failed = 0;

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║       LeetCode 739 — Daily Temperatures Tests         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        // ── Test 1: LeetCode example 1 ────────────────────────────────────
        // 73→1(74), 74→1(75), 75→4(76), 71→2(72), 69→1(72), 72→1(76), 76→0, 73→0
        if (runTest(sol, "Test 1: LeetCode Example 1",
                new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                new int[]{ 1,  1,  4,  2,  1,  1,  0,  0})) passed++; else failed++;

        // ── Test 2: Strictly increasing ───────────────────────────────────
        // Each day only waits 1 day for the next; last day has no warmer day.
        if (runTest(sol, "Test 2: Strictly Increasing",
                new int[]{30, 40, 50, 60},
                new int[]{ 1,  1,  1,  0})) passed++; else failed++;

        // ── Test 3: Strictly decreasing ───────────────────────────────────
        // No warmer day ever comes for any element → all zeros.
        if (runTest(sol, "Test 3: Strictly Decreasing — All Zeros",
                new int[]{60, 50, 40, 30},
                new int[]{ 0,  0,  0,  0})) passed++; else failed++;

        // ── Test 4: All same temperatures ─────────────────────────────────
        // Temperatures never rise → all zeros.
        if (runTest(sol, "Test 4: All Same Temperatures",
                new int[]{50, 50, 50, 50},
                new int[]{ 0,  0,  0,  0})) passed++; else failed++;

        // ── Test 5: Single element ─────────────────────────────────────────
        // Only one day — no next warmer day possible.
        if (runTest(sol, "Test 5: Single Element",
                new int[]{55},
                new int[]{ 0})) passed++; else failed++;

        // ── Test 6: Two elements — warmer next day ─────────────────────────
        if (runTest(sol, "Test 6: Two Elements — Warmer Next Day",
                new int[]{40, 50},
                new int[]{ 1,  0})) passed++; else failed++;

        // ── Test 7: Two elements — cooler next day ─────────────────────────
        if (runTest(sol, "Test 7: Two Elements — Cooler Next Day",
                new int[]{50, 40},
                new int[]{ 0,  0})) passed++; else failed++;

        // ── Test 8: Peak in the middle ─────────────────────────────────────
        // 60 is the global peak; temps before it wait to reach 60,
        // temps after it never find a warmer day.
        if (runTest(sol, "Test 8: Peak in the Middle",
                new int[]{40, 50, 60, 55, 45},
                new int[]{ 2,  1,  0,  0,  0})) passed++; else failed++;

        // ── Test 9: Long wait for warmer day ──────────────────────────────
        // 30 must wait 5 days until 35 appears; each subsequent day waits one less.
        if (runTest(sol, "Test 9: Long Wait for Warmer Day",
                new int[]{30, 29, 28, 27, 26, 35},
                new int[]{ 5,  4,  3,  2,  1,  0})) passed++; else failed++;

        // ── Test 10: Alternating high-low ─────────────────────────────────
        // Every low (40) waits 1 day for the next high (80);
        // highs (80) are never exceeded so they get 0.
        if (runTest(sol, "Test 10: Alternating High-Low",
                new int[]{80, 40, 80, 40, 80},
                new int[]{ 0,  1,  0,  1,  0})) passed++; else failed++;

        // ── Summary ───────────────────────────────────────────────────────
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("  Results: " + passed + " passed  |  " + failed + " failed  |  " + (passed + failed) + " total");
        System.out.println("══════════════════════════════════════════════════════");
    }
}
