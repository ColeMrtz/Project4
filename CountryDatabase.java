import java.io.*; 
import java.util.*; 
public class CountryDatabase{
    private ArrayList<Country> db = new ArrayList<Country>();

    public CountryDatabase(){
        readCountriesData("countries.csv");
    }

    public void readCountriesData(String filename){
        try{
            db.clear();
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");
            while(scnr.hasNext()){
                String name = scnr.next();
                String continent = scnr.next();
                String area = scnr.next();
                String pop = scnr.next();
                String gdp = scnr.next();
                String capital = scnr.next();
                Country read = new Country(name, continent,
                        Integer.parseInt(area), Integer.parseInt(pop),
                        Double.parseDouble(gdp), capital);
                db.add(read);
            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }
    }
    
    public Country rtn(int x){
        return db.get(x);
    }
    
    public int countAllCountries(){
        return db.size();
    }

    public ArrayList <Country> getAllCountries(){
        return db;
    }

    public Country highestGdp(String continent){
        int i = 0;
        double kept = 0;
        int rtn = 0;
        while(i < db.size()){
            if(kept < db.get(i).getGDP() && continent.equalsIgnoreCase(db.get(i).getContinent()) == true){
                kept = db.get(i).getGDP();
                rtn = i;
            }
            i++;
        }
        return db.get(rtn);
    }

    public Country smallestArea(String continent){
        int i = 0;
        int kept = 2140000000;
        int rtn = 0;
        while(i < db.size()){
            if(kept > db.get(i).getArea() && 
            continent.equalsIgnoreCase(db.get(i).getContinent()) == true){
                kept = db.get(i).getArea();
                rtn = i;
            }
            i++;
        }
        return db.get(rtn);
    }

    public String searchForCapital(String countryName){
        int j = 0;
        int i = 0;
        String name = "";
        try{
            while(j == 0){
                if(countryName.equalsIgnoreCase(db.get(i).getName()) == true){
                    name = db.get(i).getCapital();
                    j++;
                }
                else{
                    i++;
                }
            }
        }
        catch(Exception e){
            return null;
        }
        return "Capital of " + db.get(i).getName() + ": " + name;
    }

    public Country findCountryDetails(String countryName){
        int j = 0;
        int i = 0;
        Country rtn = new Country();
        try{
            while(j == 0){
                if(countryName.equalsIgnoreCase(db.get(i).getName()) == true){
                    rtn = db.get(i);
                    j++;
                }
                else{
                    i++;
                }
            }
        }
        catch(Exception e){
            return null;
        }
        return rtn;
    }

    public ArrayList <Country> searchCountriesInContinent(String cont){
        int i = 0;
        ArrayList<Country> rtn = new ArrayList<Country>();
        while(i < db.size()){
            if(cont.equalsIgnoreCase(db.get(i).getContinent()) == true){
                rtn.add(db.get(i));
            }
            i++;
        }
        Collections.sort(rtn);
        return rtn;
    }

    public ArrayList <Country> searchCountriesWithPopulation(int pop){
        int i = 0;
        ArrayList<Country> rtn = new ArrayList<Country>();
        while(i < db.size()){
            if(pop > db.get(i).getPop()){
                rtn.add(db.get(i));
            }
            i++;
        }
        return rtn;
    }
    
    public ArrayList <Country> topTenGdpCountries(String continent){
        ArrayList<Country> rtn = new ArrayList<Country>();
        ArrayList<Country> tmp = new ArrayList<Country>();
        tmp = searchCountriesInContinent(continent);
        int i = 0;
        while(i < 10){
            rtn.add(tmp.get(i));
            i++;
        }
        return rtn;
    }
}
