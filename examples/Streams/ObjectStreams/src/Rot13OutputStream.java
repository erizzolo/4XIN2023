import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Rot13OutputStream extends FilterOutputStream {

    public Rot13OutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        out.write((b - 13) & 0xFF);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            b[i] = (byte) ((b[i] - 13) & 0xFF);
        }
        out.write(b, off, len);
    }

}
