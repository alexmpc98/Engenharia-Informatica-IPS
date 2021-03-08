Java Patterns | Data Access Object
===

TODO.

## Exercises

1. Provide a *concrete BookDAO* implementation based on volatile storage, using an underlying collection of type `Map<String, Book>`.
    - Name it `BookDaoVolatileMap`.
    - This will be similar to `BookDaoVolatileList`, but a little easier to implement.
    - Change the `main` method to use this *concrete dao*. Everything should work as previously.

2. Apply the **Simple Factory** pattern to allow requesting the different available storage strategies.
    - Change the `main` method to use the factory.

3. 

Solutions can be found [here](SOLUTIONS.md).