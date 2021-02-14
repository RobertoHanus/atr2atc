/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atr2atc;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author roberto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        byte[] header = new byte[16];
        byte[] sectorInput = new byte[128];
        byte[] sectorOutputC = new byte[129];
        byte checkSum = 0x00;

        try {
        InputStream inputStream = new FileInputStream(args[0]);
        OutputStream outputStream = new FileOutputStream(args[1]);

        
            inputStream.read(header);
            outputStream.write(header);

            while (inputStream.read(sectorInput) != -1) {
                int acum = 0;
                for(int i=0;i<128;i++)
                {
                    sectorOutputC[i]=sectorInput[i];
                    acum += (int)sectorInput[i];
                    if(acum > 0xFF)
                    {
                        acum-=0x100;
                        acum++;
                    }
                }
                sectorOutputC[128]=(byte)acum;
               outputStream.write(sectorOutputC);
            }
        } catch (Exception e) {
        }

    }

}
