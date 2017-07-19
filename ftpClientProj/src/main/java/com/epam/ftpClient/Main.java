package com.epam.ftpClient;

import com.epam.ftpClient.ftp_client_commands.Commands;
import com.epam.ftpClient.ftp_client_commands.builders.ConnectToServerCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.builders.DownloadFileCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.builders.FtpClientCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.builders.GoIntoFolderCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.builders.GoOutTheFolderCommandBuilder;
import com.epam.ftpClient.ftp_client_commands.builders.PrintFolderContentCommandBuilder;
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
    FTPClient ftpClient = new FTPClient();
    FtpClientCommandBuilder commandBuilder = new ConnectToServerCommandBuilder(
        new DownloadFileCommandBuilder(new GoIntoFolderCommandBuilder(
            new GoOutTheFolderCommandBuilder(new PrintFolderContentCommandBuilder(null)))));
    ClientManager clientManager = new ClientManager(commandBuilder);

    commandBuilder.printUsage();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (!((enteredString = reader.readLine()).equalsIgnoreCase(Commands.EXIT.name()))) {
        clientManager.performCommand(enteredString, ftpClient);
      }
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    } finally {
      clientManager.closeConnection(ftpClient);
    }
  }
}
