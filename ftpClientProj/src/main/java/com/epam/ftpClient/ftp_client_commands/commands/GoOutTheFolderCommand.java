package com.epam.ftpClient.ftp_client_commands.commands;

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
      ftpClient.changeToParentDirectory();

      System.out.println("current dir: " + ftpClient.printWorkingDirectory());
      printContent(ftpClient);
  }
}
