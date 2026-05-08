import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpatialFiltering {

    public static void main(String[] args) {
        try {
            // Load the image (replace 'sample.jpg' with your image file path)
            File input = new File("sample.jpg");
            if (!input.exists()) {
                System.out.println("Error: sample.jpg not found. Please provide a valid image.");
                return;
            }
            
            BufferedImage image = ImageIO.read(input);
            if (image == null) {
                System.out.println("Error: Image could not be read.");
                return;
            }

            // 1. Apply Mean Filter
            // Create a 3x3 averaging kernel (all weights are 1/9)
            float weight = 1.0f / 9.0f;
            float[] meanMatrix = {
                weight, weight, weight,
                weight, weight, weight,
                weight, weight, weight
            };
            Kernel meanKernel = new Kernel(3, 3, meanMatrix);
            // EDGE_NO_OP leaves edge pixels unchanged
            ConvolveOp meanOp = new ConvolveOp(meanKernel, ConvolveOp.EDGE_NO_OP, null);
            BufferedImage meanFiltered = meanOp.filter(image, null);
            
            // Save the result
            File meanOutput = new File("mean_filtered.jpg");
            ImageIO.write(meanFiltered, "jpg", meanOutput);
            System.out.println("Mean filter applied and saved as 'mean_filtered.jpg'");

            // 2. Apply Gaussian Filter
            // Create a 3x3 approximation of a Gaussian kernel
            float sum = 16.0f;
            float[] gaussianMatrix = {
                1.0f/sum, 2.0f/sum, 1.0f/sum,
                2.0f/sum, 4.0f/sum, 2.0f/sum,
                1.0f/sum, 2.0f/sum, 1.0f/sum
            };
            Kernel gaussianKernel = new Kernel(3, 3, gaussianMatrix);
            ConvolveOp gaussianOp = new ConvolveOp(gaussianKernel, ConvolveOp.EDGE_NO_OP, null);
            BufferedImage gaussianFiltered = gaussianOp.filter(image, null);
            
            // Save the result
            File gaussianOutput = new File("gaussian_filtered.jpg");
            ImageIO.write(gaussianFiltered, "jpg", gaussianOutput);
            System.out.println("Gaussian filter applied and saved as 'gaussian_filtered.jpg'");

        } catch (IOException e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
