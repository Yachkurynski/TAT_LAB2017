package com.epam.ftpClient;

import com.epam.ftpClient.ftpClientCommands.Commands;
import com.epam.ftpClient.ftpClientCommands.builders.ConnectToServerCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.builders.DownloadFileCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.builders.FtpClientCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.builders.GoIntoFolderCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.builders.GoOutTheFolderCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.builders.PrintFolderContentCommandBuilder;
import com.epam.ftpClient.ftpClientCommands.commands.FtpClientCommand;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class which contains entry point. Program allows to do actions which are described in
 * Commands class.
 */
public class Main {

  /**
   * Entry point to the program.
   * @param args are not used.
   */
  public static void main(String[] args) {
    String enteredString;
    FtpClientCommand command;
    FTPClient ftpClient = new FTPClient();
    FtpClientCommandBuilder commandBuilder = new ConnectToServerCommandBuilder(
        new DownloadFileCommandBuilder(new GoIntoFolderCommandBuilder(
            new GoOutTheFolderCommandBuilder(new PrintFolderContentCommandBuilder(null)))));

    commandBuilder.printUsage();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (!((enteredString = reader.readLine()).equalsIgnoreCase(Commands.EXIT.name()))) {
        try {
          command = commandBuilder.getCommand(enteredString);
          command.doCommand(ftpClient);
        } catch (IllegalArgumentException | NullPointerException | IOException ex) {
          System.out.println(ex.getLocalizedMessage());
        }
      }
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    } finally {
      if (ftpClient.isConnected()) {
        try {
          ftpClient.disconnect();
        } catch (IOException e) {
          System.out.println(e.getLocalizedMessage());
        }
      }
    }
  }
}
