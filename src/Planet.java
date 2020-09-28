public class Planet {
    String name;
    Planet(String n){
        name = n;
    }

    public Planet createPlanet(String name){
        return new Planet(name);
    }

    public void delPlanet(Planet planet){
        planet.name = null;
    }
}
