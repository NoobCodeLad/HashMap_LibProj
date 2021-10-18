import java.util.HashMap;
import java.util.Objects;

public class Library
{

    String bookName;
    String author;
    Library()
    {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.bookName);
        hash = 83 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Library other = (Library) obj;
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }


    Library(String bookName,String author)
    {
        this.bookName=bookName;
        this.author=author;
    }
    static HashMap<Integer,Library> books= new HashMap<>();
    static HashMap<Integer,Library> bookcopy= new HashMap<>();
    static HashMap<Integer, Integer> borrowers= new HashMap<>();
    int[] borrow = new int[1000];
    String[] book = new String[999];
    public HashMap<Integer,Library> createLibraryMap(String booksInLibrary)
    {   String S=booksInLibrary.replace('|','/');
        String[] s =S.split("/");
        int i =0;
        for(String x: s) {
            String[] q =x.split(",");
            Library li = new Library(q[1],q[2]);
            books.put(Integer.parseInt(q[0]),li);
            book[i] = q[0];
            book[i+1] = q[1];
            book[i+2] = q[2];
            i=i+3;
        }
        bookcopy .putAll(books);
        return books;
    }

    public HashMap<Integer,Integer> createUserMap(String borrowedUsers)
    {   String S=borrowedUsers.replace('|','/');
        String[] s =S.split("/");
        int i=0;
        for(String x: s) {
            String[] q=x.split(",");
            books.remove(Integer.parseInt(q[0]));
            borrowers.put(Integer.parseInt(q[0]),Integer.parseInt(q[1]));
            borrow[i] = Integer.parseInt(q[1]);
            borrow[i+1] = Integer.parseInt(q[0]);
            i=i+2;
        }
        return borrowers;
    }

    public String getQuery(String booksInLibrary,String borrowedUsers,String query)
    {
        createLibraryMap(booksInLibrary);
        createUserMap(borrowedUsers);
        String[]queryparts = query.split(",");
        String s01 = "";

        switch (queryparts[0]) {
            case "1":
                int id = Integer.parseInt(queryparts[1]);
                if (books.containsKey(id)) {
                    Library l1 = books.get(id);
                    s01 = "It is available\n"+l1.author+"\n";
                } else {
                    s01 = "No stock\nIt is owned by " + borrowers.get(id)+"\n";
                }

                break;
            case "2":
                int uid = Integer.parseInt(queryparts[1]);      String temp;
                for (int i = 0;  i < ((queryparts[1].length()) * 2);  i++) {
                    if (uid == borrow[i]) {
                        int bid = borrow[i + 1];
                        if (bookcopy.containsKey(bid)) {
                            Library l2 = bookcopy.get(bid);
                            temp = bid + " " + l2.bookName + "\n";
                            s01 = temp + s01;
                        }
                    }
                }
                break;
            case "3":
                int count1 = 0, count2 = 0;
                for (Library x : books.values()) {
                    String s0 = x.bookName;
                    if (s0.equalsIgnoreCase(queryparts[1]))
                        ++count1;
                }
                for (Library x : bookcopy.values()) {
                    String s0 = x.bookName;
                    if (queryparts[1].equalsIgnoreCase(s0))
                        ++count2;
                }
                int bor = count2 - count1;
                s01 = bor + " out\n" + count1 + " in\n";
                break;
            case "4":
                for (Library x : bookcopy.values()) {
                    String s0 = x.bookName;
                    String s1 = x.author;
                    if (s1.equals(queryparts[1])) {
                        s01=s01 + s0 + "\n";
                    }
                }
                break;
            case "5":
                for (Library x : bookcopy.values()) {
                    String s0 = x.bookName;
                    String s = queryparts[1].toLowerCase();
                    if (s0.toLowerCase().contains(s)) {
                        for (int i = 0;  i < ((bookcopy.size()) * 3);  i++) {
                            if (book[i].equalsIgnoreCase(s0))
                                s01 = s01 + (book[i-1] + " " + s0) + "\n";
                        }
                    }
                }
                break;
        }
        return s01;
    }
}


