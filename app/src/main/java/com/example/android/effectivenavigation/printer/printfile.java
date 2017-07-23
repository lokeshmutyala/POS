package com.example.android.effectivenavigation.printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lokeshmutyala on 23-07-2017.
 */

public class printfile {

    public static Context mContext;


    public static void prints() throws IOException {


        BluetoothAdapter btAdapter = BluetoothUtil.getBTAdapter();
        if (btAdapter==null)

        {
            Toast.makeText(mContext, "Please Open Bluetooth!", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        BluetoothDevice device = getDevice(btAdapter);
        if (device == null) {
            Toast.makeText(mContext,"Please Make Sure Bluetooth have InnterPrinter!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        String test="HI ITZ WORKING";
        byte[] data = test.getBytes();
        BluetoothSocket socket = null;
        socket = getSocket(device);
        try {
            sendData(data, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String Innerprinter_Address = "00:11:22:33:44:55";

    public static class BluetoothUtil {
//        private static final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  //      private static final String Innerprinter_Address = "00:11:22:33:44:55";

        public static BluetoothAdapter getBTAdapter() {
            return BluetoothAdapter.getDefaultAdapter();
        }
    }

    public static BluetoothDevice getDevice(BluetoothAdapter bluetoothAdapter) {
        BluetoothDevice innerprinter_device = null;
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            if (device.getAddress().equals(Innerprinter_Address)) {
                innerprinter_device = device;
                break;
            }
        }
        return innerprinter_device;
    }

    public static BluetoothSocket getSocket(BluetoothDevice device) throws IOException {
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(PRINTER_UUID);
        socket.connect();
        return socket;
    }

    public static void sendData(byte[] bytes, BluetoothSocket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(bytes, 0, bytes.length);
        out.close();
    }

}
