
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // making the lists
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<String> maleNames = new ArrayList<>();
        ArrayList<String> femaleNames = new ArrayList<>();
        // initializing the two DoublyLinked Lists
        DoublyLinkedList males = new DoublyLinkedList();
        DoublyLinkedList females = new DoublyLinkedList();

        try{
            // this loop reads the inputs from the commandline
            for(int i = 0; i < args.length; i++){
                if(args[i].equals("-f")){
                    femaleNames.add(args[i+1]);
                    i++; // skips the next element if there's a name
                } else if(args[i].equals("-m")){
                    maleNames.add(args[i+1]);
                    i++; // skips the next element if there's a name
                } else if(args[i].contains(".csv")){ // adds all the files to the fileNames ArrayList
                    fileNames.add(args[i]);
                } else { // throws an exception if the input is invalid. If it isn't
                    throw new Exception(args[i] + " is an invalid input/ Your inputs are in the wrong order");
                }
            }
        } catch (Exception IndexOutOfBounds){ // probably not necessary but who knows?
            throw new Exception("Index out of bounds exception");
        }


        if(fileNames.isEmpty()){ // throws an exception if there's no data to look up
            throw new IllegalArgumentException("No data set to search on");
        } else if( maleNames.isEmpty() && femaleNames.isEmpty()){ // throws an exception if both the male and female names are empty
            throw new IllegalArgumentException("No names to look up");
        } else{

            for(String fileName: fileNames){ // this for loop reads all the names from the files inputted by the user
                CSVReader reader = new CSVReader();
                FileReader input = new FileReader(fileName);
                ArrayList<String[]> myEntries = reader.read(input);
                for(String[] data: myEntries){ // adds each line as a new NameData object
                    males.insertAlpha(new NameData(data[1], Integer.parseInt(data[2])));
                    females.insertAlpha(new NameData(data[3], Integer.parseInt(data[4])));
                }
            }

            if(!femaleNames.isEmpty()){ // checks if there are any femaleNames
                int totalOccurrenceFemale = females.totalOccurrence(); // gets the total occurrence of all the female names in the LinkedList
                StringBuilder femaleSb = new StringBuilder(); // makes a StringBuilder to store the output
                for(String female: femaleNames){
                    try{ // used to avoid NullPointerException for cases where the inputted name is not in the LinkedList
                        int nameOccurrence = females.fetch(female).getOccurrence(); // gets the occurrence of the NameData we have
                        double femaleStats = (double)nameOccurrence / totalOccurrenceFemale;
                        femaleSb.append(female).append(": ").append(nameOccurrence).
                                append(" occurrences in ").append(totalOccurrenceFemale).
                                append(" names (").append(String.format("%.6f", femaleStats)).append("%)\n").
                                append("Position of ").append(female).append(" in the Linked List: ").
                                append(females.findPosition(female)).append("\n");
                    } catch (NullPointerException e){
                        femaleSb.append("Name ").append(female).append(" not listed");
                    }

                }
                System.out.println(femaleSb.toString()); // prints the results
            }

            if(!maleNames.isEmpty()){ // checks if there are any maleNames
                int totalOccurrenceMale = males.totalOccurrence();  // gets the total occurrence of all the male names in the LinkedList
                StringBuilder maleSb = new StringBuilder(); // makes a StringBuilder to store the output
                for(String male: maleNames){
                    try{ // used to avoid NullPointerException for cases where the inputted name is not in the LinkedList
                        int nameOccurrence = males.fetch(male).getOccurrence(); // gets the occurrence of the NameData we have
                        double maleStats = (double)nameOccurrence / totalOccurrenceMale;
                        maleSb.append(male).append(": ").append(nameOccurrence).
                                append(" occurrences in ").append(totalOccurrenceMale).
                                append(" names (").append(String.format("%.6f", maleStats)).append("%)\n").
                                append("Position of ").append(male).append(" in the Linked List: ").
                                append(males.findPosition(male)).append("\n");
                    } catch (NullPointerException e){
                        maleSb.append("Name ").append(male).append(" not listed");
                    }

                }
                System.out.println(maleSb.toString()); // prints the result
            }
        }





        //tests with all the files
//        for(int i = 1990; i <= 2017; i++){ // this adds the name of the files to the fileNames arrayList
//            fileNames.add("names" + i + ".csv");
//        }
//
//        for(String fileName: fileNames){
//            CSVReader reader = new CSVReader();
//            FileReader input = new FileReader(fileName);
//            ArrayList<String[]> myEntries = reader.read(input);
//            for(String[] data: myEntries){
//                males.insertAlpha(new NameData(data[1], Integer.parseInt(data[2])));
//                females.insertAlpha(new NameData(data[3], Integer.parseInt(data[4])));
//            }
//        }
//        System.out.println(males.toString());
//        System.out.println(males.getSize());
//        System.out.println(males.totalOccurrence());
        //System.out.println(females.toString());

    }
}
