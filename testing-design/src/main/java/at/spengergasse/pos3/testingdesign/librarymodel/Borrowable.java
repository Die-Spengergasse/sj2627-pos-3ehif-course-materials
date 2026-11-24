package at.spengergasse.pos3.testingdesign.librarymodel;

public interface Borrowable {

    boolean isBorrowed();

    void borrow();

    void giveBack();
}
