import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;
import java.io.*;

public class IListenerClass implements ITestListener {

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext arg0) {
        File file = new File("ListenerOutput.txt");
        if(file.delete()){
            System.out.println("File Deleted");
        }
        else{
            System.out.println("File Not Deleted");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult Result) {

        System.out.println("Test Failed");
        try {
            File f1 = new File("ListenerOutput.txt");
            try(BufferedWriter fwrite = new BufferedWriter(new FileWriter(f1,true))){
                fwrite.write("Test Failed!\n" + Result + "\n\n"); // do something with the file we've opened
            }
            catch(IOException e){
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(Result);

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult Result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        System.out.println("Test Passed");
        try {
            File f1 = new File("ListenerOutput.txt");
            try(BufferedWriter fwrite = new BufferedWriter(new FileWriter(f1,true))){
                fwrite.write("Test Passed!\n" + Result + "\n\n"); // do something with the file we've opened
            }
            catch(IOException e){
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(Result);
    }
}
