package at.spengergasse.pos3.testingdesign.librarymodel;

import java.util.List;

public class Member {

    protected Member(String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public String name() {
        throw new UnsupportedOperationException("TODO");
    }

    public int maxLoans() {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Book> loans() {
        throw new UnsupportedOperationException("TODO");
    }

    public void borrow(Book book) {
        throw new UnsupportedOperationException("TODO");
    }

    public void giveBack(Book book) {
        throw new UnsupportedOperationException("TODO");
    }
}
