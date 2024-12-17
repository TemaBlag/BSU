import tkinter as tk
from tkinter import filedialog
from PIL import Image, ImageTk
import cv2
import numpy as np
import tkinter.messagebox as messagebox
import gc


current_image = None
tmp_image = None
gc.set_threshold(100, 10, 10)


def load_image():
    global current_image
    file_path = filedialog.askopenfilename()
    if file_path:
        current_image = cv2.imread(file_path, cv2.IMREAD_COLOR)
        display_image(current_image)


def resize_image(image, target_width, target_height):
    height, width = image.shape[:2]
    scaling_factor = min(target_width / width, target_height / height)
    new_size = (int(width * scaling_factor), int(height * scaling_factor))
    return cv2.resize(image, new_size)


def change_brightness(img, value=50):
    global tmp_image
    img_temp = img.astype(np.int16)
    img_temp = np.clip(img_temp + value, 0, 255)
    tmp_image = cv2.convertScaleAbs(img_temp)
    return tmp_image


def invert_colors(img):
    global tmp_image
    tmp_image = cv2.bitwise_not(img)
    return tmp_image


def linear_contrast(image):
    global tmp_image
    b_channel, g_channel, r_channel = cv2.split(image)

    def apply_contrast(channel):
        min_val = np.min(channel)
        max_val = np.max(channel)
        return ((channel - min_val) / (max_val - min_val) * 255).astype(np.uint8)

    b_contrast = apply_contrast(b_channel)
    g_contrast = apply_contrast(g_channel)
    r_contrast = apply_contrast(r_channel)
    tmp_image = cv2.merge((b_contrast, g_contrast, r_contrast))
    return tmp_image


def simple_threshold(img, threshold_value=100):
    global tmp_image
    _, tmp_image = cv2.threshold(img, threshold_value, 255, cv2.THRESH_BINARY)
    return tmp_image


