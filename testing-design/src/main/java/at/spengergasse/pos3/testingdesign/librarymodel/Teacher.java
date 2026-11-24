package at.spengergasse.pos3.testingdesign.librarymodel;

public class Teacher extends Member {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public int maxLoans() {
        throw new UnsupportedOperationException("TODO");
    }
}
