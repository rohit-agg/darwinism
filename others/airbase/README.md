# Amortize

## Prerequisites
- Java Version 1.8

## Compilation + Execution

```sh
javac -sourcepath src src/TestAmortize.java -d out/
java -classpath out/ TestAmortize
```

## Adding Test Cases

- Open `TestAmortize.java`
- Go to method `testParameters`
- Add a new `TestInput` at the end of the array list
- `TestInput` class consists of three properties: `amount`, `startDate` and `endaDate`. 
Constructor expects these 3 values in exactly this order.