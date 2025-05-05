package classes.web_3_new;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для взаимодействия с базой данных.
 * Обеспечивает операции с сущностями Point: получение, добавление, удаление.
 *
 * @author Березовский Артемий
 * @version 1.0
 * @since 04.05.2025
 */
public class DatabaseService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("classes.web_3_new");

    /**
     * Получает все точки из базы данных.
     * @return Список всех сохранённых точек.
     */
    public ArrayList<Point> getAllPoints(){
        EntityManager em = emf.createEntityManager();
        List<Point> points = em.createQuery("SELECT point FROM Point", Point.class ).getResultList();
        return new ArrayList<>(points);
    }

    /**
     * Добавляет новую точку в базу данных.
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @param r Радиус области.
     * @param hit Результат проверки попадания.
     */
    public void addPoint(double x, double y, double r, boolean hit){
        EntityManager em = emf.createEntityManager();
        var newPoint = new Point();
        newPoint.setX(x);
        newPoint.setY(y);
        newPoint.setR(r);
        newPoint.setHit(hit);
        em.getTransaction().begin();
        em.persist(newPoint);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Удаляет все точки из базы данных.
     */
    public void cleanPoints(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Point", Point.class).executeUpdate();
        em.getTransaction().commit();
    }
}
