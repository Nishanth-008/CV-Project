import cv2
import numpy as np
import matplotlib.pyplot as plt

def apply_smoothing_filters(image_path):
    """
    Applies Mean and Gaussian spatial filters to an image to reduce noise.
    """
    # Load the image
    img = cv2.imread(image_path)
    if img is None:
        print(f"Error: Could not load image at '{image_path}'. Please check the path.")
        return
    
    # Convert from BGR (OpenCV default) to RGB for correct displaying with matplotlib
    img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    
    # 1. Apply Mean Filter (Averaging)
    # Using a 5x5 convolution mask/kernel
    kernel_size = (5, 5)
    mean_filtered = cv2.blur(img_rgb, kernel_size)
    
    # 2. Apply Gaussian Filter
    # Using a 5x5 kernel, sigmaX=0 (calculated automatically from kernel size)
    gaussian_filtered = cv2.GaussianBlur(img_rgb, kernel_size, 0)
    
    # Plotting the results
    plt.figure(figsize=(15, 5))
    
    plt.subplot(1, 3, 1)
    plt.imshow(img_rgb)
    plt.title('Original Image')
    plt.axis('off')
    
    plt.subplot(1, 3, 2)
    plt.imshow(mean_filtered)
    plt.title('Mean Filter (5x5 Kernel)')
    plt.axis('off')
    
    plt.subplot(1, 3, 3)
    plt.imshow(gaussian_filtered)
    plt.title('Gaussian Filter (5x5 Kernel)')
    plt.axis('off')
    
    plt.tight_layout()
    plt.show()

if __name__ == "__main__":
    # Create a dummy image for demonstration if you don't have one
    # Alternatively, replace 'sample.jpg' with a real image path
    try:
        apply_smoothing_filters("sample.jpg")
    except Exception as e:
        print("Make sure 'sample.jpg' exists in the current directory.")
