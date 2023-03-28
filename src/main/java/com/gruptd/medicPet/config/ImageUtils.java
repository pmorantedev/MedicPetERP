package com.gruptd.medicPet.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Classe utils per comprimir i descompromir una imatge
 *
 * @author pablomorante
 */
public class ImageUtils {

    /**
     * Comprimeix la matriu de bytes d'entrada mitjançant l'algorisme Deflater
     * amb el nivell BEST_COMPRESSION.
     *
     * @param data La matriu de bytes d'entrada que s'ha de comprimir.
     * @return Una matriu de bytes comprimits.
     * @author pablomorante
     */
    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    /**
     * Descomprimeix la matriu de bytes d'entrada mitjançant l'algorisme Inflater.
     *
     * @param data La matriu de bytes d'entrada que s'ha de descomprimir.
     * @return Una matriu de bytes descomprimida.
     * @author pablomorante
     */
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException exception) {
        }
        return outputStream.toByteArray();
    }
}
