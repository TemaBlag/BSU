# PixelPath

The `PixelPath` class provides a graphical interface using `Tkinter` to demonstrate rasterization algorithms, including line and circle rendering methods such as Step-by-Step, DDA, Bresenham, Castle-Pitway, and Bresenham's circle algorithm. The app allows users to enter coordinates and parameters to draw shapes step-by-step.

#### Algorithms
- **Step-by-Step**
- **DDA (Digital Differential Analyzer)**
- **Bresenham’s Line**
- **Castle-Pitway Line**
- **Bresenham’s Circle**

# How to run

### macOS
You can download exe file and run it.

### Prerequisites

- Python 3.x
- Required Python packages(Tkinter, NumPy)
  
### Running the Application
1. Clone the repository
2. Run the script in you IDE
3. The graphical interface will open, allowing you to paint lines

## Theory

### 1. Step-by-Step
The **Step-by-Step algorithm** is a line drawing technique that incrementally calculates intermediate points between two endpoints $(x_1, y_1)$ and $(x_2, y_2)$. It computes the differences in x and y coordinates, determines the slope, and iteratively plots points based on the calculated slope and direction. This results in a straight line being rendered on the canvas by adjusting pixel positions using integer rounding to approximate coordinates.
Key Steps:
- **Calculate the differences in x and y coordinates**:
  
   - $\Delta x = \frac{x_2 - x_1}{\text{steps}}$
   - $\Delta y = \frac{y_2 - y_1}{\text{steps}}$
     
- **Calculate the slope**:
  
   - $k$ = $\frac{y_2 - y_1}{x_2 - x_1}$
     
- **Depending on the steeper direction (x or y), iterate over the necessary range and plot points accordingly using the calculated slope.**
  
   - $x$ += $i$, $y$ += $i * k$
     
---

### 2. DDA (Digital Differential Analyzer)
The **DDA algorithm** is a simple and intuitive method for line generation that uses incremental calculations based on the line's slope.

#### Steps and Formulas:
1. **Calculate the Slope**:
   - $k$ = $\frac{y_2 - y_1}{x_2 - x_1}$

2. **Determine Steps**:
   - steps = $\max(|x_2 - x_1|, |y_2 - y_1|)$

3. **Calculate Increments**:
   - $\Delta x = \frac{x_2 - x_1}{\text{steps}}$
   - $\Delta y = \frac{y_2 - y_1}{\text{steps}}$

4. **Plot Points Iteratively**:
   - Start from $(x_1, y_1)$.
   - Increment by $\Delta x$ and $\Delta y$ at each step.
   - Round the coordinates to the nearest integer for pixel approximation.

---

### 3. Bresenham’s Line Algorithm
**Bresenham’s Line** is an efficient integer-based line-drawing algorithm that avoids floating-point operations, making it ideal for raster graphics.

#### Steps and Formulas:
1. **Calculate Initial Parameters**:
   - $\Delta x = x_2 - x_1$
   - $\Delta y = y_2 - y_1$
   - Initial decision parameter: $p = 2 \Delta y - \Delta x$

2. **Determine Points Using Decision Parameter**:
   - If $p < 0$: plot $(x+1, y)$, update:
     - $p = p + 2 \Delta y$
   - If $p \geq 0$: plot $(x+1, y+1)$, update:
     - $p = p + 2 (\Delta y - \Delta x)$

3. **Iterate Until Endpoint**:
   - Repeat until reaching the end point $(x_2, y_2)$.

---

### 4. Castle-Pitway Line Algorithm
The **Castle-Pitway** algorithm is another incremental line-drawing approach, often using floating-point arithmetic, making it adaptable to non-integer slopes.

#### Steps and Formulas:
1. **Calculate Slope**:
   - $m = \frac{y_2 - y_1}{x_2 - x_1}$

2. **Based on Slope, Determine Increments**:
   - If $|m| < 1$: increment $x$ by 1 and compute corresponding $y$.
   - If $|m| \geq 1$: increment $y$ by 1 and compute corresponding $x$.

3. **Plot Points Iteratively**:
   - Adjust $x$ or $y$ based on the slope condition, iterating until reaching $(x_2, y_2)$.

---

### 5. Bresenham’s Circle Algorithm
**Bresenham’s Circle** algorithm utilizes circle symmetry to plot points in each octant, based on an integer-based decision parameter, making it highly efficient.

#### Steps and Formulas:
1. **Initialize Decision Parameter**:
   - Start at $(x, y) = (0, r)$.
   - Initial decision parameter: $p = 3 - 2r$

2. **Update Points Based on Decision Parameter**:
   - If $p < 0$: plot $(x+1, y)$, update:
     - $p = p + 4x + 6$
   - If $p \geq 0$: plot $(x+1, y-1)$, update:
     - $p = p + 4x - 4y + 10$

3. **Use Symmetry**:
   - Reflect each calculated point to other octants to complete the circle.

---
## Interface

![](https://res.cloudinary.com/daaivmxff/image/upload/v1734862874/app_okixgl.png)

## Usage
1. Launch the app to view the main canvas and menu button.
2. Click "Menu" to open the settings panel, enter coordinates, and choose a rasterization method.
3. Click the respective button (e.g., DDA, Bresenham’s Line) to draw the shape.
4. Use "Undo" to remove the last shape or "Hide" to close the settings panel.

## Rendering time
Line from (0, 0) to (300, 300):
- Step-by-step: 0.0432 sec
- DDA:0.0506 sec
- Bresenham line: 0.0102 sec
- Castle-Pitway Algorithm: 0.0001 sec
  
Circle in $O$(0, 0) and R=150:
- Bresenham circle: 0.0017 sec
