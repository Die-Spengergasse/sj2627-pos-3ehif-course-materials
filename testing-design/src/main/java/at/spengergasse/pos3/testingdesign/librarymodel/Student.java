package at.spengergasse.pos3.testingdesign.librarymodel;

public class Student extends Member {

    public Student(String name) {
        super(name);
    }

    @Override
    public int maxLoans() {
        throw new UnsupportedOperationException("TODO");
    }
}
