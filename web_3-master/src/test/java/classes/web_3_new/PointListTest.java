package classes.web_3_new;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PointListTest {

    // Тесты для треугольника (checkTriangle)

    @Test
    public void hitTriangleBorderXEqualsR() {
        PointList pointList = new PointList();
        assertTrue("Точка на границе X=R должна попадать в треугольник",
                pointList.checkTriangle(2.0, 0.0, 2.0));
    }

    @Test
    public void hitTriangleBorderYEqualsRDiv2() {
        PointList pointList = new PointList();
        assertTrue("Точка на границе Y=R/2 должна попадать в треугольник",
                pointList.checkTriangle(0.0, 1.0, 2.0));
    }

    @Test
    public void hitTriangleBorderXPlusYEqualsR() {
        PointList pointList = new PointList();
        assertTrue("Точка на границе X+Y=R должна попадать в треугольник",
                pointList.checkTriangle(1.0, 1.0, 2.0));
    }

    @Test
    public void missTriangleOutsideSumCondition() {
        PointList pointList = new PointList();
        assertFalse("Точка за пределами X+Y>R должна не попадать",
                pointList.checkTriangle(1.5, 1.0, 2.0));
    }

    // Тесты для прямоугольника (checkRect)

    @Test
    public void hitRectLeftBorder() {
        PointList pointList = new PointList();
        assertTrue("Точка на левой границе X=-R/2 должна попадать",
                pointList.checkRect(-1.0, 0.0, 2.0));
    }

    @Test
    public void hitRectUpperYBorder() {
        PointList pointList = new PointList();
        assertTrue("Точка на верхней границе Y=R должна попадать",
                pointList.checkRect(-0.5, 2.0, 2.0));
    }

    @Test
    public void missRectOutsideX() {
        PointList pointList = new PointList();
        assertFalse("Точка за левой границей X<-R/2 должна не попадать",
                pointList.checkRect(-1.1, 1.0, 2.0));
    }

    @Test
    public void missRectNegativeY() {
        PointList pointList = new PointList();
        assertFalse("Точка с Y<0 не должна попадать",
                pointList.checkRect(-0.5, -0.1, 2.0));
    }

    // Тесты для круга (checkCircle)

    @Test
    public void hitCircleBorderExact() {
        PointList pointList = new PointList();
        assertTrue("Точка на границе круга X²+Y²=R² должна попадать",
                pointList.checkCircle(-1.0, -1.0, Math.sqrt(2)));
    }

    @Test
    public void hitCircleInside() {
        PointList pointList = new PointList();
        assertTrue("Точка внутри круга должна попадать",
                pointList.checkCircle(-0.5, -0.5, 1.0));
    }

    @Test
    public void missCircleOutsideRadius() {
        PointList pointList = new PointList();
        assertFalse("Точка за пределами радиуса не должна",
                pointList.checkCircle(-1.0, -1.0, 1.0));
    }

    @Test
    public void missCirclePositiveQuadrant() {
        PointList pointList = new PointList();
        assertFalse("Точка в положительном квадранте не должна попадать",
                pointList.checkCircle(0.5, 0.5, 1.0));
    }

    // Общие тесты (validateHit)

    @Test
    public void hitZeroPoint() {
        PointList pointList = new PointList();
        assertTrue("Точка (0,0) должна попадать при любом R>0",
                pointList.validateHit(0.0, 0.0, 1.0));
    }

    @Test
    public void missAllAreas() {
        PointList pointList = new PointList();
        assertFalse("Точка вне всех областей не должна попадать",
                pointList.validateHit(3.0, 3.0, 1.0));
    }
}
