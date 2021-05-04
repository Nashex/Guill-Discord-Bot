package utils;

public class Exceptions {

    public static class InvalidSetupException extends Exception {
        public InvalidSetupException(String message) {
            super(message);
        }
    }

}
