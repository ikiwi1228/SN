/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

/**
 *
 * @author luj0314
 */
import java.io.*;

public class Game {

  private Snake snake;
  private GUI gui;
  
  
  public void init(int areaSize) {
    snake = new Snake();
    gui = new GUI();
    
    gui.init(areaSize, this);
  }

  public void setCoord(int x, int y) {
    snake.setPos(x, y);
    checkCollision();
    checkFood(x, y);
  }

  private void checkCollision() {
    for (int i = 0; i < (snake.getlength() - 3); i++) {
      if ((getHeadX() == snake.getX(i) && getHeadY() == snake.getY(i))||(getHeadX()<1||getHeadX()>500)||(getHeadY()<1||getHeadY()>900)) {
        checkScore();
        gui.endAnimation();
        resetAll();
        break;
      }
    }
  }

  private void checkFood(int x, int y) {
    if (snake.getFoodX() == x && snake.getFoodY() == y) {
      snake.growlength(snake.getlength());
      gui.blink();
      gui.setScore();
      spawnFood();
    }
  }

  public void resetAll() {
    snake.initializeGame(gui.getGridSize() * gui.getGridSize());
    gui.resetGUI();
    gui.start();
  }

  public void spawnFood() {
    int x = getRand();
    int y = getRand();
    if (checkFoodCollision(x, y) == 0) {
      snake.setFood(x, y);
      gui.setFoodPos(x, y);
    } else {
      spawnFood();
    }
  }

  private int checkFoodCollision(int x, int y) {
    for (int i = 1; i < (snake.getlength()); i++) {
      if (x == snake.getX(i) && y == snake.getY(i)) {
        return 1;
      }
    }
    return 0;
  }

  public String getScore() {
    return Integer.toString(snake.getScore());
  }

  public int getHeadX() {
    return snake.getX(snake.getlength());
  }

  public int getHeadY() {
    return snake.getY(snake.getlength());
  }

  public int getX(int i) {
    return snake.getX(i);
  }

  public int getY(int i) {
    return snake.getY(i);
  }

  public void forcedReset() {
    gui.endAnimation();
    resetAll();
  }

  public int getFoodX() {
    return snake.getFoodX();
  }

  public int getFoodY() {
    return snake.getFoodY();
  }

  public int getlength() {
    return snake.getlength();
  }

  public int getRand() {
    return (0 + (int) (Math.random() * (((gui.getGridSize() - 2) - 0) + 0)));
  }

  private void checkScore() {
    try {
      File scorefile = new File("score.txt");
      if (!scorefile.exists()) {
        writeScore();
      }
      BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
      if (Integer.parseInt(reader.readLine()) < snake.getScore()) {
        writeScore();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeScore() throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter("score.txt", "UTF-8");
    writer.println(getScore());
    writer.close();
    gui.newRecord();
  }
}
