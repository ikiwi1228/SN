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
public class Main {
  
  static int areaSize = 60;
  static Game game = null;

  public static void main(String[] args) {
    game = new Game();
    game.init(areaSize);
  }
}