package com.epam.ftpClient.ftpClientCommands.commands;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Allows to download file from current directory.
 */
public class DownloadFileCommand extends FtpClientCommand {
  public static final String FILE_DOWNLOAD_MSG = "File was downloaded to directory ";
  public static final String FILE_OUT_PATH = "download";
  private String fileName;

  public DownloadFileCommand(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Downloads the file according to fileName.
   * @param ftpClient FTPClient for managing.
   * @throws IOException
   */
  public void doCommand(FTPClient ftpClient) throws IOException {
    if (ftpClient.isConnected()) {
      FTPFile[] files = ftpClient.listFiles();

      for (FTPFile file : files) {
        String currentFileName = file.getName();

        if (fileName.equals(currentFileName)) {
          saveFile(currentFileName, ftpClient);
        }
      }
    } else {
      throw new IOException(NO_CONNECTION_MSG);
    }
  }

  /**
   * Saves file to your directory (FILE_OUT_PATH).
   * @param currentFile name of file which need to save.
   * @param ftpClient FTPClient for managing.
   */
  private void saveFile(String currentFile, FTPClient ftpClient) {
    (new File(FILE_OUT_PATH)).mkdir();

    try (FileOutputStream outFileStream = new FileOutputStream(FILE_OUT_PATH + "/" + currentFile)) {
      ftpClient.retrieveFile(currentFile, outFileStream);

      System.out.println(FILE_DOWNLOAD_MSG + FILE_OUT_PATH + "/");
    } catch (IOException ex) {
      System.out.println(ex.getLocalizedMessage());
    }
  }
}
