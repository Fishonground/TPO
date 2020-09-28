import org.junit.*;
import org.junit.Test;


import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
public class FunctionTest {
    @Test
    public void ZeroPointTest(){
        assertEquals("Пересечение с осью абсцисс в 0 градусов неверно!", Math.sin(0*0.0175), Function.sinus(0), 0.0000001);
    }

    @Test
    public void NinetyPointTest(){
        assertEquals("Пересечение с осью ординат в 90 градусов неверно!", Math.sin(90*0.0175), Function.sinus(90), 0.0000001);
    }
    @Test
    public void OneHEightyPointTest(){
        assertEquals("Пересечение с осью абсцисс в 180 градусов неверно!", Math.sin(180*0.0175), Function.sinus(180), 0.0000001);
    }
    @Test
    public void TwoHSeventyPointTest(){
        assertEquals("Пересечение с осью ординат в 270 градусов неверно!", Math.sin(270*0.0175), Function.sinus(270), 0.0000001);
    }

    @Test
    public void ValueTestPositive(){
        for (int i = 0; i<=100; i++){
        int q = (int)Math.random()*1000;
        assertTrue("Выходит за ОДЗ!", Function.sinus(q)>=-1 && Function.sinus(q)<=1 );
        }
    }
    @Test
    public void ValueTestNegative(){
        for (int i = 0; i<=100; i++){
        int q = (int)Math.random()*-1000;
        assertTrue("Выходит за ОДЗ!", Function.sinus(q)>=-1 && Function.sinus(q)<=1 );
        }
    }

    @Test
    public void RightUpTest(){
        assertEquals("Значение для 30 градусов неверно!", Math.sin(30*0.0175), Function.sinus(30), 0.0000001);
    }
    @Test
    public void LeftUpTest(){
        assertEquals("Значение для 150 градусов неверно!", Math.sin(150*0.0175), Function.sinus(150), 0.0000001);
    }
    @Test
    public void RightDownTest(){
        assertEquals("Значение для 210 градусов неверно!", Math.sin(210*0.0175), Function.sinus(210), 0.0000001);
    }
    @Test
    public void LeftDownTest(){
        assertEquals("Значение для 330 градусов неверно!", Math.sin(330*0.0175), Function.sinus(330), 0.0000001);
    }

    @Test
    public void backZeroPointTest(){
        assertEquals("Пересечение с осью абсцисс в -0 градусов неверно!", Math.sin(-0*0.0175), Function.sinus(-0), 0.0000001);
    }

    @Test
    public void backNinetyPointTest(){
        assertEquals("Пересечение с осью ординат в -90 градусов неверно!", Math.sin(-90*0.0175), Function.sinus(-90), 0.0000001);
    }
    @Test
    public void backOneHEightyPointTest(){
        assertEquals("Пересечение с осью абсцисс в -180 градусов неверно!", Math.sin(-180*0.0175), Function.sinus(-180), 0.0000001);
    }
    @Test
    public void backTwoHSeventyPointTest(){
        assertEquals("Пересечение с осью ординат в -270 градусов неверно!", Math.sin(-270*0.0175), Function.sinus(-270), 0.0000001);
    }

    @Test
    public void backRightUpTest(){
        assertEquals("Значение для -30 градусов неверно!", Math.sin(-30*0.0175), Function.sinus(-30), 0.0000001);
    }
    @Test
    public void backLeftUpTest(){
        assertEquals("Значение для -150 градусов неверно!", Math.sin(-150*0.0175), Function.sinus(-150), 0.0000001);
    }
    @Test
    public void backRightDownTest(){
        assertEquals("Значение для -210 градусов неверно!", Math.sin(-210*0.0175), Function.sinus(-210), 0.0000001);
    }
    @Test
    public void backLeftDownTest(){
        assertEquals("Значение для -330 градусов неверно!", Math.sin(-330*0.0175), Function.sinus(-330), 0.0000001);
    }


}
