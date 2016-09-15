import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.DelayQueue;


public class TestInputStream extends InputStream {

        private ByteBuffer buffer = ByteBuffer.allocate(8192);

        private final DelayQueue<DelayedString> queue;

        public TestInputStream(DelayQueue<DelayedString> queue) {
            this.queue = queue;
        }

        public int read() {
            updateBuffer();
            if (buffer.hasRemaining()) {
                // deliver content inside buffer
            }
        }

        public int read(char[] buffer, int count) {
            updateBuffer();
            // deliver content in byte buffer into buffer
        }

        protected void updateBuffer() {
            for (DelayedString s = queue.peek(); s != null; ) {
                if (buffer.capacity() > buffer.limit() + s.getContent().length()) {
                    s = queue.poll();
                    buffer.append(s.getContent());
                } else {
                    break;
                }
            }
        }
    }
