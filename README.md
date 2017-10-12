# Fractals-Software
A software to explore fractal types built in Java

Table of contents



Objectives        2

Three dimensional Object and Viewing        4

Development Tool selection        Error! Bookmark not defined.

Project selection        Error! Bookmark not defined.

Methodology        2

Facet Approximation Of Sphere Generation        Error! Bookmark not defined.

Motion of the Sphere        Error! Bookmark not defined.

Hidden surface Elimination        4

Geometric Transformations Used        3

a. Translation(P&#39;=T.P)        3

b. Rotation w.r.t. co-ordinate axes        3

c. Projection (perspective)        **Error! Bookmark not defined.**

Illumination        5

Intensity Attenuation        Error! Bookmark not defined.

Surface Rendering        5

Fig: screenshot of output at an instance        Error! Bookmark not defined.

Limitations        Error! Bookmark not defined.

Conclusion        7

Future Enhancement        Error! Bookmark not defined.

Difficulties        6

References        7













# Objectives

- .To grow up the basic idea of graphics.
- .To acknowledge about new geometrical object: fractal
- .To implement recursive design of 3d object/ 2d object.
- .To implement the basic 3D transformations
- .To be able to detect the visible surfaces.
- .To be able to apply surface rendering but only in illumination.



# Methodology

We used JDK 1.5 of J2SE as our development tool. Programming in java was our choice since it is platform independent and can run in almost any computer.

We first explored the creation 2D fractals .

The algorithm of a 2D fractals is very similar to  3D fractal.

Fractals have a self repeating property that is they are recursive function of themselves.

The function can be a linear transformation that is predefined such as dividing a line in the ratio 1:2 or it may be random such as x:y.the function can also involve complex numbers such as in  the Mandelbrot and Julia sets which are recursive fractals of self –squaring complex numbers.

However,our goals was to develop a 3D menger sponge. It 2D analogy is a sierpiensky triangle.

Let us first explore the sierpinsky triangle and then move onto the menger sponge.

A  sierpinsky is made up of triangle form an  which an inverted triangles are are substracted.or it can also be thought to be made up of 3 smaller triangles.

The rough java code for a sierpinsksy triangle is given below to develop an idea for the 3D model.



















 ![](data:image/*;base64,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)

**Fig** _. Snapshot at an instance of the program._

**3D Graphics procedures :**

## A. Polygon Representation:

    We tabulated Vertex table and Face table to construct the Polygon surface.We tabulated Face table in such a manner that normal of the Polygon surface  is projected outward( also called as left handed co-ordinate system) from the structure.

## B. Geometric Transformations :

Let P(x,y,z) represents the point and p&#39;(x1,y1,z1) represents the point after operation .

           The 3D Geometric Transformations Used are:

### a. Translation(P&#39;=T.P)

           The sphere points are calculated, taking the center at the standard position(origin) and then translated to the required position.

    x1=x+tx

      y1=y+ty

      z1=z+tz

where tx,ty,tz are the translation distance w.r.t. x,y &amp;z axes respectively

### b. Rotation w.r.t. co-ordinate axes

**   rotation about x-axis**

      P&#39;=Rx(θ).P

     x1=x

     y1=ycos(θ)-ysin(θ)

     z1=ysin(θ)+zcos(θ)

          where x1, y1,z1 are the rotated coordinates with angle θ about x axis

**   rotation about y-axis**

      P&#39;=Rx(θ).P

     x1=xcos(θ)+zsin(θ)

     y1=y1

     z1=-xsin(θ)+zcos(θ)

          where x1, y1,z1 are the rotated coordinates with angle θ about y axis

    rotation about z-axis

    P&#39;=Rz(θ).P

      x1=xcos(θ)-ysin(θ)

      y1=xsin(θ)+ycos(θ)

      z1=z

          where x1, y1,z1 are the rotated coordinates with angle θ about z axis





# C.Three dimensional Viewing

The following is the three dimensional viewing of an object. To model an object and then converting to the device co-ordinate involves the different transformations.





**Fig.** Three dimensional viewing pipeline

We used parallel projection (Orthographic Projection).The viewing plane is XY plane/i.e. Z-axis.

## D. Hidden surface Elimination

## 1.Backsurface detection method

 Here we implemented the back-face-detection, in object space. Image space is the better one but due to some difficulties, we implemented the back-face-detection in object space i.e. we display or not the polygon.

Let **N**  be the normal vector of the surface, and **V** the viewing vector then the surface is a backfaceif,

**  V.N&gt;0**

θ=cos

# -1
 **N.V/(|N||V|)**

In other words the surface is visible if angle between **N&amp;V** isin the range        90

# 0
&lt;=angle&lt;=180
# 0

The normal vector for each surface is the average vector sum of its vertices. The normal vectors are emerged from the centre of the sphere.

The viewing vector is the vector between the central point of the surface and zvp. After obtaining the vectors we calculate the angle by using above formula.

Thus, there are only two chances of occurring result i.e. the whole surface is either visible or not.

## 2. Z-SORT method (Painter&#39;s  Algorithm)

1. Surfaces are sorted in order of decreasing depth .

2. Surfaces are scan converted in order, starting with the surface of greatest depth.

  Sorting operations are carried out in both image and object space, and the scan conversion of the polygon surfaces is performed in image space.

 This method for solving hidden-surface problem is often referred as Painter&#39;s algorithm.

## E. Ilumination

Single point light is kept at different locations( along X-axis, Y-axis, Z-axis and one arbitrary point).

      Total intensity is due to

1. **1.** Ambient light

Intensity due to the ambient light is

      I

# A
=Ka.Ia

Where

          Ka is a constant called diffuse reflection coefficient (or diffuse reflectivity) between 0-1

            Ia is ambient light intensity

1. **2.** Diffuse light intensity

The intensity due to the diffuse reflection is

                   I

# D=
K
# d
I
# l.
(N.L)

                      = K

# d
I
# l
cos θ

             where

             K­

# d
--a constant called diffuse reflection coefficient  between 0-1

                   N --unit normal vector at the point

                   L – unit Vector from light source to the point on the surface point of body

                         θ -- angle between N and L

**       3.  Specular relection**

**               ** this intensity is due to the point source of light and causes light intensities to vary among the same surface.

                    I

# S=
K
# d
I
# l.
(N.H)
# ns

  H= ( L+V )/(mod( L + V ))

#



## F. Surface Rendering

Different methods are applied for rendering of the surface. Each of them have their own advantages and disadvantages. The easiest one is constant-intensity shading or flat shading.

For any surface, the intensity is constant. There is sharp change in intensity from one surface to another and the difference at the boundary is observed.

The constant shading of polygon facets provides an accurate rendering for an object if all of the following assumptions are valid:

- .The object is a polyhedron and is not an approximation of an object with a curved surface.
- .All light source position illuminating the object are sufficiently far from the surface so that N.L and the attenuation function are constant over the surface.
- .The viewing position is sufficiently far from the surface so that V.N is constant over the surface.

# G. Color Model

   There are various types of Color models like RGB Color Model, YIQ Color Model(Brightness hue and purity),CMY Color Model &amp; HSV Color Model(Hue Saturation Value).

  In our project we used RGB Color Model.

# Further enhancements

       The limitations on this project are:

1. Addition of other fractals such as Julia set, Mandelbrot etc and making it grand fractal gallery.
2. Addition of Perspective view.
3. Addition of Reflection Transfomation.
4. Addition of Intensity Attenuation, Specular Reflection and Transparency.
5. Shadow generation.
6. Multiple light source.

# Difficulties

We first implemented a 2d fractal and tried to implement a 3 d fractal. We faced many problems when trying to switch to a 3d object.the recursion functions used required a lot of time to be built.

We had to do a lot of extra research regarding this new mathematical geometry :: fractals. We discovered this to be completely different from the Euclidean geometry.( regular 2d/ 3d).

# Conclusion

The output of our project is as expected.

Fractals have a self repeating property that is they are recursive function of themselves. They also have fractional dimensions.

We are now equipped with  knowledge of  3d graphics. We represented a polygon through  polygon tables. We then performed graphical transformations such as rotation  and translation. We used a parallel orthographic projection with XY viewing plane. The hidden surface algorithms  used were back face detection method (N.V)and Z sort method. We also applied illumination models such as ambient light and diffuse light reflection intensity(N.L) .Surface rendering technique used was constant shading method.

Thus our graphics project on fractals was completed.



# References

- .Computer Graphics, C Version by Hearn Baker, Pearson education
- .class notes
- .http://en.wikipedia.org
