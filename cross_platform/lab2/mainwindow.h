#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include <QLabel>
#include <QMouseEvent>
#include <QStatusBar>
#include <QtGui>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <QWindow>
#include <QMainWindow>
#include <QApplication>
#include <QDesktopWidget>
#include <QDebug>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE


class MainWindow : public QMainWindow{
Q_OBJECT
private:
    int height;
    int width;
    int xCoordinateOfTheTreasure;
    int yCoordinateOfTheTreasure;
    int totalX;
    int totalY;
    QLabel* quarter;
    QLabel* result;
    QLabel* coordX;
    QLabel* coordY;
    QMouseEvent* pe;

protected:

    virtual void mousePressEvent(QMouseEvent* pe)
    {
       int firstCoordinate = pe->x();
       int secondCoordinate = pe->y();
       if((firstCoordinate <= width / 2) && (secondCoordinate <= height / 2)) {
            quarter->setText("Second Quarter");
       }

       if((firstCoordinate >= width / 2) && (secondCoordinate <= height / 2)) {
            quarter->setText("First Quarter");
       }

       if((firstCoordinate <= width / 2) && (secondCoordinate >= height / 2)) {
            quarter->setText("Third Quarter");
       }

       if((firstCoordinate >= width / 2) && (secondCoordinate >= height / 2)) {
            quarter->setText("Fourth Quarter");
       }
        int click = pe->buttons();
        if(click == 1){
            result->setText("LEFT");
        }else if(click == 2){
            result->setText("RIGHT");
        }

    }

    virtual void mouseMoveEvent(QMouseEvent* pe){
        this->totalX = pe->x();
        this->totalY = pe->y();
    }

    virtual void keyPressEvent(QKeyEvent* kpe){
        int keys = kpe->key();
        if((keys == Qt::Key_Up) || (keys == Qt::Key_Down) || (keys == Qt::Key_Left) || (keys == Qt::Key_Right)){
            getCoordMouse();
        }
    }

    void getCoordMouse(){

        coordX->setText(QString::number(totalX));
        coordY->setText(QString::number(totalY));
    }


    int getRandomNumber(int min, int max){
        srand(time(NULL));
        int num = min + rand()%(max - min + 1);
        return num;
    }
    void setX(int num){
        this->xCoordinateOfTheTreasure = num;
    }
    void setY(int num){
        this->yCoordinateOfTheTreasure = num;
    }

public:
    MainWindow(QWidget* pwgt = 0) : QMainWindow(pwgt)
    {
       setX(getRandomNumber(0,1900));
       setY(getRandomNumber(0,950));
       setMouseTracking(true);
       result = new QLabel(this);
       quarter = new QLabel(this);
       coordX = new QLabel(this);
       coordY = new QLabel(this);
       statusBar()->addWidget(result);
       statusBar()->addWidget(quarter);
       statusBar()->addWidget(coordX);
       statusBar()->addWidget(coordY);

       auto const rec = QApplication::desktop()->screenGeometry();
       auto const height = rec.height();
       auto const width = rec.width();
       this->height = height;
       this->width = width;
    }
};


#endif // MAINWINDOW_H
