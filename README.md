# QueryStream

---- 

## 1. Introduction

QueryStream is a Java library for continuous query processing and ternary operator improvement.

1. Able to express naturally as if writing English sentences.
2. Confusing symbols(!, &&, ||, etc.) can be expressed in words(such as not, and, or).
3. Improved Java ternary operator which is less readable.
4. Provides methods such as throwing exceptions.

> * requires JDK 1.8 or higher.

## 2. Adding to your build

To add a dependency using maven :

```xml

<dependency>
    <groupId>io.github.eatnuh</groupId>
    <artifactId>querystream</artifactId>
    <version>1.0</version>
</dependency>
```

To add a dependency using gradle :

```groovy
dependencies {
    implementation 'io.github.eatnuh:querystream:1.0'
}
```

## 3. Usages

### 3.1. Create query

> Creating a query starts by calling the static method ask or askNot.

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;

    public static void main(String[] args) {

        QueryStream.ask(A);     // A
        QueryStream.askNot(B);  // Not B

        // if static import, you can write
        ask(A);                 // A
        askNot(B);              // B
    }
}
```

### 3.2. Return query result

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;

    public static void main(String[] args) {

        boolean resultAisTrue = ask(A).isTrue();        // return A is true?
        boolean resultAisFalse = askNot(B).isFalse();   // return Not B is False?
        boolean resultAisThat = ask(A).is(true);        // return A is param?
    }
}
```

### 3.3. Combine query

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    public static void main(String[] args) {

        ask(A).and(B);                  // A and B
        ask(A).andNot(B);               // A and Not B
        askNot(B).orNot(A);             // Not B or Not A
        askNot(A).and(B).orNot(C);      // Not A and B or Not C
    }
}
```

### 3.4. Initialize query

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    public static void main(String[] args) {

        ask(A).and(B).reAsk(C);             // initialize C
        ask(A).and(B).reAskNot(C).and(B);      // initialize Not C and B
    }
}
```

### 3.5. Run according to the query

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    public static void main(String[] args) {

        ask(A).then(() -> System.out.println("A is true"));     // run if A is true.
        ask(B).orElse(() -> System.out.println("B is false"));  // run if A is false.
    }
}
```

### 3.6. Throws exception according to the query

```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    public static void main(String[] args) {

        ask(A).thenThrow(() -> new RuntimeException());     // throws if A is true.
        ask(B).orElseThrow(() -> new RuntimeException());   // throws if A is false.
    }
}
```

### 3.7. Return value according to the query
> thenReturn, orElseReturn method return new QueryReturnStream instance
```java
import static querystream.QueryStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String VALUE3 = "value3";
    private static final String VALUE4 = "value4";


    public static void main(String[] args) {

        // return VALUE1 if A is true, 
        // otherwise VALUE2 
        String v1 = ask(A).thenReturn(VALUE1).orElse(VALUE2);

        // return VALUE1 if B is false, 
        // otherwise VALUE2
        String v2 = ask(B).orElseReturn(VALUE1).orElse(VALUE2);

        // return VALUE1 if C is true, 
        // or else VALUE2 if A is true, 
        // otherwise VALUE3
        String v3 = ask(C).thenReturn(VALUE1)
                .orElseIf(A, VALUE2)
                .orElse(VALUE3);
        
        // return VALUE1 if Not C is false, 
        // or else VALUE2 if A is not true, 
        // or else VALUE3 if B is true,
        // otherwise VALUE4
        String v4 = askNot(C).orElseReturn(VALUE1)
                .orElseIfNot(A, VALUE2)
                .orElseIf(B, VALUE3)
                .orElse(VALUE1);
    }
}
```

### 3.8. Use QueryReturnStream
```java
import static querystream.QueryReturnStream.*;

public class Main {

    private static final boolean A = true;
    private static final boolean B = false;
    private static final boolean C = true;

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String VALUE3 = "value3";
    private static final String VALUE4 = "value4";


    public static void main(String[] args) {

        // return VALUE1 if A is true, 
        // otherwise VALUE2 
        String v1 = ifTrueReturn(A, VALUE1).orElse(VALUE2);
        
        // return VALUE1 if B is false, 
        // otherwise VALUE2
        String v2 = ifFalseReturn(B, VALUE1).orElse(VALUE2);

        // return VALUE1 if C is true, 
        // or else VALUE2 if A is true, 
        // otherwise VALUE3
        String v3 = ifTrueReturn(C, VALUE1)
                .orElseIf(A, VALUE2)
                .orElse(VALUE3);
        
    }
}
```

## 4. License

----
    MIT License

    Copyright (c) 2022 김태훈

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.