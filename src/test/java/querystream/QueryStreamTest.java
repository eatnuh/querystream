package querystream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static querystream.QueryStream.*;

@ExtendWith(MockitoExtension.class)
class QueryStreamTest {

    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    @Test
    void whenAskTrueThenTrueTest() {
        assertTrue(ask(TRUE).isTrue());
    }

    @Test
    void whenAskTrueThenIsTrueTest() {
        assertTrue(ask(TRUE).is(TRUE));
    }

    @Test
    void whenAskFalseThenFalseTest() {
        assertTrue(ask(FALSE).isFalse());
    }

    @Test
    void whenAskNotTrueThenFalseTest() {
        assertTrue(askNot(TRUE).isFalse());
    }

    @Test
    void whenAskNotFalseThenFalseTest() {
        assertTrue(askNot(FALSE).isTrue());
    }

    @Test
    void whenAskTrueAndFalseThenFalseTest() {
        assertTrue(ask(TRUE).and(FALSE).isFalse());
    }

    @Test
    void whenAskTrueAndNotTrueThenFalseTest() {
        assertTrue(ask(TRUE).andNot(TRUE).isFalse());
    }

    @Test
    void whenAskTrueOrFalseThenTrueTest() {
        assertTrue(ask(TRUE).or(FALSE).isTrue());
    }

    @Test
    void whenAskFalseOrNotTrueThenFalseTest() {
        assertTrue(ask(FALSE).orNot(TRUE).isFalse());
    }

    @Test
    void whenReAskTrueThenTrueTest() {
        assertTrue(ask(FALSE).reAsk(TRUE).isTrue());
    }

    @Test
    void whenReAskNotTrueThenFalseTest() {
        assertTrue(ask(TRUE).reAskNot(TRUE).isFalse());
    }

    @Test
    void whenAskTrueThenThenRunTest() {
        //given
        Runnable mock = mock(Runnable.class);
        //when
        ask(TRUE).then(mock);
        //then
        verify(mock, only()).run();
    }

    @Test
    void whenAskFalseThenThenDoesNotRunTest() {
        //given
        Runnable mock = mock(Runnable.class);
        //when
        ask(FALSE).then(mock);
        //then
        verify(mock, never()).run();
    }

    @Test
    void whenAskFalseThenExecuteRunnableOrElseTest() {
        //given
        Runnable mock = mock(Runnable.class);
        //when
        ask(FALSE).orElse(mock);
        //then
        verify(mock, only()).run();
    }

    @Test
    void whenAskTrueThenNotExecuteRunnableOrElseTest() {
        //given
        Runnable mock = mock(Runnable.class);
        //when
        ask(TRUE).orElse(mock);
        //then
        verify(mock, never()).run();
    }

    @Test
    void whenAskTrueThenThenThrowThrows() {
        assertThrows(Throwable.class, () -> ask(TRUE).thenThrow(Throwable::new));
    }

    @Test
    void whenAskFalseThenThenThrowNotThrows() {
        assertDoesNotThrow(() -> ask(FALSE).thenThrow(Throwable::new));
    }

    @Test
    void whenAskFalseThenOrElseThrowThrows() {
        assertThrows(Throwable.class, () -> ask(FALSE).orElseThrow(Throwable::new));
    }

    @Test
    void whenAskTrueThenOrElseThrowNotThrows() {
        assertDoesNotThrow(() -> ask(TRUE).orElseThrow(Throwable::new));
    }

    @Test
    void thenReturnTest() {
        QueryStream mock = mock(QueryStream.class);
        mock.thenReturn(any());
        verify(mock, only()).thenReturn(any());
    }

    @Test
    void orElseReturnTest() {
        QueryStream mock = mock(QueryStream.class);
        mock.orElseReturn(any());
        verify(mock, only()).orElseReturn(any());
    }
}