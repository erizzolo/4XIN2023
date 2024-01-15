import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Rot13InputStream extends FilterInputStream {

    protected Rot13InputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int read = in.read();
        return read == -1 ? read : (read + 13) & 0xFF;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read = in.read(b, off, len);
        for (int i = off; i < off + read; i++) {
            b[i] = (byte) ((b[i] + 13) & 0xFF);
        }
        return read;
    }

}
