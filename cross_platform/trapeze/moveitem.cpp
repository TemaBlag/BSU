#include "moveitem.h"
MoveItem::MoveItem(QObject *parent) : QObject(parent), QGraphicsItem() {};
MoveItem::~MoveItem(){};
QRectF MoveItem::boundingRect() const
{
 return QRectF (0,0,100,60);
}
void MoveItem::paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget)
{
 painter->setPen(Qt::black);
 painter->setBrush(Qt::blue);
 painter->drawRect(0,0,100,60);
 QLinearGradient grad1(0, 0, 100, 60);
 grad1.setColorAt(0.1, Qt::black);
 grad1.setColorAt(0.5, Qt::yellow);
 grad1.setColorAt(0.9, Qt::black);
 painter->fillRect(0, 0, 100, 60, grad1);
 Q_UNUSED(option);
 Q_UNUSED(widget);
}
