import tkinter as tk
from tkinter import ttk
from tkinter.messagebox import showerror
import numpy as np
import time


class PixelPath(tk.Tk):
    time = 0

    def __init__(self):
        super().__init__()
        self.title("PixelPath")
        self.geometry("800x600")
        self.style = ttk.Style(self)
        self.style.configure("TButton", font=("Arial", 12), padding=(0, 0))
        self.style.configure("TFrame", background="lightblue")
        self.canvas = tk.Canvas(self, bg="white", highlightthickness=0)
        self.canvas.pack(fill=tk.BOTH, expand=True)
        self.canvas.bind("<MouseWheel>", self.zoom_with_scroll)
        self.canvas.bind("<B1-Motion>", self.pan_canvas)
        self.canvas.bind("<ButtonPress-1>", self.start_pan)
        self.canvas.bind("<Configure>", lambda event: self.draw_grid())
        self.start_x = 400
        self.start_y = 300
        self.x0_entry = None
        self.x1_entry = None
        self.y0_entry = None
        self.y1_entry = None
        self.o1_entry = None
        self.o2_entry = None
        self.time = []
        self.radius_entry = None
        self.lines = []
        self.circles = []
        self.hover_button = None
        self.param_frame = None
        self.zoom_level = 1
        self.pan_start_x = 0
        self.pan_start_y = 0
        self.param_frame = tk.Frame(self, width=390, height=120, padx=10, pady=10)
        self.create_main_interface()

    def create_main_interface(self):
        """Create frame with menu"""
        main_frame = tk.Frame(self, width=50, height=20, padx=0, pady=0)
        main_frame.place(x=0, y=0)

        self.hover_button = ttk.Button(main_frame, text="Menu", command=self.open_params_window)
        self.hover_button.pack(pady=0)

    def open_params_window(self):
        """Create a frame with interface of the application"""
        self.param_frame = tk.Frame(self, width=390, height=120, padx=10, pady=10)
        self.param_frame.place(x=0, y=0)

        part_window = self.param_frame

        a_label = tk.Label(part_window, text="A(x, y): ")
        a_label.grid(row=0, column=0, padx=(5, 2), pady=0, sticky="w")

        self.x0_entry = ttk.Entry(part_window, width=5)
        self.x0_entry.grid(row=0, column=0, padx=(0, 0), pady=0)

        self.y0_entry = ttk.Entry(part_window, width=5)
        self.y0_entry.grid(row=0, column=0, padx=(120, 0), pady=0)

        b_label = tk.Label(part_window, text="B(x, y): ")
        b_label.grid(row=1, column=0, padx=(5, 2), pady=0, sticky="w")

        self.x1_entry = ttk.Entry(part_window, width=5)
        self.x1_entry.grid(row=1, column=0, padx=(2, 2), pady=0)

        self.y1_entry = ttk.Entry(part_window, width=5)
        self.y1_entry.grid(row=1, column=0, padx=(120, 0), pady=0)

        o_label = tk.Label(part_window, text="O(x, y): ")
        o_label.grid(row=2, column=0, padx=(5, 2), pady=0, sticky="w")

        self.o1_entry = ttk.Entry(part_window, width=5)
        self.o1_entry.grid(row=2, column=0, padx=(2, 2), pady=0)

        self.o2_entry = ttk.Entry(part_window, width=5)
        self.o2_entry.grid(row=2, column=0, padx=(120, 0), pady=0)

        radius_label = tk.Label(part_window, text="R:")
        radius_label.grid(row=3, column=0, padx=(37, 2), pady=0, sticky="w")

        self.radius_entry = ttk.Entry(part_window, width=5)
        self.radius_entry.grid(row=3, column=0, padx=(6, 5), pady=0)

        step_button = ttk.Button(part_window, text="Step-By-Step", command=self.create_line, width=15)
        step_button.grid(row=4, column=0, padx=0, pady=0)

        dda_button = ttk.Button(part_window, text="DDA", command=self.dda, width=15)
        dda_button.grid(row=5, column=0, padx=0, pady=0)

        bresenham_button = ttk.Button(part_window, text="Bresenham's line", command=self.bresenham_line, width=15)
        bresenham_button.grid(row=6, column=0, padx=0, pady=0)

        castle_button = ttk.Button(part_window, text="Castle-Pitway Line", command=self.castle_pitway, width=15)
        castle_button.grid(row=7, column=0, padx=0, pady=0)

        circle_button = ttk.Button(part_window, text="Bresenham's circle", command=self.bresenham_circle, width=15)
        circle_button.grid(row=8, column=0, padx=0, pady=0)

        undo_button = ttk.Button(part_window, text="Undo", command=self.undo)
        undo_button.grid(row=9, column=0, padx=0, pady=0, sticky="w")

        hide_button = ttk.Button(part_window, text="Hide", command=self.hide_settings)
        hide_button.grid(row=9, column=0, padx=0, pady=0, sticky="e")

    def draw_grid(self):
        """Paint grid in the app"""
        self.canvas.delete("grid")
        width = self.canvas.winfo_width()
        height = self.canvas.winfo_height()
        grid_size = 20
        center_x, center_y = width // 2, height // 2
        self.start_x, self.start_y = center_x, center_y
        # create axis x,y
        self.canvas.create_line(center_x, 0, center_x, height, fill="black", tags="grid", width=2)
        self.canvas.create_line(0, center_y, width, center_y, fill="black", tags="grid", width=2)
        # create steps of axis
        scale_step = 50
        scale_range_x = range(self.start_x, width + scale_step, scale_step)
        scale_range_y = range(self.start_y, height + scale_step, scale_step)
        scale_range_x_ = range(self.start_x, -scale_step, -scale_step)
        scale_range_y_ = range(self.start_y, -scale_step, -scale_step)
        # creat label x, y
        self.canvas.create_text(center_x - 15, 0, text="Y", anchor="nw", font=("Arial", 12), fill="black", tags="grid")
        self.canvas.create_text(width - 10, center_y - 18, text="X", anchor="nw", font=("Arial", 12), fill="black", tags="grid")
        # create arrow for x
        arrow_size = 5
        self.canvas.create_line(center_x, 0, center_x - arrow_size, arrow_size, fill="black", tags="grid")
        self.canvas.create_line(center_x, 0, center_x + arrow_size, arrow_size, fill="black", tags="grid")
        # create arrow for y
        self.canvas.create_line(width, center_y, width - arrow_size, center_y - arrow_size, fill="black", tags="grid")
        self.canvas.create_line(width, center_y, width - arrow_size, center_y + arrow_size, fill="black", tags="grid")
        # create points with text (axis X)
        self.create_axis_labels_x(scale_range_x, center_x, center_y)
        self.create_axis_labels_x(scale_range_x_, center_x, center_y)
        self.create_axis_labels_y(scale_range_y, center_x, center_y)
        self.create_axis_labels_y(scale_range_y_, center_x, center_y)
        self.canvas.create_oval(center_x - 2, center_y - 2, center_x + 2, center_y + 2, fill="black", tags="grid", )
        self.canvas.create_text(center_x + 11, center_y + 10, text="0,0", fill="black", tags="grid")
        # create grid
        for i in np.arange(0, width, grid_size):
            self.canvas.create_line(i, 0, i, height, fill="gray", tags="grid")
        for j in np.arange(0, height, grid_size):
            self.canvas.create_line(0, j, width, j, fill="gray", tags="grid")

    def create_axis_labels_x(self, scale_range_x, center_x, center_y):
        """Create X-axis signature"""
        for x in scale_range_x:
            if x != center_x:
                self.canvas.create_oval(x - 2, center_y - 2, x + 2, center_y + 2, fill="black", tags="grid")
                self.canvas.create_text(x, center_y + 10, text=str(x - center_x), fill="black", tags="grid")

    def create_axis_labels_y(self, scale_range_y, center_x, center_y):
        """Create Y-axis signature"""
        for y in scale_range_y:
            if y != center_y:
                self.canvas.create_oval(center_x - 2, y - 2, center_x + 2, y + 2, fill="black", tags="grid")
                self.canvas.create_text(center_x + 15, y, text=str(center_y - y), fill="black", tags="grid")

    def hide_settings(self):
        """Hide the menu frame after clicking the hide button"""
        self.param_frame.place_forget()

    def zoom_with_scroll(self, event, direction=None):
        """Adjusts the zoom level of the canvas based on mouse scroll events"""
        if direction == "in" or (not direction and event.delta > 0):
            scale_factor = 1.1
        elif direction == "out" or (not direction and event.delta < 0):
            scale_factor = 0.9
        else:
            return
        self.zoom_level *= scale_factor
        self.canvas.scale("all", self.start_x, self.start_y, scale_factor, scale_factor)
        self.canvas.configure(scrollregion=self.canvas.bbox("all"))

    def start_pan(self, event):
        """Get start position of the pan"""
        self.pan_start_x = event.x
        self.pan_start_y = event.y

    def pan_canvas(self, event):
        """Move canvas to the last position of the pan"""
        dx = event.x - self.pan_start_x
        dy = event.y - self.pan_start_y
        self.start_x += dx
        self.start_y += dy
        self.canvas.move("all", dx, dy)
        self.pan_start_x = event.x
        self.pan_start_y = event.y

    def get_reprint_func(self, type_line):
        """Chose function for reprinting line"""
        func = None
        if type_line == 's':
            func = self.create_line
        elif type_line == 'd':
            func = self.dda
        elif type_line == 'b':
            func = self.bresenham_line
        elif type_line == 'c':
            func = self.castle_pitway
        return func

    def undo(self):
        """Undo rendering the last edit line"""
        if self.lines:
            self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
            if self.lines[-1] != 'circle':
                line_type = self.lines[-1][-1]
                self.canvas.delete(line_type)
                self.lines.pop(-1)
                # reprint lines
                func = self.get_reprint_func(line_type)
                for line in self.lines:
                    if line[-1] == line_type:
                        func(*line[:4], new=False)
            else:
                self.lines.pop(-1)
                self.circles.pop(-1)
                self.canvas.delete("cir")
                # repaint circles
                for circle in self.circles:
                    self.bresenham_circle(circle[0], circle[1], circle[2],
                                          new=False)
            self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)

    def get_points(self, alg="simple"):
        """Get point from entries"""
        try:
            x0, y0, x1, y1 = int(self.x0_entry.get()), int(self.y0_entry.get()), int(self.x1_entry.get()), int(
                self.y1_entry.get())
            if x0 == x1 and y0 == y1:
                showerror(title="Error", message="Input two different points")
                return
        except ValueError:
            showerror(title="Error", message=f"Incorrect input: \nInput must be an integer")
            return
        if alg == "simple":
            self.lines.append([x0, -y0, x1, -y1, 's'])
        elif alg == "dda":
            self.lines.append([x0, -y0, x1, -y1, 'd'])
        elif alg == "bres":
            self.lines.append([x0, -y0, x1, -y1, 'b'])
        elif alg == "castle":
            self.lines.append([x0, -y0, x1, -y1, 'c'])
        return x0, -y0, x1, -y1

    def get_circle_coordinates(self):
        """Get O(x,y) and radius of circle"""
        try:
            o1, o2 = int(self.o1_entry.get()), int(self.o2_entry.get())
            r = int(self.radius_entry.get())
            if r < 0:
                showerror(title="Error", message="Radius must be positive")
        except ValueError:
            showerror(title="Error", message=f"Incorrect input: \nInput must be an integer")
            return
        self.lines.append('circle')
        self.circles.append([o1, o2, r])
        return o1, o2, r

    def create_line(self, x1=0, y1=0, x2=0, y2=0, new=True):
        """Implements step-by-step algorithm to create line"""
        try:
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
                x1, y1, x2, y2 = self.get_points(alg='simple')
            start_time = time.time()
            d_x = x2 - x1
            d_y = y2 - y1
            x1 += self.start_x
            y1 += self.start_y
            x2 += self.start_x
            y2 += self.start_y
            k = d_y / d_x if d_x != 0 else 0
            sign_d_x, sign_d_y = np.sign(d_x), np.sign(d_y)
            if sign_d_x * d_x:
                for i in np.arange(1, sign_d_x * d_x + 1):
                    x = x1 + sign_d_x * i
                    y = y1 + sign_d_x * int(k * i)
                    self.canvas.create_oval(x, y, x + 1, y + 1, fill="black", width=1, tags=["grid", "s"])
            else:
                for i in np.arange(1, abs(d_y) + 1):
                    y = y1 + sign_d_y * i
                    self.canvas.create_oval(x1, y, x1 + 1, y + 1, fill="black", width=1, tags=["grid", "s"])
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)
            end_time = time.time()
            elapsed_time = end_time - start_time
            PixelPath.time = elapsed_time
        except TypeError:
            return

    def dda(self, x0=0, y0=0, x1=0, y1=0, new=True):
        """Implements DDA algorithm to create line"""
        try:
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
                x0, y0, x1, y1 = self.get_points(alg='dda')
            start_time = time.time()
            d_x, d_y = x1 - x0, y1 - y0
            k = d_y / d_x if d_x != 0 else 0
            x0 += self.start_x
            y0 += self.start_y
            x1 += self.start_x
            y1 += self.start_y
            sign_d_x, sign_d_y = np.sign(d_x), np.sign(d_y)
            if abs(d_x) > abs(d_y):
                for i in np.arange(abs(d_x)):
                    x = x0 + i * sign_d_x
                    y = y0 + int(k * i * sign_d_x)
                    self.canvas.create_oval(x, y, x + 1, y + 1, fill="black", width=1, tags=["grid", "d"])
            else:  # abs(d_x) <= abs(d_y)
                for i in np.arange(abs(d_y)):
                    x = x0 + int(i / k * sign_d_y) if k != 0 else x0
                    y = y0 + i * sign_d_y
                    self.canvas.create_oval(x, y, x + 1, y + 1, fill="black", width=1, tags=["grid", "d"])
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)
            end_time = time.time()
            elapsed_time = end_time - start_time
            PixelPath.time = elapsed_time
        except TypeError:
            return

    def bresenham_line(self, x0=0, y0=0, x1=0, y1=0, new=True):
        """Implements Bresenham algorithm to create line"""
        try:
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
                x0, y0, x1, y1 = self.get_points(alg="bres")
            start_time = time.time()
            d_x = abs(x1 - x0)
            d_y = abs(y1 - y0)
            sign_d_x = 1 if x1 > x0 else -1
            sign_d_y = 1 if y1 > y0 else -1
            error = d_x - d_y
            x0 += self.start_x
            y0 += self.start_y
            x1 += self.start_x
            y1 += self.start_y
            while True:
                self.canvas.create_oval(x0, y0, x0 + 1, y0 + 1, fill="black", width=1, tags=["grid", "b"])
                if x0 == x1 and y0 == y1:
                    break
                error2 = 2 * error
                if error2 > -d_y:
                    error -= d_y
                    x0 += sign_d_x
                if error2 < d_x:
                    error += d_x
                    y0 += sign_d_y
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)
            end_time = time.time()
            elapsed_time = end_time - start_time
            PixelPath.time = elapsed_time
        except TypeError:
            return

    def castle_pitway(self, x0=0, y0=0, x1=0, y1=0, new=True):
        """Implements Castle-Pitway Algorithm to create line"""
        try:
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
                x0, y0, x1, y1 = self.get_points(alg='castle')
            start_time = time.time()
            x0 += self.start_x
            y0 += self.start_y
            x1 += self.start_x
            y1 += self.start_y
            dx, dy = abs(x1 - x0), abs(y1 - y0)
            sx = 1 if x0 < x1 else -1
            sy = 1 if y0 < y1 else -1
            err = dx - dy
            while True:
                self.canvas.create_oval(x0, y0, x0 + 1, y0 + 1, fill="black", width=1, tags=["grid", "c"])
                if x0 == x1 and y0 == y1:
                    break
                e2 = 2 * err
                if e2 > -dy:
                    err -= dy
                    x0 += sx
                if e2 < dx:
                    err += dx
                    y0 += sy
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)
            end_time = time.time()
            elapsed_time = end_time - start_time
            PixelPath.time = elapsed_time
        except TypeError:
            return

    def bresenham_circle(self, xc=0, yc=0, radius=0, new=True):
        """Implements Bresenham algorithm to create circle"""
        try:
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, 1 / self.zoom_level, 1 / self.zoom_level)
                xc, yc, radius = self.get_circle_coordinates()
            start_time = time.time()
            if radius == 0:
                self.canvas.create_oval(xc, yc, xc + 1, yc + 1, fill="black", width=1, tags=["grid", "cir"])
                return
            x = 0
            y = radius
            d = 3 - 2 * radius
            self.plot_circle_points(xc, yc, x, y)
            while y >= x:
                x += 1
                if d > 0:
                    y -= 1
                    d = d + 4 * (x - y) + 10
                else:
                    d = d + 4 * x + 6
                self.plot_circle_points(xc, yc, x, y)
            if new:
                self.canvas.scale("all", self.start_x, self.start_y, self.zoom_level, self.zoom_level)
            end_time = time.time()
            elapsed_time = end_time - start_time
            PixelPath.time = elapsed_time
        except TypeError:
            return

    def plot_circle_points(self, xc, yc, x, y):
        """Renders dots of circles on the canvas"""
        cx, cy = self.start_x, self.start_y
        self.canvas.create_oval(cx + xc + x, cy - (yc + y), cx + xc + x + 1, cy - (yc + y) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc - x, cy - (yc + y), cx + xc - x + 1, cy - (yc + y) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc + x, cy - (yc - y), cx + xc + x + 1, cy - (yc - y) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc - x, cy - (yc - y), cx + xc - x + 1, cy - (yc - y) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc + y, cy - (yc + x), cx + xc + y + 1, cy - (yc + x) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc - y, cy - (yc + x), cx + xc - y + 1, cy - (yc + x) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc + y, cy - (yc - x), cx + xc + y + 1, cy - (yc - x) + 1, fill="black", tags=["grid", "cir"])
        self.canvas.create_oval(cx + xc - y, cy - (yc - x), cx + xc - y + 1, cy - (yc - x) + 1, fill="black", tags=["grid", "cir"])


if __name__ == "__main__":
    app = PixelPath()
    app.mainloop()

