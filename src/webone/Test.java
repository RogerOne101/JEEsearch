package webone;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test")
public final class Test extends HttpServlet {

  // private long hits;

  // private final AtomicLong hits = new AtomicLong(0);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // final long hits;
    // synchronized (this) {
    // hits = (this.hits += 1);
    // }
    // response.getWriter().append("Hits: ")
    // .append(String.valueOf(hits.incrementAndGet()));
  }

}
