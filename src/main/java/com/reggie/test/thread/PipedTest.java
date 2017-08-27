package com.reggie.test.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by reggie on 2017/8/25.
 */
public class PipedTest {

  public static void main(String[] args) throws IOException {
    PipedReader in = new PipedReader();
    PipedWriter out = new PipedWriter();
    out.connect(in);
//    in.connect(out);
    Thread thread = new Thread(new Print(in),"print-thread");
    thread.start();

    int reviece = 0;
    try {
      while ((reviece = System.in.read()) != -1){
        out.write(reviece);
      }
    } finally {
      out.close();
    }
  }

  static class Print implements Runnable {

    private PipedReader in;

    public Print(PipedReader in){
      this.in = in;
    }

    @Override
    public void run() {
      int reviece = 0;
      try {
        while ((reviece = in.read()) != -1){
          System.out.print((char) reviece);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}
