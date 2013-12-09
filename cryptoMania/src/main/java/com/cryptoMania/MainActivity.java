package com.cryptoMania;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends ActionBarActivity {

    private String LOG_TAG = "MAIN";

    private String seed = "ASDFWERWFASD";

    private TextView textView;
    private Spinner spinnerAlgorithms;
    private Spinner spinnerSizes;
    private Spinner spinnerModes;
    private Spinner spinnerPaddings;

    private int keySize;
    private String algorithm;
    private String mode;
    private String padding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (Provider provider: Security.getProviders()) {
            System.out.println(provider.getName());
            for (String key: provider.stringPropertyNames())
                Log.e(LOG_TAG, key);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public byte[] encrypt(String message){
        if( algorithm.equals("DES") || algorithm.equals("AES") || algorithm.equals("Blowfish"))
            try {
                byte[] rawKey = getRawKey(seed.getBytes());

                SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, algorithm);

                Cipher cipher;
                if( mode.equals("default") )
                    cipher = Cipher.getInstance(algorithm);
                else
                    cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                byte[] encrypted = cipher.doFinal(message.getBytes());

                Log.e("CIPHER", encrypted.toString());
                return encrypted;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return new byte[0];

            }
        else
            try{
                byte[] rawKey = getRawKey(seed.getBytes());
                // get an hmac_sha1 key from the raw key bytes
                SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, algorithm);

                // get an hmac_sha1 Mac instance and initialize with the signing key
                Mac mac = Mac.getInstance(algorithm);
                mac.init(secretKeySpec);

                // compute the hmac on input data bytes
                byte[] encrypted = mac.doFinal(message.getBytes());
                Log.e("CIPHER2", encrypted.toString());

                return encrypted;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return new byte[0];
            }

    }

    private byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(keySize, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button cipherButton = (Button) rootView.findViewById(R.id.cipher_button);
            final EditText input = (EditText) rootView.findViewById(R.id.text_input);
            textView =          (TextView) rootView.findViewById(R.id.text_view);
            spinnerAlgorithms = (Spinner) rootView.findViewById(R.id.spinner_algorithms);
            spinnerSizes =      (Spinner) rootView.findViewById(R.id.spinner_sizes);
            spinnerModes =      (Spinner) rootView.findViewById(R.id.spinner_modes);
            spinnerPaddings =   (Spinner) rootView.findViewById(R.id.spinner_paddings);

            spinnerAlgorithms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    algorithm = parent.getItemAtPosition(position).toString();
                    Log.e(LOG_TAG, algorithm);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    keySize = Integer.parseInt(parent.getItemAtPosition(position).toString());
                    Log.e(LOG_TAG, String.valueOf(keySize));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerModes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mode = parent.getItemAtPosition(position).toString();
                    Log.e(LOG_TAG, mode);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinnerPaddings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    padding = parent.getItemAtPosition(position).toString();
                    Log.e(LOG_TAG, padding);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            cipherButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    byte[] encrypted = encrypt(input.getText().toString());
                    textView.setText(bytesToHex(encrypted));
                }
            });

            return rootView;
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
