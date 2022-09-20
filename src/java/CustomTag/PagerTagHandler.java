/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomTag;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import product.ProductDAO;

/**
 *
 * @author looby
 */
public class PagerTagHandler extends SimpleTagSupport {

    private String pIndex;
    private String productSize;
    private String page;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");
            if (pIndex.equals("")) {
                pIndex = "1";
            }
//            if (pIndex.equals("")) {
//                pIndex = "10";
//            }
            int pageid = Integer.parseInt(pIndex);
            int size = Integer.parseInt(productSize);
//            int allProductSize = ProductDAO.getAllProduct().size();
            int lastindex = size / 5;
            boolean isEven = size % 5 != 0;
            if (isEven) {
                lastindex++;
            }

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            out.println("<a href=\"MainController?op=index&pageindex=1\">First</a>");
            if (size <= 10) {
                if (pageid == 1) {
                    out.println("<a>" + pageid + "</a>");
                    out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid + 1) + "\">" + (pageid + 1) + "</a>");
                } else if (pageid == 2) {
                    out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid - 1) + "\">" + (pageid - 1) + "</a>");
                    out.println("<a>" + pageid + "</a>");
                }
            } 
            else if (pageid == 1) {
                out.println("<a>" + pageid + "</a>");
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid + 1) + "\">" + (pageid + 1) + "</a>");
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid + 2) + "\">" + (pageid + 2) + "</a>");
            } else if (pageid == (lastindex)) {
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid - 2) + "\">" + (pageid - 2) + "</a>");
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid - 1) + "\">" + (pageid - 1) + "</a>");
                out.println("<a>" + pageid + "</a>");
            } else {
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid - 1) + "\">" + (pageid - 1) + "</a>");
                out.println("<a>" + pageid + "</a>");
                out.println("<a href=\"MainController?op=" + page + "&pageindex=" + (pageid + 1) + "\">" + (pageid + 1) + "</a>");
            }
            out.println("<a href=\"MainController?op=" + page + "&pageindex=" + lastindex + "\">Last</a>");

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in PagerTagHandler tag", ex);
        }
    }

    public void setPindex(String pIndex) {
        this.pIndex = pIndex;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
