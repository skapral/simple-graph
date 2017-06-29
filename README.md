# simple-graph

[![Build Status](https://img.shields.io/travis/skapral/simple-graph/master.svg)](https://travis-ci.org/skapral/simple-graph)

Simple graph library

## Quick start

1. Define your node type

```java
      /**
       * User-defined node which holds some string value
       * 
       * @author Kapralov Sergey
       */
      class NString extends NValue<String> {
          public NString(String value) {
              super(value);
          }
      }
```

2. Define your edge type

```java
      /**
       * User-defined edge
       * 
       * @author Kapralov Sergey
       */
      class EString extends EDirected<String, NString, EString> {
          public EString(NString start, NString end) {
              super(start, end);
          }

          /**
           * You can define more constructors for convenience
           * 
           * @param start
           * @param end 
           */
          public EString(String start, String end) {
              super(
                      new NString(start),
                      new NString(end)
              );
          }
      }
```

3. Compose your graph

```java
      // create a graph
      StructuredGraph<String, NString, EString> sg = new SgEmpty<>();

      // add nodes
      sg = new SgWithNodes<>(
        sg,
        new NString("a"),
        new NString("a1"),
        new NString("a2"),
        new NString("a3"),
        new NString("b1"),
        new NString("b2"),
        new NString("b3"),
        new NString("b")
      );

      // add edges
      sg = new SgWithEdges<>(
        sg,
        new EString("a", "a1"),
        new EString("a", "a2"),
        new EString("a", "a3"),
        new EString("a1", "b1"),
        new EString("a1", "b2"),
        new EString("a2", "b1"),
        new EString("a2", "b3"),
        new EString("a3", "b2"),
        new EString("a3", "b3"),
        new EString("b1", "b"),
        new EString("b2", "b"),
        new EString("b3", "b")
      );
```

4. Start working with graph

```java
      // prepare the graph for path finding
      NavigableGraph<String, NString, EString> g = new NgFromStructure<>(sg);

      // Rock'n'roll!
      List<EString> path = new PftNaive<>(g).path(
              new NString("a"), 
              new NString("b2")
      ).get();
```
