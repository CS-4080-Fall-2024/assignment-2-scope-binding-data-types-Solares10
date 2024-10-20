import java.util.Arrays;

public class RubiksCube {
    private int size;
    private char[][][] cube; // 3D array representing the cube's faces

    // Face names: F (front), B (back), U (up), D (down), L (left), R (right)
    private final String[] faces = {"F", "B", "U", "D", "L", "R"};
    private final char[] colors = {'W', 'Y', 'R', 'O', 'G', 'B'}; // white, yellow, red, orange, green, blue

    public RubiksCube(int size) {
        this.size = size;
        cube = new char[6][size][size]; // 6 faces, each with size x size tiles
        initializeCube();
    }

    // Initialize the cube: Each face is filled with its corresponding color
    private void initializeCube() {
        for (int i = 0; i < 6; i++) {
            for (int row = 0; row < size; row++) {
                Arrays.fill(cube[i][row], colors[i]);
            }
        }
    }

    // Rotate a face 90 degrees clockwise
    public void rotateFaceClockwise(String face) {
        int faceIndex = getFaceIndex(face);
        cube[faceIndex] = rotateMatrixClockwise(cube[faceIndex]);
        
        // Handle adjacent face rotations
        int[][] adjacentFaces = {
            {4, 0, 1, 2}, // Adjacent faces for F: L, U, R, D
            {2, 0, 4, 1}, // Adjacent faces for B: U, L, D, R
            {0, 1, 4, 3}, // Adjacent faces for U: F, B, L, R
            {0, 3, 4, 1}, // Adjacent faces for D: F, L, R, B
            {0, 2, 1, 3}, // Adjacent faces for L: F, U, B, D
            {1, 2, 3, 0}   // Adjacent faces for R: B, U, F, D
        };

        // Save the top row of the adjacent faces that need to be rotated
        char[] temp = new char[size];
        for (int i = 0; i < size; i++) {
            temp[i] = cube[adjacentFaces[faceIndex][0]][faceIndex == 0 ? i : size - 1][0];
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][0]][faceIndex == 0 ? i : size - 1][0] = cube[adjacentFaces[faceIndex][1]][i][0];
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][1]][i][0] = cube[adjacentFaces[faceIndex][2]][size - 1 - i][0];
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][2]][size - 1 - i][0] = cube[adjacentFaces[faceIndex][3]][size - 1 - i][0];
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][3]][size - 1 - i][0] = temp[i];
        }
    }

    // Helper function to rotate a matrix 90 degrees clockwise
    private char[][] rotateMatrixClockwise(char[][] matrix) {
        char[][] rotated = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][size - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

    // Helper function to get face index by its name
    private int getFaceIndex(String face) {
        for (int i = 0; i < faces.length; i++) {
            if (faces[i].equals(face)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid face name: " + face);
    }

    // Print the current state of the cube (for debugging)
    public void printCube() {
        for (int i = 0; i < 6; i++) {
            System.out.println(faces[i] + " Face:");
            for (int row = 0; row < size; row++) {
                System.out.println(Arrays.toString(cube[i][row]));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube(3); // 3x3 cube
        cube.printCube();
        
        // Example: Rotate the front face
        cube.rotateFaceClockwise("F");
        System.out.println("After rotating the front face:");
        cube.printCube();
    }
}
