#ifndef CARITEM_H
#define CARITEM_H

#include "qpainter.h"
#include "qtmetamacros.h"
#include <QGraphicsItem>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsRectItem>
#include <QGraphicsEllipseItem>
#include <QTimer>
class CarItem : public QObject, public QGraphicsItem {
public:
        Q_OBJECT
        Q_INTERFACES(QGraphicsItem)
        explicit CarItem() : wheelAngle(0) {
                QTimer *timer = new QTimer(this);
                connect(timer, &QTimer::timeout, this, &CarItem::animate);
                timer->start(20);
        }//тут возникает ошибка что конструктор является приватным методом, но он находится в public, не понимаю как исправить

    virtual ~CarItem();
    QRectF boundingRect() const override;
private:
    int wheelAngle;
    void paint(QPainter *painter, const QStyleOptionGraphicsItem *option, QWidget *widget) override;
    void animate();
signals:
public slots:
};

#endif // CARITEM_H
