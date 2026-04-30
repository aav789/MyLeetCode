package Stacks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element — all in O(1) time.
 *
 * Approach: Two stacks
 *   - stack    → stores every pushed value (main stack)
 *   - minStack → parallel stack whose top always reflects the current minimum
 *                Push to minStack only when val ≤ current min, so the min
 *                is naturally restored when we pop the minimum off main stack.
 */
public class LeetCode_155_MinStack {

    // ── Internal stacks ────────────────────────────────────────────────────
    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;  // top always == current minimum

    // ── Constructor ────────────────────────────────────────────────────────
    public LeetCode_155_MinStack() {
        stack    = new Stack<>();
        minStack = new Stack<>();
    }

    // ── Push — O(1) ────────────────────────────────────────────────────────
    // Always push to main stack.
    // Push to minStack only when it is empty OR val ≤ current min,
    // so minStack top always equals the running minimum.
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // ── Pop — O(1) ─────────────────────────────────────────────────────────
    // Remove top of main stack.
    // If the removed value was the current minimum, pop minStack too
    // so the previous minimum is restored automatically.
    public void pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack Underflow — cannot pop from empty stack");
        }
        int removed = stack.pop();
        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    // ── Top — O(1) ─────────────────────────────────────────────────────────
    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty — nothing to peek");
        }
        return stack.peek();
    }

    // ── getMin — O(1) ──────────────────────────────────────────────────────
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty — no minimum available");
        }
        return minStack.peek();
    }

    // ── Main — test scenarios ──────────────────────────────────────────────
    public static void main(String[] args) {
        int passed = 0, failed = 0;

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         LeetCode 155 — Min Stack Tests            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        // ── Test 1: LeetCode example ───────────────────────────────────────
        System.out.println("── Test 1: LeetCode Example ─────────────────────────");
        LeetCode_155_MinStack ms1 = new LeetCode_155_MinStack();
        ms1.push(-2);
        ms1.push(0);
        ms1.push(-3);

        int r1 = ms1.getMin();   // -3
        System.out.println("getMin() after push(-2,0,-3) → Expected: -3  | Got: " + r1 + "  " + (r1 == -3 ? "✅" : "❌"));
        if (r1 == -3) passed++; else failed++;

        ms1.pop();               // removes -3
        int r2 = ms1.top();      // 0
        System.out.println("top()    after pop()         → Expected:  0  | Got: " + r2 + "  " + (r2 == 0  ? "✅" : "❌"));
        if (r2 == 0) passed++; else failed++;

        int r3 = ms1.getMin();   // -2
        System.out.println("getMin() after pop()         → Expected: -2  | Got: " + r3 + "  " + (r3 == -2 ? "✅" : "❌"));
        if (r3 == -2) passed++; else failed++;

        // ── Test 2: Ascending push order ───────────────────────────────────
        System.out.println("\n── Test 2: Ascending Order (1 → 2 → 3) ─────────────");
        LeetCode_155_MinStack ms2 = new LeetCode_155_MinStack();
        ms2.push(1);
        ms2.push(2);
        ms2.push(3);

        int r4 = ms2.getMin();   // 1 — first pushed is the smallest
        System.out.println("getMin() → Expected: 1  | Got: " + r4 + "  " + (r4 == 1 ? "✅" : "❌"));
        if (r4 == 1) passed++; else failed++;

        int r5 = ms2.top();      // 3
        System.out.println("top()    → Expected: 3  | Got: " + r5 + "  " + (r5 == 3 ? "✅" : "❌"));
        if (r5 == 3) passed++; else failed++;

        // ── Test 3: Descending push order ──────────────────────────────────
        System.out.println("\n── Test 3: Descending Order (5 → 3 → 1) ────────────");
        LeetCode_155_MinStack ms3 = new LeetCode_155_MinStack();
        ms3.push(5);
        ms3.push(3);
        ms3.push(1);

        int r6 = ms3.getMin();   // 1
        System.out.println("getMin()              → Expected: 1  | Got: " + r6 + "  " + (r6 == 1 ? "✅" : "❌"));
        if (r6 == 1) passed++; else failed++;

        ms3.pop();               // removes 1 — min should restore to 3
        int r7 = ms3.getMin();   // 3
        System.out.println("getMin() after pop(1) → Expected: 3  | Got: " + r7 + "  " + (r7 == 3 ? "✅" : "❌"));
        if (r7 == 3) passed++; else failed++;

        ms3.pop();               // removes 3 — min should restore to 5
        int r8 = ms3.getMin();   // 5
        System.out.println("getMin() after pop(3) → Expected: 5  | Got: " + r8 + "  " + (r8 == 5 ? "✅" : "❌"));
        if (r8 == 5) passed++; else failed++;

        // ── Test 4: Duplicate values ────────────────────────────────────────
        System.out.println("\n── Test 4: Duplicate Minimum Values ─────────────────");
        LeetCode_155_MinStack ms4 = new LeetCode_155_MinStack();
        ms4.push(2);
        ms4.push(2);
        ms4.push(2);

        int r9 = ms4.getMin();   // 2
        System.out.println("getMin() with all 2s     → Expected: 2  | Got: " + r9 + "  " + (r9 == 2 ? "✅" : "❌"));
        if (r9 == 2) passed++; else failed++;

        ms4.pop();               // removes one 2, min should still be 2
        int r10 = ms4.getMin();  // 2
        System.out.println("getMin() after pop()     → Expected: 2  | Got: " + r10 + "  " + (r10 == 2 ? "✅" : "❌"));
        if (r10 == 2) passed++; else failed++;

        ms4.pop();               // removes another 2, min should still be 2
        int r11 = ms4.getMin();  // 2
        System.out.println("getMin() after 2nd pop() → Expected: 2  | Got: " + r11 + "  " + (r11 == 2 ? "✅" : "❌"));
        if (r11 == 2) passed++; else failed++;

        // ── Test 5: Single element ──────────────────────────────────────────
        System.out.println("\n── Test 5: Single Element ───────────────────────────");
        LeetCode_155_MinStack ms5 = new LeetCode_155_MinStack();
        ms5.push(42);

        int r12 = ms5.top();     // 42
        System.out.println("top()    → Expected: 42  | Got: " + r12 + "  " + (r12 == 42 ? "✅" : "❌"));
        if (r12 == 42) passed++; else failed++;

        int r13 = ms5.getMin();  // 42
        System.out.println("getMin() → Expected: 42  | Got: " + r13 + "  " + (r13 == 42 ? "✅" : "❌"));
        if (r13 == 42) passed++; else failed++;

        // ── Test 6: Negative numbers ────────────────────────────────────────
        System.out.println("\n── Test 6: Negative Numbers ─────────────────────────");
        LeetCode_155_MinStack ms6 = new LeetCode_155_MinStack();
        ms6.push(-5);
        ms6.push(-1);
        ms6.push(-10);
        ms6.push(-3);

        int r14 = ms6.getMin();  // -10
        System.out.println("getMin()               → Expected: -10  | Got: " + r14 + "  " + (r14 == -10 ? "✅" : "❌"));
        if (r14 == -10) passed++; else failed++;

        ms6.pop();               // removes -3, min stays -10
        int r15 = ms6.getMin();  // -10
        System.out.println("getMin() after pop(-3)  → Expected: -10  | Got: " + r15 + "  " + (r15 == -10 ? "✅" : "❌"));
        if (r15 == -10) passed++; else failed++;

        ms6.pop();               // removes -10, min restores to -5
        int r16 = ms6.getMin();  // -5
        System.out.println("getMin() after pop(-10) → Expected:  -5  | Got: " + r16 + "  " + (r16 == -5 ? "✅" : "❌"));
        if (r16 == -5) passed++; else failed++;

        // ── Test 7: Exceptions on empty stack ───────────────────────────────
        System.out.println("\n── Test 7: Exceptions on Empty Stack ────────────────");
        LeetCode_155_MinStack ms7 = new LeetCode_155_MinStack();

        try {
            ms7.pop();
            System.out.println("pop()    on empty → Expected: exception  | Got: no exception  ❌");
            failed++;
        } catch (RuntimeException e) {
            System.out.println("pop()    on empty → \"" + e.getMessage() + "\"  ✅");
            passed++;
        }

        try {
            ms7.top();
            System.out.println("top()    on empty → Expected: exception  | Got: no exception  ❌");
            failed++;
        } catch (RuntimeException e) {
            System.out.println("top()    on empty → \"" + e.getMessage() + "\"  ✅");
            passed++;
        }

        try {
            ms7.getMin();
            System.out.println("getMin() on empty → Expected: exception  | Got: no exception  ❌");
            failed++;
        } catch (RuntimeException e) {
            System.out.println("getMin() on empty → \"" + e.getMessage() + "\"  ✅");
            passed++;
        }

        // ── Summary ─────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  Results: " + passed + " passed  |  " + failed + " failed  |  " + (passed + failed) + " total");
        System.out.println("══════════════════════════════════════════════════════");
    }
}
