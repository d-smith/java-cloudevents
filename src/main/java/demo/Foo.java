package demo;

public class Foo {
    String foo;
    public Foo(String foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "foo='" + foo + '\'' +
                '}';
    }
}
