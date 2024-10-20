public class StaticScope {
    static int u = 42;
    static int v = 69;
    static int w = 17;

    // Procedure add that modifies the global u
    public static void add(int z) {
        u = v + u + z;
    }

    // Procedure bar that creates a local u and invokes the passed function
    @SuppressWarnings("unused")
    public static void bar(Runnable fun) {
        int u = w; // Local u shadows the global one
        fun.run();
    }

    // Procedure foo that redefines v and calls bar
    public static void foo(int x, int w) {
        int v = x; // Local v shadows the global one
        bar(() -> add(v)); // Pass 'add' as a parameter to bar
    }

    public static void main(String[] args) {
        foo(u, 13);
        System.out.println(u);
    }
}
