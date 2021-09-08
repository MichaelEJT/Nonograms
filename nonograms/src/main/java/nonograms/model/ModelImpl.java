package nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private List<Clues> _cluesList;
  private Clues _clue;
  private int _index;
  private Board _board;
  private List<ModelObserver> _observers;

  public ModelImpl(List<Clues> cls) {
    if (cls == null) {
      throw new IllegalArgumentException();
    }
    _index = 0;
    _cluesList = cls;
    _clue = cls.get(0);
    _board = new BoardImpl(_clue);
    _observers = new ArrayList<>();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return _board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    _board.toggleCellShaded(row, col);
    notify(this);
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    _board.toggleCellEliminated(row, col);
    notify(this);
  }

  @Override
  public void clear() {
    _board.clear();
    notify(this);
  }

  @Override
  public int getWidth() {
    return _clue.getWidth();
  }

  @Override
  public int getHeight() {
    return _clue.getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return _clue.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return _clue.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return _clue.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return _clue.getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return _cluesList.size();
  }

  @Override
  public int getPuzzleIndex() {
    return _index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleCount()) {
      throw new IllegalArgumentException();
    }
    _index = index;
    _clue = _cluesList.get(_index);
    _board = new BoardImpl(_clue);
    notify(this);
  }

  public int getIndex() {
    return _index;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    //    if (observer == null) {
    //      throw new IllegalArgumentException();
    //    }
    //    if (_observers.contains(observer)) {
    //      return;
    //    }
    _observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    //    if (observer == null) {
    //      throw new IllegalArgumentException();
    //    }
    //    if (!_observers.contains(observer)) {
    //      throw new IllegalArgumentException();
    //    }
    _observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    return (isColSolved() && isRowSolved());
  }

  private boolean isRowSolved() {
    for (int i = 0; i < _clue.getHeight(); i++) {
      int shaded = 0;
      int[] rowClues = getRowClues(i);
      int supposed = 0;
      for (int a : rowClues) {
        supposed += a;
      }
      for (int j = 0; j < _clue.getWidth(); j++) {
        if (_board.isShaded(i, j)) {
          shaded++;
        }
      }
      if (shaded != supposed) {
        return false;
      }
    }
    return true;
  }

  private boolean isColSolved() {
    for (int i = 0; i < _clue.getWidth(); i++) {
      int shaded = 0;
      int[] colClues = getColClues(i);
      int supposed = 0;
      for (int a : colClues) {
        supposed += a;
      }
      for (int j = 0; j < _clue.getHeight(); j++) {
        if (_board.isShaded(j, i)) {
          shaded++;
        }
      }
      if (shaded != supposed) {
        return false;
      }
    }
    return true;
  }

  private void notify(Model model) {
    for (ModelObserver observer : _observers) {
      observer.update(model);
    }
  }

  public Clues getClues() {
    return _clue;
  }

  public Board getBoard() {
    return _board;
  }
}
