public class Maintest {

    public static void main(String[] args) {

        String booksInLibrary = "1001,Adventures of Sherlock Holmes,Sir Arthur Conan Doyle|" +
                "1002,Adventures of Tom Sawyer,The Mark Twain|1003,Alice in the Wonderland,Lewis Carroll|" +
                "1004,All’s Well that Ends well,William Shakespeare|1005,As you like it,William Shakespeare|" +
                "1006,Anand Math,Bankim Chandra Chatterjee";
        String borrowedUsers = "1006,101|1003,102|1001,101";
        Library l = new Library();
        l.createLibraryMap(booksInLibrary);
        l.createUserMap(borrowedUsers);
        String s = l.getQuery(booksInLibrary,borrowedUsers,"5,o");
        System.out.print(s);
    }
}
