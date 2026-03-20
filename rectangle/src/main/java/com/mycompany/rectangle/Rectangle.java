package com.mycompany.rectangle;

public class Rectangle {
    
    //instance variable (properties)
    //constructors (overloaded)
    //setter/getter methods 
    //other methods e.g. getArea()
    //toString()
    //RECTANGLE IS THE SUPER CLASS
    
    //instance variables
    private float width;
    private float length;
    
    //Overloaded constructors
    //Full argument constructor
    
    public Rectangle(float width,float length)
    {
        this.width = width;
        this.length = length;
    }
    //no argument constructor
    public Rectangle()
    {
    this.width = 0.0f;
    this.length = 0.0f;
    }
    
    //Setter and getter methods
    public void setWidth(float width)
    {
    this.width = width;
    }
    
    public float getWidth()
    {
        return width;
    }
    
        public void setLength(float length)
    {
    this.length = length;
    }
        
    public float getLength()
    {
        return length;
    }
    
    public float getArea()
    {
        return width * length;
    }
    public String toString()
    {
        return "Width: " + width + "\n Length: " + length + "\n Area: " + getArea();
    }
    public static void main(String[] args) {
        System.out.println("Rectangle 1: ");
        //Testing no args constructor
        Rectangle r1 = new Rectangle();
        
        //Display the r1 rectangle
        System.out.println(r1.toString());
        
        //Test setter and getter methods
        r1.setWidth(10);
        r1.setLength(20);
        System.out.println(r1.getLength());
        System.out.println(r1.getWidth());
        
        //Construct a second rectangle
        //test full args constructor
        Rectangle r2 = new Rectangle(20,20);
        System.out.println(r2.toString());
        
        //Test get area
        System.out.println("Area: " + r2.getArea());
    }//end main
}//end class
