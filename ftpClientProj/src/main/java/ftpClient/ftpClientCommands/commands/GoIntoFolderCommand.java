package ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Allows to open some folder in the current directory.
 */
public class GoIntoFolderCommand extends FtpClientCommand {
  public static final String CURRENT_DIR_MSG = "current dir: ";
  private String folderName;

  public GoIntoFolderCommand(String folderName) {
    this.folderName = folderName;
  }

  /**
   * Opens folder according to folderName parameter.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  public void doCommand(FTPClient ftpClient) throws IOException {
    if (ftpClient.isConnected()) {
      ftpClient.changeWorkingDirectory(folderName);

      System.out.println(CURRENT_DIR_MSG + ftpClient.printWorkingDirectory());
      printContent(ftpClient);
    } else {
      throw new IOException(NO_CONNECTION_MSG);
    }
  }
}
