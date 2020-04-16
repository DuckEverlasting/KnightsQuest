package KnightsQuest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import java.awt.image.BufferedImage;

public class RenderHandler {

  private BufferedImage view;
  private int[] pixels;

	public RenderHandler(int width, int height) {
    view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
	}

	public void render(Graphics graphics) {
    graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
  }
  
  public void renderImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom) {
    int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    for(int y = 0; y < image.getHeight() * yZoom; y++) {
      for(int x = 0; x < image.getWidth() * xZoom; x++) {
        setPixel(imagePixels[(y / yZoom) * image.getWidth() + (x / xZoom)], x + xPos, y + yPos);
      }  
    }
  }

  private void setPixel(int pixel, int x, int y) {
    int pixelIndex = y * view.getWidth() + x;
    if(pixels.length > pixelIndex) {
      pixels[pixelIndex] = pixel;
    }
  }

}