package ua.university;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class StatusFile {

    public static void initFile(String file, int size) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(size);
        }
    }

    public static void updateStatus(String file, int index, byte status) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
             FileChannel channel = raf.getChannel()) {
            channel.position(index);
            channel.write(ByteBuffer.wrap(new byte[]{status}));
        }
    }

    public static byte readStatus(String file, int index) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1);
            channel.position(index);
            channel.read(buffer);

            buffer.flip();
            return buffer.get();
            //ff
        }
    }
}
