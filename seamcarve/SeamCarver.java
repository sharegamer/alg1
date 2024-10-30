
/* 
By convention, the indices x and y are integers between 0 and width ? 1 and between 0 and height ? 1 respectively, where width is the width of the current image and height is the height. Throw an IllegalArgumentException if either x or y is outside its prescribed range.
Throw an IllegalArgumentException if the constructor, removeVerticalSeam(), or removeHorizontalSeam() is called with a null argument.
Throw an IllegalArgumentException if removeVerticalSeam() or removeHorizontalSeam() is called with an array of the wrong length or if the array is not a valid seam (i.e., either an entry is outside its prescribed range or two adjacent entries differ by more than 1).
Throw an IllegalArgumentException if removeVerticalSeam() is called when the width of the picture is less than or equal to 1 or if removeHorizontalSeam() is called when the height of the picture is less than or equal to 1.

 */
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Topological;

public class SeamCarver {

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException("picture is null");
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width())
            throw new IllegalArgumentException("x is outside its prescribed range");
        if (y < 0 || y >= height())
            throw new IllegalArgumentException("y is outside its prescribed range");
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000;
        double xGradient = gradient(picture.get(x - 1, y), picture.get(x + 1, y));
        double yGradient = gradient(picture.get(x, y - 1), picture.get(x, y + 1));
        return Math.sqrt(xGradient + yGradient);
    }

    private double gradient(Color c1, Color c2) {
        double red = c1.getRed() - c2.getRed();
        double green = c1.getGreen() - c2.getGreen();
        double blue = c1.getBlue() - c2.getBlue();
        return red * red + green * green + blue * blue;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam(){
        double[][] energy = new double[width()][height()];
        for (int x = 0; x < width(); x++)
            for (int y = 0; y < height(); y++)
                energy[x][y] = energy(x, y);
        int[] seam = new int[width()];
        double[][] distTo = new double[width()][height()];
        int[][] edgeTo = new int[width()][height()];
        for (int x = 0; x < width(); x++)
            for (int y = 0; y < height(); y++)
                distTo[x][y] = Double.POSITIVE_INFINITY;
        for (int x = 0; x < height(); x++)
            distTo[0][x] = 1000;
        for (int x = 1; x < width(); x++)
            for (int y = 0; y < height() ; y++)
                for (int dy = -1; dy <= 1; dy++)
                    if (y + dy >= 0 && y + dy < height())
                        if (distTo[x][y]  > distTo[x -1][y + dy] + energy[x][y]) {
                            distTo[x ][y ] = distTo[x-1][y+dy] + energy[x ][y];
                            edgeTo[x ][y ] = y+dy;
                        }
        double min = Double.POSITIVE_INFINITY;
        for(int x=height()-1;x>0;x--)
            if(distTo[width()-1][x]<min){
                min=distTo[width()-1][x];
                seam[width()-1]=x;
            }
        for(int x=width()-1;x>0;x--){
            seam[x-1]=edgeTo[x][seam[x]];
        }
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam(){
        Picture transpose = transpose(picture);
        SeamCarver sc = new SeamCarver(transpose);
        return sc.findHorizontalSeam();
    }
    private Picture transpose(Picture picture){
        Picture transpose = new Picture(picture.height(),picture.width());
        for(int x=0;x<picture.width();x++)
            for(int y=0;y<picture.height();y++)
                transpose.set(y,x,picture.get(x,y));
        return transpose;
    }
 
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        if (seam == null)
            throw new IllegalArgumentException("seam is null");
        if (height() <= 1)
            throw new IllegalArgumentException("height of the picture is less than or equal to 1");
        if (seam.length != width())
            throw new IllegalArgumentException("array of the wrong length");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= height())
                throw new IllegalArgumentException("seam is not a valid seam");
            if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException("seam is not a valid seam");
        }
        Picture newPicture = new Picture(width(), height() - 1);
        for (int x = 0; x < width(); x++)
            for (int y = 0; y < height() - 1; y++) {
                if (y < seam[x])
                    newPicture.set(x, y, picture.get(x, y));
                else
                    newPicture.set(x, y, picture.get(x, y + 1));
            }
        picture = newPicture;
    

    }
 
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam){
        if (seam == null)
            throw new IllegalArgumentException("seam is null");
        if (width() <= 1)
            throw new IllegalArgumentException("width of the picture is less than or equal to 1");
        if (seam.length != height())
            throw new IllegalArgumentException("array of the wrong length");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= width())
                throw new IllegalArgumentException("seam is not a valid seam");
            if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException("seam is not a valid seam");
        }
        Picture newPicture = new Picture(width() - 1, height());
        for (int y = 0; y < height(); y++)
            for (int x = 0; x < width() - 1; x++) {
                if (x < seam[y])
                    newPicture.set(x, y, picture.get(x, y));
                else
                    newPicture.set(x, y, picture.get(x + 1, y));
            }
        picture = newPicture;
    }
 
    //  unit testing (optional)
    public static void main(String[] args){
        Picture picture = new Picture(args[0]);
        SeamCarver sc = new SeamCarver(picture);
        int i=picture.width();
        for(int j=0;j<i/2;j++){
            int[] seam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(seam);
        }
        int[] seam = sc.findVerticalSeam();
        sc.removeVerticalSeam(seam);
        sc.picture().show();
    }
 
 }