/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runegen;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Pritchy
 */
public class RuneGen {
    public enum vertexTypes { BLANK, ENDPOINT, MIDPOINT, SPECIAL }

  
  
  private static final int VERTEXES_PER_ROW = 12;
  private static final int CHAR_WIDTH = 4;
  private static final int VERTEX_CHAR_GAP = 0;
  private static vertexTypes[][] sheet = new vertexTypes[VERTEXES_PER_ROW][VERTEXES_PER_ROW];
  private static Random random = new Random();
   static Point pointA, pointB;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) { 
      
    for (int i = 0; i < VERTEXES_PER_ROW; i++) 
     Arrays.fill(sheet[i], vertexTypes.BLANK);
     
    for (int x = 0; x <= VERTEXES_PER_ROW - CHAR_WIDTH; x += (CHAR_WIDTH + VERTEX_CHAR_GAP)) {
      for (int y = 0; y <= VERTEXES_PER_ROW - CHAR_WIDTH; y+= (CHAR_WIDTH + VERTEX_CHAR_GAP)) {
        //stuff
        generateCharacter(x, y);
      }
    }
    
      
      printSheet();
      Renderer renderer = new Renderer(sheet);
   
  }
  
  public static void generateCharacter(int x, int y) {
    //pick two (outer?) points
    if (random.nextInt(2) == 0) //50:50
      pointA = new Point(random.nextInt(CHAR_WIDTH), random.nextInt(2)*(CHAR_WIDTH-1));
    else 
      pointA = new Point(random.nextInt(2)*(CHAR_WIDTH-1), random.nextInt(CHAR_WIDTH));
    
    pointB = pointA;
    
    while(pointB.equals(pointA)) {    
      if (random.nextInt(2) == 0) 
        pointB = new Point(random.nextInt(CHAR_WIDTH), random.nextInt(2)*(CHAR_WIDTH-1));
      else 
        pointB = new Point(random.nextInt(2)*(CHAR_WIDTH-1), random.nextInt(CHAR_WIDTH));
    }
    
    //Connect them
    
    Point distance = new Point(pointA.x - pointB.x, pointA.y - pointB.y);
    
    while (distance.x != 0 || distance.y != 0) {
      
          if (distance.x < 0) {
            distance.x++;
          } else if (distance.x > 0) {
            distance.x--;
          }
          
          if (distance.y < 0) {
            distance.y++;
          } else if (distance.y > 0) {
            distance.y--;
          }
      
      sheet[x + pointB.x + distance.x][y + pointB.y + distance.y] = vertexTypes.MIDPOINT;
    }
    
    //pointA = new Point(CHAR_WIDTH-1, CHAR_WIDTH-1);
    
    sheet[x + pointA.x][y + pointA.y] = vertexTypes.ENDPOINT;
    sheet[x + pointB.x][y + pointB.y] = vertexTypes.ENDPOINT;


    //pathfind3
    
  }
  
  public static void printSheet() {
    for (int y = 0; y < VERTEXES_PER_ROW; y++) {
      for (int x = 0; x < VERTEXES_PER_ROW; x++) {
        if (sheet[x][y] == vertexTypes.BLANK)
          System.out.print(" 0 ");
        else if (sheet[x][y] == vertexTypes.MIDPOINT)
          System.out.print(" 1 ");
        else if (sheet[x][y] == vertexTypes.ENDPOINT)
          System.out.print(" 2 ");

      }
      System.out.print("\n");
    }
  }
  
}
