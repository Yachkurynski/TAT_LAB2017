package ftpClient.ftpClientCommands.commands;

/**
 *
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

    public void doCommand() {

    }
}
