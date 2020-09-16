import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private int[] snakeXlength=new int[750];
    private int[] snakeYlength=new int[750];

    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lengthOfSnake=2;

    private Timer timer;
    private int delay=100;

    private ImageIcon snakeimage;

    private int[] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyYpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private ImageIcon enemyimage;
    private Random random=new Random();
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);

    private int score=0;

    private int moves=0;


    private ImageIcon titleImage;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){
        if (moves==0){
            snakeXlength[1]=75;
            snakeXlength[0]=100;

            snakeYlength[1]=100;
            snakeYlength[0]=100;
        }
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);


        g.setColor(Color.black);
        g.fillRect(25,11,850,54);
        g.setColor(Color.white);
        g.setFont(new Font("Courier",Font.BOLD,20));
        g.drawString("SNAKE GAME ", 400,43);


        g.setColor(Color.white);
        g.drawRect(24,74,851,577);

        g.setColor(Color.black);
        g.fillRect(25,75,850,576);

        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("Length: " + lengthOfSnake, 780,50);

        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("Scores: " + score, 780,30);

        rightmouth=new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);

        for (int i=0;i<lengthOfSnake;i++){
            if (i==0 && right){
                rightmouth=new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
            }
            if (i==0 && left){
                leftmouth=new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
            }
            if (i==0 && down){
                downmouth=new ImageIcon("downmouth.png");
                downmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
            }
            if (i==0 && up){
                upmouth=new ImageIcon("upmouth.png");
                upmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
            }

            if (i!=0){
                snakeimage=new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
            }
        }

        enemyimage=new ImageIcon("enemy.png");

        if ((enemyXpos[xpos]==snakeXlength[0]) && enemyYpos[ypos]==snakeYlength[0])
        {
            score++;
            lengthOfSnake++;
            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }

        enemyimage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);

        for (int k=1;k<lengthOfSnake;k++)
        {
            if (snakeXlength[k]==snakeXlength[0] && snakeYlength[k] == snakeYlength[0])
            {
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.white);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("Game Over",300,300);


                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Space to RESTART",350,340);
            }

        }
        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right){
            for (int j=lengthOfSnake-1;j>=0;j--){
                snakeYlength[j+1]=snakeYlength[j];
            }
            for (int j=lengthOfSnake;j>=0;j--){
               if (j==0){
                   snakeXlength[j]=snakeXlength[j]+25;
               }
               else {
                   snakeXlength[j]=snakeXlength[j-1];
               }
               if (snakeXlength[j]>850){
                   snakeXlength[j]=25;
               }
            }
            repaint();
        }
        if (left){
            for (int j=lengthOfSnake-1;j>=0;j--){
                snakeYlength[j+1]=snakeYlength[j];
            }
            for (int j=lengthOfSnake;j>=0;j--){
                if (j==0){
                    snakeXlength[j]=snakeXlength[j]-25;
                }
                else {
                    snakeXlength[j]=snakeXlength[j-1];
                }
                if (snakeXlength[j]<25){
                    snakeXlength[j]=850;
                }
            }
            repaint();
        }
        if (up){
            for (int j=lengthOfSnake-1;j>=0;j--){
                snakeXlength[j+1]=snakeXlength[j];
            }
            for (int j=lengthOfSnake;j>=0;j--){
                if (j==0){
                    snakeYlength[j]=snakeYlength[j]-25;
                }
                else {
                    snakeYlength[j]=snakeYlength[j-1];
                }
                if (snakeYlength[j]<75){
                    snakeYlength[j]=625;
                }
            }
            repaint();
        }
        if (down){
            for (int j=lengthOfSnake-1;j>=0;j--){
                snakeXlength[j+1]=snakeXlength[j];
            }
            for (int j=lengthOfSnake;j>=0;j--){
                if (j==0){
                    snakeYlength[j]=snakeYlength[j]+25;
                }
                else {
                    snakeYlength[j]=snakeYlength[j-1];
                }
                if (snakeYlength[j]>625){
                    snakeYlength[j]=75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            moves=0;
            score=0;
            lengthOfSnake=2;
            repaint();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if (!left){
                right=true;
            }else {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if (!right){
                left=true;
            }else {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            up=true;
            if (!down){
                up=true;
            }else {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if (!up){
                down=true;
            }else {
                down=false;
                up=true;
            }
            right=false;
            left=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
