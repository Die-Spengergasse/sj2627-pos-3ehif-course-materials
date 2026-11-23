package at.spengergasse.pos3.modernjava.notifierfactory;

record EmailNotifier(String address) implements Notifier {

    @Override
    public String send(String message) {
        return "EMAIL to " + address + ": " + message;
    }
}
