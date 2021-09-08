package nonograms.model;

public class CluesImpl implements Clues {

  private final int[][] _rowClues;
  private final int[][] _colClues;

  public CluesImpl(int[][] row, int[][] col) {
    if (row == null || col == null) {
      throw new IllegalArgumentException();
    }
    _rowClues = row;
    _colClues = col;
  }

  @Override
  public int getWidth() {
    return _colClues.length;
  }

  @Override
  public int getHeight() {
    return _rowClues.length;
  }

  @Override
  public int[] getRowClues(int index) {
    if (index >= _rowClues.length || index < 0) {
      throw new IllegalArgumentException();
    }
    return _rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    if (index >= _colClues.length || index < 0) {
      throw new IllegalArgumentException();
    }

    return _colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return _rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return _colClues[0].length;
  }
}
