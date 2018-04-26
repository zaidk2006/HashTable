
/* ****************************************************************************
 * Name: Zaid Khoury
 * Date: 12/8/2015 
 * Project Info: CS 182 Lab Project 6
 * Description: This class creates the constructor of the HashTable. I create
 * a few methods to get the information of the crashed items with the help of
 * vectors.
 * *****************************************************************************
 */
import java.util.Vector;
import java.io.*;

class HashTable {

    private DataItem[] hashArray;
    private int arraySize;
    private DataItem nonItem; // for deleted items
    private Vector<String> crashName;
    private Vector<String> crashShould;
    private Vector<String> crashAt;
    private int crashNumber = 0;

    HashTable(int size) // constructor
    {
        crashName = new Vector<String>();
        crashShould = new Vector<String>();
        crashAt = new Vector<String>();
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem("-1");
    }
// ------------------------------------------------------------- 

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
            System.out.println("");
        }

    }

    public int hashFunc1(int key) {
        return key % arraySize;
    }

    public int hashFunc2(int key) {
// non-zero, less than array size, different from hF1
// array size must be relatively prime to 5, 4, 3, and 2 
        return 5 - key % 5;
    }

    public int hashFunc3(String key) {
        int hashVal = 0;
        for (int j = 0; j < key.length(); j++) {
            int letter = key.charAt(j) - 96;
            hashVal = (hashVal * 27 + letter) % arraySize;
        }
        return hashVal;

    }

    public void insert(String key) {
        int hashVal = hashFunc3(key); // hash the key 
        int stepSize = 1; // get step size
        boolean crash = false;
        // until empty cell or -1
        while (hashArray[hashVal] != null && !hashArray[hashVal].getDataItem().equals("-1")) {
            crash = true;
            hashVal += stepSize;
            if (hashVal == hashArray.length) {
                hashVal = 0;
            }
        }
        if (crash) {
            crashNumber++;
            crashName.add(key);
            crashShould.add(Integer.toString(hashFunc3(key)));
            crashAt.add(Integer.toString(hashVal));
        }
        DataItem temp = new DataItem(key);
        hashArray[hashVal] = temp;

    }

    public int crashes() {
        return crashNumber;
    }

    public Vector<String> itemName() {
        return crashName;
    }

    public Vector<String> itemShould() {
        return crashShould;
    }

    public Vector<String> itemAt() {
        return crashAt;
    }

    public DataItem delete(int key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal]
                        = nonItem;
                return temp;
            }

            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    public DataItem find(int key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }

        return null;

    }
}

