% Image Smoothing using Mean and Gaussian Filters
function spatial_filtering()
    % Read the image
    % Replace 'peppers.png' with your own image if needed
    % 'peppers.png' is a built-in image in MATLAB
    try
        img = imread('peppers.png');
    catch
        disp('Error: Could not load image. Make sure the image path is correct.');
        return;
    end
    
    % 1. Mean Filter (Averaging Filter)
    % Create a 5x5 average filter (convolution mask)
    mean_filter = fspecial('average', [5 5]);
    
    % Apply the filter using convolution (imfilter)
    % 'replicate' handles border pixels by replicating the nearest border pixel
    mean_filtered = imfilter(img, mean_filter, 'replicate');
    
    % 2. Gaussian Filter
    % Create a 5x5 Gaussian filter with standard deviation (sigma) of 2
    gaussian_filter = fspecial('gaussian', [5 5], 2);
    
    % Apply the filter using convolution
    gaussian_filtered = imfilter(img, gaussian_filter, 'replicate');
    
    % Display the original and filtered images
    figure('Name', 'Spatial Filtering: Noise Reduction', 'Position', [100, 100, 1200, 400]);
    
    subplot(1, 3, 1);
    imshow(img);
    title('Original Image');
    
    subplot(1, 3, 2);
    imshow(mean_filtered);
    title('Mean Filter (5x5)');
    
    subplot(1, 3, 3);
    imshow(gaussian_filtered);
    title('Gaussian Filter (5x5, \sigma=2)');
    
    % Optional: Save the smoothed images to disk
    % imwrite(mean_filtered, 'mean_filtered_matlab.jpg');
    % imwrite(gaussian_filtered, 'gaussian_filtered_matlab.jpg');
    
    disp('Filtering complete. Check the generated figure window.');
end
