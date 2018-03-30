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

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
      }
    }
    );
  }

  public void paint(Graphics g) {
    int cellWidth = 20, xyOrigin = 40, circleSize = 10;
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.black);
    g2d.drawRect(xyOrigin, xyOrigin, sheet.length * cellWidth, sheet.length * cellWidth);

    for (int x = 0; x < sheet.length; x++) {
      for (int y = 0; y < sheet.length; y++) {

        //Draw Grid and Dividers.
        if (((x + 1) % 4 == 0 && x != 0) || ((y + 1) % 4 == 0 && y != 0)) {
          g2d.setColor(Color.gray);
          g2d.fillRect(xyOrigin + (x * cellWidth), xyOrigin + (y * cellWidth), 20, 20);
        } else {
          g2d.setColor(Color.black);
          g2d.drawRect(xyOrigin + (x * cellWidth), xyOrigin + (y * cellWidth), 20, 20);
        }
      }
    }
    
    for (int x = 0; x < sheet.length; x++) {
      for (int y = 0; y < sheet.length; y++) {
            if (sheet[x][y] != vertexTypes.BLANK) {
                
              for (int x2 = -1; x2 < 2; x2++) {
                if (x2 == 1 && ((x + 1) % 4 == 0) || x == sheet.length-1) continue; 
                if (x2 == -1 && (x % 4 == 0)) continue;

                for (int y2 = -1; y2 < 2; y2++) {
                  if (y2 == 1 && ((y + 1) % 4 == 0 || y == sheet.length-1)) continue; 
                  if (y2 == -1 && (y % 4 == 0)) continue;
  
                  
                  if (sheet[x + x2][y + y2] != vertexTypes.BLANK) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.drawLine((x * cellWidth) + xyOrigin, (y * cellWidth) + xyOrigin,
                        ((x + x2) * cellWidth) + xyOrigin, ((y + y2) * cellWidth) + xyOrigin);
                  }
                } 
              }
            }
          }      
        }

    for (int x = 0; x < sheet.length; x++) {
      for (int y = 0; y < sheet.length; y++) {

        g2d.setColor(Color.MAGENTA);
        
        if (sheet[x][y] == vertexTypes.ENDPOINT) {
          g2d.drawOval((x * cellWidth) + xyOrigin - (circleSize / 2), (y * cellWidth) + xyOrigin - (circleSize / 2), circleSize, circleSize);
        }
      }
    }

  }
}



    // //Go through each char, get top left inner vertex.
    // for (int x = 1; x < sheet.length; x += 4) {
    //   for (int y = 1; y < sheet.length; y += 4) {
    //     //Go through each inner vert of each char and, if it is a point on the line, check it's neighbouring verts for connections
    //     //This is done so that neighbouring chars do not connect.
    //     for (int x2 = 0; x2 < 2; x2++) {
    //       for (int y2 = 0; y2 < 2; y2++) {
    //         if (sheet[x + x2][y + y2] != vertexTypes.BLANK) {
    //           for (int x3 = -1; x3 < 2; x3++) {
    //             for (int y3 = -1; y3 < 2; y3++) {
    //               if (sheet[x + x2 + x3][y + y2 + y3] != vertexTypes.BLANK) {
    //                 g2d.setColor(Color.MAGENTA);
    //                 g2d.drawLine(((x + x2) * cellWidth) + xyOrigin, ((y + y2) * cellWidth) + xyOrigin,
    //                     ((x + x2 + x3) * cellWidth) + xyOrigin, ((y + y2 + y3) * cellWidth) + xyOrigin);
    //               }
    //             }
    //           }
    //         }
    //       }
    //     }
    //   }