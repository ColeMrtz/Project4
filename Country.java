import java.text.*;
public class Country implements Comparable{
    private String country = "";
    private String continent = "";
    private int area = 0;
    private int pop = 0;
    private double gdp = 0;
    private String capital = "";

    public Country(){
        country = "United States of America";
        continent = "North America";
        capital = "Washington D.C.";
    }

    public int compareTo(Object other){
        Country c = (Country) other;
        return (int)(c.gdp - gdp);
    }

    public Country(String name, String cont, int a, int p, double g, String cap){
        country = name;
        continent = cont;
        area = a;
        pop = p;
        gdp = g;
        capital = cap;
    }

    public String getName(){
        return country;
    }

    public String getContinent(){
        return continent;
    }

    public int getArea(){
        return area;
    }

    public int getPop(){
        return pop;
    }

    public double getGDP(){
        return gdp;
    }

    public String getCapital(){
        return capital;
    }

    public void setName(String x){
        country = x;
    }

    public void setContinent(String x){
        continent = x;
    }

    public void setArea(int x){
        area = x;
    }

    public void setPop(int x){
        pop = x;
    }

    public void setGDP(double x){
        gdp = x;
    }

    public void setCapital(String x){
        capital = x;
    }

    public String toString(){
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double dispGDPNum = gdp / 1000000000;
        double perCapNum = gdp / pop;
        String dispGDP = decimalFormat.format(dispGDPNum);
        String perCap = decimalFormat.format(perCapNum);
        return "\nCountry Name:\t" + getName() + 
                    "\nContinent:\t" + getContinent() +
                    "\nCapital:\t" + getCapital() +
                    "\nArea in sq km:\t" + decimalFormat.format(getArea()) +
                    "\nPopulation:\t" + decimalFormat.format (getPop() / 1000000) + " million" +
                    "\nGDP:\t" + dispGDP + " billion" +
                    "\nPerCapita GDP:\t" + perCap;
    }
}