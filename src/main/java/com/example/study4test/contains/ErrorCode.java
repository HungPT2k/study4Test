package com.example.study4test.contains;

public class ErrorCode {
    public static class Code {
        public static final String SUCCESS = "00";
        public static final String LOGIN_INVALID = "01";
        public static final String USER_INACTIVE = "02";
        public static final String NOT_FOUND = "03";
        public static final String ALREADY_EXISTS = "04";
        public static final String FAIL = "05";
    }

    public static class Type {
        public static final String LOGIN_INVALID = "LOGIN_INVALID";
        public static final String EXAM_EROS ="EXAM_EROS";
        public static final String FILE_EROS ="FILE_EROS";
        public static final String SUCCESS = "SUCCESS";
        public static final String ASSIGMENT_EROS = "ASSIGMENT_EROS";
        public static final String UPDATE_FAIL = "INPUT_EROS";
    }

    public static class Message {
        public static final String LOGIN_INVALID = "Username or password invalid.";
        public static final String USER_INACTIVE = "User inactive.";
        public static final String SUCCESS = "SUCCESS.";
        public static final String ALREADY_EXISTS = "%s already exists.";
        public static final String NOT_EXISTS = "%s not exists.";
        public static final String UPDATE_FAIL ="update fail";
        public static final String POST_FILE_FAIL ="Eros read file";
    }
}
