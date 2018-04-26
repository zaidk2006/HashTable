
/**
 *  * **************************************************************************
 * Name: Zaid Khoury Date: 12/ 8/2015 Project Info: CS 182 Lab Project 6
 * Description: This is project implements the Hashtable data structure. I use
 * the the Hash Functions to convert the ParseInted string that is inserted on
 * the GUI Screen to a code that does the modulous calculation to store the
 * string into the data structure.
 * *****************************************************************************
 *
 * *************************************************************
 *
 * Project Number 6 - Comp Sci 182 - Data Structures Start Code - Build your
 * program starting with this code
 *
 * Copyright 2005 Christopher C. Ferguson This code may only be used with the
 * permission of Christopher C. Ferguson
 *
 **************************************************************
 */
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrimeHash extends JFrame implements ActionListener {

    private static int win_xpos = 0, win_ypos = 0;// place window here
    private static int win_xsize = 1400, win_ysize = 800;//  window size

// Private state variables.
    private Font boldfont = new Font("TimesRoman", Font.BOLD, 22);
    private Font plainfont = new Font("TimesRoman", Font.PLAIN, 14);
    private boolean error = false;
    private JButton hashbutton, exitbutton;
    private JPanel northPanel;
    private MyJPanel centerPanel;
    private JTextField hashsizefield;
    private String thetext = "101";
    private HashTable hashTable;

////////////MAIN////////////////////////
    String[] names = {"fred", "barney", "tom", "jerry", "larry", "moe", "curly",
        "betty", "wilma", "bart", "homer", "marge", "maggie", "lisa",
        "pebbles", "bambam", "smithers", "burns", "milhouse", "george", "astro",
        "dino", "mickey", "minnie", "pluto", "goofy", "donald", "huey",
        "louie", "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy",
        "dopey", "sleepy", "bambi", "belle", "gaston", "tarzan", "jane",
        "simba", "scar", "mufasa", "ariel", "flounder", "bugs", "daffy",
        "elmer", "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby",
        "peggy", "spot", "pongo", "perdy", "buzz", "potatohead", "woody",
        "chuckie", "tommy", "phil", "lil", "angelica", "dill", "spike",
        "pepe", "speedy", "yosemite", "sam", "tweety", "sylvester", "granny",
        "spiderman", "batman", "superman", "supergirl", "robin", "jimmy", "olsen",
        "thing", "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman"};

    public static void main(String[] args) {
        HashTable table = new HashTable(101);
        PrimeHash tpo = new PrimeHash();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

////////////CONSTRUCTOR/////////////////////
    public PrimeHash() {
        northPanel = new JPanel();
        northPanel.add(new Label("Enter a hashtable size: "));
        hashsizefield = new JTextField(thetext, 20);
        northPanel.add(hashsizefield);
        hashbutton = new JButton("CreateHash");
        northPanel.add(hashbutton);
        hashbutton.addActionListener(this);
        exitbutton = new JButton("Exit");
        northPanel.add(exitbutton);
        exitbutton.addActionListener(this);
        getContentPane().add("North", northPanel);
        centerPanel = new MyJPanel();
        getContentPane().add("Center", centerPanel);
        setSize(win_xsize, win_ysize);
        setLocation(win_xpos, win_ypos);
        setVisible(true);
    }

////////////BUTTON CLICKS ///////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitbutton) {
            dispose();
            System.exit(0);
        }

        if (e.getSource() == hashbutton) {

            error = false;
            try {
                thetext = hashsizefield.getText();
                int size = Integer.parseInt(thetext);
                hashTable = new HashTable(size);
                for (int i = 0; i < names.length; i++) {
                    System.out.println(hashTable.hashFunc3(names[i]));
                    hashTable.insert(names[i]);
                }
                repaint();
            } catch (NumberFormatException l) {
                error = true;
                repaint();
            }
        } // end actionPerformed
    }

    class MyJPanel extends JPanel {

        ////////////    PAINT   ////////////////////////////////
        public void paintComponent(Graphics g) {

            g.setFont(plainfont);
            int xpos = 25;
            int ypos = 20;
            int x = 0;
            if (error == true) {
                g.drawString("INVALID VALUE. TRY AGAIN ", 25, 25);
                return;
            }
            if (hashTable != null) {
                Vector<String> crashName = hashTable.itemName();
                Vector<String> crashShould = hashTable.itemShould();
                Vector<String> crashAt = hashTable.itemAt();
                int crashNums = hashTable.crashes();

                if (crashNums == 0) {
                    g.drawString("THERE ARE NO CRASHES!!!", 500, 200);
                }

                for (int i = 0; i < crashName.size(); i++) {
                    x++;
                    g.drawString(x + ". Hash Crash:     " + crashName.elementAt(i), xpos, ypos * x);
//                    thetext = hashsizefield.getText();
//                    int size = Integer.parseInt(thetext);
//                    hashTable = new HashTable(size);
                    g.drawString("Should Be At: ", 225, ypos * x);
                    g.drawString("" + crashShould.elementAt(i), 320, ypos * x);
                    g.drawString("Found At: ", 400, ypos * x);
                    g.drawString("" + crashAt.elementAt(i), 480, ypos * x);
                    g.drawString("Hash Crash Amount Is : " + crashNums, 700, 10);

                }
            }
        }

    } // End Of MyJPanel

}     // End Of PrimeHash
