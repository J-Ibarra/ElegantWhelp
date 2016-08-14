package com.jcs;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.jcs.IOUtil.ioResourceToByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * Created by Jcs on 14/8/2016.
 */
public class Texture {
    public int id;
    public int width;
    public int height;

    public Texture(String path) {
        ByteBuffer image;
        ByteBuffer imageBuffer;

        try {
            imageBuffer = ioResourceToByteBuffer(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        IntBuffer wb = BufferUtils.createIntBuffer(1);
        IntBuffer hb = BufferUtils.createIntBuffer(1);
        IntBuffer comb = BufferUtils.createIntBuffer(1);

        if (!stbi_info_from_memory(imageBuffer, wb, hb, comb))
            throw new RuntimeException("Failed to read image information: " + stbi_failure_reason());

        image = stbi_load_from_memory(imageBuffer, wb, hb, comb, 0);
        if (image == null)
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());

        width = wb.get(0);
        height = hb.get(0);
        int comp = comb.get(0);

        id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);

        if (comp == 3) {
            if ((width & 3) != 0)
                glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (width & 1));
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glBindTexture(GL_TEXTURE_2D, 0);

    }

    public void bind(int sampler) {
        if (sampler >= 0 && sampler <= 31) {
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, id);
        }
    }
}
