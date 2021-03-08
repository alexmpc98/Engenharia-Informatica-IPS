package patterns.singleton;

import exceptions.LoggerException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * Final class that will get the loggers
 */
public final class Logger {
    private static Logger instance;
    private static final String LOGFILE = "logs.txt";
    private static PrintStream printStream;

    /**
     * Method that will inicialize the logger
     */
    private Logger() {
        connect();
    }

    /**
     * Method that will instanciate the logger
     *
     * @return the logger to write the datas
     */
    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    /**
     * Method that will return true if the connection will be successful otherwise false
     *
     * @return true or false depending of the connection
     * @throws LoggerException log exception when writing to file
     */
    private boolean connect() throws LoggerException {
        if (printStream == null) {
            try {
                printStream = new PrintStream(new FileOutputStream(LOGFILE), true);
            } catch (FileNotFoundException ex) {
                printStream = null;
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * Method that will reset the logger
     *
     * @throws LoggerException log exception when writing to file
     */
    public void resetLog() throws LoggerException {
        checkPrintStream();
        printStream.print("");
    }

    /**
     * Method that will receive a string and will write it in the log
     *
     * @param str message to write in the log
     * @throws LoggerException log exception when writing to file
     */
    public void writeToLog(String str) throws LoggerException {
        checkPrintStream();
        printStream.println(new Date().toString() + " | " + str);
    }

    /**
     * Method that will check the print if it the PrintStream is null will throw a exception
     *
     * @throws LoggerException log exception when writing to file
     */
    public void checkPrintStream() throws LoggerException {
        if (printStream == null) {
            throw new LoggerException("Connection fail");
        }
    }
}
