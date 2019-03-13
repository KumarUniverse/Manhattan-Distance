package extra_credit;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * This program lists all the paths from the king to the chess piece
 * with the same Manhattan-Distance.
 * Date modified: 3 December 2018
 * @author Akash Kumar
 * @version 1.0
 */
public class ManhattanDistance {
  private static int xPositionOfKing, yPositionOfKing, xPositionOfChessPiece, yPositionOfChessPiece;
  private static int totalPaths = 0;
  private static int[] temp;
  public static void main(String[] args) {
    // get the positions of the two chess pieces.
    Scanner kb = new Scanner(System.in);
    do {
      System.out.print("Enter the x position of the king piece (0-7): ");
      xPositionOfKing = kb.nextInt();
    }
    while(xPositionOfKing < 0 || xPositionOfKing > 7);
    do {
      System.out.print("Enter the y position of the king piece (0-7): ");
      yPositionOfKing = kb.nextInt();
    }
    while(yPositionOfKing < 0 || yPositionOfKing > 7);
    do {
      System.out.print("Enter the x position of the chess piece (0-7): ");
      xPositionOfChessPiece = kb.nextInt();
    }
    while(xPositionOfChessPiece < 0 || xPositionOfChessPiece > 7);
    do {
      System.out.print("Enter the y position of the chess piece (0-7): ");
      yPositionOfChessPiece = kb.nextInt();
    }
    while(yPositionOfChessPiece < 0 || yPositionOfChessPiece > 7);

    // calculate the manhattan distance.
    int currentXDistance = Math.abs(xPositionOfKing-xPositionOfChessPiece);
    int currentYDistance = Math.abs(yPositionOfKing-yPositionOfChessPiece);
    int manhattanDistance = currentXDistance + currentYDistance;
    int firstIndex = xPositionOfKing*10 + yPositionOfKing;
    int[] firstArray = {firstIndex};
    System.out.println();
    System.out.println("The king can take the following manhattan paths " +
                       "to get to the chess piece:");
    findPaths(xPositionOfKing, yPositionOfKing, currentXDistance, currentYDistance,
              manhattanDistance, firstArray);
    System.out.println();
    System.out.println("There are a total of " + totalPaths + " paths.");
    System.out.println("The king has to take " + manhattanDistance +
                       " steps to get to the chess piece. ");
    kb.close();
  } // end main

  private static void findPaths(int x, int y, int currentXDistance, int currentYDistance,
                                int distance, int[] path) {
    // && (x == xPositionOfChessPiece) && (y == yPositionOfChessPiece)
    if(distance == 0 && (x == xPositionOfChessPiece) && (y == yPositionOfChessPiece)) { // base case
      for(int i = 0; i < path.length; i++) {
        if(i != (path.length-1))
          System.out.print("(" + (path[i]/10) + "," + (path[i]%10) + ") --> ");
        else
          System.out.print("(" + (path[i]/10) + "," + (path[i]%10) + ")");
      }
      System.out.println();
      ++totalPaths;
    }
    else { // recursive case
      if ((rightOne(x, y) != -1) &&
          (Math.abs(x + 1 - xPositionOfChessPiece) < currentXDistance)) {
        temp = new int[path.length + 1];
        for (int i = 0; i < path.length; i++) {
          temp[i] = path[i];
        }
        temp[temp.length - 1] = rightOne(x, y);
        findPaths((x+1), y, currentXDistance-1, currentYDistance,
         distance-1, temp);
      }
      if ((leftOne(x, y) != -1) &&
          (Math.abs(x - 1 - xPositionOfChessPiece) < currentXDistance)) {
        temp = new int[path.length + 1];
        for (int i = 0; i < path.length; i++) {
          temp[i] = path[i];
        }
        temp[temp.length - 1] = leftOne(x, y);
        findPaths((x-1), y, currentXDistance-1, currentYDistance,
         distance-1, temp);
      }
      if ((downOne(x, y) != -1) &&
          (Math.abs(y + 1 - yPositionOfChessPiece) < currentYDistance)) {
        temp = new int[path.length + 1];
        for (int i = 0; i < path.length; i++) {
          temp[i] = path[i];
        }
        temp[temp.length - 1] = downOne(x, y);
        findPaths(x, (y+1), currentXDistance, currentYDistance-1,
         distance-1, temp);
      }
      if ((upOne(x, y) != -1) &&
          (Math.abs(y - 1 - yPositionOfChessPiece) < currentYDistance)) {
        temp = new int[path.length + 1];
        for (int i = 0; i < path.length; i++) {
          temp[i] = path[i];
        }
        temp[temp.length - 1] = upOne(x, y);
        findPaths(x, (y-1), currentXDistance, currentYDistance-1,
         distance-1, temp);
      }
    }

  } // end of findPaths method

  // move the king piece one square right.
  private static int rightOne(int x, int y) {
    if((x+1) < 8)
      return (x+1)*10 + y;
    return -1;
  }

  // move the king piece one square left.
  private static int leftOne(int x, int y) {
    if((x-1) > -1)
      return (x-1)*10 + y;
    return -1;
  }

  // move the king piece one square down.
  private static int downOne(int x, int y) {
    if((y+1) < 8)
      return x*10 + (y+1);
    return -1;
  }

  // move the king piece one square up.
  private static int upOne(int x, int y) {
    if((y-1) > -1)
      return x*10 + (y-1);
    return -1;
  }
} // end class
