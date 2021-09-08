package nonograms.controller;

import nonograms.model.Clues;
import nonograms.model.Model;
import nonograms.model.ModelImpl;

import java.util.Random;

public class ControllerImpl implements Controller {

  private Model _model;

  public ControllerImpl(Model m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    _model = m;
  }

  @Override
  public Clues getClues() {
    return ((ModelImpl) _model).getClues();
  }

  @Override
  public boolean isSolved() {
    return _model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    _model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    _model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int index = _model.getPuzzleIndex();
    int total = _model.getPuzzleCount();
    if (index + 1 == total) {
      return;
    } else {
      _model.setPuzzleIndex(index + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    int index = _model.getPuzzleIndex();
    if (index - 1 == -1) {
      return;
    } else {
      _model.setPuzzleIndex(index - 1);
    }
  }

  @Override
  public void randPuzzle() {
    Random rand = new Random();
    int random = rand.nextInt(_model.getPuzzleCount());
    while (random == _model.getPuzzleIndex()) {
      random = rand.nextInt(_model.getPuzzleCount());
    }
    _model.setPuzzleIndex(random);
  }

  @Override
  public void clearBoard() {
    _model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return _model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return _model.getPuzzleCount();
  }
}
