package at.spengergasse.pos3.testingdesign.librarymodel;

public class Book implements Borrowable {

    Book(String title, String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    public String title() {
        throw new UnsupportedOperationException("TODO");
    }

    public String isbn() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean isBorrowed() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void borrow() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void giveBack() {
        throw new UnsupportedOperationException("TODO");
    }
}
