#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow), function(QRegularExpression("^\\d{3}$"))
{
    ui->setupUi(this);
    scene = new QGraphicsScene(this);
    scene->setItemIndexMethod(QGraphicsScene::NoIndex);
    ui->graphicsView->resize(400,400);
    ui->graphicsView->setScene(scene);
    ui->graphicsView->setRenderHint(QPainter::Antialiasing);
    ui->graphicsView->setCacheMode (QGraphicsView::CacheBackground);
    ui->graphicsView->setViewportUpdateMode(QGraphicsView::BoundingRectViewportUpdate);
    scene->setSceneRect(0,0,300,300);
    ui->lineEdit->setValidator(&function);
}
double f1(double x, int a, int b, int c)
{
    return a*pow(x,2)+b*x+c;
}
MainWindow::~MainWindow()
{
    delete ui;
}
double MainWindow::fun(double a, double b,  double (*f)(double,int,int,int))
{
    int num = ui->lineEdit->text().toInt();
    int num2 = ui->lineEdit_3->text().toInt();
    int num3 = ui->lineEdit_4->text().toInt();
    double h = (b-a)/4;
    double Int = 2*h/45 * (7*f(a,num,num2,num3) + 32*f(a+h,num,num2,num3) + 12*f((a+2*h),num,num2,num3) + 32*f((a+3*h),num,num2,num3) + 7*f(b,num,num2,num3) );
    return Int;
}

void MainWindow::on_pushButton_clicked()
{
double I;
double top = ui->comboBox->currentText().toDouble();
double bot = ui->comboBox_2->currentText().toDouble();
if(bot >=top) QMessageBox::warning(this,"Ошибка", "Нижний предел не может быть больше верхнего!");
I = fun(bot,top,&f1);
QString s1 = QString::number(I);
ui->lineEdit_2->setText(s1);
}


void MainWindow::on_pushButton_2_clicked()
{
    QMessageBox msgBox;
    msgBox.setText("Как вводить функцию?");
    msgBox.setInformativeText("Коэффициенты параболы вводить через пробел.\n Пример: 123\n x^2+2*x+3");
    msgBox.exec();
}


void MainWindow::on_pushButton_3_clicked()
{
    double x = ui->graphicsView->width()/2;
    x -= 100;
    double f;
    double h=1,k=0.00005,a=200;
    double top = ui->comboBox->currentText().toDouble();
    double dx = ui->comboBox_2->currentText().toDouble();
    int num = ui->lineEdit->text().toInt();
    int num3 = num % 10;
    num /= 10;
    int num2 = num % 10;
    num /= 10;
    double rad = 1;
        while(dx != top) {
        f = k*num*pow((dx-x),2)*a+num2*k*(dx-x)*a+num3;
           scene->addEllipse(dx, f, rad*2.0, rad*2.0, QPen(), QBrush(Qt::SolidPattern));
         dx = dx + h;
     }
}


void MainWindow::on_pushButton_4_clicked()
{
    scene->clear();
}
