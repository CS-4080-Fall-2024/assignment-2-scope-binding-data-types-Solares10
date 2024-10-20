public class StaticScope {
    static int u = 42; // I declare a static variable u and initialize it to 42
    static int v = 69; // I declare a static variable v and initialize it to 69
    static int w = 17; // I declare a static variable w and initialize it to 17

    // I define a procedure add that modifies the global u
    public static void add(int z) {
        u = v + u + z; // I update u by adding v, the current value of u, and the parameter z
    }

    // I define a procedure bar that creates a local u and invokes the passed function
    @SuppressWarnings("unused") // I suppress the warning for the unused parameter
    public static void bar(Runnable fun) {
        int u = w; // I create a local variable u that shadows the global one and set it to w
        fun.run(); // I execute the passed function
    }

    // I define a procedure foo that redefines v and calls bar
    public static void foo(int x, int w) {
        int v = x; // I create a local variable v that shadows the global one and set it to x
        bar(() -> add(v)); // I pass the add method as a parameter to bar, using a lambda expression
    }

    public static void main(String[] args) {
        foo(u, 13); // I call foo with u and 13 as arguments
        System.out.println(u); // I print the value of u to the console
    }
}
