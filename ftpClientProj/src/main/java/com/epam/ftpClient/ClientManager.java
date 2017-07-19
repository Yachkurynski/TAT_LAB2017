package com.epam.ftpClient;

import com.epam.ftpClient.ftp_client_commands.builders.FtpClientCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.commands.FtpClientCommand;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Provides some organizational actions like perform some command or close connection.
 */
public class ClientManager {
  private FtpClientCommand command;
  private FtpClientCommandBuilder commandBuilder;

  public ClientManager(FtpClientCommandBuilder commandBuilder) {
    this.commandBuilder = commandBuilder;
  }

  /**
   * Performs Command according to command in given string.
   *
   * @param enteredString given string with command and parameters.
   * @param ftpClient FTPClient for managing.
   */
  public void performCommand(String enteredString, FTPClient ftpClient) {
    try {
      command = commandBuilder.getCommand(enteredString);
      command.doCommand(ftpClient);
    } catch (IllegalArgumentException | NullPointerException | IOException ex) {
      System.out.println(ex.getLocalizedMessage());
    }
  }

  /**
   * Performs disconnection.
   * @param ftpClient FTPClient for managing.
   */
  public void closeConnection(FTPClient ftpClient) {
      try {
        ftpClient.disconnect();
      } catch (IOException e) {
        System.out.println(e.getLocalizedMessage());
      }
  }
}
