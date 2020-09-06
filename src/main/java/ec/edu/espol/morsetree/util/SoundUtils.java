
package ec.edu.espol.morsetree.util;

/**
 * Autor : Réal Gagnon
 */

import ec.edu.espol.morsetree.model.NotificationLabel;
import javax.sound.sampled.*;

public class SoundUtils {

    private SoundUtils() {
    }
    
  public static final float SAMPLERATE = 8000f;

  
  public static void tone(int hz, int msecs) 
      
  {
     tone(hz, msecs, 1.0);
  }

  public static void tone(int hz, int msecs, double vol)
      
  {
      
      try {
          byte[] buf = new byte[1];
          AudioFormat af =
                  new AudioFormat(
                          SAMPLERATE, // sampleRate
                          8,           // sampleSizeInBits
                          1,           // channels
                          true,        // signed
                          false);      // bigEndian
          try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
              sdl.open(af);
              sdl.start();
              for (int i=0; i < msecs*8; i++) {
                  double angle = i / (SAMPLERATE / hz) * 2.0 * Math.PI;
                  buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
                  sdl.write(buf,0,1);
              }
              sdl.drain();
              sdl.stop();
          }
      } catch (LineUnavailableException ex) {
          NotificationLabel.getInstance().send(ex.getMessage());
      }
      
  }

}