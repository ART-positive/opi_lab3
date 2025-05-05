package classes.web_3_new;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Управляет списком точек и проверкой попадания в область.
 *
 * @author Березовский Артемий
 * @version 1.0
 * @since 04.05.2025
 */
@Named("pointListBean")
//@ManagedBean
@ApplicationScoped
public class PointList implements Serializable {

    /**
     * Инициализирует список точек из базы данных при создании.
     */
    @PostConstruct
    public void init(){
        try{
            var ds = new DatabaseService();
            setPointList(ds.getAllPoints());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /** Список точек. */
    private ArrayList<Point> pointList = new ArrayList<>();

    /**
     * Добавляет точку в список.
     * @param p Точка для добавления.
     */
    public void add(Point p){
        pointList.add(p);
    }

    /**
     * Возвращает текущий список точек.
     * @return Список точек.
     */
    public ArrayList<Point> getPointList(){
        return pointList;
    }

    /**
     * Заменяет текущий список точек новым.
     * @param pointList Новый список точек.
     */
    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    /**
     * Очищает все точки:
     * - Удаляет данные из базы данных.
     * - Сбрасывает локальный список точек.
     */
    public void clean(){
        var ds = new DatabaseService();
        ds.cleanPoints();
        this.pointList = new ArrayList<>();
    }

    /**
     * Используется для создания новых точек через контекст.
     */
    @Inject
    private Instance<Point> pointBean;

    /**
     * Проверяет попадание точки в область и сохраняет её.
     */
    public void handle(){
        var point = pointBean.get();
        point.setHit(validateHit(point.getX(),point.getY(),point.getR()));
        var np = new Point();
        np.setX(point.getX());
        np.setY(point.getY());
        np.setR(point.getR());
        np.setHit(point.getHit());
        pointList.add(np);
        try{
            var ds = new DatabaseService();
            ds.addPoint(np.getX(),np.getY(),np.getR(),np.getHit());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Проверяет попадание точки в одну из трёх областей: треугольник, прямоугольник, круг.
     * @return {@code true} если точка попала хотя бы в одну область.
     */
    public Boolean validateHit(double x, double y, double r){
        return checkCircle(x,y,r) || checkRect(x,y,r) || checkTriangle(x,y,r);
    }

    /**
     * Проверяет попадание в треугольник (X ≥ 0, Y ≥ 0, X ≤ R, Y ≤ R/2, X + Y ≤ R).
     */
    public Boolean checkTriangle(double x, double y, double r){
        return (x>=0 && y>=0 && x<=r && y<=r/2 && x+y-r<=0);
    }

    /**
     * Проверяет попадание в прямоугольник (X ∈ [-R/2, 0], Y ∈ [0, R]).
     */
    public Boolean checkRect(double x, double y, double r){
        return (x>=-r/2 && y<=r && x<=0 && y>=0);
    }

    /**
     * Проверяет попадание в круг (X² + Y² ≤ R², X ≤ 0, Y ≤ 0).
     */
    public Boolean checkCircle(double x, double y, double r){
        return ((Math.pow(x,2) + Math.pow(y,2) <= Math.pow(r,2)) && x<=0 && y<=0);
    }

}
