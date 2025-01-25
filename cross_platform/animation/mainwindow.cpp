#include "mainwindow.h"
#include "caritem.h"
#include "qgraphicsitem.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    scene = new QGraphicsScene;
    scene->setSceneRect(0,0,300,300);
    ui->graphicsView->setScene(scene);
    QGraphicsRectItem *road = new QGraphicsRectItem(50, 200, 200, 10);
    road->setBrush(Qt::gray);
    scene->addItem(road);
    CarItem *car = new CarItem();
    car->setPos(50, 180);
    scene->addItem(car);
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_pushButton_clicked()
{
}

