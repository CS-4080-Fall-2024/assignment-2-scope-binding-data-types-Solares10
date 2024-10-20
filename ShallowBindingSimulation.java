public class ShallowBindingSimulation {
    static int u = 42; // I declare a global variable 'u' and set it to 42
    static int v = 69; // I declare a global variable 'v' and set it to 69
    static int w = 17; // I declare a global variable 'w' and set it to 17

    // Procedure add that modifies the global 'u'
    public static void add(int z, int localU, int localV) {
        // I use the local values of 'u' and 'v' (simulating shallow binding)
        u = localV + localU + z; // I update the global 'u' using the local 'u' and 'v' values along with 'z'
    }

    // Procedure bar that creates a local 'u' and invokes the passed function
    public static void bar(int vFromFoo, RunnableWithEnv fun) {
        int u = w; // I create a local 'u' that shadows the global 'u' (setting it to 17)
        fun.run(u, vFromFoo); // I call the passed function with my local 'u' and the 'v' from foo
    }

    // Procedure foo that redefines 'v' and calls 'bar'
    public static void foo(int x, int w) {
        int v = x; // I create a local 'v' that shadows the global 'v' (setting it to the value of 'x')
        bar(v, (localU, localV) -> add(v, localU, localV)); // I capture my local 'v' and pass it to 'bar'
    }

    public static void main(String[] args) {
        foo(u, 13); // I call foo with the global 'u' (42) and 13 as arguments
        System.out.println(u); // I print the modified global 'u'
    }
}

// Custom functional interface to pass environment variables (localU and localV)
interface RunnableWithEnv {
    void run(int localU, int localV); // I define a method that takes 'localU' and 'localV' as arguments
}
