package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.OrderDAO;
import order.OrderDTO;
import product.ProductDAO;
import static product.ProductDAO.getAllProductByBrand;
import product.ProductDTO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author looby
 */
@WebServlet(urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "/error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String op = request.getParameter("op");
        HttpSession session = request.getSession();
        UserDTO userCheck = (UserDTO) session.getAttribute("user");
        String indexString = request.getParameter("pageindex");
        int index = 0;
        if (indexString == null) {
            indexString = "1";
        }
        index = Integer.parseInt(indexString);
        try {
            switch (op) {
                case "Login":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    UserDTO user = UserDAO.checkLogin(username, password);
                    if (null == user.getName() || user.getName().equals("")) {
                        request.setAttribute("error", "Invalid username or password");
                        request.setAttribute("username", username);
                        url = "login.jsp";
                    } else {
                        session.setAttribute("user", user);
                        url = "index.jsp";
                    }
                    break;
                case "index":

                    List<ProductDTO> listAllProduct = ProductDAO.getAllProduct();
                    List<ProductDTO> listProduct = ProductDAO.getProduct(index, listAllProduct);
                    request.setAttribute("list", listProduct);
//                  Set attribute for Paging
                    request.setAttribute("size", listAllProduct.size());
                    request.setAttribute("listAllProduct", listAllProduct);
                    request.setAttribute("index", indexString);
                    request.setAttribute("page", "index");

                    url = "allproducts.jsp";
                    break;
                case "adminLogin":
                    url = "login.jsp";
                    break;
                case "Logout":
                    session.invalidate();
                    url = "login.jsp";
                    break;
                case "SearchByBrand":
                    String brand = request.getParameter("name");
                    listAllProduct = ProductDAO.getAllProductByBrand(brand);
                    listProduct = ProductDAO.getProduct(index, listAllProduct);
                    request.setAttribute("listAllProduct", listAllProduct);
                    request.setAttribute("list", listProduct);
//                    Set attribute for Paging
                    request.setAttribute("index", indexString);
                    request.setAttribute("size", listAllProduct.size());
                    request.setAttribute("page", "SearchByBrand&name=" + brand);

                    url = "allproducts.jsp";
                    break;
                case "SearchByName":
                    String name = request.getParameter("name");
                    listAllProduct = ProductDAO.getAllProductByName(name);
                    listProduct = ProductDAO.getProduct(index, listAllProduct);
                    request.setAttribute("listAllProduct", listAllProduct);
                    request.setAttribute("list", listProduct);
                    request.setAttribute("searchname", name);
//                    Set attribute for Paging
                    request.setAttribute("index", indexString);
                    request.setAttribute("size", listAllProduct.size());
                    request.setAttribute("page", "SearchByName&name=" + name);
                    url = "allproducts.jsp";
                    break;
                case "Order":
                    String id = request.getParameter("id");
                    ProductDTO car = ProductDAO.getProductById(id);
                    request.setAttribute("car", car);
                    url = "order.jsp";
                    break;
                case "submitOrder":
                    String carId = request.getParameter("carid");
                    String cusName = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    boolean check = false;
                    if (cusName.equals("")) {
                        request.setAttribute("errorname", "Input Your Name");
                        check = true;
                    }
                    if (phone.equals("")) {
                        request.setAttribute("errorphone", "Input Your Phone Number");
                        check = true;
                    }
                    if (address.equals("")) {
                        request.setAttribute("erroraddress", "Input Your Address");
                        check = true;
                    }
                    if (check) {
                        request.setAttribute("car", ProductDAO.getProductById(carId));
                        request.setAttribute("name", cusName);
                        request.setAttribute("phone", phone);
                        request.setAttribute("address", address);
                        url = "order.jsp";
                        break;
                    }
                    OrderDAO.submitOrder(carId, cusName, phone, address);
                    url = "orderSuccess.jsp";
                    break;
                case "ViewOrders":
                    List<OrderDTO> listOrder = OrderDAO.getOrderList();
                    request.setAttribute("listOrder", listOrder);
                    url = "vieworder.jsp";
                    break;
                case "SearchByPrice":
                    String priceFrom = request.getParameter("pricefrom");
                    String priceTo = request.getParameter("priceto");
                    listAllProduct = ProductDAO.getAllProductByPrice(priceFrom, priceTo);
                    request.setAttribute("listAllProduct", listAllProduct);
//                    Set attribute for Paging
                    request.setAttribute("list", ProductDAO.getProduct(index, listAllProduct));
                    request.setAttribute("index", indexString);
                    request.setAttribute("size", listAllProduct.size());
                    request.setAttribute("page", "SearchByPrice&pricefrom=" + priceFrom + "&priceto=" + priceTo);
                    url = "allproducts.jsp";
                    break;
                case "SearchByType":
                    String type = request.getParameter("type");
                    listAllProduct = ProductDAO.getAllProductByType(type);
                    request.setAttribute("listAllProduct", listAllProduct);
//                    Set attribute for Paging
                    request.setAttribute("list", ProductDAO.getProduct(index, listAllProduct));
                    request.setAttribute("index", indexString);
                    request.setAttribute("size", listAllProduct.size());
                    request.setAttribute("page", "SearchByType&type=" + type);
                    url = "allproducts.jsp";
                    break;

                case "Create":
                    if (userCheck != null) {
                        url = "create.jsp";
                    }
                    break;
                case "SubmitCreate":
                    String carInputId = request.getParameter("id");
                    String carInputName = request.getParameter("name");
                    String carInputPrice = request.getParameter("price");
                    String carInputBrand = request.getParameter("brand");
                    String carInputColor = request.getParameter("color");
                    String carInputType = request.getParameter("type");
                    String carInputDescription = request.getParameter("description");
                    String carInputImage = request.getParameter("imagepath");
                    //                    Check validate
                    check = false;
                    if (carInputId.equals("")) {
                        request.setAttribute("errorCarId", "Input CarID");
                        check = true;
                    }
                    if (carInputName.equals("")) {
                        request.setAttribute("errorCarName", "Input Car Name");
                        check = true;
                    }
                    if (carInputPrice.equals("")) {
                        request.setAttribute("errorCarPrice", "Input Car Price");
                        check = true;
                    }
                    if (carInputColor.equals("")) {
                        request.setAttribute("errorCarColor", "Input Car Color");
                        check = true;
                    }
                    if (carInputType.equals("")) {
                        request.setAttribute("errorCarType", "Input Car Type");
                        check = true;
                    }
                    if (carInputDescription.equals("")) {
                        request.setAttribute("errorCarDescription", "Input Description");
                        check = true;
                    }
                    if (carInputImage.equals("")) {
                        request.setAttribute("errorImagePath", "Input Image Path");
                        check = true;
                    }
                    if (check) {
                        request.setAttribute("id", carInputId);
                        request.setAttribute("name", carInputName);
                        request.setAttribute("price", carInputPrice);
                        request.setAttribute("brand", carInputBrand);
                        request.setAttribute("color", carInputColor);
                        request.setAttribute("type", carInputType);
                        request.setAttribute("description", carInputDescription);
                        request.setAttribute("imagepath", carInputImage);
                        url = "create.jsp";
                        break;
                    }
                    ProductDAO.create(carInputId, carInputName, carInputPrice, carInputBrand, carInputColor, carInputDescription, carInputType, carInputImage);
                    url = "index.jsp";
                    break;
                case "Update":
                    if (userCheck != null) {
                        ProductDTO carUpdate = ProductDAO.getProductById(request.getParameter("id"));
                        request.setAttribute("car", carUpdate);
                        url = "update.jsp";
                    }
                    break;
                case "SubmitUpdate":
                    carInputId = request.getParameter("id");
                    carInputName = request.getParameter("name");
                    carInputPrice = request.getParameter("price");
                    carInputBrand = request.getParameter("brand");
                    carInputColor = request.getParameter("color");
                    carInputType = request.getParameter("type");
                    carInputDescription = request.getParameter("description");
                    carInputImage = request.getParameter("imagepath");

                    ProductDAO.update(carInputId, carInputName, carInputPrice, carInputBrand, carInputColor, carInputDescription, carInputType, carInputImage);
                    url = "index.jsp";
                    break;
                case "Delete":
                    if (userCheck != null) {
                        String carID = request.getParameter("id");
                        ProductDAO.deleteCar(carID);
                        url = "index.jsp";
                    }
                    break;
            }

        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
