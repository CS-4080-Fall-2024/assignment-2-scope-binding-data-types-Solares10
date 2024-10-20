public class DeepBindingSimulation {
    static int u = 42; // I define a global variable 'u' and set it to 42
    static int v = 69; // I define a global variable 'v' and set it to 69
    static int w = 17; // I define a global variable 'w' and set it to 17

    // I create a method 'add' that modifies the global 'u', but it uses 'v' from the calling scope
    public static void add(int z) {
        u = v + u + z; // I modify the global 'u' here by adding 'v', the current global 'u', and 'z'
    }

    // I create a method 'bar' that defines a local 'u' and invokes the passed function
    public static void bar(Runnable fun) {
        int u = w; // I define a local 'u' that shadows the global 'u' by setting it to 'w'
        fun.run(); // I call the function (add) which operates with deep binding
    }

    // I create a method 'foo' that redefines 'v' and calls 'bar'
    public static void foo(int x, int w) {
        int v = x; // I define a local 'v' that shadows the global 'v' and set it to 'x'
        
        // I use deep binding, capturing 'v' from this scope when I call 'add'
        bar(() -> {
            // I simulate deep binding by forcing the global 'v' to be the one from 'foo'
            DeepBindingSimulation.v = v;
            add(v); // I pass 'v' from 'foo' as the captured variable to 'add'
        });
    }

    public static void main(String[] args) {
        foo(u, 13); // I call 'foo' with the values of global 'u' and 13
        System.out.println(u); // I print the value of the global 'u' after it has been modified
    }
}
