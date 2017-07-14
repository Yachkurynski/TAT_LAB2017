package ftpClient.ftpClientCommands.commands;

/**
 * Created by Pavel_Yachkurynski on 7/13/2017.
 */
public class GoIntoFolderCommand extends FtpClientCommand {
    private String folderName;

    public GoIntoFolderCommand(String folderName) {
        this.folderName = folderName;
    }

    public void doCommand() {

    }
}
