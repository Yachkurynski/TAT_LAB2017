package ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

/**
 * Allows to connect to ftp server.
 */
public class ConnectToServerCommand extends FtpClientCommand {
  private String url;
  private String login;
  private String password;

  public ConnectToServerCommand(String url, String login, String password) {
    this.url = url;
    this.login = login;
    this.password = password;
  }

  /**
   * Performs connecting to ftp server by url.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  public void doCommand(FTPClient ftpClient) throws IOException {
    ftpClient.connect(url);
    ftpClient.login(login, password);

    String status = ftpClient.isConnected() ? "connected to " + url : "there is no connection..";
    System.out.println("Status: " + status);
  }
}
