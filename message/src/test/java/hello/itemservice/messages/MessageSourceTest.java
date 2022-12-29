package hello.itemservice.messages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()-> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefultMessage(){
        String message = ms.getMessage("no_code", null, "기본메시지", null);
        assertThat(message).isEqualTo("기본메시지");
    }

    @Test
    void argumentMessage(){
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang(){
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");

        String result2 = ms.getMessage("hello", null, Locale.KOREA);
        assertThat(result2).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        String result = ms.getMessage("hello", null, Locale.ENGLISH);
        assertThat(result).isEqualTo("hello");
    }
}
