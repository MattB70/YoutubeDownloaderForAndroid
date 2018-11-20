
//TODO: Integrated this class into MainActivity because Android hates Java, why is Java the default language Android Studio uses when it doesn't even work properly. 1/10 just use C or even C3, it's only Java that has these issues.


package mattborle.youtubedownloader;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.SystemClock;
import java.text.DateFormat;
import java.net.URLDecoder;


public class DownloadFromURL extends Thread{
/*
    public static void downloadFromURL(String stringURL) throws MalformedURLException, UnsupportedEncodingException {

        String decodedURL = URLDecoder.decode(stringURL, "UTF-8");
        final URL u = new URL("https://r1---sn-nx5e6nes.googlevideo.com/videoplayback?dur=651.984&fvip=1&signature=55D6A0F00B498BD0F0C450E75001134F5B94F63F.34F6682195BB0D98D4DEC594830CF447162A5F21&beids=9466588&mime=video%2Fwebm&key=cms1&ei=TlvyW7eqC9XzkgbQhp1A&ipbits=0&txp=5530432&clen=138522774&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278%2C394%2C395%2C396%2C397%2C398&c=web&requiressl=yes&ip=96.50.138.49&itag=248&source=youtube&pl=22&id=o-AOw7ObgP-c_LqFGnUjW6lCKR6UMpSg5IEIAgH6dFbQ62&expire=1542631342&gir=yes&sparams=aitags,clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,keepalive,lmt,mime,mm,mn,ms,mv,pl,requiressl,source&lmt=1541939272478992&keepalive=yes&alr=yes&cpn=m4GBhDixAcOaZE6b&cver=html5&rn=26&rbuf=18231&redirect_counter=1&rm=sn-ni5f-ttjs7e&fexp=9466588,23760286,23763603&req_id=42c934a80a31a3ee&cms_redirect=yes&ipbypass=yes&mm=29&mn=sn-nx5e6nes&ms=rdu&mt=1542610378&mv=m");

        Thread thread = new Thread() {
            public void run() {
                System.out.println("Download Thread Created.");
                // I/O stream.
                InputStream is = null;

                try {

                    is = u.openStream();                                                                        //Open Download Stream
                    HttpURLConnection huc = (HttpURLConnection) u.openConnection();                              //to know the size of video
                    int size = huc.getContentLength();
                    System.out.println("Filesize: " + size);

                    if (huc != null) {
                        String fileName = "FILE.mp4";                                                           //Name of File f
                        //String storagePath = Environment.getExternalStorageDirectory().toString();              //Gets External Storage Path
                        File f = new java.io.File((getActivity()
                                .getApplicationContext().getFileStreamPath("FileName")
                                .getPath()));
                        System.out.println("File: \"" + fileName + "\" created.");

                        FileOutputStream fos = new FileOutputStream(f);                                         //Output to File f
                        byte[] buffer = new byte[1024];
                        System.out.println("Buffer created.");
                        int len1 = 0;
                        if (is != null) {
                            while ((len1 = is.read(buffer)) > 0) {
                                fos.write(buffer, 0, len1);
                            }
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    }
                } catch (MalformedURLException mue) {

                    System.out.println("Download Failed. Error: 1");
                    mue.printStackTrace();

                } catch (IOException ioe) {

                    System.out.println("Download Failed. Error: 2");
                    ioe.printStackTrace();

                } finally {

                    try {

                        if (is != null) {
                            is.close();
                        }

                    } catch (IOException ioe) {

                        System.out.println("Download Failed. Error: 3");
                        throw new RuntimeException(ioe);
                        // just going to ignore this one

                    }

                }
            }
        };

        thread.start();
    }
*/
}
