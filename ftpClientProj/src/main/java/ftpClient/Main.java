package ftpClient;

import ftpClient.ftpClientCommands.builders.*;
import ftpClient.ftpClientCommands.commands.FtpClientCommand;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        FtpClientCommandBuilder commandBuilder = new ConnectToServerCommandBuilder(
                new DownloadFileCommandBuilder(new GoIntoFolderCommandBuilder(
                        new GoOutTheFolderCommandBuilder(new PrintFolderContentCommandBuilder(
                                null)))));

        FtpClientCommand command = commandBuilder.getCommand("connect", "blablabla");
    }
}
