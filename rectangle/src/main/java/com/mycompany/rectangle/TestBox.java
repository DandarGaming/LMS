package com.mycompany.rectangle;

public class TestBox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            //Create Box 1
            float width = 10.0f;
            float length = 5.5f;
            float height = 12.0f;
            
            
            //Testing the full arg container
            Box b1 = new Box(width,length,height);
            
            //Test toString method
            System.out.println("Box 1");
            System.out.println("-------");
            System.out.println(b1.toString());
            
            
            //Test no args constructor
            
            Box b2 = new Box();
            
          
            
            System.out.println("\nBox 2");
            System.out.println("------");
            System.out.println(b2.toString());
            
            width = 15.0f;
            length = 20.0f;
            height = 4.5f;
              //Test setter and getter methods
            b2.setWidth(width);
            b2.setLength(length);
            b2.setHeight(height);
            
            
            System.out.println(b2.getWidth());
            System.out.println(b2.getLength());
            System.out.println(b2.getHeight());
            
            //test getVolume
            System.out.println(b2.getVolume());
    }//end main
    
}//end class
