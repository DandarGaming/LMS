
package com.mycompany.rectangle;

//The extends keyword in java
//implements the inheritance relationship
//include extends in the class declaration

//Box subclass is a child of the Rectangle
//super class
public class Box extends Rectangle{
    //instance variables
    private float height;
    
    //Constructors
    //Full argument constructor
    public Box(float width,float length,float height)
    {
        //this.width = width; width is private
        //invoke super class constructor
        super(width,length);
        this.height = height;
    }
    
    public Box()
    {
    super();
    this.height = 0.0f;
    }
    //setter and fetter for height
    public void setHeight(float height){
    this.height = height;
    }
    public float getHeight() {
        
    return height;
    }
    
    public float getVolume() {
     //vol = width*width*height;
     //wont work width and length are private
     float volume = super.getArea() * height;  
     
     return volume;
    }
    // AT OVERRIDE ANNOTATION
    //Tells the compiler that we are overriding
    //The inherited implementation of the 
    //Rectangle super class to string method
    //ie. the Box sub class has its own implementation
    //this process is called method overriding
    @Override
    public String toString() 
    {   
        //return width, length , height
        //width and length are private - not gonna work
        //return "Width" + width + "\nLength: " + Length;
        return super.toString() +"\nHeight: " + height + "\nVolume: " + getVolume();
    }
} //end class
