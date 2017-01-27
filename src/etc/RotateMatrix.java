package etc;

/**
 * Created by mccccopley on 1/19/2017.
 */
public class RotateMatrix {
    public static class Coords {
        Coords(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int row;
        public int col;
    }

    private static int GetIndexForCoords(Coords coords, int cols) {
        return (coords.row * cols) + coords.col;
    }

    private static Coords Get90CWCoords(Coords coords, int rows, int cols) {
        int row = coords.col;
        int col = rows - coords.row - 1;
        return new Coords(row, col);
    }

    public static void RotateMatrix90CW(byte[] matrix, int cols) {
        int rows = matrix.length / cols;
        int len = rows * cols;

        if (len != matrix.length) {
            return;
        }

        int halfRows = (rows + 1) / 2;
        int halfCols = (cols + 1) / 2;

        for (int row = 0; row < halfRows; row++) {
            for (int col = 0; col < halfCols; col++) {
                Coords r0Coords = new Coords(row, col);
                Coords r1Coords = Get90CWCoords(r0Coords, rows, cols);
                Coords r2Coords = Get90CWCoords(r1Coords, rows, cols);
                Coords r3Coords = Get90CWCoords(r2Coords, rows, cols);

                int r0Index = GetIndexForCoords(r0Coords, cols);
                int r1Index = GetIndexForCoords(r1Coords, cols);
                int r2Index = GetIndexForCoords(r2Coords, cols);
                int r3Index = GetIndexForCoords(r3Coords, cols);

                byte r0Byte = matrix[r0Index];
                byte r1Byte = matrix[r1Index];
                byte r2Byte = matrix[r2Index];
                byte r3Byte = matrix[r3Index];

                matrix[r0Index] = r3Byte;
                matrix[r1Index] = r0Byte;
                matrix[r2Index] = r1Byte;
                matrix[r3Index] = r2Byte;
            }
        }
    }
}
