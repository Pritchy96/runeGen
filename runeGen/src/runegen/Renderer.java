/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runegen;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import runegen.RuneGen.vertexTypes;

/**
 *
 * @author Pritchy
 */
public class Renderer extends Frame {
  
  private static vertexTypes[][] sheet;
  
  public Renderer(vertexTypes[][] sheet) {
    super("Rendered output");

    this.sheet = sheet;
   
    setVisible(true);
    setSize(910, 910);
    
    addWindowListener(new WindowAdapter() 
      {public void windowClosing(WindowEvent e) 
         {dispose(); System.exit(0);}  
      }
    );
  }
  
    public void paint(Graphics g) {
    int scaleFactor = 20, xyOrigin = 50;
    Graphics2D g2d = (Graphics2D)g;
    
    g2d.setColor(Color.black);  
    g2d.drawRect(xyOrigin, xyOrigin, sheet.length * scaleFactor, sheet.length * scaleFactor);  
    
    g2d.setColor(Color.blue);
    for (int x = 0; x < sheet.length; x++) {
      for (int y = 0; y < sheet.length; y++) {
        if (sheet[x][y] == vertexTypes.ENDPOINT)
          g2d.drawOval((x * scaleFactor) + xyOrigin, (y * scaleFactor) + xyOrigin, scaleFactor, scaleFactor);
      }
      
      System.out.print("\n");
    }
    
  }
}
