package Stacks;

public class StackImplementationArray {
        // ── Internal array and tracking variables ──────────────────────────────
        private int[] data;
        private int top;
        private int capacity;

        // ── Constructor ────────────────────────────────────────────────────────
        public StackImplementationArray(int capacity) {
            this.capacity = capacity;
            this.data     = new int[capacity];
            this.top      = -1;  // -1 means stack is empty
        }

        // ── Push — add element to top ──────────────────────────────────────────
        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("Stack Overflow — cannot push " + value + ", stack is full");
            }
            data[++top] = value;
            System.out.println("Pushed: " + value);
        }

        // ── Pop — remove and return top element ────────────────────────────────
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack Underflow — cannot pop from empty stack");
            }
            int value = data[top--];
            System.out.println("Popped: " + value);
            return value;
        }

        // ── Peek — view top element without removing ───────────────────────────
        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty — nothing to peek");
            }
            return data[top];
        }

        // ── isEmpty ────────────────────────────────────────────────────────────
        public boolean isEmpty() {
            return top == -1;
        }

        // ── isFull ─────────────────────────────────────────────────────────────
        public boolean isFull() {
            return top == capacity - 1;
        }

        // ── size ───────────────────────────────────────────────────────────────
        public int size() {
            return top + 1;
        }

        // ── print stack contents ───────────────────────────────────────────────
        public void print() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            System.out.print("Stack (top → bottom): ");
            for (int i = top; i >= 0; i--) {
                System.out.print(data[i]);
                if (i > 0) System.out.print(" → ");
            }
            System.out.println();
        }

        // ── Main — demonstration ───────────────────────────────────────────────
        public static void main(String[] args) {
            StackImplementationArray stack = new StackImplementationArray(5);
            // Push elements
            stack.push(10);
            stack.push(20);
            stack.push(30);
            stack.push(40);
            stack.push(50);

            stack.print();
            // Stack (top → bottom): 50 → 40 → 30 → 20 → 10
            System.out.println("Peek: "  + stack.peek());   // 50
            System.out.println("Size: "  + stack.size());   // 5
            System.out.println("Full: "  + stack.isFull()); // true

            // Pop elements
            stack.pop();  // removes 50
            stack.pop();  // removes 40
            stack.print();
            // Stack (top → bottom): 30 → 20 → 10

            System.out.println("Size after pops: " + stack.size()); // 3
            // Test overflow
            try {
                stack.push(60);
                stack.push(70);
                stack.push(80);
                stack.push(90); // this will overflow
            } catch (RuntimeException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // Test underflow
            try {
                StackImplementationArray emptyStack = new StackImplementationArray(3);
                emptyStack.pop(); // this will underflow
            } catch (RuntimeException e) {
                System.out.println("Caught: " + e.getMessage());
            }
        }
    }
