# Color Model Conversion Web Application


This web application allows users to interactively select and manipulate colors, displaying their components in three different color models: CMYK, RGB, and HSV.

## Key Features:
- **Interactive color selection**: Users can pick a color from a palette, input exact color values, or adjust colors using sliders.
- **Automatic conversion**: Any change in one color model will immediately update the other two models.
- **Support for three color models**: RGB, CMYK, and HSV, with live conversion between them.
- **User-friendly interface**: Provides multiple ways to interact with colors, ensuring ease of use and smooth adjustments.

## How to Run
* Clone the repository from GitHub: ```git clone https://github.com/yourusername/color-conversion-app.git```
* Open the ```color-converter.html``` file in your preferred web browser.

## Usage
+ **Input Fields**: You can manually enter color values in the fields and press ```Enter``` for the color components in each model (RGB, CMYK, or HSV).
+ **Sliders**: Adjust the sliders to modify the color dynamically.
+ **Color Picker**: You can use the color palette(square in the center) to select a base color, which will automatically update all corresponding values in the models.


## Color Models
* **RGB (Red, Green, Blue)**:
  The RGB color model is an additive color model primarily used for displaying colors on digital screens. It works by combining different intensities of 
  red, green, and blue light. Each color channel can have a value ranging from 0 to 255, allowing for over 16 million possible color combinations. When 
  the values for all three channels are set to 0, the result is black, while setting them to their maximum (255, 255, 255) produces white. This model is 
  widely used in web design, digital photography, and video.

* **HSV (Hue, Saturation, Value)**:  
  The HSV color model represents colors in a way that is more intuitive for human perception. It consists of three components:
  
    * **Hue**: Represents the color type and is measured as an angle (0° to 360°) on the color wheel (e.g., red at 0°, green at 120°, blue at 240°).
    * **Saturation**: Indicates the intensity or purity of the color, ranging from 0% (gray) to 100% (full color).
    * **Value (Brightness)**: Refers to the brightness of the color, with 0% being completely dark and 100% being the brightest.  
      
  This model is especially useful in graphic design and image editing, as it aligns better with how people perceive and manipulate colors.
  
* **CMYK (Cyan, Magenta, Yellow, Black)**:
  The CMYK color model is a subtractive color model used primarily in color printing. It works by subtracting varying percentages of cyan, magenta, 
  yellow, and black inks from a white background to produce different colors. The model is based on the CMY color model, with black (K) added to enhance 
  depth and detail, as mixing CMY inks can produce unsatisfactory results. Each component can range from 0% to 100%, where 0% means no ink is used, and 
  100% indicates full saturation. This model is essential in the printing industry for producing high-quality color outputs.

