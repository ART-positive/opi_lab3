package classes.web_3_new;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, представляющий точку с координатами (X, Y, R) и результатом проверки попадания.
 *
 * @author Березовский Артемий
 * @version 1.0
 * @since 04.05.2025
 */
@Named("pointBean")
@ViewScoped
@Entity
@Table(name="points")
public class Point implements Serializable {
    @Id
    @GeneratedValue
    protected long id;
    private double x;
    private double y;
    private double r;
    private boolean hit;

    /**
     * Возвращает координату x.
     */
    public double getX() {
        return x;
    }

    /**
     * Устанавливает координату x.
     * @param x Новая координата x.
     */
    public void setX(double x) {
        this.x = x;
        System.out.println(x);
    }

    /**
     * Возвращает координату y.
     */
    public double getY() {
        return y;
    }

    /**
     * Устанавливает координату y.
     * @param y Новая координата y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Возвращает радиус R.
     */
    public double getR() {
        return r;
    }

    /**
     * Устанавливает радиус R.
     * @param r Новая радиус R.
     */
    public void setR(double r) {
        this.r = r;
    }

    /**
     * Возвращает результат проверки попадания точки в область.
     * @return {@code true}, если точка попала в область, иначе {@code false}.
     */
    public boolean getHit(){
        return hit;
    }

    /**
     * Устанавливает результат проверки попадания точки в область.
     * @param hit Результат проверки ({@code true} — попадание, {@code false} — нет).
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * Возвращает строковое представление точки в формате:
     * <pre>Point{x=..., y=..., r=..., hit=...}</pre>
     * @return Строка с координатами и результатом проверки.
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                '}';
    }

    /**
     * Сравнивает точку с другим объектом.
     * @param o Объект для сравнения.
     * @return {@code true}, если объекты идентичны по полям (x, y, r, hit), иначе {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0 && Double.compare(r, point.r) == 0 && hit == point.hit;
    }

    /**
     * Возвращает хеш-код точки на основе её полей (x, y, r, hit).
     * @return Значение хеш-кода.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, hit);
    }

    /**
     * Возвращает текущий экземпляр точки.
     * Используется для работы с контекстом CDI.
     * @return Текущая точка.
     */
    public Point getBean(){
        return this;
    }

}
