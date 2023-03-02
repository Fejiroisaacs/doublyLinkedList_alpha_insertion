<h1 style="text-align: center;">Lab 4: Baby Names</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: Thursday, Mar. 16th at 11:59pm </h3>


# Overview

In  this  assignment,  we’ll  be  exploring  linked  lists  and  more  complex  custom-designed
classes.  Your  task  will  be to  design  a  linked  list  that manages  annual statistics
about baby names in the United States,  allow it to take specific command line inputs,
and print out statistics based on the input.

**OBS**: This is a lengthy lab. Make sure to start working on it as soon as possible.

# The Input
We’ll be taking input from files containing lines in the following format:

``rank, male-name, male-number, female-name, female-number``

where the comma-separated fields have the following meanings:
- `rank`: the ranking of the names in this file;
- `male-name`: a male name of this rank;
- `male-number`: number of males with this name;
- `female-name`: a female name of this rank;
- `female-number`: number of females with this name.

This  is  the  format  of  database  files  obtained  from  the  U.S.  Social  Security  Administration of the top
1000 registered baby names. Each line begins with a rank, followed by the male name at that rank,  followed by the
number of males with that name, etc.  Here is an example showing data from the year 2002:

```
1,Jacob,30568,Emily,24463
2,Michael,28246,Madison,21773
3,Joshua,25986,Hannah,18819
4,Matthew,25151,Emma,16538
5,Ethan,22108,Alexis,15636
...
996,Ean,157,Johana,221
997,Jovanni,157,Juana,221
998,Alton,156,Juanita,221
999,Gerard,156,Katerina,221
1000,Keandre,156,Amiya,220
```

As  you  can  see  from  the  above,  in  2002,  there  were  30,568  male  babies  named Jacob  and  24,463  babies
named  Emily,  making  them  the  most  popular  names used in that year.  Similarly, going down the list, we see that
there were 220 new-born females named Amiya, making it the 1000th most popular female baby name.

The entire data set contains a file for each year from 1990 to 2017, named `names1990.csv`, ...  ,`names2017.csv`,
respectively.

> One of the end goals of this lab is to print out the statistics of a given (input) name for given (input)
files. This will be further specified in this document, but for now just understand that the program will eventually
give output for specific names and files that are given.

# Classes to be Implemented

In this lab, you will be building two Doubly Linked Lists to store the baby names found in the given files, one for
the male names and one for the female names. The linked lists should be kept in alphabetically sorted order by name.

## 1. ` NameData` class
This class represents the data that pertains to one name, i.e., the name itself (a "Mary", for example) and the number of
occurrences (2000, for example) of that name in the consulted CSV files. Make sure to add the appropriate
getter and setter methods. Also add a `toString()` and a `compareTo()` method. Finally, add a method that increments
its number of occurrences by a value.

## 2. ` Node` class
This is the Node class that will be used in your Linked List data structure. The data (the element) in it should be of
type `NameData`. **This class can me made generic, but you don't need to do it in this lab.**

## 3. ` DoublyLinkedList` class
This is the Linked List class that will carry nodes holding `NameData` objects. It can be made using the code provided
in the text book. **This class can me made generic, but you don't need to do it in this lab.** Along with the classical
methods in Doubly Linked Lists, you should add four methods to this class:
1. `insertAlpha(NameData inputName)`: Here you will insert a node in the Doubly Linked List with its element `NameData`
   as `inputName`. The nodes should be inserted in ways that keep the **alphabetical order** of the names in their `NameData`.
2. `fetch(String name)`: This method returns the `NameData` whose name attribute is the same as `name`. If there is no
   `NameData` with that name, it returns `null`.
3. `findPosition(String name)`: This method finds the position in the Doubly Linked List of the name `name`. It returns
   -1 if that name is not in the list.
4. `toString()`.


## 4. ``Main`` Class
In this class, you'll only implement the main method (`public static void main(String[] args)`). Your main method should:
- **Read the command line arguments**, which should come in three possible ways (_assume they will always follow this
  format_):
    - "-f" or "-m" (without the quotation marks): which means that there is a female ("-f") or male ("-m") baby name
      coming in the next argument,
    - A baby name: it can be any name, present or not in the databases,
    - A database filename: "names1990.csv", "names2001.csv", "names2011.csv" (without the quotation marks), etc.

  _Suggestion_: create an ArrayList to hold the queried male names, another to hold the female ones and a
  third one to hold the filenames.
- **Create two Doubly Linked Lists**, one for the nodes corresponding to the male names and another one for the nodes for the female names.
- For each input file, you should **read the names in there** using a `CSVReader` (more on how to use it in the notes) and
  populate the Doubly Linked Lists, adding each new name in the list in **alphabetical order** or updating the number of
  occurrences of an already existing name.
