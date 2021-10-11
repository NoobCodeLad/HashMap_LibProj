public class Maintest {

    public static void main(String[] args) {
        String booksInLibrary="125,C programming,Brian W. Kernighan|"
                + "546,Java programming,James Gosling|"
                + "897, Data Structure,Adam Drozdek|"
                + "265, Data Structure,Adam Drozdek|"
                + "234,The Java Language Specification,James Gosling|";
        String borrowedUsers="125,101|897,104|546,101";
        Library l = new Library();
        l.createLibraryMap(booksInLibrary);
        l.createUserMap(borrowedUsers);
        //l.getQuery(booksInLibrary,"546","1");
        l.getQuery(booksInLibrary,"101","2");
        //l.getQuery(booksInLibrary,"Data Structures","3");
        //l.getQuery(booksInLibrary,"James Gosling","4");
        //l.getQuery(booksInLibrary,"java","5");
    }
}
