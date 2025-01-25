#include "caritem.h"//;

QRectF boundingRect() {
    return QRectF(-50, -20, 150, 60);
}
void CarItem::animate() {
    wheelAngle += 5;
    if (wheelAngle >= 360) {
        wheelAngle = 0;
    }
    update();
}

void CarItem::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget){
    painter->setRenderHint(QPainter::Antialiasing);

    painter->setPen(Qt::black);
    painter->setBrush(Qt::red);
    painter->drawRect(20, 20, 100, 40);

    painter->save();
    painter->translate(40, 80);
    painter->rotate(wheelAngle);
    painter->setBrush(Qt::black);
    painter->drawEllipse(-10, -10, 20, 20);
    painter->restore();

    painter->save();
    painter->translate(100, 80);
    painter->rotate(wheelAngle);
    painter->setBrush(Qt::black);
    painter->drawEllipse(-10, -10, 20, 20);
    painter->restore();

    painter->setBrush(Qt::white);
    painter->drawRect(30, 25, 25, 20);
    painter->drawRect(70, 25, 25, 20);

    painter->setBrush(Qt::yellow);
    painter->drawEllipse(105, 35, 10, 10);
}
