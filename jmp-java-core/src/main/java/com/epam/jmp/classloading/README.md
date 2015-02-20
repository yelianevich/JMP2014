# Class Loading Task

Simple application that can load math modules from `jar` file and that provide console interface for them.

## Math Modules

Math module is a implementation of `com.epam.jmp.classloading.module.MathModule` abstract class.
Number of implementations could be packed together into `jar`.
There are 2 basic implementations in the project available:
* `com.epam.jmp.classloading.module.impl.Multiply`
* `com.epam.jmp.classloading.module.impl.Sum`

## How to run

Steps to get it running:
* Pack `jar` with `com.epam.jmp.classloading.module.MathModule` implementations
* Run `com.epam.jmp.classloading.Runner` class with path to `jar` file as an argument
