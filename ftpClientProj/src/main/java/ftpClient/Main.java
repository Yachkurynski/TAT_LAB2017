package ftpClient;

import ftpClient.ftpClientCommands.builders.*;
import ftpClient.ftpClientCommands.commands.FtpClientCommand;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
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
      while (!((enteredString = reader.readLine()).equals("exit"))) {
        command = commandBuilder.getCommand(enteredString);
        command.doCommand(ftpClient);
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
