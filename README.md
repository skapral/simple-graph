# simple-graph

[![Build Status](https://img.shields.io/travis/skapral/simple-graph/master.svg)](https://travis-ci.org/skapral/simple-graph)

Simple graph library

## How to build

Simply clone the repository and run Maven from its root:

```bash
  $ mvn clean install
``` 

## Quick start

1. Define your graph

```java
// create a graph
        StructuredGraph<String, Integer> sg = new CpgwmBiDirectedGraph<String, Integer>()
                // add nodes
            .withNode(new NValue<>("a"),  0)
            .withNode(new NValue<>("a1"), 0)
            .withNode(new NValue<>("a2"), 0)
            .withNode(new NValue<>("a3"), 0)
            .withNode(new NValue<>("b1"), 0)
            .withNode(new NValue<>("b2"), 0)
            .withNode(new NValue<>("b3"), 0)
            .withNode(new NValue<>("b"),  0)
                // add edges
            .withEdge(new NValue<>("a"), new NValue<>("a1"), 1)
            .withEdge(new NValue<>("a"), new NValue<>("a2"), 1)
            .withEdge(new NValue<>("a"), new NValue<>("a3"), 1)
            .withEdge(new NValue<>("a1"), new NValue<>("b2"), 1)
            .withEdge(new NValue<>("a1"), new NValue<>("b3"), 1)
            .withEdge(new NValue<>("a2"), new NValue<>("b1"), 1)
            .withEdge(new NValue<>("a2"), new NValue<>("b3"), 1)
            .withEdge(new NValue<>("a3"), new NValue<>("b1"), 1)
            .withEdge(new NValue<>("a3"), new NValue<>("b2"), 1)
            .withEdge(new NValue<>("b1"), new NValue<>("b"), 1)
            .withEdge(new NValue<>("b2"), new NValue<>("b"), 1)
            .withEdge(new NValue<>("b3"), new NValue<>("b"), 1)
            .result();
```

2. Start working with graph

```java
      // prepare the graph for path finding
      NavigableGraph<String, Integer> g = new NgFromStructure<>(sg);

      // Rock'n'roll!
      List<Edge<String>> path = new PftDijkstra<>(g).path(
                    new NValue<>("a"), 
                    new NValue<>("b2")
      ).get();
```

## Need more info?

TODO: place link to the user manual
