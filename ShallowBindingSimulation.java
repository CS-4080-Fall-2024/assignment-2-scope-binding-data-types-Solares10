public class ShallowBindingSimulation {
    static int u = 42;
    static int v = 69;
    static int w = 17;

    // Procedure add that modifies the global u
    public static void add(int z, int localU, int localV) {
        // Use the local values of u and v (shallow binding simulation)
        u = localV + localU + z; // Use the local 'u' and 'v' instead of global ones
    }

    // Procedure bar that creates a local 'u' and invokes the passed function
    public static void bar(int vFromFoo, RunnableWithEnv fun) {
        int u = w; // Local 'u' shadows the global one (u = 17)
        fun.run(u, vFromFoo); // Pass the local 'u' and the 'v' from foo as arguments
    }

    // Procedure foo that redefines 'v' and calls 'bar'
    public static void foo(int x, int w) {
        int v = x; // Local 'v' shadows the global one (v = 42)
        bar(v, (localU, localV) -> add(v, localU, localV)); // Capture local 'v' and pass to 'bar'
    }

    public static void main(String[] args) {
        foo(u, 13); // foo(u, 13) -> foo(42, 13)
        System.out.println(u); // This will print the modified global 'u'
    }
}

// Custom functional interface to pass environment variables (u and v)
interface RunnableWithEnv {
    void run(int localU, int localV); // Takes 'localU' and 'localV' as arguments
}
