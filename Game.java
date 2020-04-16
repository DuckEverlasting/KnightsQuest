package KnightsQuest;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.lang.Runnable;
import java.lang.Thread;

import javax.swing.JFrame;

import javax.imageio.ImageIO;

import java.io.IOException;

public class Game extends JFrame implements Runnable {

  private Canvas canvas = new Canvas();
  private RenderHandler renderer;
  private BufferedImage testImage;

  public Game () {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1000, 800);
    setTitle("Knight's Quest");
    setLocationRelativeTo(null);
    add(canvas);
    setVisible(true);
    canvas.createBufferStrategy(3);
    renderer = new RenderHandler(getWidth(), getHeight());

    testImage = loadImage("./images/Blue_Gem.png");
  }
  
  public void update() {

  }

  private BufferedImage loadImage(String path) {
    try {
      BufferedImage loadedImage = ImageIO.read(Game.class.getResource(path));
      BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
      formattedImage.createGraphics().drawImage(loadedImage, 0, 0, null);
      return formattedImage;
    } catch(IOException exception) {
      exception.printStackTrace();
      return null;
    }
  }

  public void render() {
    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
    Graphics graphics = bufferStrategy.getDrawGraphics();
    super.paint(graphics);
    
    renderer.renderImage(testImage, 0, 0, 5, 5);
    renderer.render(graphics);
    
    graphics.dispose();
    bufferStrategy.show();
  }
  
  @Override
  public void run() {
    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
    int i = 0;
    int x = 0;

    long lastTime = System.nanoTime();
    double nanoSecondConversion = 1000000000.0 / 60;
    double changeInSeconds = 0;

    while(true) {
      long now = System.nanoTime();

      changeInSeconds += (now - lastTime) / nanoSecondConversion;
      while(changeInSeconds >= 1) {
        update();
        changeInSeconds = 0;
      }

      render();
      lastTime = now;
    }
  }

  public static void main(String[] args) {
    Game game = new Game();
    Thread gameThread = new Thread(game);
    gameThread.start();
  }
}