def otsu_threshold(img):
    global tmp_image
    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    _, tmp_image = cv2.threshold(gray_img, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
    return tmp_image


def display_image(img):
    target_width = image_panel.winfo_width()
    target_height = image_panel.winfo_height()
    img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    img = resize_image(img_rgb, target_width,
                       target_height)
    img = Image.fromarray(img)
    imgtk = ImageTk.PhotoImage(image=img)
    image_panel.config(image=imgtk)
    image_panel.image = imgtk


def apply_brightness():
    global current_image
    if current_image is not None:
        try:
            value = int(brightness_entry.get())
            if value < -255 or value > 255:
                messagebox.showerror("Error", "Brightness value must be between [-255, 255].")
                return
            result = change_brightness(current_image, value=value)
            display_image(result)
        except ValueError as e:
            messagebox.showerror("Error", "Brightness value must be numer between [-255, 255].")


def apply_invert_colors():
    global current_image
    if current_image is not None:
        result = invert_colors(current_image)
        display_image(result)


def apply_linear_contrast():
    global current_image
    if current_image is not None:
        result = linear_contrast(current_image)
        display_image(result)


def apply_simple_threshold():
    global current_image
    if current_image is not None:
        try:
            value = int(threshold_entry.get())
            if value < 0 or value > 255:
                messagebox.showerror("Error", "Threshold must be between [0, 255].")
                return
            result = simple_threshold(current_image, threshold_value=value)
            display_image(result)
        except ValueError as e:
            messagebox.showerror("Error", "Threshold must be numer between [0, 255].")


def apply_otsu_threshold():
    global current_image
    if current_image is not None:
        result = otsu_threshold(current_image)
        display_image(result)


def save_image():
    file_path = filedialog.asksaveasfilename(defaultextension=".jpg",
                                             filetypes=[("JPEG files", "*.jpg"), ("PNG files", "*.png"),
                                                        ("All files", "*.*")])
    if file_path:
        try:
            cv2.imwrite(file_path, tmp_image)
            messagebox.showinfo("Save", "Image saved successfully!")
        except Exception as e:
            messagebox.showerror("Error", f"Exception: {e}")


root = tk.Tk()
root.title("ImageMaster")
root.geometry("1000x800")
root.configure(bg="#34495e")

frame_top = tk.Frame(root, bg="#2c3e50", height=100)
frame_top.pack(fill="x")

frame_left = tk.Frame(root, bg="#2c3e50", width=300)
frame_left.pack(side="left", fill="y")

frame_right = tk.Frame(root, bg="#34495e")
frame_right.pack(side="right", expand=True, fill="both")


image_panel = tk.Label(frame_right, bg="#ecf0f1")
image_panel.pack(expand=True, fill="both", padx=20, pady=20)


button_style = {
    "bg": "#e74c3c",
    "fg": "black",
    "font": ("Helvetica", 14, "bold"),
    "activebackground": "#c0392b",
    "activeforeground": "white",
    "bd": 2,
    "relief": "groove",
    "pady": 10,
    "width": 20
}

title_label = tk.Label(frame_top, text="ImageMaster", bg="#2c3e50", fg="white", font=("Helvetica", 24, "bold"))
title_label.grid(row=0, column=0, padx=5, pady=5)

btn_load = tk.Button(frame_left, text="Load Image", command=load_image, **button_style)
btn_load.grid(row=0, column=0, padx=5, pady=5)

label_brightness = tk.Label(frame_left, text="Piecemeal operations and", bg="#2c3e50", fg="white", font=("Helvetica", 18))
label_brightness_lc = tk.Label(frame_left, text="Linear Contrast:", bg="#2c3e50", fg="white", font=("Helvetica", 18))

label_brightness.grid(row=1, column=0, padx=5, pady=5)
label_brightness_lc.grid(row=2, column=0, padx=5, pady=5)

btn_brightness = tk.Button(frame_left, text="Increase Brightness", command=apply_brightness, **button_style)
btn_brightness.grid(row=3, column=0, padx=5, pady=5)


label_brightness = tk.Label(frame_left, text="Value:", bg="#2c3e50", fg="white", font=("Helvetica", 18))
label_brightness.grid(row=4, column=0, padx=(30, 0), pady=5, sticky="w")

brightness_entry = tk.Entry(frame_left, font=("Helvetica", 18), width=5)
brightness_entry.insert(0, "50")
brightness_entry.grid(row=4, column=0, padx=1, pady=5)

btn_invert = tk.Button(frame_left, text="Invert Colors", command=apply_invert_colors, **button_style)
btn_invert.grid(row=5, column=0, padx=5, pady=5)

btn_contrast = tk.Button(frame_left, text="Linear Contrast", command=apply_linear_contrast, **button_style)
btn_contrast.grid(row=6, column=0, padx=5, pady=5)

label_simple_thresh = tk.Label(frame_left, text="Global Threshold Processing:", bg="#2c3e50", fg="white", font=("Helvetica", 18))
label_simple_thresh.grid(row=7, column=0, padx=5, pady=5)

btn_simple_thresh = tk.Button(frame_left, text="Simple Threshold", command=apply_simple_threshold, **button_style)
btn_simple_thresh.grid(row=8, column=0, padx=5, pady=5)

threshold = tk.Label(frame_left, text="Threshold:", bg="#2c3e50", fg="white", font=("Helvetica", 18))
threshold.grid(row=9, column=0, padx=(30, 0), pady=5, sticky="w")

threshold_entry = tk.Entry(frame_left, font=("Helvetica", 18), width=5)
threshold_entry.insert(0, "100")
threshold_entry.grid(row=9, column=0, padx=(0, 60), pady=5, sticky="e")

btn_adaptive_thresh = tk.Button(frame_left, text="Otsu's Thresholding", command=apply_otsu_threshold, **button_style)
btn_adaptive_thresh.grid(row=10, column=0, padx=5, pady=5)

label_simple_thresh = tk.Label(frame_left, text="Saving:", bg="#2c3e50", fg="white", font=("Helvetica", 18))
label_simple_thresh.grid(row=11, column=0, padx=5, pady=5)

save_button = tk.Button(frame_left, text="Save", command=save_image, **button_style)
save_button.grid(row=12, column=0, padx=5, pady=5)


root.mainloop()









