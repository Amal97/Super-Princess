package SuperPrincess.view;

import javafx.scene.image.*;

public class Princess {

  private ImageView showPrincess = new ImageView();
  private Image princess;
  private double moveVertically, moveHorizontally;
  private double startX, startY;

  public void princess(int princessNum, double scaleX, double scaleY, double startX, double startY) {
    makePrincess(princessNum, scaleX, scaleY, startX, startY);
  }

  public ImageView makePrincess(int princessNum, double width, double height, double startX, double startY) {
    startX = 333.0;
    startY = 154.0;

    Image princessOne = new Image("file:res/Amalia.png");
    Image princessTwo = new Image("file:res/Kunalia.png");
    Image princessThree = new Image("file:res/Dhariyana.png");
    switch (princessNum) {
    case 1:
      princess = princessOne;
      break;
    case 2:
      princess = princessTwo;
      break;
    case 3:
      princess = princessThree;
      break;
    }
    this.startX = startX;
    this.startY = startY;
    moveVertically = startY;
    moveHorizontally = startX;
    showPrincess.setImage(princess);
    showPrincess.setFitHeight(height);
    showPrincess.setFitWidth(width);
    showPrincess.setX(startX);
    showPrincess.setY(startY);
    return showPrincess;
  }

  public ImageView showPrincess() {
    return showPrincess;
  }

  public Image princess() {
    return princess;
  }

  public double startX() {
    return startX;
  }

  public double startY() {
    return startY;
  }

  public void removeMoveVertically() {
    moveVertically = startY;
  }

  public void removeMoveHorizontally() {
    moveHorizontally = startX;
  }

  public void moveUp(int moveUpSpeed) {
    moveVertically = moveVertically - moveUpSpeed;
    showPrincess.setY(moveVertically);
  }

  public void moveDown(int moveDownSpeed) {
    moveVertically = moveVertically + moveDownSpeed;
    showPrincess.setY(moveVertically);
  }

  public void moveLeft(int moveLeftSpeed) {
    moveHorizontally = moveHorizontally - moveLeftSpeed;
    showPrincess.setX(moveHorizontally);
  }

  public void moveRight(int moveRightSpeed) {
    moveHorizontally = moveHorizontally + moveRightSpeed;
    showPrincess.setX(moveHorizontally);
  }

}
