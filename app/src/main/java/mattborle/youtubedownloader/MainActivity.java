package mattborle.youtubedownloader;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.Button1);

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {

                EditText urlInEditText = findViewById(R.id.urltextbox);
                String urlString = urlInEditText.getText().toString();

                // Example URLs:
                // https://youtu.be/DhAzb5JhF80                     this type is when clicking "share"
                // https://www.youtube.com/watch?v=DhAzb5JhF80      this type is when copying web page URL.

                //TODO: This could be cleaned up a lot.

                if(urlString.startsWith("https://www.youtube.com/watch?v=") || urlString.startsWith("https://youtu.be/")){
                    System.out.println("URL Received: " + urlString);

                    try {

                        System.out.println("GOOD URL Received: " + urlString);
                        downloadFromURL(urlString);

                    }catch(MalformedURLException e){

                        System.out.println("BAD URL Received, Error 1 : " + urlString);
                        throw new RuntimeException(e);
                        //display invalid URL dialog
                    }catch(UnsupportedEncodingException e){

                    System.out.println("BAD URL Received, Error 2 : " + urlString);
                    throw new RuntimeException(e);
                    //display invalid URL dialog
                }

                }
                else
                {
                    System.out.println("BAD URL Received, Error 3 : " + urlString);
                    //TODO: display invalid URL dialog
                }
                //URL url = new URL(urlString);
            }
        });

    }

    /* Unused
    private void showURLAddDialog() {

        DialogFragment newFragment = new addUrlDialogFragment();
        newFragment.show(getSupportFragmentManager(), "URLaddDialog");

    }
    */


    // This method handles the creation of the download thread, and downloads the file in O(1) ; 1 = video size.
    // Many things in this method have been changed in an attempt to get around Android and Java's incompatibilities. (This would have been a lot easier with C, but I'm not confident with C yet)
    public static void downloadFromURL(String stringURL) throws MalformedURLException, UnsupportedEncodingException {
                                                                                                    //TODO                  IMPORTANT NOTE:
        String decodedURL = URLDecoder.decode(stringURL, "UTF-8");                              //TODO           YouTube has hidden their .mp4 availability.
        final URL u = new URL(decodedURL);                                                          //TODO           Video and Audio are given as separate streams.
                                                                                                    //TODO           It may be possible to combine these using accelerated screen recording or re-rendering, but both of these options are not perfect and are out of the scope of this project.
        Thread thread = new Thread() {
            public void run() {
                System.out.println("Download Thread Created.");
                // I/O stream.
                InputStream is = null;

                try {

                    is = u.openStream();                                                            //Open Download Stream
                    HttpURLConnection huc = (HttpURLConnection) u.openConnection();                 //to know the size of video
                    int size = huc.getContentLength();
                    System.out.println("Filesize: " + size);

                    if (huc != null) {
                        String fileName = "media";                                                  //Name of File f

                        String storagePath = Environment.getExternalStorageDirectory().toString();  //Gets External Storage Path

                        //  A few attempts ate trying to resolve Error 2, nothing has worked.
                        //  Storage path still doesn't exist, or access is denied.
                        //  All permissions exist in xml.
                        //  Possible limitation in using a physical phone for testing? (Galaxy S5 / Android Ver. 6.0.1)

                        //ContextWrapper cw = new ContextWrapper(MyApplication.getAppContext());
                        //File f = MyApplication.getAppContext().getDir(Environment.DIRECTORY_DOWNLOADS, Context.MODE_PRIVATE);
                        /*
                        File f = new File("data/data/mattborle.youtubedownloader/media");
                        if (!f.exists()) {
                            try {
                                f.createNewFile();
                            } catch (IOException e) {
                                System.out.print("Download Failed. Error: 0");
                                e.printStackTrace();
                            }
                        }
                        */

                        // Specifically streaming to "/storage/sdcard0" was done in an attempt to solve Error 2.
                        File f = new File("/storage/sdcard0", fileName);
                        System.out.println("File: \"" + fileName + "\" created.");

                        FileOutputStream fos = new FileOutputStream(f.getParentFile());             //Output to File f
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

                    System.out.println("Download Failed. Error: 1");                                // This should never happen as it is handled in onCreate. If it does, call the police.
                    mue.printStackTrace();

                } catch (IOException ioe) {

                    System.out.println("Download Failed. Error: 2");                                // This probably happens when the file destination is missing or access is denied.
                    ioe.printStackTrace();

                } finally {

                    try {

                        if (is != null) {
                            is.close();
                        }

                    } catch (IOException ioe) {

                        System.out.println("Download Failed. Error: 3");                            // This is unlikely, but it is possible for the file to download, yet the stream still throws a non specific IOException.
                        throw new RuntimeException(ioe);
                        // just going to ignore this one

                    }

                }
            }
        };

        thread.start();                                                                             // Run this mess.
    }
}



