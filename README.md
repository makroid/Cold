Cold
====
This is a very simple domain coloring app for android.
So you can generate pictures like these:

![Cold](http://i.imgur.com/u0vImWy.jpg?1)


Introduction
------------
There is lots of reading stuff available for domain coloring. To give a very brief overview:
The goal is to plot a complex function. Since complex variables are 2 dimensional, there is no
simple way to draw a graph of a complex function (because intput and output are 2 dimensional => 4 dimension)
Thus one uses the following simple procedure instead:

    for all z in the visible plain:
      f = func(z)  // apply a given function func
      col = complex2Color(f)
      colorize(z, col) // color position z with col
    
Cold
----
In order to speed up the execution, it suggests itself to use opengl shaders for the coloring.

ANTLR allows simple parsing of expressions into shader-valid functions.

There are many different colorings possible, yet there are only two simple ones implemented.

Android min API level: 11, opengl es 2.0

Miscellaneous
-------------
Up to now, this is mainly a tool to generate nice pictures but not to enhance the understanding of the mathematics behind.

Planned Features
----------------
* add more predefined functions (sinh, cosh, ...)
* save functions
* animate variables