- **Print the required statistics** of the queried names according to the input databases. The statistics we require you to
  print for each queried name are: (1) the number of occurrences of that name in all input databases, collectively;
  (2) the percentage that that name occurs considering all name occurrences in all input databases; (3) the position of
  that word in its respective Linked List. Some examples of outputs are shown in the section below.
- **Add the necessary try-catch statements**.
- In the output examples below, you'll also see some **special cases** you should consider (when no name or no database
  name is given as an argument).

# Output

Your program should output the number of occurrences of each of the input names in the years given by the input
datasets. For example, if a female name "x" occurred 1000 times in 1997 and 2000 times 1998, your program should display
3000 occurrences, if the user gives "-f" "x", "names1997.csv" and "names1998.csv" as arguments to your program.
The program should also display the total number of names overall, the percentage of the desired names in that total
and the position of each desired name in the final Doubly Linked List.

In the following, you can find some examples of expected outputs according to their respective arguments
you give to `java Main`. On IntelliJ you can add these arguments to the execution of your `Main` class in
Run > Edit Configuration > Main > Program Arguments. Then, whenever you run `Main` those command line arguments
will be given to it.

### First example
Command line argument:
```
-m John names2017.csv
```
Output:
```
John: 9434 occurrences in 1529670 names (0.006167%)
Position of John in the Linked List: 507
```


### Second example
Command line argument:
```
-f Mary -f Anna -m John -m Aaron names1990.csv names1991.csv names1992.csv names1993.csv names1994.csv names1995.csv 
names1996.csv names1997.csv names1998.csv names1999.csv names2000.csv names2001.csv names2002.csv names2003.csv 
names2004.csv names2005.csv names2006.csv names2007.csv names2008.csv names2009.csv names2010.csv names2011.csv
names2012.csv names2013.csv names2014.csv names2015.csv names2016.csv names2017.csv
```
Output:
```
Mary: 142630 occurrences in 39289517 names (0.003630%)
Position of Mary in the Linked List: 1423

Anna: 211760 occurrences in 39289517 names (0.005390%)
Position of Anna in the Linked List: 170

John: 485331 occurrences in 47747562 names (0.010165%)
Position of John in the Linked List: 903

Aaron: 273978 occurrences in 47747562 names (0.005738%)
Position of Aaron in the Linked List: 2
```

### Third example
Command line argument:
```
-m John
```
Output (some details below may be different depending on your implementation):
```
Exception in thread "main" java.lang.IllegalArgumentException: No data set to search on!
	at Main.main(Main.java:33)
```

### Fourth example
Command line argument:
```
names1997.csv
```
Output (some details below may be different depending on your implementation):
```
Exception in thread "main" java.lang.IllegalArgumentException: No names to look up!
	at Main.main(Main.java:37)
```

### Fifth example
Command line argument:
```
-m X names1997.csv
```
Output (some details below may be different depending on your implementation):
```
Name X not listed.
```


# Notes for Lab 4

- Use `.equals()` when comparing strings.  For this lab, you do not need to overwrite the `.equals()` method for lists
  or nodes, you can use `String` to compare two names directly.
- Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to learn about
  the`.compareTo()` method for strings in Java.
- In  Java,  dividing  two  integers  results  in  an  integer  (rounded  down).   To achieve a double or fractional
  result, first cast one of the integers to a double.
- For all classes you create, add a `.toString()` method to help with debugging.
- You are given a `test.csv` file, which is a shortened version of one of the longer files. It will also be helpful to use
  it to start off your code, to make sure you’re sorting the names correctly.
- To  round  your  results  to  6  decimal  places,  you  can  use  string  formatting (similar to Python).  
  For example:
```Java
double fraction = 0.456622723;
System.out.println(String.format("%.6g%n", fraction));
```
- Similarly to `CSVReaderHeaderAware`, `CSVReader` can be used as follows, with `file` as the name of the CVS files you'd
  like to open (as their names suggest, `CSVReader` assumes that the file does not have a header):
```java
CSVReader reader = new CSVReader(new FileReader(file));
ArrayList<String[]> myEntries = new ArrayList<String[]>(reader.readAll());
reader.close();
```

# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

-   Remember to use Javadoc style conventions while commenting.
    > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
    > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
    from class as a reference for what your style should look like.

-   Write your variables in CamelCase, and be sure to indent properly.

-   Don't forget to add comments! Include...
    > - A document header (name, date, program description)
    > - Comments at the top of each function describing what the
        >   function does
    > - Comments within the functions as needed, to describe
        >   non-obvious steps

-   When working on larger programs, test as you go! That is, write
    a little of code, then make sure it works before building on it.
-   The same goes for larger datasets.  Make a small test dataset and make sure your code works before trying it on the full dataset.

-   Focus on correctness rather than efficiency.



