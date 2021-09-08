package nonograms.view;

import nonograms.controller.ControllerImpl;
import nonograms.model.Clues;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class View implements FXComponent {

  private ControllerImpl _controller;

  public View(ControllerImpl c) {
    _controller = c;
  }

  @Override
  public Parent render() {
    Clues clue = _controller.getClues();
    Pane retVal = new HBox();
    Label displayIndex = new Label("Puzzle No." + (_controller.getPuzzleIndex() + 1) + " out of 5");
    retVal.getChildren().add(displayIndex);
    BorderPane borderPane = new BorderPane();
    HBox col = new HBox();
    VBox row = new VBox();
    VBox blank = new VBox();
    blank.setMinWidth(20 * clue.getRowCluesLength());
    col.getChildren().add(blank);
    for (int i = 0; i < clue.getWidth(); i++) {
      VBox c = new VBox();
      c.setMinWidth(17);
      // c.setMaxHeight(15);
      for (int j = 0; j < clue.getColCluesLength(); j++) {
        c.getChildren().add(makeTile(clue.getColClues(i)[j]));
      }
      col.getChildren().add(c);
    }
    for (int i = 0; i < clue.getHeight(); i++) {
      HBox h = new HBox();
      h.setMinHeight(16);
      h.setMaxHeight(16);
      h.setMinWidth(20);
      for (int j = 0; j < clue.getRowCluesLength(); j++) {
        h.getChildren().add(makeTile(clue.getRowClues(i)[j]));
        HBox newBlank = new HBox();
        newBlank.setMinWidth(10);
        h.getChildren().add(newBlank);
      }
      row.getChildren().add(h);
    }
    GridPane cells = new GridPane();
    for (int i = 0; i < clue.getHeight(); i++) {
      for (int j = 0; j < clue.getWidth(); j++) {
        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.setStroke(Color.BLACK);
        if (_controller.isEliminated(i, j)) {
          rectangle.setFill(Color.BLACK);
        } else if (_controller.isShaded(i, j)) {
          rectangle.setFill(Color.YELLOW);
        } else {
          rectangle.setFill(Color.WHITE);
        }
        int finalRowIndex = i;
        int finalColIndex = j;
        rectangle.setOnMouseClicked(
            mouseEvent -> {
              if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                _controller.toggleEliminated(finalRowIndex, finalColIndex);
              }
              if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                _controller.toggleShaded(finalRowIndex, finalColIndex);
              }
              if (_controller.isEliminated(finalRowIndex, finalColIndex)) {
                rectangle.setFill(Color.BLACK);
              } else if (_controller.isShaded(finalRowIndex, finalColIndex)) {
                rectangle.setFill(Color.CYAN);
              } else {
                rectangle.setFill(Color.WHITE);
              }
              if (_controller.isSolved()) {
                Label l = new Label("Congratulations! You Solved this puzzle!!");
                l.setFont(Font.font(20));
                borderPane.setBottom(l);
              } else {
                Label l2 = new Label("You might still need to check..");
                l2.setFont(Font.font(20));
                borderPane.setBottom(l2);
              }
            });
        cells.add(rectangle, j, i);
      }
    }
    borderPane.setLeft(row);
    borderPane.setCenter(cells);
    borderPane.setTop(col);
    VBox example = new VBox();
    Label eliminated = new Label("Eliminated: ");
    Label shaded = new Label("Shaded");
    Rectangle eliminatedRec = new Rectangle(15, 15);
    eliminatedRec.setStroke(Color.BLACK);
    eliminatedRec.setFill(Color.BLACK);
    Rectangle shadedRec = new Rectangle(15, 15);
    shadedRec.setStroke(Color.BLACK);
    shadedRec.setFill(Color.CYAN);
    example.getChildren().add(shaded);
    example.getChildren().add(shadedRec);
    example.getChildren().add(eliminated);
    example.getChildren().add(eliminatedRec);
    borderPane.setRight(example);
    retVal.getChildren().add(borderPane);
    
    return retVal;
  }

  private static Label makeTile(int num) {
    javafx.scene.control.Label ret;
    ret = new javafx.scene.control.Label(String.valueOf(num));
    ret.getStyleClass().add("tile");
    ret.getStyleClass().add("tile" + num);
    return ret;
  }
}
