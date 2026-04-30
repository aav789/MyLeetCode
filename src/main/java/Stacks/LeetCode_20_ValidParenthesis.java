package Stacks;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */

public class LeetCode_20_ValidParenthesis {
    public boolean isValid(String s) {
        if(s.length()%2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '{' || c == '[' || c =='(') stack.push(c);
            else if(!stack.isEmpty() && c == ')' && stack.peek() == '(') stack.pop();
            else if(!stack.isEmpty() && c == '}' && stack.peek() == '{') stack.pop();
            else if(!stack.isEmpty() && c == ']' && stack.peek() == '[') stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }

    // ── Main — test scenarios ──────────────────────────────────────────────
    public static void main(String[] args) {
        LeetCode_20_ValidParenthesis solution = new LeetCode_20_ValidParenthesis();
        int passed = 0, failed = 0;

        // Helper: run a single test case and print result
        // ─────────────────────────────────────────────────────────────────
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║       LeetCode 20 — Valid Parentheses Tests       ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        // ── 1. Basic valid cases ───────────────────────────────────────────
        System.out.println("── Valid Cases ──────────────────────────────────────");

        // single pair
        String t1 = "()";
        boolean r1 = solution.isValid(t1);
        System.out.println("Input: \"" + t1 + "\"  →  Expected: true  |  Got: " + r1 + "  " + (r1 ? "✅" : "❌"));
        if (r1) passed++; else failed++;

        // multiple different pairs
        String t2 = "()[]{}";
        boolean r2 = solution.isValid(t2);
        System.out.println("Input: \"" + t2 + "\"  →  Expected: true  |  Got: " + r2 + "  " + (r2 ? "✅" : "❌"));
        if (r2) passed++; else failed++;

        // nested pairs
        String t3 = "{[()]}";
        boolean r3 = solution.isValid(t3);
        System.out.println("Input: \"" + t3 + "\"  →  Expected: true  |  Got: " + r3 + "  " + (r3 ? "✅" : "❌"));
        if (r3) passed++; else failed++;

        // deeply nested
        String t4 = "({[({[]})]})";
        boolean r4 = solution.isValid(t4);
        System.out.println("Input: \"" + t4 + "\"  →  Expected: true  |  Got: " + r4 + "  " + (r4 ? "✅" : "❌"));
        if (r4) passed++; else failed++;

        // ── 2. Basic invalid cases ─────────────────────────────────────────
        System.out.println("\n── Invalid Cases ────────────────────────────────────");

        // wrong closing bracket
        String t5 = "(]";
        boolean r5 = solution.isValid(t5);
        System.out.println("Input: \"" + t5 + "\"  →  Expected: false  |  Got: " + r5 + "  " + (!r5 ? "✅" : "❌"));
        if (!r5) passed++; else failed++;

        // mismatched nested
        String t6 = "([)]";
        boolean r6 = solution.isValid(t6);
        System.out.println("Input: \"" + t6 + "\"  →  Expected: false  |  Got: " + r6 + "  " + (!r6 ? "✅" : "❌"));
        if (!r6) passed++; else failed++;

        // only opening brackets
        String t7 = "((({{{[[[";
        boolean r7 = solution.isValid(t7);
        System.out.println("Input: \"" + t7 + "\"  →  Expected: false  |  Got: " + r7 + "  " + (!r7 ? "✅" : "❌"));
        if (!r7) passed++; else failed++;

        // only closing brackets
        String t8 = ")))";
        boolean r8 = solution.isValid(t8);
        System.out.println("Input: \"" + t8 + "\"  →  Expected: false  |  Got: " + r8 + "  " + (!r8 ? "✅" : "❌"));
        if (!r8) passed++; else failed++;

        // ── 3. Edge cases ──────────────────────────────────────────────────
        System.out.println("\n── Edge Cases ───────────────────────────────────────");

        // single bracket (odd length → false)
        String t9 = "(";
        boolean r9 = solution.isValid(t9);
        System.out.println("Input: \"" + t9 + "\"  →  Expected: false  |  Got: " + r9 + "  " + (!r9 ? "✅" : "❌"));
        if (!r9) passed++; else failed++;

        // single closing bracket
        String t10 = "}";
        boolean r10 = solution.isValid(t10);
        System.out.println("Input: \"" + t10 + "\"  →  Expected: false  |  Got: " + r10 + "  " + (!r10 ? "✅" : "❌"));
        if (!r10) passed++; else failed++;

        // reversed valid pair
        String t11 = "}{";
        boolean r11 = solution.isValid(t11);
        System.out.println("Input: \"" + t11 + "\"  →  Expected: false  |  Got: " + r11 + "  " + (!r11 ? "✅" : "❌"));
        if (!r11) passed++; else failed++;

        // unclosed bracket at end
        String t12 = "({[]})()((";
        boolean r12 = solution.isValid(t12);
        System.out.println("Input: \"" + t12 + "\"  →  Expected: false  |  Got: " + r12 + "  " + (!r12 ? "✅" : "❌"));
        if (!r12) passed++; else failed++;

        // ── Summary ────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  Results: " + passed + " passed  |  " + failed + " failed  |  " + (passed + failed) + " total");
        System.out.println("══════════════════════════════════════════════════════");
    }

}
