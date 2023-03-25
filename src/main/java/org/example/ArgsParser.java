package org.example;

import java.security.InvalidParameterException;

public class ArgsParser {
    public String parseInputFilePath(String[] args) {
        if (args.length != 1) {
            throw new InvalidParameterException("please provide one file name");
        }
        return args[0].trim();
    }
}
