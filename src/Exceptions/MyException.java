package Exceptions;

public class MyException extends Exception{
    public MyException() {
        super("Invalid Input");
    }
    public static class justNumbers extends NumberFormatException{
        public justNumbers(){
            super("You must only to request digits");
        }
    }
    public static class NegNumNotAllowed extends Exception {
        public NegNumNotAllowed(){
            super("must be number >=0");
        }
    }
}
