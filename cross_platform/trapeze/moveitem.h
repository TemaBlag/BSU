#ifndef MOVEITEM_H
#define MOVEITEM_H
#include <QObject>
#include <QGraphicsItem>
#include <QPainter>
#include <QGraphicsSceneMouseEvent>
#include <QDebug>
#include <QCursor>
class MoveItem : public QObject, public QGraphicsItem
{
 Q_OBJECT
public:
 explicit MoveItem(QObject *parent = 0);
 virtual ~MoveItem();
signals:
private:
 QRectF boundingRect() const;
 void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget);
public slots:
};
#endif // MOVEITEM_H
