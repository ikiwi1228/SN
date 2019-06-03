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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GUI implements Movedir {

    Game game;
    private JFrame frame;
    private JLabel[][] screen;
    private JPanel gamepanel;
    private int x, y;
    private String movedir;
    private int areaSize = 60;
    private int TIMER = 100;
    private boolean mvlock;
    private final Color bgColor = Color.GREEN.darker().darker();
    private final Color snColor = Color.BLACK;
    private final Color foodColor = Color.WHITE;
    private ImageIcon Icon;


    public void init(int areaSize, Game game) {

        this.areaSize = areaSize;
        this.game = game;
        frame = new JFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamepanel = new JPanel(new GridLayout(areaSize, areaSize));
        

        gamepanel.setBackground(bgColor);
        frame.setContentPane(gamepanel);
        screen = new JLabel[areaSize][areaSize];

        inputKeys();
        this.x = 0;
        this.y = 0;
        while (y < areaSize - 1) {
            screen[this.x][this.y] = new JLabel();
            screen[this.x][this.y].setText("■");
            gamepanel.add(screen[x][y]);
            this.x++;
            if (this.x == areaSize - 1 && this.y < areaSize - 1) {
                this.x = 0;
                this.y++;
            }
        }
        frame.setSize(getFrameSize(), getFrameSize());

        frame.setResizable(false);
        this.game.resetAll();


        
    }
    public void resetGUI() {
        setScore();
        this.x = 0;
        this.y = 0;
        while (y < areaSize - 1) {
            screen[this.x][this.y].setForeground(bgColor);
            this.x++;
            if (this.x == areaSize - 1 && this.y < areaSize - 1) {
                this.x = 0;
                this.y++;
            }
        }

    }
    public void show(){
         JWindow window = new JWindow();
        JLabel splash = new JLabel();
        splash.setText("Leo Lu Period 1 APCSP           Snake of The Forest             Use arrows to control the snake to eat the rabbits ");
        window.getContentPane().add(splash);
        window.setBounds(400, 200, 703, 391);
        window.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }
    public void start() {
        this.x = areaSize / 2;
        this.y = areaSize / 2;
        movedir = "LEFT";
        game.spawnFood();
        while (!"".equals(movedir)) {
            switch (movedir) {
                case "UP":
                    moveUp();
                    break;
                case "DOWN":
                    moveDown();
                    break;
                case "LEFT":
                    moveLeft();
                    break;
                case "RIGHT":
                    moveRight();
                    break;
                case "":
                    break;
            }
        }
    }

    public void stop() {
        movedir = "";
    }

    public void moveUp() {
        //movedir="UP";
        if (this.y != 0) {
            this.y--;
        } else {
            this.y = areaSize - 2;
        }
        waitInterval(TIMER);
        updatePosition();
        game.setCoord(x, y);
        mvlock = false;
    }

    public void moveDown() {
        //movedir="DOWN";
        if (this.y != areaSize - 2) {
            this.y++;
        } else {
            this.y = 0;
        }
        waitInterval(TIMER);
        updatePosition();
        game.setCoord(x, y);
        mvlock = false;
    }

    public void moveLeft() {
        //movedir="LEFT";
        if (this.x != 0) {
            this.x--;
        } else {
            this.x = areaSize - 2;
        }
        waitInterval(TIMER);
        updatePosition();
        game.setCoord(x, y);
        mvlock = false;
    }

    public void moveRight() {
        //movedir="RIGHT";
        if (this.x != areaSize - 2) {
            this.x++;
        } else {
            this.x = 0;
        }
        waitInterval(TIMER);
        updatePosition();
        game.setCoord(x, y);
        mvlock = false;
    }

    public void updatePosition() {
        screen[game.getX(0)][game.getY(0)].setForeground(bgColor);
        screen[game.getHeadX()][game.getHeadY()].setForeground(snColor);
    }

    public void blink() {
        screen[game.getHeadX()][game.getHeadY()].setForeground(foodColor);
        waitInterval(TIMER);
        screen[game.getHeadX()][game.getHeadY()].setForeground(snColor);
    }

    public void endAnimation() {
        for (int a = 0; a < game.getlength() - 1; a++) {
            screen[game.getX(a)][game.getY(a)].setForeground(Color.BLACK);
        }
        waitInterval(80);
        for (int a = 0; a < game.getlength() - 1; a++) {
            screen[game.getX(a)][game.getY(a)].setForeground(snColor);
        }
        waitInterval(40);
        for (int a = 0; a < game.getlength() - 1; a++) {
            screen[game.getX(a)][game.getY(a)].setForeground(Color.DARK_GRAY);
        }
        waitInterval(500);
    }

    public void waitInterval(int time) {
        if (time > 0) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
        }
    }

    public void setFoodPos(int a, int b) {
        screen[a][b].setForeground(foodColor);
    }

    public void removeFood(int a, int b) {
        screen[a][b].setForeground(bgColor);
    }

    public int getGridSize() {
        return this.areaSize;
    }

    public void setTimer(int i) {
        this.TIMER = i;
    }

    public int getFrameSize() {                          //skaalautuvuutta, jos pelin ruudukon kokoa suurennellaan tai pienennetään
        if (this.areaSize > 29) {
            return this.areaSize * 11;
        } else {
            return this.areaSize * 12;
        }
    }

    public void inputKeys() {
        gamepanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        gamepanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        gamepanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        gamepanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        gamepanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "AUTO");

        gamepanel.getActionMap().put("UP", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!movedir.equals("DOWN")) {
                    if (mvlock == false) {
                        movedir = "UP";
                        mvlock = true;
                    }
                }
            }
        });
        gamepanel.getActionMap().put("LEFT", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!movedir.equals("RIGHT")) {
                    if (mvlock == false) {
                        movedir = "LEFT";
                        mvlock = true;
                    }
                }
            }
        });
        gamepanel.getActionMap().put("DOWN", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!movedir.equals("UP")) {
                    if (mvlock == false) {
                        movedir = "DOWN";
                        mvlock = true;
                    }
                }
            }
        });
        gamepanel.getActionMap().put("RIGHT", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!movedir.equals("LEFT")) {
                    if (mvlock == false) {
                        movedir = "RIGHT";
                        mvlock = true;
                    }
                }
            }
        });

    }

    ;

  public void setScore() {
        frame.setTitle("Score: " + game.getScore());
    }

    public void newRecord() {
        frame.setTitle("Score: " + game.getScore() + " New Record!");
    }

    void setMovedir(String mvdir) {
        this.movedir = mvdir;
    }
}
