package de.fhaachen.navi.sudoku;


public class SudokuGenerator {

        //box size, and game SIZE ==> e.g. size = 3, SIZE = 9
        //game will be the game
        private int size, SIZE;
        private Field field = new Field;

        public SudokuGenerator() {
            size = 3; //muss geändert werden
            SIZE = size * size;
            field = generateField();
        }

        //This will return the game
        private int[][] generateField() {
            //Set everything to -1 so that it cannot be a value
            int[][] g = new int[SIZE][SIZE];
            for(int i = 0; i < SIZE; i++)
                for(int j = 0; j < SIZE; j++)
                    g[i][j] = -1;

            if(createGame(0, 0, g))
                return g;
            return null;
        }

        //Create the game
        private boolean createField(int x, int y, int[][] g) {
            //An array of integers
            Rand r = new Rand(SIZE);

            //for every random num in r
            for(int NUM = 0; NUM < size; NUM++) {
                int num = r.get(NUM);

                //if num is valid
                if(isValid(x, y, g, num)) {
                    //next cell coordinates
                    int nx = (x+1)%SIZE, ny = y;
                    if(nx == 0) ny++;

                    //set this cell to num
                    g[x][y] = num;

                    //if the next cell is valid return true
                    if(createGame(nx, ny, g)) return true;

                    //otherwise return false
                    g[x][y] = -1;
                    return false;
                }
            }
            return false;
        }

        private boolean isValid(int x, int y, int[][] g, int num) {
            //Rows&&Cols
            for(int i = 0; i < SIZE; i++)
                if(g[i][y] == num || g[x][i] == num) return false;
            //Box
            int bx = x - x%size;, by = y - y%size;
            for(int i = bx; i < bx + size; i++) {
                for(int j = by; j < by + size; j++) {
                    if(g[i][j] == num)return false;
                }
            }
            return true;
        }
    }

    public class Rand {
        private int rSize;
        private int[] r;
        public Rand(int _size) {
            rSize = _size;
            r = new int[size];
            for(int i = 0; i < rSize; r++)r[i] = i;

            for(int i = 0; i < rSize*5; r++) {
                int a = (int)(Math.random()*rSize);
                int b = (int)(Math.random()*rSize);
                int n = r[a];
                r[a] = r[b];
                r[b] = n;
            }
            public void get(int i) {
                if(i >= 0 && i < rSize) return r[i]; return -1;
            }
        }



        //Test
        //   public SudokuGenerator() {
        //       System.out.println("Test");
        //   }

}