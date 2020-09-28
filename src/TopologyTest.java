
import org.junit.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertEquals;

public class TopologyTest {
        public RealWorld rw;
        public List<Creatures> creatures;
        public List<Furniture> list;
        public ArrayList<Planet> planets;
        public Long time;
        public List<Room> roomList;
        public Catalogy catalogy;

        @Before
        public void setup(){
            Furniture f = new Furniture("Мебель", Material.Plush);
            list = new ArrayList<Furniture>();
            list.add(f);
            creatures = new ArrayList<Creatures>();
            Creatures cr = new Creatures("Мы", Parametres.small);
            creatures.add(cr);
            planets = new ArrayList<Planet>();
            Planet planet = new Planet("Земля");
            planets.add(planet);
            catalogy = new Catalogy("Каталог", planets, null);
            Room room = new Room(list, creatures, catalogy );
            roomList = new ArrayList<Room>();
            roomList.add(room);
            rw= new RealWorld("Earth", roomList);
            long st = System.nanoTime();
            creatures = catalogy.wasReadedBy(creatures, catalogy);
            long fn = System.nanoTime();
            time = fn-st;
        }

        @Test
        public void RealWoldExistsTest(){
            assertNotNull("Ничего нет!", rw);
        }
        @Test
        public void MagratenaninAppearedTest(){
            assertTrue(creatures.get(creatures.size()-1).name.equals("Magratenanin"));//,creatures.contains(cr)==true);
        }
        @Test
        public void GetByNameTest(){
            assertNotNull("Объект не существует!", creatures.get(0).getByname(creatures, "Мы"));
        }
        @Test
        public void CatalogReadingTest(){
        assertFalse("Каталог не прочитан!", catalogy.size==0);
        }
        @Test
        public void CreatureAddingTest(){
            creatures = creatures.get(0).addSomeone(creatures,"Новичек", Parametres.High);
        assertNotNull("Объект не существует!", creatures.get(0).getByname(creatures, "Новичек"));
        }
        @Test
        public void CreatureDeleatingTest(){
        assertNull("Объект существует!", creatures.get(0).getByname(creatures, "Новичек"));
        }

        @Test
        public void FurnitureAddingTest(){
            roomList.get(roomList.size()-1).furnitures.add(new Furniture("награды", Material.Something));
            assertTrue("Нет наград!",  roomList.get(roomList.size()-1).furnitures.get(roomList.get(roomList.size()-1).furnitures.size()-1).name.equals("награды"));
        }
        @Test
        public void FurnitureExistingTest(){
            Furniture f = roomList.get(roomList.size()-1).furnitures.get(roomList.get(roomList.size()-1).furnitures.size()-1);
            assertTrue("Нет мебели!", f.name.equals("Мебель")&& f.material.equals(Material.Plush));
        }
        @Test
        public void PlanetAddingTest(){
            planets.add(new Planet("Qwerty!"));
            assertTrue("Не существует!", planets.get(planets.size()-1).name.equals("Qwerty!"));
        }
        @Test
        public void RoomIsHereTest(){
            Creatures cr = new Creatures("test", Parametres.small);
            roomList.get(roomList.size()-1).addCreatures(roomList.get(roomList.size()-1).creatures, cr );
            assertTrue("Не здесь!",roomList.get(roomList.size()-1).isHere(cr));
        }
        @Test
        public void ReadingTimeTest(){
            assertTrue(time>=1000000);
        }

}

