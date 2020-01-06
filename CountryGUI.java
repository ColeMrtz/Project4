import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.IOException;

/*************************************************************
 * GUI for a Country Database class
 * 
 * @Ana Posada
 * @version March 2018
 ************************************************************/
public class CountryGUI extends JFrame implements ActionListener{
    // Define on object of CountryDatabas
    private CountryDatabase world;

    /** JButtons */
    private JButton smallestAreaBtn;
    private JButton highestGdpBtn;
    private JButton countriesInContinentBtn;
    private JButton topTenGDPBtn;
    private JButton populationBtn;
    private JButton capitalBtn;
    private JButton detailsBtn;

    /** JTextFields */
    private JTextField continentTextField;
    private JTextField populationTextField;
    private JTextField nameTextField;

    /** Results text area */
    private JTextArea resultsArea;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem listAllItem;
    private JMenuItem quitItem;
    private JMenuItem openItem;
    private JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        CountryGUI gui = new CountryGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Countries of the world");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor 
     ****************************************************************/    
    public CountryGUI(){
        world = new CountryDatabase();

        // setting up the GUI
        setupGui();

        // hide details of creating menus
        setupMenus();
    }

    /*****************************************************************
     * sets up the layout and the GUI
     ****************************************************************/
    private void setupGui () {
        // set the layout to GridBag       
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;  
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);  

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Searches label
        loc.gridx = 1;
        loc.gridwidth = 2;
        add(new JLabel("Searches"), loc);     

        // instantiate the JButtons 
        // we are providing a few - complete the rest
        smallestAreaBtn = new JButton ("Smallest Area");
        highestGdpBtn = new JButton ("Highest GDP");    
        topTenGDPBtn = new JButton ("Top Ten GDP");  
        countriesInContinentBtn = new JButton ("Countries in Continent");  
        populationBtn = new JButton ("Population");  
        capitalBtn = new JButton ("Capital");  
        detailsBtn = new JButton ("Detais");  

        // instantiate the JTextFields
        continentTextField = new JTextField (20);
        populationTextField = new JTextField (20);
        nameTextField = new JTextField (20);

        // adding labels and buttons
        loc = new GridBagConstraints();

