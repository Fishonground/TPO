import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Catalogy {
    ArrayList<Planet> planets;
    String name;
    Integer size;
    RealWorld realWorld;
    Catalogy(String n, ArrayList<Planet> alp, RealWorld re){
        planets = alp;
        name = n;
        size = alp.size();
        realWorld = re;
    }
    public Catalogy addCatalogy(String name, ArrayList<Planet> alp, RealWorld re){
        return new Catalogy(name,alp, re);
    }
    public void delCatalogy(Catalogy catalogy){
        catalogy.name = null;
        catalogy.size = 0;
    }

    public void addPlanet(Planet planet){
        planets.add(planet);
        size++;
    }
    public void delPlanet(Planet planet){
        planets.remove(planet);
        size--;
    }
    public boolean isEmpty(Catalogy catalogy){
        return  planets.isEmpty();
    }
    public List<Creatures> wasReadedBy(List<Creatures> creaturesList, Catalogy catalogy){
        for (Creatures cre :
                creaturesList) {
            cre.isReading = true;
        }
        while (!isEmpty(catalogy)){
            try{
            Thread.sleep(100/size);
                System.out.println("Планета исчезает!");
           catalogy.planets.remove(0);
            }
            catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
        Creatures Mir = new Creatures("Magratenanin", Parametres.High /*, creaturesList.get(0).room*/);
        //Mir.isReading = false;
        creaturesList.add(Mir);
        for (Creatures cre :
                creaturesList) {
            cre.isReading = false;
        }
        return  creaturesList;
        //creaturesList.get(0).room.addCreatures(Mir);
        //return  Mir;
    }
}
