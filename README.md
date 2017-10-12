# Fractals-Software
A software to explore fractal types built in Java


Objectives
To grow up the basic idea of graphics.
To acknowledge about new geometrical object: fractal
To implement recursive design of 3d object/ 2d object.
To implement the basic 3D transformations
To be able to detect the visible surfaces.
To be able to apply surface rendering but only in illumination.



Methodology

We used JDK 1.5 of J2SE as our development tool. Programming in java was our choice since it is platform independent and can run in almost any computer.

We first explored the creation 2D fractals .
The algorithm of a 2D fractals is very similar to  3D fractal.
Fractals have a self repeating property that is they are recursive function of themselves.
The function can be a linear transformation that is predefined such as dividing a line in the ratio 1:2 or it may be random such as x:y.the function can also involve complex numbers such as in  the Mandelbrot and Julia sets which are recursive fractals of self –squaring complex numbers.
However,our goals was to develop a 3D menger sponge. It 2D analogy is a sierpiensky triangle.
Let us first explore the sierpinsky triangle and then move onto the menger sponge.
A  sierpinsky is made up of triangle form an  which an inverted triangles are are substracted.or it can also be thought to be made up of 3 smaller triangles.
The rough java code for a sierpinsksy triangle is given below to develop an idea for the 3D model.























Fig. Snapshot at an instance of the program.










3D Graphics procedures :
A. Polygon Representation:	
    We tabulated Vertex table and Face table to construct the Polygon surface.We tabulated Face table in such a manner that normal of the Polygon surface  is projected outward( also called as left handed co-ordinate system) from the structure.

B. Geometric Transformations :
Let P(x,y,z) represents the point and p’(x1,y1,z1) represents the point after operation .   
           The 3D Geometric Transformations Used are:
a. Translation(P’=T.P)
           The sphere points are calculated, taking the center at the standard position(origin) and then translated to the required position.
    x1=x+tx
      y1=y+ty
      z1=z+tz 
where tx,ty,tz are the translation distance w.r.t. x,y &z axes respectively

b. Rotation w.r.t. co-ordinate axes
   rotation about x-axis
      P’=Rx(θ).P
     x1=x
     y1=ycos(θ)-ysin(θ)
     z1=ysin(θ)+zcos(θ)
          where x1, y1,z1 are the rotated coordinates with angle θ about x axis 

   rotation about y-axis
      P’=Rx(θ).P
     x1=xcos(θ)+zsin(θ)
     y1=y1
     z1=-xsin(θ)+zcos(θ)
          where x1, y1,z1 are the rotated coordinates with angle θ about y axis 

    rotation about z-axis
    P’=Rz(θ).P
      x1=xcos(θ)-ysin(θ)
      y1=xsin(θ)+ycos(θ)
      z1=z  
          where x1, y1,z1 are the rotated coordinates with angle θ about z axis 
  





C.Three dimensional Viewing       
The following is the three dimensional viewing of an object. To model an object and then converting to the device co-ordinate involves the different transformations.
 

                                                                               







Fig. Three dimensional viewing pipeline

We used parallel projection (Orthographic Projection).The viewing plane is XY plane/i.e. Z-axis.
D. Hidden surface Elimination 
1.Backsurface detection method

 Here we implemented the back-face-detection, in object space. Image space is the better one but due to some difficulties, we implemented the back-face-detection in object space i.e. we display or not the polygon.       
Let N  be the normal vector of the surface, and V the viewing vector then the surface is a back face if,


θ=cos-1 N.V/(|N||V|)
                    
In other words the surface is visible if angle between N&V is in the range        900<=angle<=1800
The normal vector for each surface is the average vector sum of its vertices. The normal vectors are emerged from the centre of the sphere. 
The viewing vector is the vector between the central point of the surface and zvp. After obtaining the vectors we calculate the angle by using above formula. 
Thus, there are only two chances of occurring result i.e. the whole surface is either visible or not. 

2. Z-SORT method (Painter’s  Algorithm)
1. Surfaces are sorted in order of decreasing depth .
2. Surfaces are scan converted in order, starting with the surface of greatest depth.
  Sorting operations are carried out in both image and object space, and the scan conversion of the polygon surfaces is performed in image space.
 This method for solving hidden-surface problem is often referred as Painter’s algorithm.

E. Ilumination
Single point light is kept at different locations( along X-axis, Y-axis, Z-axis and one arbitrary point). 
            
      Total intensity is due to
1. Ambient light
Intensity due to the ambient light is
      IA=Ka.Ia
Where 
          Ka is a constant called diffuse reflection coefficient (or diffuse reflectivity) between 0-1
            Ia is ambient light intensity

2. Diffuse light intensity
The intensity due to the diffuse reflection is
                   ID=Kd Il. (N.L)
                      = Kd Il  cos θ
             where
             K­d --a constant called diffuse reflection coefficient  between 0-1
                   N --unit normal vector at the point
                   L – unit Vector from light source to the point on the surface point of body
                         θ -- angle between N and L 
       3.  Specular relection
		this intensity is due to the point source of light and causes light intensities to vary among the same surface.
                    IS=Kd Il. (N.H)ns
		H= ( L+V )/(mod( L + V ))
	

	
F. Surface Rendering
Different methods are applied for rendering of the surface. Each of them have their own advantages and disadvantages. The easiest one is constant-intensity shading or flat shading.
For any surface, the intensity is constant. There is sharp change in intensity from one surface to another and the difference at the boundary is observed.
The constant shading of polygon facets provides an accurate rendering for an object if all of the following assumptions are valid:
The object is a polyhedron and is not an approximation of an object with a curved surface.
All light source position illuminating the object are sufficiently far from the surface so that N.L and the attenuation function are constant over the surface.
The viewing position is sufficiently far from the surface so that V.N is constant over the surface.

G. Color Model
   There are various types of Color models like RGB Color Model, YIQ Color Model(Brightness hue and purity),CMY Color Model & HSV Color Model(Hue Saturation Value).
  In our project we used RGB Color Model.

Further enhancements

       The limitations on this project are:
1. Addition of other fractals such as Julia set, Mandelbrot etc and making it grand fractal gallery.
2. Addition of Perspective view.
3. Addition of Reflection Transfomation.
4. Addition of Intensity Attenuation, Specular Reflection and Transparency.
5. Shadow generation.
6. Multiple light source.

Difficulties
We first implemented a 2d fractal and tried to implement a 3 d fractal. We faced many problems when trying to switch to a 3d object.the recursion functions used required a lot of time to be built.
We had to do a lot of extra research regarding this new mathematical geometry :: fractals. We discovered this to be completely different from the Euclidean geometry.( regular 2d/ 3d).

Conclusion
The output of our project is as expected.
Fractals have a self repeating property that is they are recursive function of themselves. They also have fractional dimensions.
We are now equipped with  knowledge of  3d graphics. We represented a polygon through  polygon tables. We then performed graphical transformations such as rotation  and translation. We used a parallel orthographic projection with XY viewing plane. The hidden surface algorithms  used were back face detection method (N.V)and Z sort method. We also applied illumination models such as ambient light and diffuse light reflection intensity(N.L) .Surface rendering technique used was constant shading method.
Thus our graphics project on fractals was completed.



References
Computer Graphics, C Version by Hearn Baker, Pearson education
class notes  
http://en.wikipedia.org
