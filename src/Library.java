import java.util.*;

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
    static HashMap<Integer,Library> books= new HashMap<Integer, Library>();
    static HashMap<Integer,Library> bookcopy= new HashMap<Integer, Library>();
    static HashMap<Integer, Integer> borrowers=new HashMap<Integer,Integer>();

    public HashMap<Integer,Library> createLibraryMap(String booksInLibrary)
    {   String S=booksInLibrary.replace('|','/');
        String s[]=S.split("/");
        for(String x: s) {
            String q[]=x.split(",");
            Library li = new Library(q[1],q[2]);
            books.put(Integer.parseInt(q[0]),li);
        }
        bookcopy .putAll(books);
        System.out.println(bookcopy); System.out.println(books);
        return books;
    }

    public HashMap<Integer,Integer> createUserMap(String borrowedUsers)
    {   String S=borrowedUsers.replace('|','/');
        String s[]=S.split("/");
        for(String x: s) {
            String q[]=x.split(",");
            books.remove(Integer.parseInt(q[0]));
            borrowers.put(Integer.parseInt(q[0]),Integer.parseInt(q[1]));
        }
        System.out.println(borrowers);
        return borrowers;
    }

    public String getQuery(String booksInLibrary,String borrowedUsers,String query)
    {   if(query=="1") {
        int id=Integer.parseInt(borrowedUsers);
        if(books.containsKey(id)) {
            System.out.println(books.get(id));
        }else {
            System.out.println("No stock");
        }
        if(borrowers.containsKey(id)) {
            System.out.println("It is owned by "+borrowers.get(id));
        }
    }else if(query=="2") {
        int id=Integer.parseInt(borrowedUsers);
        if(borrowers.containsValue(id)) {
            System.out.printf(id+", "+ bookcopy.get(id).bookName);
        }
    }else if(query=="3") {
        int count1=0,count2=0;
        for(Integer x: books.keySet())
            {
                ++count1;
            }
        for(Integer x:borrowers.keySet())
            {
                ++count2;
            }
        System.out.println(count2+" out");
        System.out.println(count1+" in");
    }else if(query=="4") {
        for(Library x:books.values()) {
            String s0 = x.bookName;
            String s1 = x.author;
            if(s1.equals(borrowedUsers)) {
                System.out.println(s0);
            }
        }
    }else if(query=="5"){
        for(Integer x: books.keySet()) {
            Library lx = books.get(x);
            String S0=lx.bookName;
            String S1= lx.author;
            if(S0.contains(borrowedUsers)) {
                System.out.println(x+" "+S0);
            }
        }
    }

        return null;
    }

}


