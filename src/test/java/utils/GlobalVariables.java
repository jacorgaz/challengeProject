package utils;

public class GlobalVariables {
    private static final ThreadLocal<Boolean> executionFail = new ThreadLocal<>();

    public static boolean getFailExecutionStatus(){
        return executionFail.get();
    }

    public static void setFailExecutionStatus(boolean executionFaile){
        executionFail.set(executionFaile);
    }
}