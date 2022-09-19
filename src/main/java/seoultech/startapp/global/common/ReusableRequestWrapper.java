package seoultech.startapp.global.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class ReusableRequestWrapper extends HttpServletRequestWrapper {

  private ByteArrayOutputStream cachedBytes;

  public ReusableRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (cachedBytes == null) {
      cacheInputStream();
    }

    return new CachedServletInputStream();
  }

  private void cacheInputStream() throws IOException {

    cachedBytes = new ByteArrayOutputStream();
    IOUtils.copy(super.getInputStream(), cachedBytes);
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  private class CachedServletInputStream extends ServletInputStream {

    private ByteArrayInputStream input;

    public CachedServletInputStream() {
      this.input = new ByteArrayInputStream(cachedBytes.toByteArray());
    }

    @Override
    public boolean isFinished() {
      return input.available() == 0;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      throw new UnsupportedOperationException("지원하지 않는 기능");
    }

    @Override
    public int read() throws IOException {
      return input.read();
    }
  }

}
