Cold
====
This is a very simple domain coloring app for android.
So you can generate pictures like these:

![Cold](http://i.imgur.com/nEeOHXS.jpg)


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

There are many different colorings possible, yet only two simple ones are implemented.

Android min API level: 11, opengl es 2.0

Usage
-----
Having opened the app, type in a formula in the upper input field. Some predefined functions (such as `sin`, `cos`) are available by clicking on the plus button next to the input field. For example:

        z/(sin(z)*a)+2i*b
        
The independent variable is `z`, other variables can be called arbitrarily (consisting of characters [a-zA-Z]), the imaginary unit `i` is also reserved. 

Clicking `Ok` starts rendering the expression. You can pan and zoom the plane. The sliders at the bottom allow to alter the real and imaginary part of any user-defined variable.

Animations
----------
The real and imaginary part of each variable can be animated automatically by pressing the play button on its right. "Edit" allows to set up a range, time and animation mode. 


Miscellaneous
-------------
Up to now, this is mainly a tool to generate nice pictures but not to enhance the understanding of the mathematics behind.

Planned Features
----------------
* add more predefined functions (sinh, cosh, ...)
* save functions
