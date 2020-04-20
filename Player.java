import java.util.HashMap;

public class Player implements GameObject {
  Rectangle playerRect;
  int speed = 10;
  boolean isMoving = false;
  HashMap<String, AnimatedSprite> sprites;
  String direction;

  public Player() {
    playerRect = new Rectangle(4, 0, 24, 32);
    playerRect.generateGraphics(3, 0xFFFF00FF);
  }

  public Player(HashMap<String, AnimatedSprite> sprites) {
    this.sprites = sprites;
    this.direction = "d";
    playerRect = new Rectangle(4, 0, 24, 32);

  }

  @Override
  public void render(RenderHandler renderer, XY zoom) {
    if (direction != null) {
      renderer.renderSprite(sprites.get(direction), new XY(playerRect.x, playerRect.y), zoom);
    } else {
      renderer.renderRectangle(playerRect, zoom);
    }
  }
  
  @Override
  public void update(Game game) {
    KeyboardListener keyListener = game.getKeyListener();
    XY prev = new XY(playerRect.x, playerRect.y);

    if (keyListener.up()) {
      playerRect.y -= speed;
    }
    if (keyListener.down()) {
      playerRect.y += speed;
    }
    if (keyListener.left()) {
      playerRect.x -= speed;
    }
    if (keyListener.right()) {
      playerRect.x += speed;
    }

    if (direction != null) {
      updateSprite(game, prev);
    }

    updateCamera(game.getRenderer().getCamera());
  }

  private void updateSprite(Game game, XY prev) {
    String prevDir = direction;
    if (prev.y != playerRect.y) {
      if (prev.y < playerRect.y) {
        direction = "d";
      } else {
        direction = "u";
      }
      if (prevDir == direction && isMoving) {
        sprites.get(direction).update(game);
      } else {
        isMoving = true;
        sprites.get(direction).setCell(0);
      }
    } else if (prev.x != playerRect.x) {
      if (prev.x < playerRect.x) {
        direction = "r";
      } else {
        direction = "l";
      }
      if (prevDir == direction && isMoving) {
        sprites.get(direction).update(game);
      } else {
        isMoving = true;
        sprites.get(direction).setCell(0);
      }
    } else if (isMoving) {
      isMoving = false;
      sprites.get(direction).setCell(1);
    }
  }

  public void updateCamera(Rectangle camera) {
    camera.x = playerRect.x - (camera.w / 2);
    camera.y = playerRect.y - (camera.h / 2);
  }


  public void handleMouseClick(Rectangle mouseRectangle, Rectangle cam, XY zoom) {}
}