package com.jcs;

import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Jcs on 13/8/2016.
 */
public class IOUtil {

    public static ByteBuffer ioResourceToByteBuffer(String resource) throws Exception {
        ByteBuffer buffer;

        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource(resource).toURI());
        //Path path = Paths.get(resource);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);

                while (fc.read(buffer) != -1)
                    ;

                buffer.flip();
                return buffer;
            }

        } else
            return null;

    }

    public static String ioReadFileAsString(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null)
                result.append(line).append('\n');
            br.close();
        }
        return result.toString();
    }
}