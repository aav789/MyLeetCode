package Stacks;

import java.util.LinkedList;

public class StackImplementationLinkedList<T> {
        // ── Internal LinkedList ────────────────────────────────────────────────
        private LinkedList<T> data;

        // ── Constructor ────────────────────────────────────────────────────────
        public StackImplementationLinkedList() {
            this.data = new LinkedList<>();
        }

        // ── Push — add element to top (front of LinkedList) ───────────────────
        public void push(T value) {
            data.addFirst(value);
            System.out.println("Pushed: " + value);
        }

        // ── Pop — remove and return top element ────────────────────────────────
        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack Underflow — cannot pop from empty stack");
            }
            T value = data.removeFirst();
            System.out.println("Popped: " + value);
            return value;
        }

        // ── Peek — view top element without removing ───────────────────────────
        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty — nothing to peek");
            }
            return data.getFirst();
        }

        // ── isEmpty ────────────────────────────────────────────────────────────
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // ── size ───────────────────────────────────────────────────────────────
        public int size() {
            return data.size();
        }

        // ── search — returns 1-based position from top, -1 if not found ───────
        public int search(T value) {
            int index = data.indexOf(value);
            return index == -1 ? -1 : index + 1;
        }

        // ── print stack contents ───────────────────────────────────────────────
        public void print() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            System.out.print("Stack (top → bottom): ");
            int i = 0;
            for (T item : data) {
                System.out.print(item);
                if (i < data.size() - 1) System.out.print(" → ");
                i++;
            }
            System.out.println();
        }

        // ── Main — demonstration ───────────────────────────────────────────────
        public static void main(String[] args) {

            // ── Integer Stack ──────────────────────────────────────────────────
            System.out.println("=== Integer Stack ===");
            StackImplementationLinkedList<Integer> intStack = new StackImplementationLinkedList<>();

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

            // ── Search ─────────────────────────────────────────────────────────
            System.out.println("\n=== Search Test ===");
            System.out.println("Position of 20 from top: " + intStack.search(20)); // 2
            System.out.println("Position of 10 from top: " + intStack.search(10)); // 3
            System.out.println("Position of 99 from top: " + intStack.search(99)); // -1

            // ── String Stack ───────────────────────────────────────────────────
            System.out.println("\n=== String Stack ===");
            StackImplementationLinkedList<String> strStack = new StackImplementationLinkedList<>();

            strStack.push("Apple");
            strStack.push("Banana");
            strStack.push("Cherry");

            strStack.print();
            // Stack (top → bottom): Cherry → Banana → Apple

            System.out.println("Peek: " + strStack.peek()); // Cherry
            strStack.pop();
            strStack.print();
            // Stack (top → bottom): Banana → Apple

            // ── Underflow test ─────────────────────────────────────────────────
            System.out.println("\n=== Underflow Test ===");
            try {
                StackImplementationLinkedList<Integer> emptyStack = new StackImplementationLinkedList<>();
                emptyStack.pop();
            } catch (RuntimeException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // ── Unlimited growth — no overflow ─────────────────────────────────
            System.out.println("\n=== Auto Grow Test ===");
            StackImplementationLinkedList<Integer> growStack = new StackImplementationLinkedList<>();
            for (int i = 1; i <= 10; i++) {
                growStack.push(i * 10);
            }
            System.out.println("Size after 10 pushes: " + growStack.size()); // 10
            growStack.print();
        }
    }
