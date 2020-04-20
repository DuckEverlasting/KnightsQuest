import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.awt.event.KeyEvent;

import java.lang.Runnable;
import java.lang.Thread;
import java.util.HashMap;

import javax.swing.JFrame;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Game extends JFrame implements Runnable {

  public static int alpha = 0xFF77FF00;
  public static final int FRAMERATE = 60;
  public static final XY TILESIZE = new XY(32, 32);
  
  private XY zoom = new XY(2, 2);
  
  private Canvas canvas = new Canvas();
  private RenderHandler renderer;

  private GameObject[] gameObjects;
  private UpdatableEntity[] updatableEntities;
  private KeyboardListener keyListener = new KeyboardListener(this);
  private MouseEventListener mouseListener = new MouseEventListener(this);

  private Player player;

  private SpriteSheet testSheet;
  private SpriteSheet plainsSheet;
  private SpriteSheet charSheet;
  private Tiles testTiles;
  private Tiles plainsTiles;
  private Sprite testSprite;
  private HashMap<String, AnimatedSprite> charSprites;
  private Map map;

  public Game () {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1000, 800);
    setTitle("Knight's Quest");
    setLocationRelativeTo(null);
    add(canvas);
    setVisible(true);
    canvas.createBufferStrategy(3);
    renderer = new RenderHandler(getWidth(), getHeight());

    // Load Assets

    testSheet = new SpriteSheet(loadImage("./sprites/items/Spritesheet_1.png"));
    testSheet.loadSprites(TILESIZE);
    testSprite = testSheet.getSprite(0);
    plainsSheet = new SpriteSheet(loadImage("./sprites/environment/Plains_Terrain.png"));
    plainsSheet.loadSprites(TILESIZE);
    charSheet = new SpriteSheet(loadImage("./sprites/characters/char_spritesheet.png"));
    charSheet.loadSprites(new XY(24, 32));
    charSprites = new HashMap<>(4);
    charSprites.put("u", new AnimatedSprite(charSheet, new XY(24, 32), 2, 12, "alternate"));
    charSprites.put("r", new AnimatedSprite(charSheet, new XY(24, 32), 6, 8, 12, "alternate"));
    charSprites.put("d", new AnimatedSprite(charSheet, new XY(24, 32), 12, 14, 12, "alternate"));
    charSprites.put("l", new AnimatedSprite(charSheet, new XY(24, 32), 18, 20, 12, "alternate"));
    
    testTiles = new Tiles(new File("./sprites/items/Spritesheet_1.txt"), testSheet);
    plainsTiles = new Tiles(new File("./sprites/environment/Plains_Terrain.txt"), plainsSheet);
    map = new Map(new File("./sprites/environment/Plains_Terrain_map1.txt"), plainsTiles);

    // Load Objects and Entities
    gameObjects = new GameObject[1];
    player = new Player(charSprites);
    gameObjects[0] = player;
    updatableEntities = new UpdatableEntity[0];
    
    // Add Listeners
    canvas.addKeyListener(keyListener);
    canvas.addFocusListener(keyListener);
    canvas.addMouseListener(mouseListener);
    canvas.addMouseMotionListener(mouseListener);
  }
  
  public void update() {
    for (int i = 0; i < gameObjects.length; i++) {
      gameObjects[i].update(this);
    }
    for (int i = 0; i < updatableEntities.length; i++) {
      updatableEntities[i].update(this);
    }
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
    
    testTiles.renderTile(4, renderer, new XY(100, 200), zoom);
    map.render(renderer, zoom);
    renderer.renderSprite(testSprite, new XY(0, 0), zoom);

    for (int i = 0; i < gameObjects.length; i++) {
      gameObjects[i].render(renderer, zoom);
    }
    
    renderer.render(graphics);
    
    graphics.dispose();
    bufferStrategy.show();
    renderer.clear();
  }
  
  @Override
  public void run() {
    BufferStrategy bufferStrategy = canvas.getBufferStrategy();
    int i = 0;
    int x = 0;

    long lastTime = System.nanoTime();
    double nanoSecondConversion = 1000000000.0 / FRAMERATE;
    double timePassed = 0;

    while(true) {
      long now = System.nanoTime();

      timePassed += (now - lastTime) / nanoSecondConversion;
      while(timePassed >= 1) {
        update();
        timePassed--;
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

  public void leftClick(XY loc) {
    loc.x = (int) Math.floorDiv(loc.x + renderer.getCamera().x, TILESIZE.x * zoom.x);
    loc.y = (int) Math.floorDiv(loc.y + renderer.getCamera().y, TILESIZE.y * zoom.y);
    map.setTile(14, loc);
  }

  public void rightClick(XY loc) {
    loc.x = (int) Math.floorDiv(loc.x + renderer.getCamera().x, TILESIZE.x * zoom.x);
    loc.y = (int) Math.floorDiv(loc.y + renderer.getCamera().y, TILESIZE.y * zoom.y);
    map.clearTile(loc);
  }

  public void handleCTRL(boolean[] keys) {
    if (keys[KeyEvent.VK_S]) {
      map.saveMap();
    }
  }

  public void handleSHIFT(boolean[] keys) {

  }

  public void handleALT(boolean[] keys) {

  }

  public KeyboardListener getKeyListener() {
    return keyListener;
  }

  public MouseEventListener getMouseListener() {
    return mouseListener;
  }

  public RenderHandler getRenderer() {
    return renderer;
  }
}