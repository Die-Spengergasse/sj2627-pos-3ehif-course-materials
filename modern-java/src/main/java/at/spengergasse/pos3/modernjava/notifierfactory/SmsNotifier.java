package at.spengergasse.pos3.modernjava.notifierfactory;

record SmsNotifier(String number) implements Notifier {

    @Override
    public String send(String message) {
        return "SMS to " + number + ": " + message;
    }
}
