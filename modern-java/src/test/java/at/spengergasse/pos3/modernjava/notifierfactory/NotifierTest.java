package at.spengergasse.pos3.modernjava.notifierfactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NotifierTest {

    @Test
    void emailNotifierSendsToTheAddress() {
        assertThat(Notifier.email("anna@school.at").send("Hi")).isEqualTo("EMAIL to anna@school.at: Hi");
    }

    @Test
    void smsNotifierSendsToTheNumber() {
        assertThat(Notifier.sms("+43660123").send("Hi")).isEqualTo("SMS to +43660123: Hi");
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "anna", "+43660"})
    void emailRejectsAddressesWithoutAt(String address) {
        assertThatThrownBy(() -> Notifier.email(address)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "0660123", "anna@school.at"})
    void smsRejectsNumbersWithoutPlus(String number) {
        assertThatThrownBy(() -> Notifier.sms(number)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void silentAlwaysReturnsTheSharedInstance() {
        assertThat(Notifier.silent()).isSameAs(Notifier.silent());
        assertThat(Notifier.silent()).isSameAs(SilentNotifier.INSTANCE);
        assertThat(Notifier.silent().send("Hi")).isEmpty();
    }

    @Test
    void toPicksEmailForTargetsWithAt() {
        assertThat(Notifier.to("ben@school.at")).isInstanceOf(EmailNotifier.class);
        assertThat(Notifier.to("ben@school.at").send("Yo")).isEqualTo("EMAIL to ben@school.at: Yo");
    }

    @Test
    void toPicksSmsForTargetsStartingWithPlus() {
        assertThat(Notifier.to("+4366099")).isInstanceOf(SmsNotifier.class);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "ben", "0660"})
    void toRejectsUnknownTargets(String target) {
        assertThatThrownBy(() -> Notifier.to(target)).isInstanceOf(IllegalArgumentException.class);
    }
}
