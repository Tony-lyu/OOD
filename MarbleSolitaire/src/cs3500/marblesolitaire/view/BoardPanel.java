package cs3500.marblesolitaire.view;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

//import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class BoardPanel extends JPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX,originY;
  
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    originX = (int) (this.getPreferredSize().getWidth()
            / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight()
            / 2 - this.modelState.getBoardSize() * cellDimension / 2);

    int arm = (this.modelState.getBoardSize() + 2) / 3;
    // loop to draw rows
    for (int y = 0; y < this.modelState.getBoardSize(); y++) {
      originX = (int) (this.getPreferredSize().getWidth()
              / 2 - this.modelState.getBoardSize() * cellDimension / 2);
      // loop to draw columns
      for (int x = 0; x < this.modelState.getBoardSize(); x++) {
        // starts a new line for each row
        if (x == 0 && y > 0) {
          originY += 50;
        }
        int i = arm - 1 - y;
        if (y == 0 && x > 2 * arm - 2) {
          break;
        }
        // printing the last marble at top and bottom part
        if ((y < arm - 1 || y > 2 * arm - 2) && x > i + (y + 1) * arm - 1) {
          g.drawImage(marbleSlot, originX, originY, null);
          break;
        }
        // printing marbles
        else {
          // first marble of each row
          if (x == 0 && arm - 1 < y && y < 2 * arm - 2) {
            g.drawImage(marbleSlot, originX, originY, null);
          }
          else if (x == 3 && y == 3) {
            g.drawImage(emptySlot, originX, originY, null);
          }
          else {
            if ((this.modelState.getSlotAt(y, x) == MarbleSolitaireModelState.SlotState.Invalid)
                    && x < arm) {
              g.drawImage(blankSlot, originX, originY, null);
            }
            else if ((this.modelState.getSlotAt(y, x) == MarbleSolitaireModelState.SlotState.Invalid)
                    && x > arm) {
              break;
            }
            else {
              g.drawImage(marbleSlot, originX, originY, null);
            }
          }
        }
        originX += 50;
      }
    }
    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    
  }


 
}
