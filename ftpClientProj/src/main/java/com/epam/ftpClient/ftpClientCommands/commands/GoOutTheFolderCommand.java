package com.epam.ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Allows to go out the current directory.
 */
public class GoOutTheFolderCommand extends FtpClientCommand {

  /**
   * Goes out the current directory.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  public void doCommand(FTPClient ftpClient) throws IOException {
    if (ftpClient.isConnected()) {
      ftpClient.changeToParentDirectory();

      System.out.println("current dir: " + ftpClient.printWorkingDirectory());
      printContent(ftpClient);
    } else {
      throw new IOException(NO_CONNECTION_MSG);
    }
  }
}