        loc.anchor = GridBagConstraints.LINE_END;
        loc.insets = new Insets(5,5,5,5);
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel ("Continent"), loc);
        loc.gridx = 2;
        loc.anchor = GridBagConstraints.LINE_START;
        add(continentTextField, loc);

        loc.gridy++;
        add(smallestAreaBtn, loc);
        loc.gridy++;
        add(highestGdpBtn, loc);
        loc.gridy++;
        add(topTenGDPBtn, loc);
        loc.gridy++;
        add(countriesInContinentBtn, loc);

        // associated with the continent search options

        //adding the JLabel for population 
        loc.gridx = 1;
        loc.gridy++;
        loc.insets = new Insets(30,5,5,5);
        loc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel ("Population"), loc);

        //adding the JTextField for the population
        loc.gridx = 2;
        add(populationTextField, loc);

        //adding the JButton for the population
        loc.gridy++;
        loc.insets = new Insets(5,5,5,5);
        loc.anchor = GridBagConstraints.LINE_START;
        add(populationBtn, loc);

        loc.gridx = 1;
        loc.gridy++;
        loc.insets = new Insets(30,5,5,5);
        loc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel ("Country"), loc);

        loc.gridx = 2;
        add(nameTextField, loc);

        loc.gridy++;
        loc.insets = new Insets(5,5,5,5);
        loc.anchor = GridBagConstraints.LINE_START;
        add(capitalBtn, loc);
        loc.gridy++;
        add(detailsBtn, loc);

        // registering the listeners for the buttons
        highestGdpBtn.addActionListener(this); 
        smallestAreaBtn.addActionListener(this);
        topTenGDPBtn.addActionListener(this);
        countriesInContinentBtn.addActionListener(this);
        populationBtn.addActionListener(this);
        capitalBtn.addActionListener(this);
        detailsBtn.addActionListener(this);
    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // loads the file with the countries of the world    
        if (buttonPressed == openItem)
            openFile();

        // quit item 
        if (buttonPressed == quitItem){ 
            System.exit(1);
        }
        if (world.countAllCountries() == 0)
            JOptionPane.showMessageDialog(this, "Forgot to read the file?");
        else { 
            if (buttonPressed == countItem)
                displayCounts();

            else if (buttonPressed == listAllItem)
                displayCountries (world.getAllCountries ()); 
            else if(buttonPressed == smallestAreaBtn)
                displaySmallestArea();
            else if(buttonPressed == highestGdpBtn)
                displayHighestGdp();
            else if(buttonPressed == countriesInContinentBtn)
                displayCountriesInContinent();
            else if (buttonPressed == populationBtn)
                displayPopulation(); 
            else if(buttonPressed == capitalBtn)
                displaySearchForCapital();
            else if(buttonPressed == detailsBtn)
                displaySearchForCountry();
            else if(buttonPressed == topTenGDPBtn)
                displayTopTen();
        }
    }

    /*****************************************************************
     * displays the ArrayList passed as input parameter
     * @param - ArrayList <Country>
     ****************************************************************/ 
    private void displayCountries(ArrayList <Country> list) {
        resultsArea.setText("");
        for(Country c : list){
            resultsArea.append("\n" + c.toString());
        }
        // FIX ME: complete the code for this method. See example
        // on project description on page 8

    }

    /*****************************************************************
     * display country with smallest area
     ****************************************************************/ 
    private void displaySmallestArea (){
        if(continentTextField.getText().length() > 0)
            resultsArea.setText(world.smallestArea(continentTextField.getText()).
                toString());
        else
            JOptionPane.showMessageDialog(this, "Enter a continent");
    }

    /*****************************************************************
     * display country with highest GDP
     ****************************************************************/ 
    private void displayHighestGdp ()  {
        if (continentTextField.getText().length() > 0) 
            resultsArea.setText(world.highestGdp(continentTextField.getText()).
                toString());
        else
            JOptionPane.showMessageDialog(this, "Enter a continent");
    }

    /*****************************************************************
     * display the countries in a particular continent
     ****************************************************************/ 
    private void displayCountriesInContinent() {
        if (continentTextField.getText().length() > 0) 
            displayCountries (world.searchCountriesInContinent (continentTextField.getText()));     
        else
            JOptionPane.showMessageDialog(this, "Enter a continent");

    }

    /*****************************************************************
     * display top ten GDP countries
     ****************************************************************/ 
    private void displayTopTen () {
        if (continentTextField.getText().length() > 0) 
            displayCountries(world.topTenGdpCountries(continentTextField.getText()));
        else
            JOptionPane.showMessageDialog(this, "Enter a continent");
    }

    /*****************************************************************
     * display the capital for a particular country
     ****************************************************************/ 
    private void displaySearchForCapital ()  {
        resultsArea.setText(world.searchForCapital(nameTextField.getText()));
    }

    /*****************************************************************
     * display the facts about a country
     ****************************************************************/ 
    private void displaySearchForCountry ()  {
        DecimalFormat fmt = new DecimalFormat ("###,###,###,###");
        if (nameTextField.getText().length() > 0){
            Country country = world.findCountryDetails (nameTextField.getText());
            if (country != null){
                resultsArea.setText ("\nCountry Name:\t" + country.getName() + 
                    "\nContinent:\t" + country.getContinent() +
                    "\nCapital:\t" + country.getCapital() +
                    "\nArea in sq km:\t" + fmt.format(country.getArea()) +
                    "\nPopulation:\t" + fmt.format (country.getPop() / 1000000) + " million" +
                    "\nGDP:\t" + fmt.format (country.getGDP() / 1000000000) + " billion" +
                    "\nPerCapita GDP:\t" + fmt.format (country.getGDP() / country.getPop()) );
            }
            else 
                resultsArea.setText ("Country not found");
        }
        else
            JOptionPane.showMessageDialog(this, "Enter a country");
    }

    /*****************************************************************
     * display counts - total number of countries
     ****************************************************************/ 
    private void displayCounts () {
        resultsArea.setText("Total Countries in Database: " + world.countAllCountries());
    }

    /*****************************************************************
     * display countries with a population greater than a specific value
     ****************************************************************/ 
    private void displayPopulation () {
        try {
            int people = Integer.parseInt(populationTextField.getText());
            displayCountries (world.searchCountriesWithPopulation(people));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid number for population");
        }

    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            world.readCountriesData(filename);          
        }
    }

    /*******************************************************
     * Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Counts");
        listAllItem = new JMenuItem("List Countries");
        openItem = new JMenuItem("Open...");

        fileMenu.add(openItem);
        fileMenu.add(countItem);
        fileMenu.add(listAllItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener
        countItem.addActionListener(this); 
        openItem.addActionListener(this);
        listAllItem.addActionListener(this);
        quitItem.addActionListener(this);
    }
}