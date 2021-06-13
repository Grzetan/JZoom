# Introduction

JZoom is an extenstion to JAVA Swing and JAVA AWT that allows to zoom into drawn figures without quality loss.

# How it works

JZoom sees canvas not as pixel grid but as coordinates system.

When using JZoom you can declare key point's of figures by using doubles.

That means you can for example declare the center of a circle at (x=20.34, y=189.798).

It also allows you to draw very small objects like a line with starting point (0,0) and ending point (0.2, 0.2).
Of course, you will not see it when you are zoomed out all the way but when you start to zoom in this tiny line will slowly get bigger.

# Installation

Copy src/com/grzetan/jzoom/JZoom.java file to your working directory.
When you want to use it, just import it on top of the file.

# Tutorial

After importing JZoom class

```java
import com.grzetan.jzoom.JZoom;
```

we can declare an instance of imported object at the top of JPanel class.
(If you want to hava a full look on tutorial files, you can find them in the same directory as JZoom.java).

```java
JZoom jZoom = new JZoom(width,height);
```

You can change the background color by using

```java
jZoom.setBackground(color);
```

'width' and 'height' arguments are width and height of JZoom canvas.

Then, in the constructor, we have to install mouse adapter.

```java
jZoom.installMouseAdapter(this);
```

The next step is creating draw method that will be called in every frame.

```java
public void draw() {
    //draw figures
}
```
This function contains all drawings.

The last step is creating 'paint' method.

```java
public void paint(Graphics g){
    draw();
    Zoom.render(g, x, y, observer);
}
```

First we need to call draw method so the figures are drown, then we are rendering an image that JZoom generated to window.

(x,y) - coordinates of the starting point of the JZoom canvas.

observer - Observer of the canvas. Just pass 'this' (JPanel class).

### Drawing figures with JZoom

#### - Line

```java
jZoom.drawLine(x1,y1,x2,y2,color);
```

(x1,y1) - coordinates of starting point

(x2,y2) - coordinates of ending point

#### - Rectangle

Filled:

```java
jZoom.fillRect(x,y,width,height,color);
```

Not filled:

```java
jZoom.drawRect(x,y,width,height,color);
```
(x,y) - coordinates of top left corner

(width,height) - width and height of the rectangle

#### - Oval

Filled:

```java
jZoom.fillOval(x,y,r1,r2,color);
```

Not filled:

```java
jZoom.drawOval(x,y,r1,r2,color);
```
(x,y) - coordinates of the top left corner

(r1,r2) - width and height of the oval

#### - Polygon

Filled:

```java
jZoom.fillPolygon(x,y,num,color);
```

Not filled:

```java
jZoom.drawPolygon(x,y,num,color);
```
(

x - Array of x coordinates of points.

y - Array of y coordinates of points

num - Number of corner's of this polygon. (This number must be the same as length of x and length of y).

## Drawing images

```java
jZoom.drawImage(image, x , y, observer);
```

image - image to display (type = BufforedImage).

(x,y) - where image should be placed.

observer - observer of this image. Just pass 'this' (Jpanel class).

Remember! When you zoom into drawn image, it will slowly lose quality. Lossless zooming works only with shapes.

# Disabling zooming and dragging

To manage zooming use this command

```java
jZoom.allowZooming(bool);
```

When bool == true zooming will be enabled.
When bool == false zooming will be disabled.

Zooming is allowed by default.

------------------

To manage dragging use this command

```java
jZoom.allowDragging(bool);
```

When bool == true draging will be enabled.
When bool == false draging will be disabled.

Dragging is allowed by default.

# Follow point feature

JZoom has a feature that allows to follow a certain point on canvas.

To enable this feature use this command (It should be called in every frame):

```java
jZoom.followPoint(x,y,zoom);
```

(x,y) - coordinates of the point to follow.

zoom - zoom while following point. Example - 3.14.

It is recommended to disable zooming and dragging while using this feature.

## Thanks for using JZoom <3
