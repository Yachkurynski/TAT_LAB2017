package ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

/**
 * Parent class for all available commands, which can to perform ftp client.
 */
public abstract class FtpClientCommand {
  public static final String NO_CONNECTION_MSG = "There is no connection to server!";
  public static final String FILE_IDENTIFIER = "f: ";
  public static final String DIRECTORY_IDENTIFIER = "d: ";

  /**
   * Does some command.
   */
  public abstract void doCommand(FTPClient ftpClient) throws IOException;

  /**
   * Prints folder's content for better orientation in it.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  protected void printContent(FTPClient ftpClient) throws IOException {
    if (ftpClient.isConnected()) {
      FTPFile[] content = ftpClient.listFiles();

      for (FTPFile file : content) {
        String prefix = file.isFile() ? FILE_IDENTIFIER : DIRECTORY_IDENTIFIER;
        System.out.println(prefix + file.getName());
      }
    } else {
      throw new IOException(NO_CONNECTION_MSG);
    }
  }
}
