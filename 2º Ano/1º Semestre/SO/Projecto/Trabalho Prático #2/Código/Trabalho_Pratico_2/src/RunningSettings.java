import java.util.ArrayList;

public class RunningSettings {
    int threadNumber;
    String fileName;

    public void setRunningSettings(int threadNumber, String fileName) {
        this.threadNumber = threadNumber;
        this.fileName = fileName;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
