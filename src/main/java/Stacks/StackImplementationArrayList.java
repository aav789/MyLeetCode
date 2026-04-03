package Stacks;

import java.util.ArrayList;

public class StackImplementationArrayList<T> {

        // ── Internal ArrayList ─────────────────────────────────────────────────
        private ArrayList<T> data;

        // ── Constructor ────────────────────────────────────────────────────────
        public StackImplementationArrayList() {
            this.data = new ArrayList<>();
        }

        // ── Push — add element to top ──────────────────────────────────────────
        public void push(T value) {
            data.add(value);
            System.out.println("Pushed: " + value);
        }

        // ── Pop — remove and return top element ────────────────────────────────
        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack Underflow — cannot pop from empty stack");
            }
            T value = data.remove(data.size() - 1);
            System.out.println("Popped: " + value);
            return value;
        }

        // ── Peek — view top element without removing ───────────────────────────
        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty — nothing to peek");
            }
            return data.get(data.size() - 1);
        }

        // ── isEmpty ────────────────────────────────────────────────────────────
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // ── size ───────────────────────────────────────────────────────────────
        public int size() {
            return data.size();
        }

        // ── print stack contents ───────────────────────────────────────────────
        public void print() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            System.out.print("Stack (top → bottom): ");
            for (int i = data.size() - 1; i >= 0; i--) {
                System.out.print(data.get(i));
                if (i > 0)
                    System.out.print(" → ");
            }
            System.out.println();
        }

        // ── Main — demonstration ───────────────────────────────────────────────
        public static void main(String[] args) {

            // ── Integer Stack ──────────────────────────────────────────────────
            System.out.println("=== Integer Stack ===");
            StackImplementationArrayList<Integer> intStack = new StackImplementationArrayList<>();

            intStack.push(10);
            intStack.push(20);
            intStack.push(30);
            intStack.push(40);
            intStack.push(50);

            intStack.print();
            // Stack (top → bottom): 50 → 40 → 30 → 20 → 10

            System.out.println("Peek : " + intStack.peek());    // 50
            System.out.println("Size : " + intStack.size());    // 5
            System.out.println("Empty: " + intStack.isEmpty()); // false

            intStack.pop(); // removes 50
            intStack.pop(); // removes 40
            intStack.print();
            // Stack (top → bottom): 30 → 20 → 10

            // ── String Stack ───────────────────────────────────────────────────
            System.out.println("\n=== String Stack ===");
            StackImplementationArrayList<String> strStack = new StackImplementationArrayList<>();

            strStack.push("Apple");
            strStack.push("Banana");
            strStack.push("Cherry");

            strStack.print();
            // Stack (top → bottom): Cherry → Banana → Apple

            System.out.println("Peek: " + strStack.peek()); // Cherry
            strStack.pop(); // removes Cherry
            strStack.print();

            // ── Test underflow ─────────────────────────────────────────────────
            System.out.println("\n=== Underflow Test ===");
            try {
                StackImplementationArrayList<Integer> emptyStack = new StackImplementationArrayList<>();
                emptyStack.pop();
            } catch (RuntimeException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // ── No overflow — ArrayList grows automatically ────────────────────
            System.out.println("\n=== Auto Grow Test ===");
            StackImplementationArrayList<Integer> growStack = new StackImplementationArrayList<>();
            for (int i = 1; i <= 20; i++) {
                growStack.push(i * 10);
            }
            System.out.println("Size after 20 pushes: " + growStack.size()); // 20
            growStack.print();
        }
    }

