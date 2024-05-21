public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double[][] masses = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char[][] image = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }


    public static boolean isWordSymmetric(String[] words, int start, int end) {
        if(words.length==0) {
            return true;
        }
        if(!words[start].equalsIgnoreCase(words[end])) {
            return false;
        }
        else if(start>=end) {
            return true;
        }
        else {
            return isWordSymmetric(words, start+1, end-1);
        }
    }

    public static long arraySum(int[] data, int position) {
        if(data.length==0) {
            return 0;
        }
        if(position==data.length-1) {
            return data[position];
        }
        return data[position]+arraySum(data,position+1);
    }

    public static int arrayMin(int[] data, int position) {
        if(position==data.length-1) {
            return data[position];
        }
        else {
            int minValOfRest = arrayMin(data,position+1);
            if(data[position] < minValOfRest) {
                return data[position];
            } else {
                return minValOfRest;
            }
        }
    }

    public static double computePyramidWeights(double[][] masses, int row, int column) {
        try {
            return masses[row][column] + computePyramidWeights(masses, row - 1, column)/2 + computePyramidWeights(masses, row - 1, column - 1)/2;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int howManyOrganisms(char[][] image) {
        char label='a';
        int count=0;
        for(int i=0; i< image.length; i++) {
            for(int j=0; j<image[i].length; j++) {
                if(image[i][j]=='*') {
                    labelOrganisms(image,i,j,label++);
                    count++;
                }
            }
        }
        return count;
    }

    public static void labelOrganisms(char[][] image, int row, int col, char label) {
        for(int i=row-1; i<=row+1; i++) {
            for(int j=col-1; j<=col+1; j++) {
                if(!(i==row-1 && j==col-1) && !(i==row-1 && j==col+1) && !(i==row+1 && j==col-1) && !(i==row+1 && j==col+1) ) { //exlude corners
                    if (i >= 0 && j >= 0 && i < image.length && j < image[i].length) {
                        if (image[i][j] == '*') {
                            image[i][j] = label;
                            labelOrganisms(image,i,j,label);
                        }
                    }
                }
            }
        }
    }



}
