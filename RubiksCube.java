import java.util.Arrays;

public class RubiksCube {
    private int size;
    private char[][][] cube; // I use a 3D array to represent the cube's faces

    // I define the face names: F (front), B (back), U (up), D (down), L (left), R (right)
    private final String[] faces = {"F", "B", "U", "D", "L", "R"};
    private final char[] colors = {'W', 'Y', 'R', 'O', 'G', 'B'}; // I use these colors: white, yellow, red, orange, green, blue

    public RubiksCube(int size) {
        this.size = size;
        cube = new char[6][size][size]; // I create 6 faces, each with size x size tiles
        initializeCube(); // I call the method to initialize the cube
    }

    // I initialize the cube by filling each face with its corresponding color
    private void initializeCube() {
        for (int i = 0; i < 6; i++) {
            for (int row = 0; row < size; row++) {
                Arrays.fill(cube[i][row], colors[i]); // I fill each row with the color of the current face
            }
        }
    }

    // I rotate a face 90 degrees clockwise
    public void rotateFaceClockwise(String face) {
        int faceIndex = getFaceIndex(face); // I get the index of the specified face
        cube[faceIndex] = rotateMatrixClockwise(cube[faceIndex]); // I rotate the face

        // I handle adjacent face rotations
        int[][] adjacentFaces = {
            {4, 0, 1, 2}, // I define the adjacent faces for F: L, U, R, D
            {2, 0, 4, 1}, // I define the adjacent faces for B: U, L, D, R
            {0, 1, 4, 3}, // I define the adjacent faces for U: F, B, L, R
            {0, 3, 4, 1}, // I define the adjacent faces for D: F, L, R, B
            {0, 2, 1, 3}, // I define the adjacent faces for L: F, U, B, D
            {1, 2, 3, 0}   // I define the adjacent faces for R: B, U, F, D
        };

        // I save the top row of the adjacent faces that need to be rotated
        char[] temp = new char[size];
        for (int i = 0; i < size; i++) {
            temp[i] = cube[adjacentFaces[faceIndex][0]][faceIndex == 0 ? i : size - 1][0]; // I save the top row of the first adjacent face
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][0]][faceIndex == 0 ? i : size - 1][0] = cube[adjacentFaces[faceIndex][1]][i][0]; // I move the row from the second adjacent face
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][1]][i][0] = cube[adjacentFaces[faceIndex][2]][size - 1 - i][0]; // I move the row from the third adjacent face
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][2]][size - 1 - i][0] = cube[adjacentFaces[faceIndex][3]][size - 1 - i][0]; // I move the row from the fourth adjacent face
        }
        for (int i = 0; i < size; i++) {
            cube[adjacentFaces[faceIndex][3]][size - 1 - i][0] = temp[i]; // I restore the saved row to the last adjacent face
        }
    }

    // I create a helper function to rotate a matrix 90 degrees clockwise
    private char[][] rotateMatrixClockwise(char[][] matrix) {
        char[][] rotated = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][size - 1 - i] = matrix[i][j]; // I rotate the matrix
            }
        }
        return rotated; // I return the rotated matrix
    }

    // I create a helper function to get the face index by its name
    private int getFaceIndex(String face) {
        for (int i = 0; i < faces.length; i++) {
            if (faces[i].equals(face)) {
                return i; // I return the index if the face name matches
            }
        }
        throw new IllegalArgumentException("Invalid face name: " + face); // I throw an error if the face name is invalid
    }

    // I create a method to print the current state of the cube (for debugging)
    public void printCube() {
        for (int i = 0; i < 6; i++) {
            System.out.println(faces[i] + " Face:"); // I print the name of the current face
            for (int row = 0; row < size; row++) {
                System.out.println(Arrays.toString(cube[i][row])); // I print each row of the face
            }
            System.out.println(); // I print an empty line for better readability
        }
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube(3); // I create a 3x3 cube
        cube.printCube(); // I print the initial state of the cube
        
        // Example: I rotate the front face
        cube.rotateFaceClockwise("F");
        System.out.println("After rotating the front face:");
        cube.printCube(); // I print the state of the cube after the rotation
    }
}
