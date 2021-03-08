Solutions of exercises
===

1. TODO

2. The *simple factory*. Two creation methods are available; one uses the name of the *concrete dao*, the other an *enum type*. Only one is really needed.

```java
public abstract class BookDaoFactory { /* abstract is not required by the pattern */

    public enum Type {
        MEMORY,
        SERIALIZATION,
        TEXT
    }

    /**
     * Returns the appropriate BookDao implementation based on <code>type</code> parameter.
     *
     * Currently accepted types are:
     * - "memory"
     * - "serialization"
     * - "text"
     * @param type type of dao implementation to create
     * @return a concrete dao instance.
     */
    public static BookDao create(String type) {
        switch (type) {
            case "memory": return new BookDaoVolatileList();
            case "serialization": return new BookDaoSerialization();
            case "text": return new BookDaoTextFiles();
            default: throw new UnsupportedOperationException("Invalid type received.");
        }
    }

    /**
     * Returns the appropriate BookDao implementation based on <code>type</code> parameter.
     *
     * @param type type of dao implementation to create
     * @return a concrete dao instance.
     */
    public static BookDao create(Type type) {
        switch(type) {
            case MEMORY: return new BookDaoVolatileList();
            case SERIALIZATION: return new BookDaoSerialization();
            case TEXT: return new BookDaoTextFiles();
            default: throw new UnsupportedOperationException("Invalid type received.");
        }
    }
}
```

The `main` method requires only changes to the instantiation of the *concrete dao*:

```java
BookDao dao = BookDaoFactory.create("serialization");
//or: BookDao dao = BookDaoFactory.create(BookDaoFactory.Type.SERIALIZATION);

//...
```