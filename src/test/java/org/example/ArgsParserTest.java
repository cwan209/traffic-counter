package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class ArgsParserTest {

    private ArgsParser subject;

    @BeforeEach
    public void setUp() {
        subject = new ArgsParser();
    }

    @Test
    void should_throw_exception_when_args_is_empty() {
        // given
        String[] args = new String[0];

        // then
        Assertions.assertThrows(InvalidParameterException.class,
                // when
                () -> subject.parseInputFilePath(args)
        );
    }
    @Test
    void should_throw_exception_when_number_of_args_is_more_than_one() {
        // given
        String[] args = new String[]{"file1", "file2"};

        // then
        Assertions.assertThrows(InvalidParameterException.class,
                // when
                () -> subject.parseInputFilePath(args)
        );
    }
    @Test
    void should_trim_and_return_the_file_path_correctly() {
        // given
        String[] args = new String[]{"  file_path   "};

        // then
        String actual = subject.parseInputFilePath(args);

        // then
        Assertions.assertEquals("file_path", actual);
    }
}
