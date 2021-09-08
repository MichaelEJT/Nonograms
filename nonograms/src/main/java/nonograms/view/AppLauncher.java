package nonograms.view;

import nonograms.PuzzleLibrary;
import nonograms.controller.ControllerImpl;
import nonograms.model.Clues;
import nonograms.model.Model;
import nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {
  List<Clues> clues = PuzzleLibrary.create();
  Model model = new ModelImpl(clues);
  ControllerImpl controller = new ControllerImpl(model);
  Pane overview = new VBox();
  HBox options = new HBox();

  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    stage.setTitle("Nonogram!");
    Button erase = new Button("Erase Board");
    erase.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
          // add setoptions at last
          controller.clearBoard();
          overview.getChildren().clear();
          overview.getChildren().add(new View(controller).render());
          overview.getChildren().add(options);
        });
    Button previous = new Button("Previous Puzzle");
    previous.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
          overview.getChildren().clear();
          overview.getChildren().add(new View(controller).render());
          overview.getChildren().add(options);
        });
    Button next = new Button("Next Puzzle");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
          overview.getChildren().clear();
          overview.getChildren().add(new View(controller).render());
          overview.getChildren().add(options);
        });
    Button random = new Button("Random Puzzle");
    random.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
          overview.getChildren().clear();
          overview.getChildren().add(new View(controller).render());
          overview.getChildren().add(options);
        });
    options.getChildren().add(previous);
    options.getChildren().add(erase);
    options.getChildren().add(random);
    options.getChildren().add(next);
    overview.getChildren().add(new View(controller).render());
    overview.getChildren().add(options);

    // check.fire();
    Scene scene = new Scene(overview, 450, 450);
    stage.setScene(scene);
    stage.show();
  }
}
