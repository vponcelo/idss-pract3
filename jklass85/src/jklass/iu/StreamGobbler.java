package jklass.iu;

import java.io.*;
import java.util.logging.*;

class StreamGobbler extends Thread {
  private static Logger logger = Logger.getLogger(StreamGobbler.class.getName());
  InputStream is;
  String type;

  StreamGobbler(InputStream is, String type) {
    this.is = is;
    this.type = type;
  }

  public void run() {
    try {
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line = null;
      while ( (line = br.readLine()) != null){
        //System.out.println(type + "> " + line);
        logger.fine(type + "> " + line);
      }
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}