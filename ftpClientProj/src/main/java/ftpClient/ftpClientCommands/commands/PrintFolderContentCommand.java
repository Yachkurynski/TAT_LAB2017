package ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

/**
 * Allows to print folder's content.
 */
public class PrintFolderContentCommand extends FtpClientCommand {

  /**
   * Prints folder's content.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  public void doCommand(FTPClient ftpClient) throws IOException {
    printContent(ftpClient);
  }
}
