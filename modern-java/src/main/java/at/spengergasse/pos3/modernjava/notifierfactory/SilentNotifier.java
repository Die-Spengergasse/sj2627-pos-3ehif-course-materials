package at.spengergasse.pos3.modernjava.notifierfactory;

final class SilentNotifier implements Notifier {

    static final SilentNotifier INSTANCE = new SilentNotifier();

    private SilentNotifier() {
    }

    @Override
    public String send(String message) {
        return "";
    }
}
