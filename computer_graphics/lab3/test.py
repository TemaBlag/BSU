from unittest.mock import patch
import numpy as np
from PixelPath import PixelPath


def test_create_line(capsys):
    with capsys.disabled():
        with patch("tkinter.Tk"), patch("tkinter.Canvas") as MockCanvas:
            mock_canvas_instance = MockCanvas.return_value
            k = 100
            results = []
            for i in range(k):
                PixelPath.create_line(mock_canvas_instance, 0, 0, 300, 300, new=False)
                results.append(PixelPath.time)
            print(f'Average time of step-by-step algorithm: {np.mean(results)}')


def test_dda(capsys):
    with capsys.disabled():
        with patch("tkinter.Tk"), patch("tkinter.Canvas") as MockCanvas:
            mock_canvas_instance = MockCanvas.return_value
            k = 100
            results = []
            for i in range(k):
                PixelPath.dda(mock_canvas_instance, 0, 0, 300, 300, new=False)
                results.append(PixelPath.time)
            print(f'Average time of dda algorithm: {np.mean(results)}')


def test_bresenham_line(capsys):
    with capsys.disabled():
        with patch("tkinter.Tk"), patch("tkinter.Canvas") as MockCanvas:
            mock_canvas_instance = MockCanvas.return_value
            k = 100
            results = []
            for i in range(k):
                PixelPath.bresenham_line(mock_canvas_instance, 0, 0, 300, 300, new=False)
                results.append(PixelPath.time)
            print(f'Average time of Bresenham algorithm: {np.mean(results)}')


def test_castle_pitway(capsys):
    with capsys.disabled():
        with patch("tkinter.Tk"), patch("tkinter.Canvas") as MockCanvas:
            mock_canvas_instance = MockCanvas.return_value
            k = 100
            results = []
            for i in range(k):
                PixelPath.castle_pitway(mock_canvas_instance, 0, 0, 300, 300, new=False)
                results.append(PixelPath.time)
            print(f'Average time of Castle-Pitway algorithm: {np.mean(results)}')


def test_bresenham_circle(capsys):
    with capsys.disabled():
        with patch("tkinter.Tk"), patch("tkinter.Canvas") as MockCanvas:
            mock_canvas_instance = MockCanvas.return_value
            k = 100
            results = []
            for i in range(k):
                PixelPath.bresenham_circle(mock_canvas_instance, 0, 0, 150, new=False)
                results.append(PixelPath.time)
            print(f'Average time of Bresenham algorithm of creating circle: {np.mean(results)}')