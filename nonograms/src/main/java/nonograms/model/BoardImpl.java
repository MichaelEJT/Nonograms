package nonograms.model;

public class BoardImpl implements Board {

  private Clues _clues;
  private int[][] _cells;
  private int _row;
  private int _col;

  public BoardImpl(Clues cl) {
    if (cl == null) {
      throw new IllegalArgumentException();
    }
    _clues = cl;
    _row = cl.getHeight();
    _col = cl.getWidth();
    _cells = new int[_row][_col];
    for (int i = 0; i < _row; i++) {
      for (int j = 0; j < _col; j++) {
        _cells[i][j] = 0;
      }
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row >= _row || col >= _col || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    return (_cells[row][col] == 1);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row >= _row || col >= _col || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    return (_cells[row][col] == 2);
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row >= _row || col >= _col || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    return (_cells[row][col] == 0);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row >= _row || col >= _col || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    if (isShaded(row, col)) {
      _cells[row][col] = 0;
    } else {
      _cells[row][col] = 1;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row >= _row || col >= _col || row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    if (isEliminated(row, col)) {
      _cells[row][col] = 0;
    } else {
      _cells[row][col] = 2;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < _row; i++) {
      for (int j = 0; j < _col; j++) {
        _cells[i][j] = 0;
      }
    }
  }
}
