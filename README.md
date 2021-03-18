# plantuml-test

[![CI](https://github.com/plantuml/plantuml-test/actions/workflows/ci.yml/badge.svg)](https://github.com/plantuml/plantuml-test/actions/workflows/ci.yml)

This is a small start toward tests for PlantUML.

Currently only contains a tool for developers to experiment with different config settings across different diagram types.
See `net.sourceforge.plantuml.test.playground.Playground`.

Test code is deliberately separate from the main PlantUML repo however the overall plan for testing is not finalised.
There's some discussion [here](https://github.com/plantuml/plantuml/pull/485).

# PlantUML Dependency

This project depends on a SNAPSHOT version of PlantUML.  Snapshots are not published anywhere public so Maven will complain that it cannot
find the dependency.  To solve this you should do something locally such as:

* Run `mvn install` in the `plantuml` project to put a snapshot build in `~/.m2/repository/`.

* For IntelliJ, add `plantuml-test` [as a module to an existing](https://www.jetbrains.com/help/idea/maven-support.html#maven_add_module)
  `plantuml` project.  IntelliJ will probably add automatically the plantuml module as a dependency for the new test module, if not you can
  [add it manually](https://www.jetbrains.com/help/idea/working-with-module-dependencies.html#add-a-new-dependency).

# CI Builds

A [GitHub Action](https://github.com/plantuml/plantuml-test/actions/workflows/ci.yml) runs these tests against a fresh checkout of the
[plantuml](https://github.com/plantuml/plantuml) repo, so we are always testing the latest available code.
