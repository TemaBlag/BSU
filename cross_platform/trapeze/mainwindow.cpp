#include "mainwindow.h"
#include "moveitem.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->resize(640,640);
    this->setFixedSize(640,640);
    scene = new QGraphicsScene(this);
    scene->setItemIndexMethod(QGraphicsScene::NoIndex);
    ui->graphicsView->resize(600,600);
    ui->graphicsView->setScene(scene);
    ui->graphicsView->setRenderHint(QPainter::Antialiasing);
    ui->graphicsView->setCacheMode (QGraphicsView::CacheBackground);
    ui->graphicsView->setViewportUpdateMode(QGraphicsView::BoundingRectViewportUpdate);
    scene->setSceneRect(0,0,500,500);
}
void Build(QGraphicsScene *scene, int c){
    int odin = 1;
    int temp = 0;
    for(int i = 0; i<=c-1;i++){
        MoveItem *item = new MoveItem();
        if(i==0) item->setPos(200,60);
        else if(i<=2){
            odin *= -1;
            item->setPos(200+odin*50,120);
        }
        else if(i>=3 && i<=5){
            item->setPos(100+temp,180);
            temp += 100;
        }
        else if(i>=6&&i<=9){
            item->setPos(50+temp,240);
            temp -= 100;
        }
        else if(i>=10&&i<=14){
            item->setPos(100+temp,300);
            temp += 100;
        }
        else if(i>=15){
            item->setPos(50+temp,360);
            temp -= 100;
        }
        scene->addItem(item);
    }
}
MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::on_pushButton_clicked()
{
    int ryad = ui->lineEdit->text().toInt();
    if(ryad>6 || ryad<1) ui->label_2->setText("Кол-во рядов от 1 до 6");
    int c = (ryad*(ryad+1))/2;
    Build(scene,c);
  }
void MainWindow::on_pushButton1_clicked()
{
    scene->clear();
}

