public class DeepBindingSimulation {
    static int u = 42;
    static int v = 69;
    static int w = 17;

    // Procedure add that modifies the global u, but in deep binding, 'v' is captured from the calling scope
    public static void add(int z) {
        u = v + u + z; // The global 'u' is modified here
    }

    // Procedure bar that creates a local 'u' and invokes the passed function
    public static void bar(Runnable fun) {
        @SuppressWarnings("unused")
        int u = w; // Local 'u' shadows the global one
        fun.run(); // Calls the function (add) which operates with deep binding
    }

    // Procedure foo that redefines 'v' and calls 'bar'
    public static void foo(int x, int w) {
        int v = x; // Local 'v' shadows the global 'v'
        
        // Using deep binding, 'v' from this scope will be captured when calling 'add'
        bar(() -> {
            // Simulate deep binding by forcing 'v' to be the one from 'foo'
            DeepBindingSimulation.v = v;
            add(v); // Pass 'v' from 'foo' as the captured variable
        });
    }

    public static void main(String[] args) {
        foo(u, 13); // foo(u, 13) -> foo(42, 13)
        System.out.println(u); // This will print the value of the global 'u' after modification
    }
}
