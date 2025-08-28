package Calculate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculate")
public class Servlet extends HttpServlet {

	@EJB(lookup = "java:global/Demo/StatelessBean!Calculate.Bean")
    private Bean stateless;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Hiển thị form
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Calculator</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: #f4f7fa; text-align: center; }");
        out.println(".container { margin: 50px auto; padding: 30px; background: white; border-radius: 12px; "
                + "box-shadow: 0 4px 10px rgba(0,0,0,0.2); width: 400px; }");
        out.println("h2 { color: #333; }");
        out.println("input, select { width: 90%; padding: 8px; margin: 10px 0; border-radius: 6px; border: 1px solid #ccc; }");
        out.println("input[type=submit] { background: #4CAF50; color: white; font-weight: bold; cursor: pointer; }");
        out.println("input[type=submit]:hover { background: #45a049; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h2>EJB Calculator Demo</h2>");
        out.println("<p><i>So sánh <b>Stateful</b> và <b>Stateless</b> EJB</i></p>");
        out.println("<form action='calculate' method='post'>");
        out.println("<input type='text' name='x' placeholder='Nhập số X'><br>");
        out.println("<input type='text' name='y' placeholder='Nhập số Y'><br>");
        out.println("<select name='op'>"
                + "<option value='+'>Add</option>"
                + "<option value='-'>Sub</option>"
                + "<option value='*'>Mul</option>"
                + "<option value='/'>Div</option>"
                + "</select><br>");
        out.println("<select name='type'>"
                + "<option value='stateless'>Stateless</option>"
                + "<option value='stateful'>Stateful</option>"
                + "</select><br>");
        out.println("<input type='submit' value='Calculate'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String type = request.getParameter("type");
            String op = request.getParameter("op");
            int x = Integer.parseInt(request.getParameter("x"));
            int y = Integer.parseInt(request.getParameter("y"));

            Bean bean = null;
            HttpSession session = request.getSession();

            if ("stateful".equalsIgnoreCase(type)) {
            	bean = (Bean) session.getAttribute("statefulBean");
                boolean needNewBean = false;
                if (bean != null) {
                    try {
                        // Try to access to trigger activation
                        bean.getHistory();
                    } catch (Exception ex) {
                        // Activation failed, remove old bean
                        session.removeAttribute("statefulBean");
                        needNewBean = true;
                    }
                } else {
                    needNewBean = true;
                }
                if (needNewBean) {
                    try {
                        InitialContext ctx = new InitialContext();
                        bean = (Bean) ctx.lookup("java:global/Demo/StatefulBean!Calculate.Bean");
                        session.setAttribute("statefulBean", bean);
                    } catch (NamingException e) {
                        throw new ServletException("Cannot lookup StatefulBean", e);
                    }
                }
            } else {
                bean = stateless;
            }

            double result = 0;
            switch (op) {
                case "+": result = bean.add(x, y); break;
                case "-": result = bean.subtract(x, y); break;
                case "*": result = bean.multiply(x, y); break;
                case "/": result = bean.divide(x, y); break;
                default:
                    out.println("Unknown operation!");
                    return;
            }

            // Hiển thị kết quả
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background: #eef2f7; text-align: center; }");
            out.println(".result-box { margin: 50px auto; padding: 30px; background: white; border-radius: 12px; "
                    + "box-shadow: 0 4px 10px rgba(0,0,0,0.2); width: 600px; }");
            out.println("h2 { color: #222; }");
            out.println(".stateful { color: darkblue; font-weight: bold; }");
            out.println(".stateless { color: darkgreen; font-weight: bold; }");
            out.println("table { margin: 20px auto; border-collapse: collapse; width: 90%; }");
            out.println("th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }");
            out.println("th { background: #f2f2f2; }");
            out.println("a { display: inline-block; margin-top: 20px; text-decoration: none; "
                    + "padding: 10px 20px; background: #4CAF50; color: white; border-radius: 6px; }");
            out.println("a:hover { background: #45a049; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='result-box'>");
            out.println("<h2>Calculation Result</h2>");
            out.println("<p>Bean Type: <span class='" + type + "'>" + type.toUpperCase() + "</span></p>");
            out.println("<p>Operation: " + x + " " + op + " " + y + "</p>");
            out.println("<h2 style='color: #d9534f;'>Result = " + result + "</h2>");

            if ("stateful".equalsIgnoreCase(type)) {
            	Bean sf = (Bean) session.getAttribute("statefulBean");
                List<String> history = sf.getHistory();
                out.println("<p class='stateful'>Stateful giữ lại kết quả trong session.<br>"
                        + "Lịch sử:</p>");
                if (history != null && !history.isEmpty()) {
                    out.println("<table>");
                    out.println("<tr><th>Lần</th><th>Phép tính</th></tr>");
                    int count = 1;
                    for (String h : history) {
                        out.println("<tr><td>" + (count++) + "</td><td>" + h + "</td></tr>");
                    }
                    out.println("</table>");
                }
            } else {
                out.println("<p class='stateless'>Stateless không lưu trạng thái.<br>"
                        + "Không có lịch sử nào được ghi nhớ.</p>");
            }

            out.println("<a href='calculate'>🔙 Quay lại</a>");
            out.println("</div>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
            out.println("<a href='calculate'>Quay lại</a>");
        }
    }
}
