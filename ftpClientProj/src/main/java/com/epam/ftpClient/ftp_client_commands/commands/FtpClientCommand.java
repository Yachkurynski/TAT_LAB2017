package com.epam.ftpClient.ftp_client_commands.commands;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

/**
 * Parent class for all available commands, which can to perform ftp client.
 */
public abstract class FtpClientCommand {
  public static final String FOLDER_NAME_BEGIN = "[";
  public static final String FOLDER_NAME_END = "]";

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
      FTPFile[] content = ftpClient.listFiles();

      for (FTPFile file : content) {
        String objectName = file.isFile() ? file.getName() : FOLDER_NAME_BEGIN + file.getName() +
            FOLDER_NAME_END;
        System.out.println(objectName);
      }
  }
}
